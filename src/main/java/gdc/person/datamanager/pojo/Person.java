package gdc.person.datamanager.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "person", catalog = "gdc_person")
public class Person implements java.io.Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 2830130455676527236L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "first_name", nullable = false, length = 100)
	private String first_name;
	@Column(name = "middle_name", length = 100)
	private String middle_name;
	@Column(name = "last_name", nullable = false, length = 100)
	private String last_name;
	@Column(name = "sur_name", length = 100)
	private String sur_name;
	@Column(name = "nic", length = 20)
	private String nic;
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_day", length = 10)
	private Date birth_day;
	@Temporal(TemporalType.DATE)
	@Column(name = "sys_add_date", length = 10)
	private Date sys_add_date;
	@Column(name = "gender", length = 10)
	private String gender;
	@Column(name = "living_status", length = 5)
	private String living_status;
	@Column(name = "nationality", length = 50)
	private String nationality;
	@Column(name = "status", length = 5)
	private String status;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
	private Set<PersonEmail> personEmails = new HashSet<PersonEmail>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
	private Set<PersonContact> personContacts = new HashSet<PersonContact>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
	private Set<PersonAddress> personAddress = new HashSet<PersonAddress>();

	public Person() {}
	public Person(long id) {
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
	public Set<PersonEmail> getPersonEmails() {
		return personEmails;
	}
	public void setPersonEmails(Set<PersonEmail> personEmails) {
		this.personEmails = personEmails;
	}
	public Set<PersonContact> getPersonContacts() {
		return personContacts;
	}
	public void setPersonContacts(Set<PersonContact> personContacts) {
		this.personContacts = personContacts;
	}
	public Set<PersonAddress> getPersonAddress() {
		return personAddress;
	}
	public void setPersonAddress(Set<PersonAddress> personAddress) {
		this.personAddress = personAddress;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
