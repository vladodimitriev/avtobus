package mk.mladen.avtobusi.util;

public class BusLine {

	private String departurePlace;
	private String destinationPlace;
	private String departureTime;
	private String arrivalTime;
	private String carrier;
	private String daysOfWork;
	private int lineNumber;
	private int distance;
	
	public BusLine() {
	}
	
	public BusLine(String departurePlace, String destinationPlace, String departureTime,
			String arrivalTime, String carrier, String daysOfWork, int lineNumber) {
		this.departurePlace = departurePlace;
		this.destinationPlace = destinationPlace;
		this.departureTime= departureTime;
		this.arrivalTime=arrivalTime;
		this.carrier=carrier;
		this.daysOfWork=daysOfWork;
		this.lineNumber=lineNumber;
	}
	
	public String getDeparturePlace() {
		return departurePlace;
	}
	public void setDeparturePlace(String departurePlace) {
		this.departurePlace = departurePlace;
	}
	public String getDestinationPlace() {
		return destinationPlace;
	}
	public void setDestinationPlace(String destinationPlace) {
		this.destinationPlace = destinationPlace;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getDaysOfWork() {
		return daysOfWork;
	}
	public void setDaysOfWork(String daysOfWork) {
		this.daysOfWork = daysOfWork;
	}
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return departurePlace + " " + destinationPlace + " " + departureTime + " " + arrivalTime + " " + carrier + ", distance: " + distance + ", days of work: " + daysOfWork + ", line number: " + lineNumber;
	}
	
}
