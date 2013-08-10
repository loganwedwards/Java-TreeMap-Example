import java.util.Scanner;

/*********************************************************************
 * This class represents a packet of data. A packet is represented by
 * multiple pieces of data: the message id, the packet id (where it
 * fits in the packet message), the packet count (how many packets are
 * contained in a message) and the data itself, which is just a string
 * in this case. This class simply sets up an instance of itself and
 * reads in a line of data and assigns the appropriate instance vars
 * to the respective data. A packet is considered <code>null</code> if
 * the payload is nothing. Do not confuse this with a space, e.g., " ",
 * which just represents a space and is valid data.
 * 
 * Solves for CSCE 146, HW7
 * 
 * @author  Logan Edwards
 * @version	Rev0, 2012-04-08
 *
 */
public class Packet implements IPacket {

	/*********************************************************************
	 * Instance variables for the class.
	 **/
	private final String classLabel = "Packet: ";

	static final int DUMMYINT = Integer.MIN_VALUE;
	static final String DUMMYSTRING = "dummy";
	private int messageID;
	private int packetID;
	private int packetCount;
	private String payload;

	/*********************************************************************
	 * Constructor.
	 **/
	public Packet() {
		
	}

	/*********************************************************************
	 * Accessors.
	 **/
	/*********************************************************************
	 * Method to get the 'messageID'.
	 *
	 * @return the value of 'messageID'.
	 **/
	public int getMessageID() {
		return this.messageID;
	}

	/*********************************************************************
	 * Method to get the 'packetID'.
	 *
	 * @return the value of 'packetID'.
	 **/
	public int getPacketID() {
		return this.packetID;
	}

	/*********************************************************************
	 * Method to get the 'packetCount'.
	 *
	 * @return the value of 'packetCount'.
	 **/
	public int getPacketCount() {
		return this.packetCount;
	}

	/*********************************************************************
	 * Method to get the 'payload'.
	 *
	 * @return the value of 'payload'.
	 **/
	public String getPayload() {
		return this.payload;
	}

	/*********************************************************************
	 * Mutators.
	 **/
	/*********************************************************************
	 * Method to set the 'messageID'.
	 *
	 * @return the value of 'messageID'.
	 **/
	public void setMessageID(int what) {
		this.messageID = what;
	}

	/*********************************************************************
	 * Method to set the 'packetID'.
	 *
	 * @return the value of 'packetID'.
	 **/
	public void setPacketID(int what) {
		this.packetID = what;
	}

	/*********************************************************************
	 * Method to set the 'packetCount'.
	 *
	 * @return the value of 'packetCount'.
	 **/
	public void setPacketCount(int what) {
		this.packetCount = what;
	}

	/*********************************************************************
	 * Method to set the 'payload'.
	 *
	 * @return the value of 'payload'.
	 **/
	public void setPayload(String what) {
		this.payload = what;
	}

	/*********************************************************************
	 * General methods.
	 **/

	/*********************************************************************
	 * Method to read the packets from an input 'Scanner' file.
	 * Note that this is more or less hard coded. Also that we don't
	 * bulletproof the input; among other things we assume that partial
	 * records don't appear in the input data.
	 **/
	public Packet readPacket(Scanner inFile) {
		
		this.messageID = inFile.nextInt();
		this.packetID = inFile.nextInt();
		this.packetCount = inFile.nextInt();
		this.payload = inFile.nextLine();
		if (this.payload.equals("")) {
			return null;
		}
		else {
			return this;
		}
	}

	/*********************************************************************
	 * Method to convert a packet to a 'String'.
	 *
	 * @return the 'String' value of the packet.
	 **/
	public String toString() {
		return String.format("%5d %5d %5d %s", this.getMessageID(), this.getPacketID(), this.getPacketCount(), this.getPayload());
	}

} // public interface IPacket
