package pojos.JPA;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "MedicalPersonnel")
public class MedicalPersonnel_JPA implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5956033813170644789L;
	
	@Id
	@GeneratedValue(generator = "MedicalPersonnel")
	@TableGenerator(name = "MedicalPersonnel", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "MedicalPersonnel")
	private Integer id;
	private String name;
	private String department;
	private String position;
	private Integer pathologyId;
	
	public MedicalPersonnel_JPA() {
		super();
	}
	
	public MedicalPersonnel_JPA(Integer id, String name, String department, String position, Integer pathologyId) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.position = position;
		this.pathologyId = pathologyId;
	}

	public MedicalPersonnel_JPA(String name, String department, String position, Integer pathologyId) {
		super();
		this.name = name;
		this.department = department;
		this.position = position;
		this.pathologyId = pathologyId;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getPathologyId() {
		return pathologyId;
	}
	public void setPathologyId(Integer pathologyId) {
		this.pathologyId = pathologyId;
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
		MedicalPersonnel_JPA other = (MedicalPersonnel_JPA) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "MedicalPersonnel_JPA [id=" + id + ", name=" + name + ", department=" + department + ", position="
				+ position + ", pathologyId=" + pathologyId + "]";
	}
		
}