package mk.mladen.avtobusi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COUNTRY")
public class CountryEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", nullable=true)
	private String name;

	@Column(name = "namecyrillic", nullable=true)
	private String namecyrillic;
	
	@Column(name = "population", nullable=true)
	private Integer population;
	
	@Column(name = "countrycode", nullable=true)
	private String countryCode;

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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public String getNamecyrillic() {
		return namecyrillic;
	}

	public void setNamecyrillic(String namecyrillic) {
		this.namecyrillic = namecyrillic;
	}

	@Override
	public String toString() {
		return String.format("CountryEntity[%s, %s]", name, namecyrillic);
	}
}
