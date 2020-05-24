package pojos.JPA;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "ClinicalHistory")
public class ClinicalHistory_JPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7105495054085627194L;
	
	@Id
	@GeneratedValue(generator="ClinicalHistory")
	@TableGenerator(name="ClinicalHistory", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="ClinicalHistory")
	private Integer id;
	private Date doe;
	private Date dod;
	private String bloodType;
	private String extraInfo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AllergyId")
	private Allergy_JPA allergy;
	@OneToOne(mappedBy = "patient")
	private Patient_JPA patient;
	
	
	public ClinicalHistory_JPA() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDoe() {
		return doe;
	}
	public void setDoe(Date doe) {
		this.doe = doe;
	}
	public Date getDod() {
		return dod;
	}
	public void setDod(Date dod) {
		this.dod = dod;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	public Allergy_JPA getAllergy() {
		return allergy;
	}
	public void setAllergy(Allergy_JPA allergy) {
		this.allergy = allergy;
	}
	public Patient_JPA getPatient() {
		return patient;
	}
	public void setPatient(Patient_JPA patient) {
		this.patient = patient;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClinicalHistory_JPA other = (ClinicalHistory_JPA) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ClinicalHistory_JPA [id=" + id + ", doe=" + doe + ", dod=" + dod + ", bloodType=" + bloodType
				+ ", extraInfo=" + extraInfo + ", allergy=" + allergy + "]";
	}
	
	
	
	
	
	
	



}
