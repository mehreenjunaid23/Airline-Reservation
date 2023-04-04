import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class Airline {
private static String fileName = "Airline.dat";
private static Scanner s = new Scanner(System.in);
public static void clearBuf(){
s.nextLine();
}

public static int getSeatNo(char chr){
	int seatNo = -1;
	if((chr == 'A') || (chr == 'a'))
	seatNo = 0;
	else if((chr == 'B') || (chr == 'b'))
	seatNo = 1;
	else if((chr == 'C') || (chr == 'c'))
	seatNo = 2;
	else if((chr == 'D') || (chr == 'd'))
	seatNo = 3;
	return seatNo;
	}


public static void writeToFile(Flight flight){
	ObjectOutputStream oos = null;
	try {
	oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
	oos.writeObject(flight);
	} catch (FileNotFoundException fnfe) {
	System.out.println("File not found");
	} catch (IOException ioe) {
	System.out.println(ioe.toString());
	} catch (Exception e) {
	System.out.println(e.toString());
	} finally {
	try {
	oos.close();
	} catch (IOException ioe) {
	System.out.println(ioe.toString());}}}


public static Flight readFromFile(){
	Flight flight = null;
	ObjectInputStream ois = null;
	File file = new File(fileName);
	try {
	if(!file.exists()){
	boolean created = file.createNewFile();
	flight = new Flight();
	} else{
	if(file.length() != 0){
	ois = new ObjectInputStream(new FileInputStream(file));
	flight = (Flight)ois.readObject();
	flight.setStats();
	} else{
	flight = new Flight();}}} 
	catch (FileNotFoundException fnfe) {
	System.out.println("File not found");
	} catch (IOException ioe) {
	System.out.println(ioe.toString());
	} catch (Exception e) {
	System.out.println(e.toString());
	} finally {
	try {
	if(ois != null)
	ois.close();
	} catch (IOException ioe) {
	System.out.println(ioe.toString());}}
	return flight;}

public static void main(String[] args) {
char ans = 'N';
Flight flight = readFromFile();
do{
System.out.println("AIRLINE RESERVATION SYSTEM");
System.out.println("The services we provide you with inlcude:");
System.out.println("1. Add a passenger to the system");
System.out.println("2. Remove a passenger from the system");
System.out.println("3. Exit the system");
System.out.println("Enter your choice : ");
int choice = s.nextInt();
clearBuf();

//To get passengers name from the user
String paxName;
String paxEmail = null;
String paxNum = null;
switch(choice){
case 1://Add PAX
	System.out.println("Enter passenger's name , email and phone number : ");
	paxName = s.next();
	paxEmail = s.next();
	paxNum = s.next();
	clearBuf();
	if(!flight.isFlightFull()){ 
	flight.displaySeatingChart();
	System.out.println("Enter the seat number [Row number followed by seat number] : ");
	String seat = s.next();
	clearBuf();
	flight.addPax(paxName, paxEmail,  paxNum , (Integer.parseInt(seat.substring(0, (seat.length() - 1))) - 1),
	getSeatNo(seat.charAt(seat.length() - 1)));
	}else if(!flight.isWaitListFull()){
	System.out.println("Flight is full. Passenger added to the wait list");
	flight.addPax(paxName,paxEmail,  paxNum, -1, -1);}
	else{
	System.out.println("Reservation cannot be completed. Please try again later.");}
	break;

case 2:
	System.out.println("Enter passenger's name : ");
	paxName = s.next();
	clearBuf();
	flight.displaySeatingChart();
	if(!flight.removePax(paxName))
	System.out.println("No passenger with the name '" + paxName + "' found");
	break;

case 3:
	writeToFile(flight);
	System.out.println("FLIGHT STATUS");
	flight.getStats();
	System.exit(0);
	break;

default:
System.out.println("Invalid Input");
ans = 'n';}

System.out.println("Do you want to continue[Y/N] : ");
ans = s.next().charAt(0);
clearBuf();
}while((ans == 'y') || (ans == 'Y'));
writeToFile(flight);
System.out.println("FLIGHT STATUS");
flight.displaySeatingChart();
s.close();
}}
