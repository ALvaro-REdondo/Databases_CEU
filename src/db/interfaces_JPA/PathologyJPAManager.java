package db.interfaces_JPA;

import java.util.List;

import pojos_JPA.Pathology_JPA;

public interface PathologyJPAManager {
	
	public void connect();
	public void disconnect();
	public void createPathology(Pathology_JPA pathology);
	public Pathology_JPA searchPathologyById(int id);
	public List<Pathology_JPA> SearchPathologyByName(String name);
	public List<Pathology_JPA> getPathologies();
	public void deletePathology(Pathology_JPA pathology);
	public void updatePathology(Pathology_JPA pathology);

}
