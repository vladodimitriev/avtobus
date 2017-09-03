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

import org.hibernate.Hibernate;

@Entity
@Table(name="CARRIER")
public class CarrierEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", nullable=true)
	private String name;
	
	@Column(name = "namecyrilic", nullable=true)
	private String nameCyrilic;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="carrierplaceid", nullable=true)
	private PlaceEntity place;
	
	public CarrierEntity() {
	}

	public CarrierEntity(String name, PlaceEntity place) {
		this.name = name;
		this.place = place;
	}
	
	public CarrierEntity(String name, String cyrilicName, PlaceEntity place) {
		this.name = name;
		this.nameCyrilic = cyrilicName;
		this.place = place;
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

	public PlaceEntity getPlace() {
		Hibernate.initialize(place);
		return place;
	}

	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return String.format("CarrierEntity[%s, %s]", name, nameCyrilic);
	}
	
}
