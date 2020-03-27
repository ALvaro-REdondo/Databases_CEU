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
		System.out.println("Hi!");
		System.out.println("What is your role?");
		System.out.println("1. Treatment creator");
		System.out.println("2. Medical personnel");
		int choice = Integer.parseInt(reader.readLine());
		switch(choice) {
		case 1:
			//TODO
			break;
		case 2:
			medicalPersonnelMenu();
			break;
		default:
			break;
		}
	}

	private static void medicalPersonnelMenu() throws Exception{
		System.out.println("Select area");
		System.out.println("1. Patient");
		System.out.println("2. Pathology");
		System.out.println("3. Treatment");
		int choice = Integer.parseInt(reader.readLine());
		switch(choice) {
		case 1:
			medicalPersonnelSubMenu1();
			break;
		case 2:
			//TODO
			break;
		case 3:
			//TODO
			break;
		default:
			break;
		}
	}
	
	private static void medicalPersonnelSubMenu1() throws Exception{

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
			//TODO
			break;
		default:
			break;
		}
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
}
