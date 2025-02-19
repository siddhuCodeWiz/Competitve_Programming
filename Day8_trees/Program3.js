/*
Employee Management WebSocket Application

Objective:
-----------
Your task is to develop a WebSocket-based Employee Management System using Node.js. 
This system will allow clients to:
    1. Insert Employee Records (INSERT <name> <salary>)
    2. Retrieve Employee List (RETRIEVE)
    3. Handle Invalid Commands (e.g., INVALID should return "Invalid command")
Your goal is to implement and test a WebSocket-based server and client, 
ensuring that all operations work correctly.

Requirements:
-------------
1. Implement WebSocket Server
	The server should:
		-> Accept multiple client connections.
		-> Process client messages and handle commands:
			1. INSERT <name> <salary> → Adds an employee to an in-memory array.
			2. RETRIEVE → Returns all stored employees.
			3. Any other command should return "Invalid command."
		-> Maintain an in-memory array of employees (no database required).
		-> Log each received command on the console.
		
		
Expected Behavior
-----------------

============================================================================================
Client Command			Server Response
============================================================================================
INSERT Alice 50000		"Employee inserted successfully."
INSERT Bob 60000		"Employee inserted successfully."
RETRIEVE				"ID: 1, Name: Alice, Salary: 50000"
                        "ID: 2, Name: Bob, Salary: 60000"
INVALID					"Invalid command."
============================================================================================

Note: 
-> The server should run on port 8080.
-> The system should allow multiple clients to connect.


EXAMPLE URL value=>   ws://10.11.xx.xx:8080
-->
<config>
    <url value=""></url>
</config>
*/

// server.js
const WebSocket = require('ws');

// Create WebSocket server on port 8080
const wss = new WebSocket.Server({ port: 8080 });

// In-memory storage for employees
let employees = [];
let currentId = 1;

// Handle incoming connections
wss.on('connection', (ws) => {
    console.log('New client connected');

    // Handle incoming messages
    ws.on('message', (message) => {
        const command = message.toString();
        console.log(`Received command: ${command}`);

        try {
            const response = processCommand(command);
            ws.send(response);
        } catch (error) {
            ws.send("Error processing command: " + error.message);
        }
    });

    // Handle client disconnection
    ws.on('close', () => {
        console.log('Client disconnected');
    });
});

// Process incoming commands
function processCommand(command) {
    const parts = command.split(' ');
    const action = parts[0].toUpperCase();

    switch (action) {
        case 'INSERT':
            if (parts.length !== 3) {
                return "Invalid command format. Use: INSERT <name> <salary>";
            }
            const name = parts[1];
            const salary = parseInt(parts[2]);
            
            if (isNaN(salary)) {
                return "Invalid salary amount";
            }

            employees.push({
                id: currentId++,
                name: name,
                salary: salary
            });
            return "Employee inserted successfully.";

        case 'RETRIEVE':
            if (employees.length === 0) {
                return "No employees found.";
            }
            return employees.map(emp => 
                `ID: ${emp.id}, Name: ${emp.name}, Salary: ${emp.salary}`
            ).join('\n');

        default:
            return "Invalid command.";
    }
}

console.log('WebSocket server started on port 8080');

// client.js
// const WebSocket = require('ws');

// class EmployeeClient {
//     constructor(url) {
//         this.ws = new WebSocket(url);
        
//         this.ws.on('open', () => {
//             console.log('Connected to server');
//         });

//         this.ws.on('message', (data) => {
//             console.log('Received:', data.toString());
//         });

//         this.ws.on('close', () => {
//             console.log('Disconnected from server');
//         });

//         this.ws.on('error', (error) => {
//             console.error('WebSocket error:', error);
//         });
//     }

//     sendCommand(command) {
//         if (this.ws.readyState === WebSocket.OPEN) {
//             this.ws.send(command);
//         } else {
//             console.error('WebSocket is not connected');
//         }
//     }

//     close() {
//         this.ws.close();
//     }
// }

// // Example usage of client
// async function runExample() {
//     const client = new EmployeeClient('ws://localhost:8080');

//     // Wait for connection to establish
//     await new Promise(resolve => setTimeout(resolve, 1000));

//     // Test commands
//     const commands = [
//         'INSERT Alice 50000',
//         'INSERT Bob 60000',
//         'RETRIEVE',
//         'INVALID'
//     ];

//     for (const command of commands) {
//         client.sendCommand(command);
//         await new Promise(resolve => setTimeout(resolve, 500));
//     }

//     // Close connection after tests
//     setTimeout(() => client.close(), 2000);
// }

// Uncomment to run the example
// runExample();