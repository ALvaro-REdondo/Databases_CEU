package pojos;

import java.io.Serializable;
import java.sql.Date;

public class Pathology implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1289491893153664835L;

	private Integer id;
	private String name;
	private Integer duration;
	private Date startDate;
	private Date endingDate;
	
	
	public Pathology(Integer id, String name, Integer duration, Date startDate, Date endingDate) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.startDate = startDate;
		this.endingDate = endingDate;
	}

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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
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

	@Override
	public String toString() {
		return "Pathology [id=" + id + ", name=" + name + ", duration=" + duration + ", startDate=" + startDate
				+ ", endingDate=" + endingDate + "]";
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
