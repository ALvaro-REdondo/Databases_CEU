package pojos.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Table(name = "roles")
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3619638295232976030L;
	
	@Id
	@GeneratedValue(generator="roles")
	@TableGenerator(name="roles", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="roles")
	private Integer id;
	private String role;
	@OneToMany(mappedBy = "role")
	private List<User> users;
	
	
	public Role() {
		super();
		this.users = new ArrayList<User>();
	}
	public Role(String roleName) {
		super();
		this.role = roleName;
		this.users = new ArrayList<User>();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
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
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + "]";
	}
	
}
