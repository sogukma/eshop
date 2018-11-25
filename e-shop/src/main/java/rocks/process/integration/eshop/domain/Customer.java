package rocks.process.integration.eshop.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Customer {

	@Id
	@GeneratedValue
	private Long customerId;
	private String name;
	private String vorname;
	private int birthdate;

	private int loyalityPoints;
	@OneToMany(mappedBy="customer")
	//@JsonIgnore
	@JsonBackReference
	private List<Orders> orders;


	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	public Long getCustomerId() {
		return customerId;
	}
	

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public int getLoyalityPoints() {
		return loyalityPoints;
	}
	public void setLoyalityPoints(int loyalityPoints) {
		this.loyalityPoints = loyalityPoints;
	}
	public int getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(int birthdate) {
		this.birthdate = birthdate;
	}

	
}