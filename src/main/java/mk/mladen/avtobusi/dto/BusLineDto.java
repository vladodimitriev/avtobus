package mk.mladen.avtobusi.dto;

import java.io.Serializable;

@SuppressWarnings("rawtypes")
public class BusLineDto implements Serializable, Comparable {

	private static final long serialVersionUID = 3314388063021551876L;

	private int id;
	
	private String name;
	
	private String departurePlace;
	
	private String destinationPlace;
	
	private String carrier;
	
	private String carrierCyrilic;
	
	private String price;
	
	private String priceReturn;
	
	private String departureTime;
	
	private String arrivalTime;
	
	public BusLineDto() {
	}
	
	public BusLineDto(String name, String departurePlace, String destinationPlace, 
			String carrier, String carrierCyrilic, String price, String priceReturn, String departureTime, String arrivalTime) {
		this.name = name;
		this.departurePlace = departurePlace;
		this.destinationPlace = destinationPlace;
		this.carrier = carrier;
		this.carrierCyrilic = carrierCyrilic;
		this.price = price;
		this.priceReturn = priceReturn;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
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

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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
	
	public String getCarrierCyrilic() {
		return carrierCyrilic;
	}

	public void setCarrierCyrilic(String carrierCyrilic) {
		this.carrierCyrilic = carrierCyrilic;
	}
	
	public String getPriceReturn() {
		return priceReturn;
	}

	public void setPriceReturn(String priceReturn) {
		this.priceReturn = priceReturn;
	}

	@Override
	public int compareTo(Object o) {
		if(o != null && o instanceof BusLineDto) {
			BusLineDto d = (BusLineDto)o;
			return this.departureTime.compareTo(d.getDepartureTime());
		}
		return 0;
	}
	
}
