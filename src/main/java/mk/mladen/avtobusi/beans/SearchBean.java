package mk.mladen.avtobusi.beans;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchBean implements Serializable {

	private static final long serialVersionUID = -2884380484908326183L;
	
	@NotNull
	private String departurePlace;
	
	@NotNull
	private String destinationPlace;

	@NotNull
	private String departureDate;
	
	private String returnDate;

	private String distance;

	private String travelTime;
	
	private Date date;

	private String place;

	private String carrier;
	
	public SearchBean() {
	}
	
	public SearchBean(PageParameters params) {
		if(params != null) {
			this.departurePlace = params.get("departure").toString();
			this.destinationPlace = params.get("destination").toString();
			this.departureDate = params.get("date").toString();
		}  
			
		if(StringUtils.isBlank(departureDate)) {
			Date d = new Date();
			SimpleDateFormat sde = new SimpleDateFormat("dd/MM/yyyy");
			this.departureDate = sde.format(d);
		}
	}
	
	public SearchBean(String departurePlace, String destinationPlace, String departureDate) {
		this.departurePlace = departurePlace;
		this.destinationPlace = destinationPlace;
		this.departureDate = departureDate;
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

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
}
