package mk.mladen.avtobusi.service.impl;

public class City {

	private String name;
	private int order;
	private String time;
	private String carrier;
	private int lineNumber;
	private String daysOfWork; 
	private int distance;
	private double dist;
	private String comment;
	private int redenBroj;
	private String lineName;
	
	public City() {
	}
	
	public City(String name, String time, int order) {
		this.name = name;
		this.time = time;
		this.order = order;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	public String getDaysOfWork() {
		return daysOfWork;
	}

	public void setDaysOfWork(String daysOfWork) {
		this.daysOfWork = daysOfWork;
	}
	
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getRedenBroj() {
		return redenBroj;
	}

	public void setRedenBroj(int redenBroj) {
		this.redenBroj = redenBroj;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof City) {
			City c = (City)obj;
			if(c.getName().equals(this.getName())
					&& c.getTime().equals(this.getTime())
					&& c.getOrder() == this.getOrder()) {
				return true;
			}
		}
		return false;
	}
	
//	@Override
//	public int compareTo(City o) {
//		if(this.order < o.order) {
//			return -1;
//		}
//		
//		if(this.order > o.order) {
//			return 1;
//		}
//		
//		return 0;
//	}
	
	@Override
	public String toString() {
		return String.format("City[%s, %s, %s, %s]", name, lineName, daysOfWork, comment);
	}
}
