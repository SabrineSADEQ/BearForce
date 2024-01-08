package fr.isika.cda.javaee.entity.platform;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
/**
 * 
 *
 * @author Bitjoka Vincent
 */
@Entity
public class Pack {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private int price;
	@OneToMany
	@JoinColumn(name = "subscription")
	private List<Subscription>subscriptions=new ArrayList<>();
	
	//@ManyToOne @JoinColumn(name = "PlatformConfiguration")
	//private PlatformConfiguration platformConfiguration;

	@ManyToMany
	@JoinTable(name ="pack_Service",
	joinColumns = @JoinColumn(name="pack_id"),
	inverseJoinColumns = @JoinColumn(name="service_id")
	)
	
	private List<Service>services = new ArrayList<Service>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String description;

	@Override
	public String toString() {
		return "Pack [id=" + id + ", name=" + name + ", price=" + price + ", subscriptions=" + subscriptions
				+  ", services=" + services + ", description="
				+ description + "]";
	}

	
	
}
