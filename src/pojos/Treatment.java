package pojos;

import java.io.Serializable;

import javax.xml.bind.annotation.*;

//this annotation indicates this class is going to be turned into an XML later
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "treatment")
@XmlType(propOrder = {"name", "medication", "description"})
public class Treatment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9161173435855282694L;
    
	//XmlTransient because it is not going to be stored, it will be ignored by XML
	//we do this because it only interests the Data Base
	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlElement
	private String medication;
	@XmlElement
	private String description;

	
	public Treatment(Integer id, String name, String medication, String description) {
		super();
		this.id = id;
		this.name = name;
		this.medication = medication;
		this.description = description;
	}
	
	public Treatment(String name, String medication, String description) {
		super();
		this.name = name;
		this.medication = medication;
		this.description = description;
	}

	public Treatment() {
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
		@Override
		public String toString() {
			return "Treatment [id=" + id + ", name=" + name + ", medication=" + medication + ", description="
					+ description + "]";
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
			Treatment other = (Treatment) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
		
}
