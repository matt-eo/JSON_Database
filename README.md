# JSON_Database
Client-Server application that allows the clients to store their data on the server in JSON format.

ABOUT

JSON database is a single file database that stores information in the form of JSON. It is a remote database, 
so it's usually accessed through the Internet.

The user can use the commands "set", "get", or, "delete" commands.

After "set", the user should specify a number (1-100) and the text to be saved in the cell. If the index is wrong, 
the program will output ERROR, otherwise, output OK. If the specified cell already contains information, it will be overwritten.

After "get", the user should specify the number of the cell from which they want to get information. If the cell is empty or the index is wrong, the program will output ERROR; 
otherwise, the program outputs the content of the cell.

After "delete", the user should specify the number of the cell. If the index is wrong, the program will output ERROR; otherwise, output OK. 

To exit the program, the user should enter "exit".

The program runs from the main method of the server/Main class.

The arguments will be passed to the client in the following format:

-t set -i 148 -m Here is some text to store on the server

-t is the type of the request, and -i is the index of the cell. -m is the value to save in the database: you only need it in case of a set request.

The server and the client are different programs that run separately.
