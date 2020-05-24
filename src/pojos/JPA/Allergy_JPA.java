package pojos.JPA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@OneToMany(mappedBy = "allergy")
	private List<ClinicalHistory_JPA> clinicalHistories;
		
	
	public Allergy_JPA() {
		super();
		this.clinicalHistories = new ArrayList<ClinicalHistory_JPA>();
	}
	
	public Allergy_JPA(String allergy, Integer degree, List<ClinicalHistory_JPA> clinicalHistories) {
		super();
		this.allergy = allergy;
		this.degree = degree;
		this.clinicalHistories = clinicalHistories;
	}

	public Allergy_JPA(Integer id, String allergy, Integer degree, List<ClinicalHistory_JPA> clinicalHistories) {
		super();
		this.id = id;
		this.allergy = allergy;
		this.degree = degree;
		this.clinicalHistories = clinicalHistories;
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
	public List<ClinicalHistory_JPA> getClinicalHistories() {
		return clinicalHistories;
	}
	public void setClinicalHistories(List<ClinicalHistory_JPA> clinicalHistories) {
		this.clinicalHistories = clinicalHistories;
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
		return "Allergy_JPA [id=" + id + ", allergy=" + allergy + ", degree=" + degree + "]";
	}
	
	

	
	
	
}
