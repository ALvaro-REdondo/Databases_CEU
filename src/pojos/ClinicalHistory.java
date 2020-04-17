package pojos;

import java.io.Serializable;
import java.sql.Date;

public class ClinicalHistory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3334006632519187423L;
	
	private Integer id;
	private Date doe;
	private Date dod;
	private String bloodType;
	private String extraInfo;
	
	
	
	public ClinicalHistory(Integer id, Date doe, Date dod, String bloodType, String extraInfo) {
		super();
		this.id = id;
		this.doe = doe;
		this.dod = dod;
		this.bloodType = bloodType;
		this.extraInfo = extraInfo;
	}

	public ClinicalHistory(Date doe, Date dod, String bloodType, String extraInfo) {
		super();
		this.doe = doe;
		this.dod = dod;
		this.bloodType = bloodType;
		this.extraInfo = extraInfo;
	}

	public ClinicalHistory() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDoe() {
		return doe;
	}

	public void setDoe(Date doe) {
		this.doe = doe;
	}

	public Date getDod() {
		return dod;
	}

	public void setDod(Date dod) {
		this.dod = dod;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	@Override
	public String toString() {
		return "ClinicalHistory [id=" + id + ", doe=" + doe + ", dod=" + dod + ", bloodType=" + bloodType
				+ ", extraInfo=" + extraInfo + "]";
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
		ClinicalHistory other = (ClinicalHistory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
