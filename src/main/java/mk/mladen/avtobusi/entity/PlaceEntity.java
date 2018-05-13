package mk.mladen.avtobusi.entity;

import javax.persistence.*;

@Entity
@Table(name="PLACE")
public class PlaceEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", nullable=true, length = 100)
	private String name;
	
	@Column(name = "namecyrillic", nullable=true, length = 100)
	private String nameCyrilic;
	
	@Column(name = "population", nullable=true)
	private Integer population;
	
	@Column(name = "postalcode", nullable=true, length = 10)
	private String postalCode;
	
	@Column(name = "sinonimi", nullable=true, length = 200)
	private String sinonimi;

	@Column(name = "importance", nullable=true)
	private Integer importance;

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
	
	public String getSinonimi() {
		return sinonimi;
	}

	public void setSinonimi(String sinonimi) {
		this.sinonimi = sinonimi;
	}

	public Integer getImportance() {
		return importance;
	}

	public void setImportance(Integer importance) {
		this.importance = importance;
	}

	@Override
	public String toString() {
		return String.format("PlaceEntity[%s, %s, %s]", name, nameCyrilic, sinonimi);
	}
}
