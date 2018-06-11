package entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import entity.CarEntityBean;

@Entity
@Table(name="Drivers")
public class DriverEntityBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CarID",unique=true)
	private CarEntityBean carEntityBean;
	
	public CarEntityBean getCarDetails() {
		return carEntityBean; 
	}
	
	public void setCarDetails(CarEntityBean car) {
		this.carEntityBean = car;
	}
	
	public int getDriverId() {
		return id;
	}
	
	public void setDriverId(int id) {
		this.id = id;
	}
	
	public String getDriverName() {
		return name;
	}
	
	public void setDriverName(String name) {
		this.name = name;
	}
	
}
