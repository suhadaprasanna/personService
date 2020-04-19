/**
 * 
 */
package gdc.person.common.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import gdc.person.datamanager.pojo.PersonAddress;

/**
 * @author suhada
 *
 */
public class TestForm implements Serializable{
	
	private long id;
	private String first_name;
	private String last_name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date today;
	private int age;
	private List<PersonAddress> addresses;
	private String contacts;
	
	/**
	 * @return the contacts
	 */
	public String getContacts() {
		return contacts;
	}
	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
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
	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}
	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}
	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	/**
	 * @return the today
	 */
	public Date getToday() {
		return today;
	}
	/**
	 * @param today the today to set
	 */
	public void setToday(Date today) {
		this.today = today;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * @return the addresses
	 */
	public List<PersonAddress> getAddresses() {
		return addresses;
	}
	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(List<PersonAddress> addresses) {
		this.addresses = addresses;
	}
	
}
