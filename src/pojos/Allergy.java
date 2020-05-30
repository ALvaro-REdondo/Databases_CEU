package pojos;

import java.io.Serializable;

import javax.xml.bind.annotation.*;
@XmlAccessorType(XmlAccessType.FIELD)
public class Allergy implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 230505548360698610L;
	
	private Integer id;
	private String allergy;
	private Integer degree;
	
	
	public Allergy(Integer id, String allergy, Integer degree) {
		super();
		this.id = id;
		this.allergy = allergy;
		this.degree = degree;
	}

	public Allergy(String allergy, Integer degree) {
		super();
		this.allergy = allergy;
		this.degree = degree;
	}

	public Allergy() {
		super();
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
	
	
	@Override
	public String toString() {
		return "Allergy [id=" + id + ", allergy=" + allergy + ", degree=" + degree + "]";
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
		Allergy other = (Allergy) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
