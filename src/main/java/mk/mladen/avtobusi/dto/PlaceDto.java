package mk.mladen.avtobusi.dto;

import java.io.Serializable;

public class PlaceDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private String nameCyrilic;

    private Integer population;

    private String postalCode;

    private Integer importance;

    private String country;
    
    private String sinonimi;

    public PlaceDto() {

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

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

	public String getSinonimi() {
		return sinonimi;
	}

	public void setSinonimi(String sinonimi) {
		this.sinonimi = sinonimi;
	}

}
