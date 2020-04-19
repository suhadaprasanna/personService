/**
 * 
 */
package gdc.person.dto;

import java.util.Date;


/**
 * @author suhada
 *
 */

public class PersonAddressDTO {

	private long id;
	private String addressl1;
	private String addressl2;
	private String addressl3;
	private String addressl4;
	private String status;
	private Date sys_add_date;
	private PersonDTO person;
	private double latitude;
	private double longitude;
	private long person_id;
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
	public PersonDTO getPerson() {
		return person;
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(PersonDTO person) {
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
	public long getPerson_id() {
		return person_id;
	}
	public void setPerson_id(long person_id) {
		this.person_id = person_id;
	}
	
}
