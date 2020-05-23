package pojos.JPA;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@OneToMany(mappedBy = "clinicalHistory_JPA")
	private List<Allergy_JPA> allergies;
	
	
	
	public ClinicalHistory_JPA() {
		super();
		this.allergies = new ArrayList<Allergy_JPA>();
	}
	
	public ClinicalHistory_JPA(Date doe, Date dod, String bloodType, String extraInfo, List<Allergy_JPA> allergies) {
		super();
		this.doe = doe;
		this.dod = dod;
		this.bloodType = bloodType;
		this.extraInfo = extraInfo;
		this.allergies = allergies;
		this.allergies = new ArrayList<Allergy_JPA>();

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
	public List<Allergy_JPA> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<Allergy_JPA> allergies) {
		this.allergies = allergies;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allergies == null) ? 0 : allergies.hashCode());
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
		if (allergies == null) {
			if (other.allergies != null)
				return false;
		} else if (!allergies.equals(other.allergies))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ClinicalHistory_JPA [id=" + id + ", doe=" + doe + ", dod=" + dod + ", bloodType=" + bloodType
				+ ", extraInfo=" + extraInfo + "]";
	}



}
