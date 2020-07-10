



/*
 * Client App upon UDP
 * Hussain Al Zerjawi
 * CS3700 - Computer Networks
 * 02/08/2020
 */ 
 

import java.io.*;
import java.net.*;
import java.util.*;

public class UDPClient {
    private static int itemID;

	public static void main(String[] args) throws IOException {
		
		System.out.println("Please type in the DNS or IP of the server machine.");
		BufferedReader sysInput = new BufferedReader(new InputStreamReader(System.in));
		String serverMachine = sysInput.readLine();
		System.out.println("The DNS or IP typed in: " + serverMachine);
		

        // creat a UDP socket
    DatagramSocket udpSocket = new DatagramSocket();
    String fromServer;
    String fromUser;
    String repeat = "Yes";
    while (repeat.contentEquals("Yes") || repeat.contentEquals("yes")) {
		System.out.format("+-----------------+---------------------+%n");
		System.out.format("| Item ID   | Item Description		|%n");
		System.out.format("+-----------------+---------------------+%n");
		System.out.format("| 00001     | New Inspiron 15		|%n");
		System.out.format("| 00002     | New Inspiron 17		|%n");
		System.out.format("| 00003     | New Inspiron 15R		|%n");
		System.out.format("| 00004     | New Inspiron 15z Ultrabook|%n");
		System.out.format("| 00005     | XPS 14 Ultrabook		|%n");
		System.out.format("| 00006     | New XPS 12 UltrabookXPS	|%n");
		System.out.format("+-----------------+---------------------+%n");
		
		Scanner sc = new Scanner(System.in);
		do {
		    System.out.println("Please enter a valid Item ID.");
		    while (!sc.hasNextInt()) {
		    	String notInt = sc.next();
		    	System.out.println("Your input " + notInt);
		        System.out.println("This is not an integer. Please enter a valid Item ID.");

		    }
		    itemID = sc.nextInt();
			System.out.println("Your input 0000" + itemID);
		} while ( itemID >= 7 || itemID <= 0);

		fromUser = Integer.toString(itemID);

			 
            // send request
          InetAddress address = InetAddress.getByName(serverMachine);
			 byte[] buf = fromUser.getBytes();
          DatagramPacket udpPacket = new DatagramPacket(buf, buf.length, address, 5010);
          long start = System.currentTimeMillis();
          udpSocket.send(udpPacket);
    
            // get response
		    byte[] buf2 = new byte[1024];
          DatagramPacket udpPacket2 = new DatagramPacket(buf2, buf2.length);
          udpSocket.receive(udpPacket2);
          long end = System.currentTimeMillis();
          long timeElapsed = end - start;
  	        // display response
          fromServer = new String(udpPacket2.getData(), 0, udpPacket2.getLength());
          
  		System.out.format("+-----------------+------------------------------------------------------------+%n");
  		System.out.format("| Item ID   | Item Description	| Unit Price	| Inventory	| RTT of Query		|%n");
  		System.out.format("+-----------------+------------------------------------------------------------+%n");
          System.out.println(fromServer + " 		" + timeElapsed + "ms");
          
          System.out.println("Would you like to continue?");
          repeat = sysInput.readLine();
  		System.out.println("Your response: " + repeat);
	 	  }
    
		  
        udpSocket.close();
    }

    }
