package gdc.person.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PersonDTO implements java.io.Serializable {

	private static final long serialVersionUID = 2830130455676527236L;

	private Long id;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String sur_name;
	private String nic;
	private Date birth_day;
	private Date sys_add_date;
	private String gender;
	private String living_status;
	private String nationality;
	private String status;
	private ArrayList<PersonEmailDTO> personEmails = new ArrayList<PersonEmailDTO>();
	private ArrayList<PersonContactDTO> personContacts = new ArrayList<PersonContactDTO>();
	private ArrayList<PersonAddressDTO> personAddress = new ArrayList<PersonAddressDTO>();

	public PersonDTO() {}
	public PersonDTO(long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getSur_name() {
		return sur_name;
	}
	public void setSur_name(String sur_name) {
		this.sur_name = sur_name;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	public Date getBirth_day() {
		return birth_day;
	}
	public void setBirth_day(Date birth_day) {
		this.birth_day = birth_day;
	}
	public Date getSys_add_date() {
		return sys_add_date;
	}
	public void setSys_add_date(Date sys_add_date) {
		this.sys_add_date = sys_add_date;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLiving_status() {
		return living_status;
	}
	public void setLiving_status(String living_status) {
		this.living_status = living_status;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public ArrayList<PersonEmailDTO> getPersonEmails() {
		return personEmails;
	}
	public void setPersonEmails(ArrayList<PersonEmailDTO> personEmails) {
		this.personEmails = personEmails;
	}
	public ArrayList<PersonContactDTO> getPersonContacts() {
		return personContacts;
	}
	public void setPersonContacts(ArrayList<PersonContactDTO> personContacts) {
		this.personContacts = personContacts;
	}
	public ArrayList<PersonAddressDTO> getPersonAddress() {
		return personAddress;
	}
	public void setPersonAddress(ArrayList<PersonAddressDTO> personAddress) {
		this.personAddress = personAddress;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
