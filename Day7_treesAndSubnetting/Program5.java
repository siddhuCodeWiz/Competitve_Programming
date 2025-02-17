package Day7_treesAndSubnetting;


/*Write a Java program that determines the class of a given IPv4 address. 
The classification follows the standard IP address classes:
	- Class A: IP addresses where the first octet is in the range 1-127.
	- Class B: IP addresses where the first octet is in the range 128-191.
	- Class C: IP addresses where the first octet is in the range 192-223.
	- Multicast (Class D): IP addresses where the first octet is in the range 224-239.

Input Format:
-------------
A string IP: The first network IP address (e.g.,0-239).

Output Format:
--------------
A boolean value, if the two subnets overlap or not.


Sample Input:
-------------
192.168.1.0

Sample Output:
--------------
Class C

Explanation:
------------
The first octet 192 is within the Multicast range. */

import java.util.Scanner;

public class Program5 {
    public static String classifyIPAddress(String ipAddress){
        String[] octets = ipAddress.split("\\.");
        
        int firstOctet = Integer.parseInt(octets[0]);

        if (firstOctet >= 1 && firstOctet <= 127) {
            return "Class A";
        } else if (firstOctet >= 128 && firstOctet <= 191) {
            return "Class B";
        } else if (firstOctet >= 192 && firstOctet <= 223) {
            return "Class C";
        } else if (firstOctet >= 224 && firstOctet <= 239) {
            return "Multicast (Class D)";
        } else {
            return "Invalid IP or Out of Range";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ipAddress = scanner.nextLine();
        scanner.close();
        System.out.println(classifyIPAddress(ipAddress));
    }
}
