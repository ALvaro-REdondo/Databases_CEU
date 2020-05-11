package db.interfaces;

import java.util.List;

import pojos.*;

public interface PathologyManager {
	
	public void add(Pathology pathology);
	public void update(Pathology pathology);
	public void delete(Pathology pathology);
	public List<Pathology> searchPathologyById(Integer id);
	public List<Pathology> searchPathologyByName(String name);
	public List<Pathology> showPathologies();
	public Pathology getPathology(int pathologyId);
}
