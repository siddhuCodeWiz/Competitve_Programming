/*
<!--
Employee Management WebSocket Application with MongoDB

Objective:
----------
Your task is to develop a WebSocket-based Employee Management System using Node.js and MongoDB. 
The system should allow multiple clients to interact with a database to perform the following operations:
	1. Insert Employee Records (INSERT <name> <salary> <role> <department> <experience>)
	2. Retrieve Employee List (RETRIEVE)
	3. Retrieve Employee List who belongs to a department (RETRIEVE_BY_DEPT <department>)
	
The WebSocket server should be capable of handling multiple concurrent clients and persist employee data in MongoDB.


// MongoDB Employee Schema
const employeeSchema = new mongoose.Schema({
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number
});

Requirements:
-------------
Implement WebSocket Server
	The server should:
		-> Accept multiple client connections. (give a response as "Connected" )
		-> Process incoming commands from clients as discussed above.
		-> Log each received command on the console.
		-> Ensure proper error handling (e.g., invalid salary, missing name, etc.).
		
Expected Behavior
-----------------

============================================================================================
Client Command			                Server Response
============================================================================================
INSERT Alice 50000 Developer IT 5	    "Employee inserted successfully."
INSERT Bob 60000 Manager IT 5	        "Employee inserted successfully."

RETRIEVE				                "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
                                        "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"

RETRIEVE_BY_DEPT IT                     "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
                                        "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"


INVALID					                "Invalid command."
============================================================================================

Note: 
-> Your implementation must use MongoDB for data persistence.
-> The server should run on port 8080.
-> The system should allow multiple clients to connect.


EXAMPLE URL value=>   ws://10.11.xx.xx:8080

-->
<config>
    <url value=""></url>
</config>
*/


const mongoose = require('mongoose');
const WebSocket = require('ws');

async function connectDB() {
    try {
        await mongoose.connect("mongodb://127.0.0.1:27017/FinishingSchool");
        console.log("DB connected successfully");
    } catch (error) {
        console.log("DB connection error:", error);
    }
}
connectDB();

const wss = new WebSocket.Server({ port: 8080 });

wss.on("connection", (ws) => {
    ws.on("message", async (message) => {
        const command = message.toString();
        console.log("Received:", command);

        const res = await processCommand(command); 
        console.log("Response:", res);

        ws.send(res);
    });
});

const employeeSchema = new mongoose.Schema({
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number,
});

const Employees = mongoose.model("Employees", employeeSchema);

async function processCommand(command) {
    const parts = command.split(" ");
    const action = parts[0].toUpperCase();

    switch (action) {
        case "INSERT":
            if (parts.length !== 6) {
                return "Invalid command format. Use: INSERT <name> <salary> <role> <department> <experience>";
            }

            const name = parts[1];
            const salary = parseInt(parts[2]);
            const role = parts[3];
            const department = parts[4];
            const experience = parseInt(parts[5]);

            if (isNaN(salary) || isNaN(experience)) {
                return "Invalid salary or experience amount";
            }

            try {
                const emp = new Employees({ name, salary, role, department, experience });
                await emp.save();
                return "Employee inserted successfully.";
            } catch (error) {
                return "Error inserting employee: " + error.message;
            }

        case "RETRIEVE":
            try {
                const data = await Employees.find({});
                return JSON.stringify(data);
            } catch (error) {
                return "Error retrieving employees: " + error.message;
            }

        case "RETRIEVE_BY_DEPT":
            if (parts.length !== 2) {
                return "Invalid format. Use: RETRIEVE_BY_DEPT <department>";
            }

            try {
                const data = await Employees.find({ department: parts[1] });
                return JSON.stringify(data);
            } catch (error) {
                return "Error retrieving data: " + error.message;
            }

        default:
            return "Invalid command.";
    }
}

console.log("WebSocket server started on port 8080");
