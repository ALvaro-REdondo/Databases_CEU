package pojos_JPA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table ( name= "Symptom")

public class Symptom_JPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3201866028429914232L;
	
	@Id
	@GeneratedValue ( generator = "Symptom")
	@TableGenerator(name= "Symptom",table = "sqlite_sequence",
	pkColumnName ="name",valueColumnName = "seq", pkColumnValue = "Symptom")
	
	private Integer id;
	private String manifestation;
	
	
	
	@ManyToMany (mappedBy = "Pathology_Symptom")
	private List<Pathology_JPA> pathologies;
	
	
	
	
	public Symptom_JPA() {
		super();
	}

	

	public Symptom_JPA(Integer id, String manifestation) {
		super();
		this.id = id;
		this.manifestation = manifestation;
		this.pathologies= new ArrayList<Pathology_JPA>();
	}



	public Symptom_JPA( String manifestation) {
		super();
		this.manifestation = manifestation;
		this.pathologies= new ArrayList<Pathology_JPA>();
		
	}

	

	public List<Pathology_JPA> getPathologies() {
		return pathologies;
	}



	public void setPathologies(List<Pathology_JPA> pathologies) {
		this.pathologies = pathologies;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getManifestation() {
		return manifestation;
	}


	public void setManifestation(String manifestation) {
		this.manifestation = manifestation;
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
		Symptom_JPA other = (Symptom_JPA) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Symptom_JPA [id=" + id + ", manifestation=" + manifestation + "]";
	}
	
	
	
	
	
	
	
	

}
