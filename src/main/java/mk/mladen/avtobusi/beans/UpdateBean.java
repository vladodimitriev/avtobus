package mk.mladen.avtobusi.beans;

import java.io.Serializable;

public class UpdateBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String departurePlace;

    private String arrivalPlace;

    private String departureTime;

    private String arrivalTime;

    private String operationDays;

    private String operationMonths;

    private String operationPeriod;

    private String carrier;

    private String lineNumber;

    private String comment;

    private String hasPrice;

    private String price;

    private String priceReturn;

    private String travelTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
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

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceReturn() {
        return priceReturn;
    }

    public void setPriceReturn(String priceReturn) {
        this.priceReturn = priceReturn;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }
}
