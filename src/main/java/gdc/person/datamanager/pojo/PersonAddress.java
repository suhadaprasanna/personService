/**
 * 
 */
package gdc.person.datamanager.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author suhada
 *
 */
@Entity
@Table(name = "person_address", catalog = "gdc_person")
public class PersonAddress {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	@Column(name = "addressl1", length = 50)
	private String addressl1;
	@Column(name = "addressl2", length = 50)
	private String addressl2;
	@Column(name = "addressl3", length = 50)
	private String addressl3;
	@Column(name = "addressl4", length = 50)
	private String addressl4;
	@Column(name = "status", length = 5)
	private String status;
	@Temporal(TemporalType.DATE)
	@Column(name = "sys_add_date", length = 5)
	private Date sys_add_date;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person", nullable = false)
	private Person person;
	@Column(name = "latitude")
	private double latitude;
	@Column(name = "longitude")
	private double longitude;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	public String getAddressl1() {
		return addressl1;
	}
	public void setAddressl1(String addressl1) {
		this.addressl1 = addressl1;
	}
	public String getAddressl2() {
		return addressl2;
	}
	public void setAddressl2(String addressl2) {
		this.addressl2 = addressl2;
	}
	public String getAddressl3() {
		return addressl3;
	}
	public void setAddressl3(String addressl3) {
		this.addressl3 = addressl3;
	}
	public String getAddressl4() {
		return addressl4;
	}
	public void setAddressl4(String addressl4) {
		this.addressl4 = addressl4;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the sys_add_date
	 */
	public Date getSys_add_date() {
		return sys_add_date;
	}
	/**
	 * @param sys_add_date the sys_add_date to set
	 */
	public void setSys_add_date(Date sys_add_date) {
		this.sys_add_date = sys_add_date;
	}
	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
