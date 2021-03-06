package pojos;

import java.io.Serializable;
import java.sql.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import xml.utils.SQLDateAdapter;

//this annotation indicates this class is going to be turned into an XML later
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="patient")
@XmlType(propOrder = {"name","gender","state","dob","pathology_id","clinicalhistory_id"})

public class Patient implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6669027231906981776L;
	
	//XmlTransient because it is not going to be stored, it will be ignored by XML
	//we do this because it only interests the Data Base
	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlElement
	private String gender;
	@XmlElement
	private String state;
	//XML recognizes text, integers and floats, but not dates, so they have to be transformed
	//we specify the class that will do this transformation
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date dob;
	@XmlElement
	private Integer pathology_id;
	@XmlElement
	private Integer clinicalhistory_id;
	
	
	
	
	
	public Patient(Integer id, String name, String gender, String state, Date dob, Integer pathology_id, Integer clinicalhistory_id) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.state = state;
		this.dob = dob;
		this.pathology_id=pathology_id;
		this.clinicalhistory_id=clinicalhistory_id;
		
	}
	
	public Patient() {
		super();
	}
	 
	
	public Patient(String name, String gender, String state, Date dob, Integer pathology_id,Integer clinicalhistory_id) {
		super();
		this.name = name;
		this.gender = gender;
		this.state = state;
		this.dob = dob;
		this.pathology_id = pathology_id;
		this.clinicalhistory_id=clinicalhistory_id;
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
	
	public Integer getPathology_id() {
		return pathology_id;
	}
	

	public void setPathology_id(Integer pathology_id) {
		this.pathology_id = pathology_id;
	}

	public Integer getClinicalhistory_id() {
		return clinicalhistory_id;
	}

	public void setClinicalhistory_id(Integer clinicalhistory_id) {
		this.clinicalhistory_id = clinicalhistory_id;
	}
	
	

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", gender=" + gender + ", state=" + state + ", dob=" + dob
				+ "]";
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
		Patient other = (Patient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
	
}
