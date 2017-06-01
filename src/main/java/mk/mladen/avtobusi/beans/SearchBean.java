package mk.mladen.avtobusi.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.wicket.request.mapper.parameter.PageParameters;

public class SearchBean implements Serializable {

	private static final long serialVersionUID = -2884380484908326183L;
	
	@NotNull
	private String departurePlace;
	
	@NotNull
	private String destinationPlace;

	@NotNull
	private String departureDate;
	
	private String returnDate;
	
	private Date date;
	
	public SearchBean() {
	}
	
	public SearchBean(PageParameters params) {
		if(params != null) {
			this.departurePlace = params.get("departure").toString();
			this.destinationPlace = params.get("destination").toString();
			this.departureDate = params.get("date").toString();
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
	
}
