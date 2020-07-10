

/*
 * Server App upon UDP
 * Hussain Al Zerjawi
 * CS3700 - Computer Networks
 * 02/08/2020
 */ 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class server {
	 static class Laptop { 
		    int itemID, inventory;
		    double unitPrice;
		    String itemDesc;
		    
		    
		  
		    public Laptop(int itemID, String itemDesc, 
		                   double unitPrice, int inventory) 
		    { 
		        this.itemID = itemID; 
		        this.itemDesc = itemDesc; 
		        this.unitPrice = unitPrice; 
		        this.inventory = inventory; 
		    } 
		   
		    public String toString() 
		    { 
		        return "0000" + this.itemID + " 		"
		            + this.itemDesc + "	 $"
		            + this.unitPrice + " 	"
		            + this.inventory; 
		    } 
		}
		public static void main(String[] args) throws IOException {

			
			Laptop[] aray = { new Laptop(00001, "New Inspiron 15", 379.99, 157), 
					  new Laptop(00002, "New Inspiron 17", 449.99, 128),
					  new Laptop(00003, "New Inspiron 15R", 549.99, 202),
					  new Laptop(00004, "New Inspiron 15z Ultrabook", 749.99, 315),
					  new Laptop(00005, "XPS 14 Ultrabook", 999.99, 261),
					  new Laptop(00006, "New XPS 12 UltrabookXPS", 1199.99, 178) }; 

	        
		     DatagramSocket udpServerSocket = null;
		        BufferedReader in = null;
				  DatagramPacket udpPacket = null, udpPacket2 = null;
				  String fromClient = null;
				  String toClient = null;
		        boolean morePackets = true;

				  byte[] buf = new byte[1024];
			
				  udpServerSocket = new DatagramSocket(5010);
			        while (morePackets) {
			            try {

			                // receive UDP packet from client
			                udpPacket = new DatagramPacket(buf, buf.length);
			                udpServerSocket.receive(udpPacket);

								 fromClient = new String(
								 		udpPacket.getData(), 0, udpPacket.getLength());
										
								 // get the response
								 int itemID = Integer.parseInt(fromClient);
								 toClient = aray[itemID -1].toString();
								// toClient = String.valueOf(aray[itemID]);
								 
								 // send the response to the client at "address" and "port"
			                InetAddress address = udpPacket.getAddress();
			                int port = udpPacket.getPort();
								 byte[] buf2 = toClient.getBytes();
			                udpPacket2 = new DatagramPacket(buf2, buf2.length, address, port);
			                udpServerSocket.send(udpPacket2);
								 
			            } catch (IOException e) {
			                e.printStackTrace();
								 morePackets = false;
			            }
			        }
					  
			        udpServerSocket.close();
			}	
}
