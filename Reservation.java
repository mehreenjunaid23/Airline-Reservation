public interface Reservation {
	public abstract void addPax(String paxName, int row, int seatNo);
	
	public abstract boolean removePax(String paxName);
	
	public abstract boolean isFull();
	
	public abstract boolean isVacant();}
