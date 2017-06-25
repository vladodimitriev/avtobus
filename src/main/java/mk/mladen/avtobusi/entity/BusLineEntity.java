package mk.mladen.avtobusi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BUSLINE")
public class BusLineEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", nullable=true)
	private String name;
	
	@Column(name = "departuretime", nullable=true)
	private String departureTime;
	
	@Column(name = "arrivaltime", nullable=true)
	private String arrivalTime;
	
	@Column(name = "distance", nullable=true)
	private Integer distance;
	
	@Column(name = "jurneytime", nullable=true)
	private String jurneyTime;
	
	@Column(name = "operationdays", nullable=true)
	private String operationDays;
	
	@Column(name = "operationperiod", nullable=true)
	private String operationPeriod;
	
	@Column(name = "price", nullable=true)
	private String price;
	
	@Column(name = "priceReturn", nullable=true)
	private String priceReturn;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="departureplaceid", nullable=true)
	private PlaceEntity departure;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="destinationplaceid", nullable=true)
	private PlaceEntity destination;
	
	@ManyToOne(fetch=FetchType.LAZY)
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

}
