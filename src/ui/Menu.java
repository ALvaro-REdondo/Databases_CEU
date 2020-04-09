package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import pojos.*;

public class Menu {

	//for parsing dates
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	// BufferedReader for my whole code
	private static BufferedReader reader;
	
	public static void main(String[] args) throws Exception{
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
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		default:
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
			medicalPersonnelSubMenu2();//añadir menus para pathology
			break;
		case 3:
			medicalPersonnelSubMenu3();//acabar el menu de patient que pablo ya empezo.
			break;
		case 4:
			medicalPersonnelSubMenu4();//añadir menus para clinical history
			break;
		case 5:
			medicalPersonnelSubMenu5();//añadir menus para allergies
			break;
		case 6:
			medicalPersonnelSubMenu6();//añadir menus para symptoms
			break;
		case 7:
			medicalPersonnelSubMenu7();//añadir menus para medical personnel
			break;
		default:
			break;
		}
	}
	
	private static void medicalPersonnelSubMenu1() throws Exception{

	}
	
	private static void medicalPersonnelSubMenu2() throws Exception{
		
	}
	
	private static void medicalPersonnelSubMenu3() throws Exception{
		
		System.out.println("Select action");
		
		System.out.println("1. Add");
		System.out.println("2. Update");
		System.out.println("3. Check");
		System.out.println("Delete");
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1:
			addPatient();
			break;
		case 2:
			searchPatientById();
			//updatePatient();
			break;
		case 3:
			//falta el metodo
			break;
		default:
			break;
		}
	}
	
	private static void medicalPersonnelSubMenu4() throws Exception{
		
	}
	
	private static void medicalPersonnelSubMenu5() throws Exception{
		
	}
	
	private static void medicalPersonnelSubMenu6() throws Exception{
		
	}
	
	private static void medicalPersonnelSubMenu7() throws Exception{
		
	}
	
	private static void addPatient() throws Exception {
		
		System.out.println("So do it!");
		
		System.out.print("Name: ");
		String name = reader.readLine();
		
		System.out.println("Gender: ");
		String gender = reader.readLine();
		
		System.out.println("State: ");
		String state = reader.readLine();
		
		System.out.println("Date of birth (yyyy-MM-dd): ");
		String dob = reader.readLine();
		LocalDate dateOfBirth = LocalDate.parse(dob, formatter);
		
		System.out.println("Pathology id: ");
		int pathologyId = Integer.parseInt(reader.readLine());
		
		Patient patient = new Patient(name, gender, state, Date.valueOf(dateOfBirth), pathologyId);
		//ME HE QUEDADO CUANDO HA ACABADO EL VIDEO DEL 2020-03-13
		//hay que insert patient aun
		}
	
	private static void searchPatientById() throws Exception {
		//TODO
		}
	
	
	private static void medicalPersonnelBossMenu() throws Exception {
		
		System.out.println("Select area \n");
		
		System.out.println("1. Pathology \n");
		System.out.println("2. Medical Personnel \n");
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1:
			medicalPersonnelBossSubMenu1(); //Menu para pathology
			break;
		case 2: 
			medicalPersonnelBossSubMenu2(); //Menu para medical personnel
			break;
		default:
			break;
		}
	}
	
	private static void medicalPersonnelBossSubMenu1() throws Exception { //Pathology
		
		System.out.println("Select action");
		
		System.out.println("1. Check pathology");
		System.out.println("2. Search pathology by id");
		System.out.println("3. Search pathology by name");
		
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
	
	private static void medicalPersonnelBossSubMenu2() throws Exception { //Medical Personnel
		
		System.out.println("Select action");
		
		System.out.println("1. Add");
		System.out.println("2. Update");
		System.out.println("3. Check");
		System.out.println("4. Delete");
		
		int choice = Integer.parseInt(reader.readLine());
		
		switch(choice) {
		
		case 1:
			addMedicalPersonnel();
			break;
		case 2: 
			
			 System.out.println("Select an option");
			 
			 System.out.println("1. Search medical personnel by id");
			 
			 System.out.println("2. Search medical personnel by name");
			 
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
			
			System.out.println("Select an option");
			 
			 System.out.println("1. Search medical personnel by id");
			 
			 System.out.println("2. Search medical personnel by name");
			 
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
			
			System.out.println("Select an option");
			 
			 System.out.println("1. Search medical personnel by id");
			 
			 System.out.println("2. Search medical personnel by name");
			 
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
		
		System.out.println("So do it!");
		
		System.out.print("Name: ");
		String name = reader.readLine();
		
		System.out.print("Department: ");
		String department = reader.readLine();
		
		System.out.print("Position: ");
		String position = reader.readLine();
		
		System.out.print("Pathology id: ");
		int pathologyId = Integer.parseInt(reader.readLine());
		
		MedicalPersonnel medicalPersonnel = new MedicalPersonnel(name, department, position, pathologyId);
		
		//Aqui hemos anadido un medical Personnel.
		
		}
		
		
	}
	
