package pojos;

import java.io.Serializable;

import javax.xml.bind.annotation.*;

//this annotation indicates this class is going to be turned into an XML later
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "medicalPersonnel")
@XmlType(propOrder = {"name", "department", "position", "pathologyId"})
public class MedicalPersonnel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4865330136642653318L;

	//XmlTransient because it is not going to be stored, it will be ignored by XML
	//we do this because it only interests the Data Base
	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlElement
	private String department;
	@XmlElement
	private String position;
	@XmlElement
	private Integer pathologyId;

	public MedicalPersonnel(Integer id, String name, String department, String position, Integer pathologyId) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.position = position;
		this.pathologyId = pathologyId;
	}

	public MedicalPersonnel(String name, String department, String position, Integer pathologyId) {
		super();
		this.name = name;
		this.department = department;
		this.position = position;
		this.pathologyId = pathologyId;
	}

	public MedicalPersonnel() {

		super();
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
	public String toString() {
		return "MedicalPersonnel [id=" + id + ", name=" + name + ", department=" + department + ", position=" + position
				+ ", pathologyId=" + pathologyId + "]";
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
		MedicalPersonnel other = (MedicalPersonnel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
