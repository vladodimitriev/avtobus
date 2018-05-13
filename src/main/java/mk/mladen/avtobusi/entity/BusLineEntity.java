package mk.mladen.avtobusi.entity;

import javax.persistence.*;

@Entity
@Table(name="BUSLINE")
public class BusLineEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", nullable=true, length = 100)
	private String name;
	
	@Column(name = "departuretime", nullable=true, length = 20)
	private String departureTime;
	
	@Column(name = "arrivaltime", nullable=true, length = 20)
	private String arrivalTime;
	
	@Column(name = "distance", nullable=true)
	private Integer distance;

	@Column(name = "dist", nullable=true)
	private Double dist;
	
	@Column(name = "jurneytime", nullable=true, length = 20)
	private String jurneyTime;
	
	@Column(name = "operationdays", nullable=true, length = 30)
	private String operationDays;
	
	@Column(name = "operationperiod", nullable=true, length = 50)
	private String operationPeriod;

	@Column(name = "operationmonths", nullable=true, length = 50)
	private String operationMonths;
	
	@Column(name = "price", nullable=true, length = 20)
	private String price;
	
	@Column(name = "pricereturn", nullable=true, length = 20)
	private String priceReturn;

	@Column(name = "comment", nullable = true, length = 1000)
	private String comment;

	@Column(name = "smallplaces", nullable = true, length = 1000)
	private String smallPlaces;
	
	@Column(name = "smallplaceslatin", nullable = true, length = 1000)
	private String smallPlacesLatin;
	
	@Column(name = "redenbroj", nullable = true)
	private Integer redenBroj; 
	
	@Column(name = "linenumber", nullable = true)
	private Integer lineNumber;
	
	@Column(name = "linename", nullable = true, length = 100)
	private String lineName;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="departureplaceid", nullable=true)
	private PlaceEntity departure;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="destinationplaceid", nullable=true)
	private PlaceEntity destination;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinColumn(name="carrierid", nullable=true)
	private CarrierEntity carrier;
	
	public BusLineEntity() {
	}
	
	public BusLineEntity(PlaceEntity departure, PlaceEntity destination, 
			String departureTime, String arrivalTime, String operationDays,
			CarrierEntity carrier) {
		this.departure = departure;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.operationDays = operationDays;
		this.carrier = carrier;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getJurneyTime() {
		return jurneyTime;
	}

	public void setJurneyTime(String jurneyTime) {
		this.jurneyTime = jurneyTime;
	}

	public String getOperationDays() {
		return operationDays;
	}

	public void setOperationDays(String operationDays) {
		this.operationDays = operationDays;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public PlaceEntity getDeparture() {
		return departure;
	}

	public void setDeparture(PlaceEntity departure) {
		this.departure = departure;
	}

	public PlaceEntity getDestination() {
		return destination;
	}

	public void setDestination(PlaceEntity destination) {
		this.destination = destination;
	}

	public CarrierEntity getCarrier() {
		return carrier;
	}

	public void setCarrier(CarrierEntity carrier) {
		this.carrier = carrier;
	}

	public String getPriceReturn() {
		return priceReturn;
	}

	public void setPriceReturn(String priceReturn) {
		this.priceReturn = priceReturn;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getOperationPeriod() {
		return operationPeriod;
	}

	public void setOperationPeriod(String operationPeriod) {
		this.operationPeriod = operationPeriod;
	}

	public Double getDist() {
		return dist;
	}

	public void setDist(Double dist) {
		this.dist = dist;
	}

	public String getOperationMonths() {
		return operationMonths;
	}

	public void setOperationMonths(String operationMonths) {
		this.operationMonths = operationMonths;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSmallPlaces() {
		return smallPlaces;
	}

	public void setSmallPlaces(String smallPlaces) {
		this.smallPlaces = smallPlaces;
	}
	
	public Integer getRedenBroj() {
		return redenBroj;
	}

	public void setRedenBroj(Integer redenBroj) {
		this.redenBroj = redenBroj;
	}
	
	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	
	public String getSmallPlacesLatin() {
		return smallPlacesLatin;
	}

	public void setSmallPlacesLatin(String smallPlacesLatin) {
		this.smallPlacesLatin = smallPlacesLatin;
	}

	@Override
	public String toString() {
		return "BusLineEntity: [" + departure + " " + destination + " " + departureTime + " " + arrivalTime + " " + carrier + ", distance: " + distance + ", days of work: " + operationDays + ", comment: " + comment + "]";
	}

}
