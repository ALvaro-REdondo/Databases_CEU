package db.interfaces;


import pojos.Symptom;
import java.util.List;

public interface SymptomManager {
	
	
			public void add(Symptom symptom);
			public List<Symptom> searchSymptomByManifestation(String manifestation);
			public Symptom searchSymptomById(int symptomId);
			public List<Symptom> showSymptoms();
			public void give(int pathologyId, int symptomId); 
			
	}



