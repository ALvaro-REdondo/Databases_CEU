package db.interfaces;


import pojos.Symptom;
import java.util.List;

public interface SymptomManager {
	
	
			public void add(Symptom symptom);
			public List<Symptom> searchSymptomById(Integer id);
			public List<Symptom> searchASymptomByManifestation(String manifestation);
			public Symptom getSymptom(int symptomId);
			public List<Symptom> showSymptoms();

	}



