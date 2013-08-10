import java.util.Scanner;

/*********************************************************************
 * Interface for 'Packet' for handling a packet.
 * @author Duncan Buell
**/
public interface IPacket
{
/*********************************************************************
 * Instance variables for the class.
**/
//  private final String classLabel = "Packet: ";

//  static final int DUMMYINT = Integer.MIN_VALUE;
//  static final String DUMMYSTRING = "dummy";
//  int messageID;
//  int packetID;
//  int packetCount;
//  String payload;

/*********************************************************************
 * Constructor.
**/
//  public Packet();

/*********************************************************************
 * Accessors.
**/
/*********************************************************************
 * Method to get the 'messageID'.
 *
 * @return the value of 'messageID'.
**/
  public int getMessageID();

/*********************************************************************
 * Method to get the 'packetID'.
 *
 * @return the value of 'packetID'.
**/
  public int getPacketID();

/*********************************************************************
 * Method to get the 'packetCount'.
 *
 * @return the value of 'packetCount'.
**/
  public int getPacketCount();

/*********************************************************************
 * Method to get the 'payload'.
 *
 * @return the value of 'payload'.
**/
  public String getPayload();

/*********************************************************************
 * Mutators.
**/
/*********************************************************************
 * Method to set the 'messageID'.
 *
 * @return the value of 'messageID'.
**/
  public void setMessageID(int what);

/*********************************************************************
 * Method to set the 'packetID'.
 *
 * @return the value of 'packetID'.
**/
  public void setPacketID(int what);

/*********************************************************************
 * Method to set the 'packetCount'.
 *
 * @return the value of 'packetCount'.
**/
  public void setPacketCount(int what);

/*********************************************************************
 * Method to set the 'payload'.
 *
 * @return the value of 'payload'.
**/
  public void setPayload(String what);

/*********************************************************************
 * General methods.
**/

/*********************************************************************
 * Method to read the packets from an input 'Scanner' file.
 * Note that this is more or less hard coded. Also that we don't
 * bulletproof the input; among other things we assume that partial
 * records don't appear in the input data.
**/
  public Packet readPacket(Scanner inFile);

/*********************************************************************
 * Method to convert a packet to a 'String'.
 *
 * @return the 'String' value of the packet.
**/
  public String toString();

} // public interface IPacket
