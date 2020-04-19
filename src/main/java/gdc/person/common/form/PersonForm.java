/**
 * 
 */
package gdc.person.common.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import gdc.person.datamanager.pojo.PersonAddress;
import gdc.person.datamanager.pojo.PersonContact;

/**
 * @author suhada
 *
 */
public class PersonForm extends Form{
	
	private long id;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String sur_name;
	private String gender;
	private String nic;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth_day;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sys_add_date;
	private String living_status;
	private String nationality;
	private long email_id;
	private String email;
	private PersonEmailForm[] emails;
	private long address_id;
	private String addressl1;
	private String addressl2;
	private String addressl3;
	private String addressl4;
	private PersonAddressForm[] addresses;
	private String contact1;
	private String contact2;
	private PersonContactForm[] contacts;
	private String status;
	
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
	 * @return the middle_name
	 */
	public String getMiddle_name() {
		return middle_name;
	}
	/**
	 * @param middle_name the middle_name to set
	 */
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
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
	 * @return the sur_name
	 */
	public String getSur_name() {
		return sur_name;
	}
	/**
	 * @param sur_name the sur_name to set
	 */
	public void setSur_name(String sur_name) {
		this.sur_name = sur_name;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the nic
	 */
	public String getNic() {
		return nic;
	}
	/**
	 * @param nic the nic to set
	 */
	public void setNic(String nic) {
		this.nic = nic;
	}
	
	/**
	 * @return the birth_day
	 */
	public Date getBirth_day() {
		return birth_day;
	}
	/**
	 * @param birth_day the birth_day to set
	 */
	public void setBirth_day(Date birth_day) {
		this.birth_day = birth_day;
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
	 * @return the living_status
	 */
	public String getLiving_status() {
		return living_status;
	}
	/**
	 * @param living_status the living_status to set
	 */
	public void setLiving_status(String living_status) {
		this.living_status = living_status;
	}
	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the addressl1
	 */
	public String getAddressl1() {
		return addressl1;
	}
	/**
	 * @param addressl1 the addressl1 to set
	 */
	public void setAddressl1(String addressl1) {
		this.addressl1 = addressl1;
	}
	/**
	 * @return the addressl2
	 */
	public String getAddressl2() {
		return addressl2;
	}
	/**
	 * @param addressl2 the addressl2 to set
	 */
	public void setAddressl2(String addressl2) {
		this.addressl2 = addressl2;
	}
	/**
	 * @return the addressl3
	 */
	public String getAddressl3() {
		return addressl3;
	}
	/**
	 * @param addressl3 the addressl3 to set
	 */
	public void setAddressl3(String addressl3) {
		this.addressl3 = addressl3;
	}
	/**
	 * @return the addressl4
	 */
	public String getAddressl4() {
		return addressl4;
	}
	/**
	 * @param addressl4 the addressl4 to set
	 */
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
	 * @return the email_id
	 */
	public long getEmail_id() {
		return email_id;
	}
	/**
	 * @param email_id the email_id to set
	 */
	public void setEmail_id(long email_id) {
		this.email_id = email_id;
	}
	/**
	 * @return the address_id
	 */
	public long getAddress_id() {
		return address_id;
	}
	/**
	 * @param address_id the address_id to set
	 */
	public void setAddress_id(long address_id) {
		this.address_id = address_id;
	}
	
	/**
	 * @return the contact1
	 */
	public String getContact1() {
		return contact1;
	}
	/**
	 * @param contact1 the contact1 to set
	 */
	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}
	/**
	 * @return the contact2
	 */
	public String getContact2() {
		return contact2;
	}
	/**
	 * @param contact2 the contact2 to set
	 */
	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}
	public PersonAddressForm[] getAddresses() {
		return addresses;
	}
	public void setAddresses(PersonAddressForm[] addresses) {
		this.addresses = addresses;
	}
	public PersonContactForm[] getContacts() {
		return contacts;
	}
	public void setContacts(PersonContactForm[] contacts) {
		this.contacts = contacts;
	}
	public PersonEmailForm[] getEmails() {
		return emails;
	}
	public void setEmails(PersonEmailForm[] emails) {
		this.emails = emails;
	}
	@Override
	public String toString() {
		return "PersonForm [id=" + id + ", first_name=" + first_name + ", middle_name=" + middle_name + ", last_name="
				+ last_name + ", sur_name=" + sur_name + ", gender=" + gender + ", nic=" + nic + ", birth_day="
				+ birth_day + ", sys_add_date=" + sys_add_date + ", living_status=" + living_status + ", nationality="
				+ nationality + ", email_id=" + email_id + ", email=" + email + ", emails=" + Arrays.toString(emails)
				+ ", address_id=" + address_id + ", addressl1=" + addressl1 + ", addressl2=" + addressl2
				+ ", addressl3=" + addressl3 + ", addressl4=" + addressl4 + ", addresses=" + Arrays.toString(addresses)
				+ ", contact1=" + contact1 + ", contact2=" + contact2 + ", contacts=" + Arrays.toString(contacts)
				+ ", status=" + status + "]";
	}
	
}
