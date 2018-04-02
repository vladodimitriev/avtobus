package mk.mladen.avtobusi.dto;

import java.io.Serializable;

public class CarrierDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private String nameCyrilic;

    private String place;

    public CarrierDto() {

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

    public String getNameCyrilic() {
        return nameCyrilic;
    }

    public void setNameCyrilic(String nameCyrilic) {
        this.nameCyrilic = nameCyrilic;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    
    @Override
    public String toString() {
    	return String.format("CarrierDto[id = %s, name = %s]", "" + id, name);
    }
}
