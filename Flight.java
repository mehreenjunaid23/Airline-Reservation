import java.io.Serializable;
public class Flight implements Serializable{
private static final long serialVersionUID = 1L;
private SeatingChart seatingChart;
private WaitingList waitingList;

public Flight() {
  seatingChart = new SeatingChart();
  waitingList = new WaitingList();}

public boolean isFlightFull(){
  return seatingChart.isFull();}

public boolean isWaitListFull(){
  return waitingList.isFull();}

public void displaySeatingChart(){
  seatingChart.displaySeatingChart(); }

public void addPax(String paxName, String paxEmail, String paxNum, int row, int seatNo) {
  if((row == -1) && (seatNo == -1))
  waitingList.addPax(paxName, row, seatNo);
  else
  seatingChart.addPax(paxName, row, seatNo);}

public boolean removePax(String paxName) {
  boolean found = false;
  if(!seatingChart.isVacant()){
    found = seatingChart.removePax(paxName);
    if(!found){
      waitingList.removePax(paxName);
      System.out.println("Passenger removed from waiting list");}
    else{
      System.out.println("Passenger removed from seating chart");
      paxName = waitingList.getFirstWaitListedPax();
      if(paxName != null)
      seatingChart.allotVacantSeat(paxName);}}
  else{
    System.out.println("Seating chart empty. Cannot delete.");
    found = true;}
  return found;}

public void setStats(){
seatingChart.resetSeatCount();
waitingList.resetWaitListCount();}

public void getStats(){
System.out.println("Total seats taken : " + SeatingChart.getSeatCount());
System.out.println("Total number of passengers in waiting list : " + (WaitingList.getWaitListNo() + 1));
}}

