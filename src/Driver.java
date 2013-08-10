import java.util.Scanner;
/*********************************************************************
 * HW 7:  Assemble packets from multiple messages
 *
 * Copyright (C) 2012 by Duncan A. Buell.  All rights reserved.
 * @author Duncan A. Buell
 * @version 1.00 2012-03-20
**/
public class Driver
{

  public static void main(String[] args)
  {
    int messageDone = Integer.MIN_VALUE;
    Scanner inFile = null;
    PacketAssembler assembler = null;

    inFile = FileUtils.ScannerOpen("zin");
    FileUtils.SetLogFile("zlog");

/*********************************************************************
 * For this program, we are writing code that will essentially
 * run forever.  We read packets, and if the packet just read
 * happens to finish a particular message, then we print that
 * message.
**/
    assembler = new PacketAssembler();
    while(inFile.hasNext())
    {
// if the returned value is positive, it's a message id number
      messageDone = assembler.readPackets(inFile);
      if(messageDone > 0)
      {
        assembler.writePackets(messageDone);
      }
    } // while(inFile.hasNext())

/*********************************************************************
 * close up and go home
**/
    FileUtils.CloseFile(inFile);
    FileUtils.logFile.printf("No more input, terminate%n");
    FileUtils.CloseLogFile();
  }
} // public class Main
