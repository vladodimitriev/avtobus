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
@Table(name="PLACE")
public class PlaceEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", nullable=true)
	private String name;
	
	@Column(name = "namecyrillic", nullable=true)
	private String nameCyrilic;
	
	@Column(name = "population", nullable=true)
	private Integer population;
	
	@Column(name = "postalcode", nullable=true)
	private String postalCode;

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="countryid", nullable=true)
	private CountryEntity country;
	
	public PlaceEntity() {
	}
	
	public PlaceEntity(String name) {
		this.name = name;
	}
	
	public PlaceEntity(String name, String cyrilicName) {
		this.name = name;
		this.nameCyrilic = cyrilicName;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity country) {
		this.country = country;
	}

	public String getNameCyrilic() {
		return nameCyrilic;
	}

	public void setNameCyrilic(String nameCyrilic) {
		this.nameCyrilic = nameCyrilic;
	}

	@Override
	public String toString() {
		return String.format("PlaceEntity[%s, %s]", name, nameCyrilic);
	}
}
