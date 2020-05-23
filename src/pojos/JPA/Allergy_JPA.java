package pojos.JPA;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "Allergy")
public class Allergy_JPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3162259565385840873L;
	
	@Id
	@GeneratedValue(generator="Allergy")
	@TableGenerator(name="Allergy", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="Allergy")
	private Integer id;
	private String allergy;
	private Integer degree;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ClinicalHistoryId")
	private ClinicalHistory_JPA clinicalHistory_JPA;
	
	public Allergy_JPA() {
		super();
	}
	
	public Allergy_JPA(String allergy, Integer degree, ClinicalHistory_JPA clinicalHistory_JPA) {
		super();
		this.allergy = allergy;
		this.degree = degree;
		this.clinicalHistory_JPA = clinicalHistory_JPA;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAllergy() {
		return allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	public Integer getDegree() {
		return degree;
	}
	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	public ClinicalHistory_JPA getClinicalHistory_JPA() {
		return clinicalHistory_JPA;
	}
	public void setClinicalHistory_JPA(ClinicalHistory_JPA clinicalHistory_JPA) {
		this.clinicalHistory_JPA = clinicalHistory_JPA;
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
		Allergy_JPA other = (Allergy_JPA) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Allergy_JPA [id=" + id + ", allergy=" + allergy + ", degree=" + degree + ", clinicalHistory_JPA="
				+ clinicalHistory_JPA + "]";
	}

	
	
	
}
