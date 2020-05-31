package pojos;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import xml.utils.SQLDateAdapter;

//this annotation indicates this class is going to be turned into an XML later
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "pathology")
@XmlType(propOrder = {"name", "startDate", "endingDate","treatmentId"})
public class Pathology implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1289491893153664835L;

	//XmlTransient because it is not going to be stored, it will be ignored by XML
	//we do this because it only interests the Data Base
	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String name;
	//XML recognizes text, integers and floats, but not dates, so they have to be transformed
	//we specify the class that will do this transformation
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date startDate;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date endingDate;
	@XmlElement
	private Integer treatmentId;
	@XmlElement (name = "symptom")
	@XmlElementWrapper(name="symptoms")
	private List<Symptom> symptoms;
	

	public Pathology(Integer id, String name, Date startDate, Date endingDate, Integer treatmentId) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endingDate = endingDate;
		this.treatmentId = treatmentId;
		this.symptoms = new ArrayList<Symptom>();
	}

	// he creado constructor con todas las variables

	public Pathology(String name, Date startDate, Date endingDate, Integer treatmentId) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endingDate = endingDate;
		this.treatmentId = treatmentId;
		this.symptoms = new ArrayList<Symptom>();
	}

	// el constructor no tiene id

	public Pathology(String name, Date startDate, Integer treatmentId) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.treatmentId = treatmentId;
		this.symptoms = new ArrayList<Symptom>();
	}

	// el constructor no tiene ending date porque es opcional. puede ser null. lo
	// mismo ocurre con duration.
	
	

	public Pathology() {

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

	public int getTreatmentId() {
		return treatmentId;
	}

	public void setEndingDate(Integer treatmentId) {
		this.treatmentId = treatmentId;
	}
	
	

	public List<Symptom> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}

	public void setTreatmentId(Integer treatmentId) {
		this.treatmentId = treatmentId;
	}

	

	@Override
	public String toString() {
		return "Pathology [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endingDate=" + endingDate
				+ ", treatmentId=" + treatmentId + ", symptoms=" + symptoms + "]";
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
		Pathology other = (Pathology) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
