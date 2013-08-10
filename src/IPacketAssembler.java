import java.io.PrintWriter;
// import java.util.TreeMap;
import java.util.Scanner;

/*********************************************************************
 *
**/
public interface IPacketAssembler
{
//  static final String classLabel = "PacketAssembler: ";
//  TreeMap<Integer,Packet> theMessage;

/*********************************************************************
 * Constructor
**/
//  public PacketAssembler()

/*********************************************************************
 * read method
**/
  public int readPackets(Scanner inFile);

/*********************************************************************
 * write method
**/
//  public void writePackets(int messageID, PrintWriter outFile);
  public void writePackets(int messageID);

} // public interface PacketAssembler
