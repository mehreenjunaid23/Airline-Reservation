import java.io.Serializable;

public class SeatingChart implements Reservation, Serializable {

	private static final long serialVersionUID = 1L;
	private String[][] paxList;
	private static int seatCount;
	private static final transient int ROW = 10; 
	private static final transient int SEAT = 4; 
	
	public SeatingChart() {
		paxList = new String[ROW][SEAT];
		seatCount = 0;}

	@Override
	public void addPax(String paxName, int row, int seatNo) {
		paxList[row][seatNo] = paxName;
		seatCount += 1;}

	@Override
	public boolean removePax(String paxName) {
		for(int i = 0 ; i < ROW ; i++){
		for(int j = 0 ; j < SEAT ; j++){
		if(paxList[i][j] != null){
		//Compare passenger names
		if(paxList[i][j].equalsIgnoreCase(paxName)){
		paxList[i][j] = null;
		seatCount -= 1;
		return true;}}}}
		return false;}

	@Override
	public boolean isFull(){
		if(seatCount == SEAT)
		return true;
		else
		return false;}

	@Override
	public boolean isVacant() {
		if(seatCount == 0)
		return true;
		else
		return false;}

	public void displaySeatingChart(){
		System.out.println("------------------------------------------");
		System.out.println("A B C D");
		for(int i = 0 ; i < ROW ; i++){
		System.out.println();
		for(int j = 0 ; j < SEAT ; j++){
		if(paxList[i][j] == null)
		System.out.print("_ ");
		else
		System.out.print("X ");}
		System.out.print((i + 1));}
		System.out.println("\nX - Occupied \t_ - Vacant\n");
		System.out.println("------------------------------------------");}

	public void allotVacantSeat(String paxName){
		for(int i = 0 ; i < ROW ; i++){
		boolean alloted = false;
		for(int j = 0 ; j < SEAT ; j++){
		if(paxList[i][j] == null){
		paxList[i][j] = paxName;
		seatCount += 1;
		alloted = true;
		break;}}
		if(alloted)
		break;}}

	public void resetSeatCount() {
		for(int i = 0 ; i < ROW ; i++){
		for(int j = 0 ; j < SEAT ; j++){
		if(paxList[i][j] != null)
		seatCount += 1;}}}

	public static int getSeatCount() {
		return seatCount;}}


