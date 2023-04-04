import java.io.Serializable;
public class WaitingList implements Reservation, Serializable {
private static final long serialVersionUID = 1L;

//Attributes
private String[] paxWaitList;
private static int waitListNo;
private static final transient int WAIT_LIST_LIMIT = 20; //transient because we do not want to store it in the file

public WaitingList() {
paxWaitList = new String[WAIT_LIST_LIMIT];
waitListNo = -1;}

@Override
public void addPax(String paxName, int row, int seatNo) {
paxWaitList[++waitListNo] = paxName;}

@Override
public boolean removePax(String paxName) {
for(int i = 0 ; i < WAIT_LIST_LIMIT ; i++){
if(paxWaitList[i] != null){
if(paxWaitList[i].equalsIgnoreCase(paxName)){
reorderList(i);
waitListNo -= 1;
return true;}}}
return false;}

public boolean isFull(){
if(waitListNo == WAIT_LIST_LIMIT)
return true;
else
return false;}

@Override
public boolean isVacant() {
if(waitListNo == -1)
return true;
else
return false;}

private void reorderList(int pos){
for(int i = pos ; i < WAIT_LIST_LIMIT ; i++){
if(paxWaitList[i + 1] != null)
paxWaitList[i] = paxWaitList[i + 1];
else{
paxWaitList[i] = null;
break;}}}

public String getFirstWaitListedPax(){
String paxName = null;
if(waitListNo >= 0){
paxName = paxWaitList[0];
waitListNo -= 1;
reorderList(0);}
return paxName;}

public void resetWaitListCount() {
for(int i = 0 ; i < WAIT_LIST_LIMIT ; i++){
if(paxWaitList[i + 1] != null){
waitListNo += 1;
} else{
break;}}}

public static int getWaitListNo() {
return waitListNo;}}

