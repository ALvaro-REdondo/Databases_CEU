package pojos_JPA;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import pojos.Patient;
import pojos.Pathology;
import pojos.ClinicalHistory;

@Entity
@Table (name="Patient")

public class Patient_JPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 155112531167812235L;

	@Id
	@GeneratedValue(generator = "Patient")
	@TableGenerator(name= "Patient",table = "sqlite_sequence",
	pkColumnName ="name",valueColumnName = "seq", pkColumnValue = "Patient")
	private Integer id;
	private String name;
	private String gender;
	private String state;
	private Date dob;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "pathology_id")
	private Pathology pathology;
	
	@OneToOne (fetch=FetchType.LAZY)
	@JoinColumn(name="clinicalhistory_id")
	private ClinicalHistory clinicalHistory;
	
	public Patient_JPA() {
		super();
		
	}

	
	public Patient_JPA(Integer id, String name, String gender, String state, Date dob, Pathology pathology,
			ClinicalHistory clinicalHistory) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.state = state;
		this.dob = dob;
		this.pathology = pathology;
		this.clinicalHistory = clinicalHistory;
	}
	
	

	public Patient_JPA( String name, String gender, String state, Date dob, Pathology pathology,
			ClinicalHistory clinicalHistory) {
		super();
		this.name = name;
		this.gender = gender;
		this.state = state;
		this.dob = dob;
		this.pathology = pathology;
		this.clinicalHistory = clinicalHistory;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Pathology getPathology() {
		return pathology;
	}

	public void setPathology(Pathology pathology) {
		this.pathology = pathology;
	}
	


	public ClinicalHistory getClinicalHistory() {
		return clinicalHistory;
	}

	public void setClinicalHistory(ClinicalHistory clinicalHistory) {
		this.clinicalHistory = clinicalHistory;
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
		Patient_JPA other = (Patient_JPA) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patient_JPA [id=" + id + ", name=" + name + ", gender=" + gender + ", state=" + state + ", dob=" + dob
				+ ", pathology=" + pathology + "]";
	}
	
	

	
	
	
	
	
	
	
	
	
	
	

}
