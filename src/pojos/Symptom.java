package pojos;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

//this annotation indicates this class is going to be turned into an XML later
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="symptom")
@XmlType(propOrder = {"name","manifestation"})
public class Symptom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2054544053127653172L;
	
	//XmlTransient because it is not going to be stored, it will be ignored by XML
	//we do this because it only interests the Data Base
	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String manifestation;
	
	
	public Symptom(Integer id, String manifestation) {
		super();
		this.id = id;
		this.manifestation = manifestation;
	}
	
	public Symptom(String manifestation) {
		super();
		this.manifestation=manifestation;
	}


	public Symptom() {
		super();
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
	public String toString() {
		return "Symptom [id=" + id + ", manifestation=" + manifestation + "]";
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
		Symptom other = (Symptom) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
