package pojos_JPA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import pojos.Pathology;

@Entity
@Table(name = "Treatment")
public class Treatment_JPA implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="Treatment")
	@TableGenerator(name="Treatment", table="sqlite_sequence",
	pkColumnName = "name",valueColumnName = "seq", pkColumnValue = "Treatment" )
	private Integer id;
	private String name;
	private String medication;
	private String description;
	@OneToMany(mappedBy="Treatment")
	private List<Pathology_JPA> pathologies;
	
	public Treatment_JPA() {
		super();
		this.pathologies = new ArrayList<Pathology_JPA>();
	}
	
	public Treatment_JPA(Integer id, String name, String medication, String description, List<Pathology> pathologies) {
		super();
		this.id = id;
		this.name = name;
		this.medication = medication;
		this.description = description;
		this.pathologies = new ArrayList<Pathology_JPA>();
	}

	public Treatment_JPA(String name, String medication, String description, List<Pathology> pathologies) {
		super();
		this.name = name;
		this.medication = medication;
		this.description = description;
		this.pathologies = new ArrayList<Pathology_JPA>();
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

	public String getMedication() {
		return medication;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Pathology_JPA> getPathologies() {
		return pathologies;
	}

	public void setPathologies(List<Pathology_JPA> pathologies) {
		this.pathologies = pathologies;
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
		Treatment_JPA other = (Treatment_JPA) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Treatment_JPA [id=" + id + ", name=" + name + ", medication=" + medication + ", description="
				+ description + ", pathologies=" + pathologies + "]";
	}
	
}
