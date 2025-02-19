package Day7_treesAndSubnetting;

/*In computer networking, subnetting is the process of dividing a larger IP network
into multiple smaller subnetworks (subnets). Given a base network IP address, 
a CIDR (Classless Inter-Domain Routing) prefix, and the number of subnets required, 
write a Java program that calculates the starting addresses of all the subnets.

Your program should take as input:
	- A base network address (e.g., 192.168.1.0).
	- A CIDR prefix (e.g., /26 means a subnet mask of 255.255.255.192).
	- The number of subnets to generate.

The program should then compute and return the starting addresses of each subnet.

Input Format:
-------------
A string NIP: The base network IP address (e.g., "192.168.1.0").
An integer CIDR: The subnet mask prefix (e.g., 26 for /26).
An integer numSubnets: The number of subnets to be generated.

Output Format:
--------------
A list of subnet addresses, each representing the starting address of a subnet.


Sample Input:
-------------
192.168.1.0
26
4

Sample Output:
--------------
[192.168.1.0/28, 192.168.1.16/28, 192.168.1.32/28, 192.168.1.48/28]

Explanation:
------------
A /26 subnet has 64 IP addresses. The starting addresses of 
the first four subnets are:
192.168.1.0/28, 
192.168.1.16/28, 
192.168.1.32/28, 
192.168.1.48/28 */

import java.util.*;

public class Program3 {

    public static List<String> generateSubnets(String baseIp, int cidr, int numSubnets) {
        List<String> subnetList = new ArrayList<>();
        String[] octets = baseIp.split("\\.");
        int baseIpInt = (Integer.parseInt(octets[0]) << 24) |
                        (Integer.parseInt(octets[1]) << 16) |
                        (Integer.parseInt(octets[2]) << 8) |
                        Integer.parseInt(octets[3]);

        int newCidr = 28;
        int subnetSize = (int) Math.pow(2, 32 - newCidr);

        for (int i = 0; i < numSubnets; i++) {
            int subnetAddress = baseIpInt + (i * subnetSize);
            String subnetIp = ((subnetAddress >> 24) & 255) + "." +
                              ((subnetAddress >> 16) & 255) + "." +
                              ((subnetAddress >> 8) & 255) + "." +
                              (subnetAddress & 255);
            subnetList.add(subnetIp + "/" + newCidr);
        }

        return subnetList;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String baseIp = scanner.nextLine();
        int cidr = scanner.nextInt();
        int numSubnets = scanner.nextInt();
        
        scanner.close();

        List<String> subnets = generateSubnets(baseIp, cidr, numSubnets);

        System.out.println(subnets);
    }
}
