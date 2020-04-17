package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import pojos.*;
import db.interfaces.*;
import db.sqlite.*;

public class Menu {
	/*alba:
	//DB Managers
	private static DBManager dbManager;
	private static PatientManager patientManager;
	private static SymptomManager symptomManager;
	*/
	//hasta aqui
	
	//for parsing dates
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	// BufferedReader for my whole code
	private static BufferedReader reader;
	
	public static void main(String[] args) throws Exception{
		
		// esto puesto por alba:
		
		/*dbManager = new SQLiteManager();
		patientManager= dbManager.getPatienManager();
		symptomManager = dbManager.getSymptomManager();
		dbManager.connect();*/
		
		//hasta aquí
		
		
		reader = new BufferedReader(new InputStreamReader(System.in));
		//Print welcome screen
		System.out.println("Hi! \n");
		System.out.println("What is your role? \n");
		System.out.println("1. Treatment creator \n");
		System.out.println("2. Medical personnel \n");
		System.out.println("3. Medical personnel boss \n");
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1:
			treatmentCreatorMenu();//hay que crearlo
			break;
		case 2:
			medicalPersonnelMenu();//hay que acabarlo
			break;
		case 3:
			medicalPersonnelBossMenu(); 
			break;			
		default:
			break;
		}
	}
	
	private static void treatmentCreatorMenu() throws Exception{
		
		System.out.println("Select area \n");
		
		System.out.println("1. Treatment \n");
		System.out.println("2. Pathology \n");
		System.out.println("3. Clinical History \n");
		System.out.println("4. Patient \n");
		System.out.println("5. Allergies \n");
		System.out.println("6. Symptoms \n");
		System.out.println("7. Medical Personnel \n");
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1:
			treatmentCreatorSubMenu1();
			break;
		case 2:
			treatmentCreatorSubMenu2Pathology();
			break;
		case 3:
			treatmentCreatorSubMenu3();
			break;
		case 4:
			treatmentCreatorSubMenu4Patient();
			break;
		case 5:
			treatmentCreatorSubMenu5();
			break;
		case 6:
			treatmentCreatorSubMenu6Symptom();
			break;
		case 7:
			treatmentCreatorSubMenu7MedicalPersonnel();
			break;
		default:
			break;
		}
	}
	
	private static void treatmentCreatorSubMenu1() throws Exception{
		System.out.println("Select action \n");
		
		System.out.println("1. Add a treatment \n");
		System.out.println("2. Update a treatment \n");
		System.out.println("3. Check a  treatment \n");
		System.out.println("4. Delete a treatment \n");
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1:
			addTreatment();//completar
			break;
		case 2: 
			updateTreatment();//crear
			break;
		case 3: 
			checkTreatment();//crear
			break;
		case 4:
			deleteTreatment();//crear
			break;
		default:
			break;
			
		}
		

	}
	
	private static void treatmentCreatorSubMenu2Pathology() throws Exception {
		
		System.out.println("2. Pathology \n");
		
		/*System.out.println("Select an option \n");
		
		System.out.println("1. Search by Id \n");
		System.out.println("2. Search by Name \n");*/
		
		searchMenu();
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1:

			searchPathologyById();// este metodo ya esta creado mas abajo
			break;
			
		case 2: 
			
			searchPathologyByName();// este metodo ya esta creado mas abajo
			break;
			
		default:
			
			break;
			
		}
		
	}
	
	private static void treatmentCreatorSubMenu3() throws Exception {
		
	}
	
	private static void treatmentCreatorSubMenu4Patient() throws Exception {
		
		System.out.println("4. Patient \n");
		
		searchMenu();
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1: 
			searchPatientById();
			break;
			
		case 2:
			
			searchPatientByName();
			break;
			
		default:
			
			break;
		
		}
		
		
	}
	
	private static void treatmentCreatorSubMenu5() throws Exception {
		
	}
	
	private static void treatmentCreatorSubMenu6Symptom() throws Exception {
		System.out.println("6. Symptom \n");
		
		searchMenu();
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1: 
			searchSymptomById();
			break;
			
		case 2:
			
			searchSymptomByManifestation();
			break;
			
		default:
			
			break;
		
		}
		
	}

	private static void treatmentCreatorSubMenu7MedicalPersonnel() throws Exception {
		
		System.out.println("7. Medical Personnel \n");
		
		searchMenu();
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1:
			
			searchMedicalPersonnelById(); //metodo ya creado mas abajo
			break;
		
		case 2: 
			
			searchMedicalPersonnelByName(); //metodo ya creado mas abajo
			break;
		
		}
		
		
	}

	private static void medicalPersonnelMenu() throws Exception{
		
		System.out.println("Select area \n");
		
		System.out.println("1. Treatment \n");
		System.out.println("2. Pathology \n");
		System.out.println("3. Patient \n");
		System.out.println("4. Clinical History \n");
		System.out.println("5. Allergies \n");
		System.out.println("6. Symptoms \n");
		System.out.println("7. Medical Personnel \n");
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1:
			medicalPersonnelSubMenu1();//añadir menus para treatment
			break;
		case 2:
			medicalPersonnelSubMenu2Pathology();
			break;
		case 3:
			medicalPersonnelSubMenu3Patient();
			break;
		case 4:
			medicalPersonnelSubMenu4();//añadir menus para clinical history
			break;
		case 5:
			medicalPersonnelSubMenu5();//añadir menus para allergies
			break;
		case 6:
			medicalPersonnelSubMenu6Symptom();
			break;
		case 7:
			medicalPersonnelSubMenu7MedicalPersonnel();
			break;
		default:
			break;
		}
	}
	
	private static void medicalPersonnelSubMenu1() throws Exception{

		System.out.println("Select action \n");
		
		System.out.println("1.Check \n");
	}
	
	//creo el submenu para pathology
	
	private static void medicalPersonnelSubMenu2Pathology() throws Exception{
		
		System.out.println("2. Pathology \n"); 
		System.out.println("Select an option \n");
		
		System.out.println("1. Add \n");
		System.out.println("2. Update \n");
		System.out.println("3. Check \n");
		System.out.println("4. Delete \n");
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1:
			
			addPathology();
			break;
			
		case 2:
			
			updatePathology();
			break;
			
		case 3: 
			
			checkPathology();
			break;
			
		case 4: 
			
			deletePathology();
			break;
		
		}
	}

	private static void updatePathology() throws Exception {
		
	}
	
	private static void deletePathology() throws Exception {
		
	}

	
	private static void medicalPersonnelSubMenu3Patient() throws Exception{
		
		System.out.println("Select action \n");
		
		System.out.println("1. Add \n");
		System.out.println("2. Update \n");
		System.out.println("3. Check \n");
		System.out.println("4. Delete \n");
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1:
			addPatient();
			break;
		case 2:
			searchPatientById();
			//updatePatient();-> TODO
			break;
		case 3:
			//checkPatient();->TODO
			break;
			
		case 4:
			//deletePatient();->TODO
			break;
		default:
			break;
		}
	}
	
	private static void medicalPersonnelSubMenu4() throws Exception{
		
	}
	
	private static void medicalPersonnelSubMenu5() throws Exception{
		
	}
	
	private static void medicalPersonnelSubMenu6Symptom() throws Exception{
		
		System.out.println("6. Symptom \n");
		
		System.out.println("Select an action: \n");
		System.out.println("1. Add \n");
		System.out.println("1. CHeck \n");
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1: 
			addSymptom();
			break;
			
		case 2:
			
			// checkPatient->TODO
			break;
			
		default:
			
			break;
		
		}
		
	}
	
	private static void medicalPersonnelSubMenu7MedicalPersonnel() throws Exception{
		
		System.out.println("7. Medical Personnel");
		
		searchMenu();
		
		int choice = Integer.parseInt(reader.readLine()); 
		
		switch(choice) {
		
		case 1:
			
			searchMedicalPersonnelById();
			break;
			
		case 2:
			
			searchMedicalPersonnelByName();
			break;
		
		}
		
	}
	
	private static void addPatient() throws Exception {
		
		System.out.println("So do it! \n");
		
		System.out.print("Name: \n");
		String name = reader.readLine();
		
		System.out.println("Gender: \n");
		String gender = reader.readLine();
		
		System.out.println("State: \n");
		String state = reader.readLine();
		
		System.out.println("Date of birth (yyyy-MM-dd): \n");
		String dob = reader.readLine();
		LocalDate dateOfBirth = LocalDate.parse(dob, formatter);
		
		System.out.println("Pathology id: \n");
		int pathologyId = Integer.parseInt(reader.readLine());
		
		System.out.println("Clinical History id: \n");
		int clinicalHistoryId = Integer.parseInt(reader.readLine());
		
		Patient patient = new Patient(name, gender, state, Date.valueOf(dateOfBirth), pathologyId, clinicalHistoryId);
		
		// Patient added
		
		// para insertarlo:
		//patientManager.add(patient);
		
		//patient inserted
		
	}
	
	private static void searchPatientById() throws Exception {
		System.out.print("Insert the id: ");
		int id=Integer.parseInt(reader.readLine());
		
		/*para buscar en la base de datos:
		List<Patient> patients= patientManager.searchPatientById(id);
		
		// para mostrarlos por pantalla:
		for (Patient patient : patients) {
			System.out.println(patient);
		}
		*/
		
		
	}
	
	private static void searchPatientByName() throws Exception {
		
		System.out.print("Insert the name: ");
		String name=reader.readLine();
		
		/*para buscar en la base de datos:
		List<Patient> patients= patientManager.searchPatientByName(name);
		
		//para mistrar por pantalla
		for (Patient patient : patients) {
			System.out.println(patient);
		}
		*/
		
	}
	
	private static void addSymptom() throws Exception{
		
		System.out.println("So do it!");
		
		System.out.print("Manifestation: ");
		String manifestation =reader.readLine();
		
		Symptom symptom = new Symptom(manifestation);
		// symptom added
		
		/*para insertar en base de datos:
		symptomManager.add(symptom);
		// symptom inserted*/
	}
	
	private static void searchSymptomById () throws Exception{
		System.out.print("Insert the id: ");
		int id=Integer.parseInt(reader.readLine());
		
		/*para buscar en la base de datos:
		List<Symptom> symptoms= symptomManager.searchSymptomById(id);
		
		// para mostrar por pantalla:
		for (Symptom symptom : symptoms) {
			System.out.println(symptom);
		}
		*/
	}
	
	private static void searchSymptomByManifestation() throws Exception{
		System.out.print("Insert the manifestation: ");
		String manifestation =reader.readLine();
		
		/* para buscar en la base de datos:
		List<Symptom> symptoms= symptomManager.searchASymptomByManifestation(manifestation);
		
		//para mostrar por pantalla:
		for (Symptom symptom : symptoms) {
			System.out.println(symptom); 
		}
		*/
	}
	
	private static void addPathology() throws Exception{
		
		System.out.println("So do it! \n");
		
		System.out.print("Name: \n");
		String name = reader.readLine();
		
		System.out.print("Duration: \n");
		int duration = Integer.parseInt(reader.readLine());
		
		System.out.print("Start Date (yyyy-MM-dd): \n");
		String startdate = reader.readLine();
		LocalDate startDate = LocalDate.parse(startdate, formatter);
		
		System.out.print("Ending Date (yyyy-MM-dd): \n");
		String endingdate = reader.readLine();
		LocalDate endingDate = LocalDate.parse(endingdate, formatter);
		
		System.out.print("Treatment id: \n");
		int treatmentId = Integer.parseInt(reader.readLine());
		
		Pathology pathology = new Pathology(name, duration, Date.valueOf(startDate), Date.valueOf(endingDate), treatmentId);
		
		//Aqui hemos creado una pathology y la añadimos.
		
		}
	
	
	private static void medicalPersonnelBossMenu() throws Exception {
		
		System.out.println("Select area \n");
		
		System.out.println("1. Pathology \n");
		System.out.println("2. Medical Personnel \n");
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1:
			medicalPersonnelBossSubMenu1Pathology(); //Menu para pathology
			break;
		case 2: 
			medicalPersonnelBossSubMenu2MedicalPersonnel(); //Menu para medical personnel
			break;
		default:
			break;
		}
	}
	
	private static void medicalPersonnelBossSubMenu1Pathology() throws Exception { //Pathology
		
		System.out.println("Select action \n");
		
		System.out.println("1. Check pathology \n");
		System.out.println("2. Search pathology by id \n");
		System.out.println("3. Search pathology by name \n");
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1:
			checkPathology();
			break;
		case 2: 
			searchPathologyById();
			break;
		case 3: 
			searchPathologyByName();
			break;
		default:
			break;
		}
		
	}
	
	
	
	private static void checkPathology() throws Exception {
		
	}
	
	private static void searchPathologyById() throws Exception {
		
	}
	
	private static void searchPathologyByName() throws Exception {
		
	}
	
	private static void medicalPersonnelBossSubMenu2MedicalPersonnel() throws Exception { //Medical Personnel
		
		System.out.println("Select action \n");
		
		System.out.println("1. Add    \n");
		System.out.println("2. Update \n");
		System.out.println("3. Check  \n");
		System.out.println("4. Delete \n");
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1:
			
			System.out.println("1. Add Medical Personnel \n");
			
			addMedicalPersonnel();
			
			break;
			
		case 2: 
			
			System.out.println("2. Update Medical Personnel \n");
			
			 /*System.out.println("Select an option \n");
			 
			 System.out.println("1. Search medical personnel by id \n");
			 
			 System.out.println("2. Search medical personnel by name \n");*/
			
			searchMenu();
			 
			 int choice2 = Integer.parseInt(reader.readLine());
			 
			 switch(choice2) {
			 
			 	case 1: 
			 		searchMedicalPersonnelById();
			 		break;
			 	case 2:
			 		searchMedicalPersonnelByName();
			 		break;
			 	default: 
			 		break;
			 }			 
			break;
			
		
		case 3: 
			
			/*System.out.println("Select an option \n");
			 
			 System.out.println("1. Search medical personnel by id \n");
			 
			 System.out.println("2. Search medical personnel by name \n");*/
			
			searchMenu();
			 
			 int choice3 = Integer.parseInt(reader.readLine());
			 
			 switch(choice3) {
			 
			 	case 1: 
			 		searchMedicalPersonnelById();
			 		break;
			 	case 2:
			 		searchMedicalPersonnelByName();
			 		break;
			 	default: 
			 		break;
			 }			
			break;
		case 4:
			
			System.out.println("4. Medical Personnel \n");
			
			/*System.out.println("Select an option \n");
			 
			 System.out.println("1. Search medical personnel by id \n");
			 
			 System.out.println("2. Search medical personnel by name \n");*/
			
			searchMenu();
			 
			 int choice4 = Integer.parseInt(reader.readLine());
			 
			 switch(choice4) {
			 
			 	case 1: 
			 		searchMedicalPersonnelById();
			 		break;
			 	case 2:
			 		searchMedicalPersonnelByName();
			 		break;
			 	default: 
			 		break;
			 }			
			break;
		default:
			break;
		}
		
	}
	
	private static void searchMedicalPersonnelById() throws Exception{
		
		//Creo que deberia de devolver el id para luego buscar el medical personnel y poder modificarlo, etc.
		
	}
	
	private static void searchMedicalPersonnelByName() throws Exception {
		
		//Creo que deberia de volver el id para luego buscar el medical personnel y poder modificarlo, etc.
		
	}
	
	private static void addMedicalPersonnel() throws Exception{
		
		System.out.println("So do it! \n");
		
		System.out.print("Name: \n");
		String name = reader.readLine();
		
		System.out.print("Department: \n");
		String department = reader.readLine();
		
		System.out.print("Position: \n");
		String position = reader.readLine();
		
		System.out.print("Pathology id: \n");
		int pathologyId = Integer.parseInt(reader.readLine());
		
		MedicalPersonnel medicalPersonnel = new MedicalPersonnel(name, department, position, pathologyId);
		
		//Aqui hemos anadido un medical Personnel.
		
		}
	
	private static void addTreatment() throws Exception{
		System.out.println("So do it! \n");
		
		System.out.print("Name: \n");
		String name = reader.readLine();
		
		System.out.print("Medication: \n");
		String medication = reader.readLine();
		
		System.out.print("Description: \n");
		String description = reader.readLine();
		
		Treatment treatment = new Treatment(name,medication,description);
		//añadimos un treatment, pero esta incompleto :c. Que triste
	}
	
	private static void updateTreatment() throws Exception{
		
	}
	
	private static void checkTreatment() throws Exception{
		
	}
	
	private static void deleteTreatment() throws Exception{
		
	}
	
	private static void searchMenu() throws Exception{
		System.out.println("Select an option \n");
		
		System.out.println("1. Search by id \n");
		System.out.println("2. Search by name \n");
		
	}
		
	//METHODS FOR CLINICAL HISTORY
	
	private static void addClinicalHistory() throws Exception{
		System.out.println("Great, you seem nice, you can do it: \n");
		
		System.out.println("Date of entry (yyyy-MM-dd): \n");
		String dateOfEntry = reader.readLine();
		LocalDate doe = LocalDate.parse(dateOfEntry, formatter);
		
		System.out.println("Date of discharge (yyyy-MM-dd): \n");
		String dateOfDischarge = reader.readLine();
		LocalDate dod = LocalDate.parse(dateOfDischarge, formatter);
		
		System.out.println("Blood type: \n");
		String bloodType = reader.readLine();
		
		System.out.println("Extra information: \n");
		String extraInfo = reader.readLine();
		
		ClinicalHistory clinicalHistory = new ClinicalHistory(Date.valueOf(doe), Date.valueOf(dod), bloodType, extraInfo);
		
	}

	//METHOS FOR ALLERGY
	
	private static void addAllergy() throws Exception{
		System.out.println("Great, you seem nice, you can do it: \n");

		System.out.println("Allergy: \n");
		String allergyName = reader.readLine();
		
		System.out.println("Degree: \n");
		int degree = Integer.parseInt(reader.readLine());
		
		Allergy allergy = new Allergy(allergyName, degree);
		
	}
}
	
