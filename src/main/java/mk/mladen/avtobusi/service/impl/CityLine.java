package mk.mladen.avtobusi.service.impl;

@SuppressWarnings("unused")
public class CityLine {

    private String name;
	private String name2;
    private int order;
    private String time;
    private String time2;
    private String carrier;
    private int lineNumber;
    private String daysOfWork;
    private int distance;

    public CityLine() {
    }

    public CityLine(String name, String name2, String time, String time2, int order) {
        this.name = name;
        this.name2 = name2;
        this.time = time;
        this.time2 = time2;
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

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof CityLine) {
            CityLine c = (CityLine)obj;
            if(c.getName().equals(this.getName())
                    && c.getName().equals(this.getName())
                    && c.getTime().equals(this.getTime())
                    && c.getTime2().equals(this.getTime2())
                    && c.getOrder() == this.getOrder()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return name + ", order: " + order + ", time: " + time + ", time2: " + time2 + ", carrier: " + carrier + ", distance: " + distance + ", days of work: " + daysOfWork + ", line number: " + lineNumber;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }
}
