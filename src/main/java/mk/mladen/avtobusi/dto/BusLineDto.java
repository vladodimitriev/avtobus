package mk.mladen.avtobusi.dto;

import java.io.Serializable;

@SuppressWarnings("rawtypes")
public class BusLineDto implements Serializable, Comparable {

	private static final long serialVersionUID = 3314388063021551876L;

	private int id;
	
	private String name;

	private int departurePlaceId;
	
	private String departurePlace;

	private int destinationPlaceId;
	
	private String destinationPlace;

	private int carrierId;

	private String carrier;
	
	private String carrierCyrilic;
	
	private String price;
	
	private String priceReturn;
	
	private String departureTime;
	
	private String arrivalTime;

	private String distance;

	private String travelTime;

	private String comment;

	private String hasPrice;

	private String operationDays;

	private String operationMonths;

	private String operationPeriod;

	private String lineNumber;

	private String smallPlaces;
	
	private int redenBroj;
	
	public BusLineDto() {
	}
	
	public BusLineDto(int id, String name, String departurePlace, String destinationPlace,
			String carrier, String carrierCyrilic, String price, String priceReturn,
		    String departureTime, String arrivalTime, Double distance, String travelTime,
		    String smallPlaces, Integer redenBroj) {
		this.id = id;
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
		this.travelTime = generateTravelTime(arrivalTime, departureTime);
		this.smallPlaces = smallPlaces;
		this.redenBroj = redenBroj != null ? redenBroj.intValue() : 0;
	}

	public BusLineDto(int id, String name, int departurePlaceId, String departurePlace, int destinationPlaceId,
					  String destinationPlace, int carrierId, String carrier, String carrierCyrilic,
					  String price, String priceReturn,
					  String departureTime, String arrivalTime, Double distance, String travelTime,
					  String smallPlaces, Integer redenBroj) {
		this.id = id;
		this.departurePlaceId = departurePlaceId;
		this.destinationPlaceId = destinationPlaceId;
		this.name = name;
		this.departurePlace = departurePlace;
		this.destinationPlace = destinationPlace;
		this.carrierId = carrierId;
		this.carrier = carrier;
		this.carrierCyrilic = carrierCyrilic;
		this.price = price;
		this.priceReturn = priceReturn;
		this.departureTime = convertTime(departureTime);
		this.arrivalTime = convertTime(arrivalTime);
		this.distance = getStrDistance(distance);
		this.travelTime = generateTravelTime(arrivalTime, departureTime);
		this.smallPlaces = smallPlaces;
		this.redenBroj = redenBroj != null ? redenBroj.intValue() : 0;
	}

	private String generateTravelTime(String arrivalTime, String departureTime) {
		try {
			String[] ata = arrivalTime.split("\\.");
			String[] dta = departureTime.split("\\.");
			
			if(ata.length < 2 || dta.length < 2) {
				ata = arrivalTime.split("\\:");
				dta = departureTime.split("\\:");
			}
			Integer ah = Integer.valueOf(ata[0]);
			Integer dh = Integer.valueOf(dta[0]);
			if(ah < dh) {
				Integer am = Integer.valueOf(ata[1]);
				Integer dm = Integer.valueOf(dta[1]);
				dh = 24 - dh;
				ah = 0 + ah;
				int mm = am - dm;
				int hh = ah + dh;
				int tm = hh * 60 + mm;
				int rh = tm / 60;
				int rm = tm % 60;
				return "" + rh + ":" + rm;
			} else {
				Integer am = Integer.valueOf(ata[1]);
				Integer dm = Integer.valueOf(dta[1]);

				int at = ah * 60 + am;
				int dt = dh * 60 + dm;

				int rmt = at - dt;
				int rh = rmt / 60;
				int rm = rmt % 60;
				return "" + rh + ":" + rm;
			}
		} catch(Exception e) {
			return "";
		}
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
		
		if(newTime != null && newTime.contains(".")) {
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getHasPrice() {
		return hasPrice;
	}

	public void setHasPrice(String hasPrice) {
		this.hasPrice = hasPrice;
	}

	public String getOperationDays() {
		return operationDays;
	}

	public void setOperationDays(String operationDays) {
		this.operationDays = operationDays;
	}

	public String getOperationMonths() {
		return operationMonths;
	}

	public void setOperationMonths(String operationMonths) {
		this.operationMonths = operationMonths;
	}

	public String getOperationPeriod() {
		return operationPeriod;
	}

	public void setOperationPeriod(String operationPeriod) {
		this.operationPeriod = operationPeriod;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getDeparturePlaceId() {
		return departurePlaceId;
	}

	public void setDeparturePlaceId(int departurePlaceId) {
		this.departurePlaceId = departurePlaceId;
	}

	public int getDestinationPlaceId() {
		return destinationPlaceId;
	}

	public void setDestinationPlaceId(int destinationPlaceId) {
		this.destinationPlaceId = destinationPlaceId;
	}

	public int getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(int carrierId) {
		this.carrierId = carrierId;
	}

	public String getSmallPlaces() {
		return smallPlaces;
	}

	public void setSmallPlaces(String smallPlaces) {
		this.smallPlaces = smallPlaces;
	}
	
	public int getRedenBroj() {
		return redenBroj;
	}

	public void setRedenBroj(int redenBroj) {
		this.redenBroj = redenBroj;
	}

	@Override
	public int compareTo(Object o) {
		if(o != null && o instanceof BusLineDto) {
			BusLineDto d = (BusLineDto)o;
			String dt1 = this.departureTime;
			String dt2 = d.getDepartureTime();
			if(dt1 == null) {
				return -1;
			}
			return dt1.compareTo(dt2);
		}
		return 0;
	}
	
}
