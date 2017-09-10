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

	private String distance;

	private String travelTime;
	
	public BusLineDto() {
	}
	
	public BusLineDto(String name, String departurePlace, String destinationPlace, 
			String carrier, String carrierCyrilic, String price, String priceReturn,
		    String departureTime, String arrivalTime, Double distance, String travelTime) {
		this.name = name;
		this.departurePlace = departurePlace;
		this.destinationPlace = destinationPlace;
		this.carrier = carrier;
		this.carrierCyrilic = carrierCyrilic;
		this.price = price;
		this.priceReturn = priceReturn;
		this.departureTime = convertTime(departureTime);
		this.arrivalTime = convertTime(arrivalTime);
		this.distance = getStrDistance(distance);
		this.travelTime = travelTime;
	}

	private String getStrDistance(Double distance) {
		String result = "";
		if(distance == null) {
			return result + " km";
		}

		if(!(distance instanceof Double)) {
			return result + " km";
		}

		try {
			result = String.valueOf(distance.doubleValue());
			if(distance.doubleValue() < 0) {
				result = String.valueOf(distance.doubleValue() * -1);
			}

		}catch(NumberFormatException nfe) {
			return result + " km";
		}

		return result + " km";
	}

	private String convertTime(String time) {
		String newTime = time;
		if(time != null && (time.length() == 4)) { 
			newTime = "0"+time;
		} else if(time != null && (time.length() == 7)) { 
			newTime = "0"+time;
			newTime = newTime.substring(0, 5);
		} else if(time != null && (time.length() == 8)) { 
			newTime = newTime.substring(0, 5);
		}
		
		if(newTime.contains(".")) {
			newTime = newTime.replace(".", ":");
		}
		
		return newTime;
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

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}

	@Override
	public int compareTo(Object o) {
		if(o != null && o instanceof BusLineDto) {
			BusLineDto d = (BusLineDto)o;
			String dt1 = this.departureTime;
			String dt2 = d.getDepartureTime();
			return dt1.compareTo(dt2);
		}
		return 0;
	}
	
}
