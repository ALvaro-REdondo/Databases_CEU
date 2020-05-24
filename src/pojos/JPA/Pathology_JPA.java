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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import pojos.Pathology;
import pojos.Patient;
import pojos.Treatment;

@Entity
@Table(name = "Pathology")
public class Pathology_JPA implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6561377107325234951L;
	
	@Id
	@GeneratedValue(generator = "Pathology")
	@TableGenerator(name = "Pathology", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "Pathology")
	private Integer id;
	private String name;
	private Date startDate;
	private Date endingDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "treatmentId")
	private Treatment treatment;
	
	@OneToMany(mappedBy="Pathology")
	private List<Patient> patients;
		
	public Pathology_JPA() {
		super();
		this.patients = new ArrayList<Patient>();
	}

	public Pathology_JPA(Integer id, String name, Date startDate, Date endingDate, Treatment treatment,
			List<Patient> patients) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endingDate = endingDate;
		this.treatment = treatment;
		this.patients = new ArrayList<Patient>();
	}

	public Pathology_JPA(String name, Date startDate, Date endingDate, Treatment treatment, List<Patient> patients) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endingDate = endingDate;
		this.treatment = treatment;
		this.patients = new ArrayList<Patient>();
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}
	
	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = new ArrayList<Patient>();
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
		Pathology_JPA other = (Pathology_JPA) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pathology_JPA [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endingDate=" + endingDate
				+ ", treatment=" + treatment + ", patients=" + patients + "]";
	}
	
}