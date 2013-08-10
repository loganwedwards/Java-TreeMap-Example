import java.io.PrintWriter;
import java.util.HashSet;
import java.util.TreeMap;

import java.util.Scanner;

/*********************************************************************
 * The <code>PacketAssembler</code> class is the application to manage
 * the assembling and writing of packet information to produce the
 * respective message that appears. The packets are added to a 
 * <code>TreeMap</code> with the <code>packetID</code> acting as the
 * key. Then another <code>TreeMap</code> is used to organize the
 * individual <code>Packet</code> by <code>messageId</code>, which is
 * the key for the parent data structure. When the <code>packetCount</code> 
 * has been reached (same size as the <code>TreeMap</code> that contains
 * it), we proceed to assemble and output the message. For
 * this assignment, there is added bullet-proofing. If a packet arrives
 * and does not contain a payload, it is not added to our <code>TreeMap
 * </code> structure. Remember that an empty space (" ") is considered data.
 * 
 * Solves: CSCE 146, HW7
 * 
 * @author  Logan Edwards
 * @version	Rev0, 2012-04-08
 *
 */
public class PacketAssembler implements IPacketAssembler {

	/*********************************************************************
	 * Instance variables for the class.
	 */
	private final String TAG = "Packet Assembler: ";
	PrintWriter outFile  = null;
	private TreeMap<Integer, TreeMap<Integer, Packet>> theInput; // the messageId as the key
	private TreeMap<Integer, Packet> theMessage; // store the packetID as the key

	/*********************************************************************
	 * Constructor
	 */
	public PacketAssembler() {
		theInput = new TreeMap<Integer, TreeMap<Integer, Packet>>();
		theMessage = new TreeMap<Integer, Packet>();
		outFile = FileUtils.PrintWriterOpen("zlog");
	}

	/*********************************************************************
	 * Accessors and mutators.
	 * None needed; the only methods are general processing methods.
	 **/

	/*********************************************************************
	 * General methods.
	 **/

	/*********************************************************************
	 * Method to store a <code>Packet</code>. The data structure is a
	 * <code>TreeMap</code> of <code>TreeMaps</code>, so we add the
	 * <code>Packet</code> to the lower level structure, which is 
	 * dynamically added and sorted into the higher level structure. If a
	 * new <code>messageId</code> is encountered, we create a new <code>
	 * TreeMap</code> to start a new message and then move on.
	 *
	 * @param a <code>Packet</code> instance
	 **/
	private void storePacket (Packet temp) {

		// What to do if the messageId has not been seen
		if (!theInput.containsKey(temp.getMessageID())) {
			theMessage = new TreeMap<Integer, Packet>();
			theMessage.put(temp.getPacketID(), temp);
			theInput.put(temp.getMessageID(), theMessage); 
		}

		// Else, add the data
		else {
			theMessage = theInput.get(temp.getMessageID());
			theMessage.put(temp.getPacketID(), temp);
			theInput.put(temp.getMessageID(), theMessage);


		}

		// FOR TESTING
		//System.out.println("---\n" + theInput.entrySet());
	}

	/*********************************************************************
	 * Method to read the packets in. Here a line of data is sent in. An
	 * instance of <code>Packet</code> is created to be added to the
	 * containing <code>TreeMap</code>. This happens in <code>storePacket()
	 * </code>. We then iterate through <code>theInput</code>, the parent
	 * data structure to see if the size of the lower level <code>TreeMap
	 * </code> is equal to the <code>packetCount</code> saved to the
	 * <code>Packet</code>. If the end has been reached, we return the 
	 * respective <code>messageId</code> to begin the output.
	 * 
	 * @param   inFile, a Scanner object
	 * @return	int, the messageId
	 */
	public int readPackets(Scanner inFile) {
		int returnMessageId = 0;

		Packet aPacket = new Packet();
		Packet thePacket = aPacket.readPacket(inFile);
		if (thePacket != null) {
			storePacket(thePacket);

			for (int messageId : theInput.keySet()) {
				// Create the TreeMap to search
				theMessage = theInput.get(messageId);
				// Pick a data value in the TreeMap
				Packet tempPacket = theMessage.firstEntry().getValue();

				// FOR TESTING
				//System.out.println("Message ID: " + messageId + ", Length:" + theMessage.size());

				// Check if the size of the TreeMap is equal to the
				// packetCount in the TreeMap. Here we assume the message
				// has been received and is ready for assembly
				if (theMessage.size() == tempPacket.getPacketCount()) {
					returnMessageId = messageId; // simply assign the value of the TreeMap size
				}

			}

		}
		return returnMessageId;
	}

	/*********************************************************************
	 * Method to write the packets into a message. We accept the
	 * <code>messageID</code> and find the <code>TreeMap</code> for that
	 * key. The <code>TreeMap</code> is iterated through and each packet
	 * (already being sorted by <code>packetID</code>) is printed to the
	 * logfile. After the message has been assembled, it is deleted from
	 * the underlying <code>TreeMap</code> so as to not interact with the
	 * message ever again.
	 * 
	 * @param	messageID, the message ID of interest
	 */
	public void writePackets(int messageID) {
		outFile = FileUtils.logFile;

		theMessage = theInput.get(messageID);

		// FOR TESTING
		System.out.println("The message length of packets: " + theMessage.size() + " for messageID: " + messageID);

		outFile.printf("%s Done with message %d%n", TAG, messageID);
		String theCompleteMessage = "";

		// Iterate through the TreeMap for a message and 
		// write to file.
		for (int packetId : theMessage.keySet()) {
			theCompleteMessage += String.format("%s %s %n", TAG, theMessage.get(packetId).toString());

		}

		outFile.append(String.format("%s", theCompleteMessage));
		theInput.remove(messageID);
		// FOR TESTING
		System.out.println("Removed messageID: " + messageID);
		System.out.println(theInput);

	}

} // public PacketAssembler
