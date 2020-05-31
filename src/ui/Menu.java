package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.xml.bind.*;

//import org.eclipse.persistence.jaxb.JAXBContext;

import db.interfaces.AllergyManager;
import db.interfaces.ClinicalHistoryManager;
import db.interfaces.DBManager;
import db.interfaces.MedicalPersonnelManager;
import db.interfaces.PathologyManager;
import db.interfaces.PatientManager;
import db.interfaces.SymptomManager;
import db.interfaces.TreatmentManager;
import db.interfaces_JPA.UserManager;
import db.jpa.JPAUserManager;
import db.sqlite.SQLiteManager;
import pojos.Allergy;
import pojos.ClinicalHistory;
import pojos.MedicalPersonnel;
import pojos.Pathology;
import pojos.Patient;
import pojos.Symptom;
import pojos.Treatment;
import pojos_users.Role;
import pojos_users.User;

//this is our menu:)
public class Menu {
	// DB Managers
	private static DBManager dbManager;
	private static PatientManager patientManager;
	private static SymptomManager symptomManager;
	private static PathologyManager pathologyManager;
	private static MedicalPersonnelManager medicalPersonnelManager;
	private static TreatmentManager treatmentManager;
	private static AllergyManager allergyManager;
	private static ClinicalHistoryManager clinicalHistoryManager;
	private static UserManager userManager;

	// for parsing dates
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	// BufferedReader for my whole code
	private static BufferedReader reader;

	public static void main(String[] args) throws Exception {

		dbManager = new SQLiteManager();
		dbManager.connect();
		patientManager = dbManager.getPatientManager();
		symptomManager = dbManager.getSymptomManager();
		pathologyManager = dbManager.getPathologyManager();
		medicalPersonnelManager = dbManager.getMedicalPersonnelManager();
		allergyManager = dbManager.getAllergyManager();
		clinicalHistoryManager = dbManager.getClinicalHistoryManager();
		treatmentManager = dbManager.getTreatmentManager();
		userManager = new JPAUserManager();
		userManager.connect();
		dbManager.createTables();

		reader = new BufferedReader(new InputStreamReader(System.in));
		// Print welcome screen
		int menuKey = 0;

		System.out.println("Hi! \n");
		System.out.println("What do you want to do? \n");
		System.out.println("1. Create a new Role \n");
		System.out.println("2. Create a new User \n");
		System.out.println("3. Login \n");
		System.out.println("4. Exit the menu \n");

		int choice = Integer.parseInt(reader.readLine());

		switch (choice) {

		case 1:// Create a new Role
			newRole();
			break;
		case 2:// Create a new User
			newUser();
			break;
		case 3:// Login
			login();
			break;
		case 4:// Exit
			System.out.println("See you soon! \n");
			menuKey = 1;
			dbManager.disconnect();
			userManager.disconnect();
			break;
		default:
			break;
		}
	}

	private static void newRole() throws Exception {
		System.out.println("Please, type the new role information \n");
		System.out.println("Role name: \n");
		String roleName = reader.readLine();
		Role role = new Role(roleName);
		System.out.println(role);
		userManager.createRole(role);
	}

	private static void newUser() throws Exception {
		System.out.println("Please, type the new user information \n");
		System.out.println("Username: \n");
		String username = reader.readLine();
		System.out.println("Password: \n");
		String password = reader.readLine();
		// Create the password's hash
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] hash = md.digest();
		// Show all the roles and let the user choose one
		List<Role> roles = userManager.getRoles();
		for (Role role : roles) {
			System.out.println(role);
		}
		System.out.println("Type the chosen role id: \n");
		int roleId = Integer.parseInt(reader.readLine());
		// get the chosen role from the database
		Role chosenRole = userManager.getRole(roleId);
		// Create the user and store it in the database
		User user = new User(username, hash, chosenRole);
		userManager.createUser(user);
	}

	private static void login() throws Exception {
		System.out.println("Please input your credentials: \n");
		System.out.println("Username: \n");
		String username = reader.readLine();
		System.out.println("Password: \n");
		String password = reader.readLine();
		User user = userManager.checkPassword(username, password);
		// We need to check if the username and password match with a user that is
		// stored in the database
		if (user == null) {// sera null si no hay match entre el username y la password con un user ya
							// almacenado
			System.out.println("Wrong credentials, please try again \n");
		} else if (user.getRole().getRole().equalsIgnoreCase("treatment creator")) {
			System.out.println("Welcome, treatment creator \n");
			treatmentCreatorMenu();
		} else if (user.getRole().getRole().equalsIgnoreCase("medical personnel")) {
			System.out.println("Welcome, medical personnel \n");
			medicalPersonnelMenu();
		} else if (user.getRole().getRole().equalsIgnoreCase("medical personnel boss")) {
			System.out.println("Welcome, medical personnel boss \n");
			medicalPersonnelBossMenu();
		} else {
			System.out.println("Invalid role. \n");
		}
	}

	private static void treatmentCreatorMenu() throws Exception {
		int exitTreatmentCreatorMenu = 0;

		while (exitTreatmentCreatorMenu == 0) {
			System.out.println("Select area \n");
			System.out.println("1. Treatment \n");
			System.out.println("2. Pathology \n");
			System.out.println("3. Clinical History \n");
			System.out.println("4. Patient \n");
			System.out.println("5. Allergies \n");
			System.out.println("6. Symptoms \n");
			System.out.println("7. Medical Personnel \n");
			System.out.println("8. Exit the menu \n");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:
				treatmentCreatorSubMenu1Treatment();
				break;
			case 2:
				treatmentCreatorSubMenu2Pathology();
				break;
			case 3:
				treatmentCreatorSubMenu3ClinicalHistory();
				break;
			case 4:
				treatmentCreatorSubMenu4Patient();
				break;
			case 5:
				treatmentCreatorSubMenu5Allergy();
				break;
			case 6:
				treatmentCreatorSubMenu6Symptom();
				break;
			case 7:
				treatmentCreatorSubMenu7MedicalPersonnel();
				break;
			case 8:
				System.out.println("See you soon, Treatment Creator! \n");
				exitTreatmentCreatorMenu = 1;
				break;
			default:
				break;
			}
		}
	}

	private static void treatmentCreatorSubMenu1Treatment() throws Exception {
		int exitSubmenu1Treatment = 0;
		while (exitSubmenu1Treatment == 0) {
			System.out.println("Select action \n");
			System.out.println("1. Add a treatment \n");
			System.out.println("2. Update a treatment \n");
			System.out.println("3. Delete a  treatment \n");
			System.out.println("4. Check a treatment \n");
			System.out.println("5. Exit the Submenu \n");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:
				addTreatment();
				break;
			case 2:
				searchMenu();
				int choice2 = Integer.parseInt(reader.readLine());
				while (choice2 != 1 || choice2 != 2) {
					System.out.println("Select a valid option, please");
				}
				if (choice2 == 1) {
					System.out.println("Type the id of the treatment you want to update \n");
					int choice3 = Integer.parseInt(reader.readLine());
					updateTreatment(choice3);
				}
				if (choice2 == 2) {
					searchTreatmentByName();
					System.out.println("Type the id of the treatment you want to update \n");
					int choice3 = Integer.parseInt(reader.readLine());
					updateTreatment(choice3);
				}
				break;
			case 3:
				searchMenu();
				int choice3 = Integer.parseInt(reader.readLine());
				while (choice3 != 1 && choice3 != 2) {
					System.out.println("Select a valid option, please");
					choice3 = Integer.parseInt(reader.readLine());
				}

				if (choice3 == 1) {
					System.out.println("Type the id of the treatment you want to delete \n");
					choice3 = Integer.parseInt(reader.readLine());
					deleteTreatment(choice3);
				}
				if (choice3 == 2) {
					searchTreatmentByName();
					System.out.println("Type the id of the treatment you want to delete \n");
					choice3 = Integer.parseInt(reader.readLine());
					deleteTreatment(choice3);
				}

				break;
			case 4:
				searchMenu();
				int choice4 = Integer.parseInt(reader.readLine());
				while (choice4 != 1 && choice4 != 2) {
					System.out.println("Select a valid option, please");
					choice4 = Integer.parseInt(reader.readLine());
				}
				if (choice4 == 1) {
					searchTreatmentById();
				}
				if (choice4 == 2) {
					searchTreatmentByName();
				}
				break;
			case 5:
				System.out.println("Treatment operations ended \n");
				exitSubmenu1Treatment = 1;
			default:
				break;

			}

		}
	}

	private static void treatmentCreatorSubMenu2Pathology() throws Exception {

		System.out.println("2. Pathology \n");

		int exitSubmenu2Pathology = 0;
		while (exitSubmenu2Pathology == 0) {

			searchMenu();
			System.out.println("3. Exit");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:

				System.out.println("1. Search Pathology by Id \n");

				searchPathologyById();
				break;

			case 2:

				System.out.println("2. Search Pathology by Name \n");

				searchPathologyByName();
				break;

			case 4:

				System.out.println("3. Exit");
				exitSubmenu2Pathology = 1;

				break;

			default:

				break;
			}
		}
	}

	private static void treatmentCreatorSubMenu3ClinicalHistory() throws Exception {
		int exitSubmenu3ClinicalHistory = 0;
		while (exitSubmenu3ClinicalHistory == 0) {
			System.out.println("1.Check clinical histories \n");
			System.out.println("2. Exit \n");
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				searchClinicalHistoryById();
				break;
			case 2:
				System.out.println("Clinical History operations terminated \n");
				exitSubmenu3ClinicalHistory = 1;
				break;
			default:
				break;
			}
		}
	}

	private static void treatmentCreatorSubMenu4Patient() throws Exception {
		int exitSubmenu4Patient = 0;
		while (exitSubmenu4Patient == 0) {
			System.out.println("4. Patient \n");

			searchMenu();
			System.out.println("3. Search by pathology id");
			System.out.println("4. Exit");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:
				searchPatientById();
				break;

			case 2:

				searchPatientByName();
				break;

			case 3:

				searchPatientByPathologyId();
				break;
			case 4:
				System.out.println("Patient operations terminated \n");
				exitSubmenu4Patient = 1;
				break;
			default:

				break;

			}
		}
	}

	private static void treatmentCreatorSubMenu5Allergy() throws Exception {
		int exitSubmenu5Allergy = 0;
		while (exitSubmenu5Allergy == 0) {

			System.out.println("Select action \n");

			System.out.println("1. Search \n");
			System.out.println("2. Check \n");
			System.out.println("3. Exit \n");

			searchMenu();
			System.out.println("3. Exit");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:
				searchMenu();
				int choiceSearch = Integer.parseInt(reader.readLine());
				while (choiceSearch != 1 || choiceSearch != 3) {
					System.out.println("Select a valid option, please");
				}
				if (choiceSearch == 1) {
					searchAllergyById();
				}
				if (choiceSearch == 2) {
					searchAllergyByName();
				}
				searchAllergyById();

				break;

			case 2:
				searchMenu();
				int choice4 = Integer.parseInt(reader.readLine());
				while (choice4 != 1 && choice4 != 2) {
					System.out.println("Select a valid option, please");
					choice4 = Integer.parseInt(reader.readLine());
				}
				if (choice4 == 1) {
					searchAllergyById();
				}
				if (choice4 == 2) {
					searchAllergyByName();
				}
				searchAllergyByName();
				break;
			case 3:
				System.out.println("Allergy operations terminated \n");
				exitSubmenu5Allergy = 1;
				break;
			default:
				break;
			}
		}
	}

	private static void treatmentCreatorSubMenu6Symptom() throws Exception {
		int exitSubmenu6Symptom = 0;
		while (exitSubmenu6Symptom == 0) {
			System.out.println("6. Symptom \n");
			searchMenu();
			System.out.println("3.Exit \n");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:
				searchSymptomById();
				break;

			case 2:

				searchSymptomByManifestation();
				break;

			case 3:
				System.out.println("Symptom operations terminated \n");
				exitSubmenu6Symptom = 1;
				break;
			default:

				break;

			}

		}
	}

	private static void treatmentCreatorSubMenu7MedicalPersonnel() throws Exception {

		System.out.println("7. Medical Personnel \n");

		int exitSubmenu1MedicalPersonnel = 0;
		while (exitSubmenu1MedicalPersonnel == 0) {

			searchMenu();
			System.out.println("3. Search by Pathology Id \n");
			System.out.println("4. Generate XML");
			System.out.println("5. Admit Medical Personnel through XML");
			System.out.println("6. Exit \n");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:

				System.out.println("1. Search Medical Personnel by Id \n");
				searchMedicalPersonnelById();
				break;

			case 2:

				System.out.println("2. Search Medical Personnel by Name \n");
				searchMedicalPersonnelByName();
				break;

			case 3:

				System.out.println("3. Search Medical Personnel by Pathology Id \n");
				searchMedicalPersonnelByPathologyId();
				break;

			case 4:

				System.out.println("4. Generate XML");

				System.out.println("Write Medical Personnel Id: ");
				int medicalPersonnelId = Integer.parseInt(reader.readLine());

				generateXMLMedicalPersonnel(medicalPersonnelId);

			case 5:
				admitMedicalPersonnelXML();
				break;
			case 6:
				System.out.println("5. Medical Personnel operations terminated \n");
				exitSubmenu1MedicalPersonnel = 1;
				break;
			default:
				break;

			}
		}

	}

	public static void admitMedicalPersonnelXML() throws Exception {
		// Create JAXB Context
		javax.xml.bind.JAXBContext context = JAXBContext.newInstance(MedicalPersonnel.class);
		// Get the unmarshaller
		Unmarshaller unmarshal = context.createUnmarshaller();
		// Unmarshall the dog from a file
		System.out.println("Type the file name for the XML document (expected in the xmls folder):");
		String path = System.getProperty("user.dir");
		String fileName = reader.readLine();
		File file = new File(path + "/src/xmls/" + fileName + ".xml");
		MedicalPersonnel medicalPersonnel = (MedicalPersonnel) unmarshal.unmarshal(file);
		// Print the Medical Personnel
		System.out.println("Added to the database: " + medicalPersonnel);
		medicalPersonnelManager.add(medicalPersonnel);

	}

	public static void generateXMLMedicalPersonnel(int medicalPersonnelId) throws Exception {

		MedicalPersonnel medicalPersonnel = medicalPersonnelManager.searchMedicalPersonnelById(medicalPersonnelId);
		// Create JAXB Context
		JAXBContext context = JAXBContext.newInstance(MedicalPersonnel.class);
		// Get the marshaller
		Marshaller marshal = context.createMarshaller();
		// Pretty formating
		marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// Marshall the dog to a file
		String path = System.getProperty("user.dir");
		System.out.println("Please, input the name for your XML file \n");
		String fileName = reader.readLine();
		File file = new File(path + "/src/xmls/" + fileName + ".xml");
		marshal.marshal(medicalPersonnel, file);
		// Marshall the dog to the screen
		marshal.marshal(medicalPersonnel, System.out);

	}

	private static void medicalPersonnelMenu() throws Exception {
		int exitSubmenuMedicalPersonnel = 0;
		while (exitSubmenuMedicalPersonnel == 0) {
			System.out.println("Select area \n");
			System.out.println("1. Treatment \n");
			System.out.println("2. Pathology \n");
			System.out.println("3. Patient \n");
			System.out.println("4. Clinical History \n");
			System.out.println("5. Allergies \n");
			System.out.println("6. Symptoms \n");
			System.out.println("7. Medical Personnel \n");
			System.out.println("8. Exit \n");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:
				medicalPersonnelSubMenu1Treatment();
				break;
			case 2:

				medicalPersonnelSubMenu2Pathology();
				break;
			case 3:
				medicalPersonnelSubMenu3Patient();
				break;
			case 4:
				medicalPersonnelSubMenu4ClinicalHistory();
				break;
			case 5:
				medicalPersonnelSubMenu5Allergy();
				break;
			case 6:
				medicalPersonnelSubMenu6Symptom();
				break;
			case 7:

				medicalPersonnelSubMenu7MedicalPersonnel();
				break;
			case 8:
				System.out.println("See you soon, Medical Personnel! \n");
				exitSubmenuMedicalPersonnel = 1;
				break;
			default:
				break;
			}
		}
	}

	private static void medicalPersonnelSubMenu1Treatment() throws Exception {
		int exitSubmenu1Treatment = 0;
		while (exitSubmenu1Treatment == 0) {
			System.out.println("Select action \n");
			System.out.println("1.Check treatment \n");
			System.out.println("2.Exit \n");
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				searchMenu();
				int choice2 = Integer.parseInt(reader.readLine());
				while (choice2 != 1 && choice2 != 2) {
					System.out.println("Select a valid option, please");
					choice2 = Integer.parseInt(reader.readLine());
				}
				if (choice2 == 1) {
					searchTreatmentById();
				}
				if (choice2 == 2) {
					searchTreatmentByName();
				}
				break;
			case 2:
				System.out.println("Treatment operations terminated \n");
				break;
			default:
				break;
			}
		}

	}

	private static void medicalPersonnelSubMenu2Pathology() throws Exception {

		System.out.println("2. Pathology \n");

		int exitSubmenu2Pathology = 0;
		while (exitSubmenu2Pathology == 0) {

			System.out.println("Select an option \n");

			System.out.println("1. Add Pathology \n");
			System.out.println("2. Update Pathology \n");
			System.out.println("3. Check Pathology\n");
			System.out.println("4. Delete Pathology \n");
			System.out.println("5. Exit");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:

				System.out.println("1. Add Pathology \n");

				addPathology();
				break;

			case 2:

				System.out.println("2. Update Pathology \n");

				System.out.println("Write pathology id");
				int pathologyId = Integer.parseInt(reader.readLine());

				updatePathology(pathologyId);
				break;

			case 3:

				System.out.println("3. Check Pathology \n");

				int exitSubmenu2Treatment2 = 0;
				while (exitSubmenu2Treatment2 == 0) {

					searchMenu();
					System.out.println("3. Exit \n");

					int choice2 = Integer.parseInt(reader.readLine());

					switch (choice2) {

					case 1:

						System.out.println("1. Search Pathology By Id \n");
						searchPathologyById();
						break;

					case 2:

						System.out.println("Search Pathology By Name \n");
						searchPathologyByName();
						break;

					case 3:

						System.out.println("3. Exit \n");
						exitSubmenu2Treatment2 = 1;
						break;

					default:
						break;
					}

					break;
				}

				break;

			case 4:

				System.out.println("4. Delete Pathology \n");

				System.out.println("Write pathology Id");
				int pathology_id = Integer.parseInt(reader.readLine());

				deletePathology(pathology_id);
				break;

			case 5:

				System.out.println("5. Exit");
				exitSubmenu2Pathology = 1;

				break;

			default:

				break;

			}
		}
	}

	private static void updatePathology(int pathologyId) throws Exception {

		// We get the pathology
		Pathology modifiedPathology = pathologyManager.searchPathologyById(pathologyId);

		System.out.println("Actual Name" + modifiedPathology.getName());
		System.out.println("Type the new Name or press enter to leave it as it is");
		String newName = reader.readLine();
		if (newName.equals("")) {

			newName = modifiedPathology.getName();

		}

		System.out.println("Actual Start Date" + modifiedPathology.getStartDate());
		System.out.println("Type the new Start Date or press enter to leave it as it is");
		String newStartDate = reader.readLine();
		Date dateNewStartDate = null;
		if (newStartDate.equals("")) {

			dateNewStartDate = modifiedPathology.getStartDate();

		} else {

			dateNewStartDate = Date.valueOf(LocalDate.parse(newStartDate, formatter));

		}

		System.out.println("Actual Ending Date" + modifiedPathology.getEndingDate());
		System.out.println("Type the new Ending Date or press enter to leave it as it is");
		String newEndingDate = reader.readLine();
		Date dateNewEndingDate = null;
		if (newEndingDate.equals("")) {

			dateNewEndingDate = modifiedPathology.getEndingDate();

		} else {

			dateNewEndingDate = Date.valueOf(LocalDate.parse(newEndingDate, formatter));

		}

		System.out.println("Actual Treatmetnt Id" + modifiedPathology.getTreatmentId());
		System.out.println("Type the new Treatment Id or press enter to leave it as it is");
		String newTreatmentId = reader.readLine();
		int intNewTreatmentId = 0;
		if (newTreatmentId.equals("")) {

			intNewTreatmentId = modifiedPathology.getTreatmentId();

		} else {

			intNewTreatmentId = Integer.parseInt(newTreatmentId);

		}

		Pathology updatedPathology = new Pathology(pathologyId, newName, dateNewStartDate, dateNewEndingDate,
				intNewTreatmentId);

		pathologyManager.update(updatedPathology);

	}

	private static void deletePathology(int pathologyId) throws Exception {

		Pathology pathologyToDelete = pathologyManager.searchPathologyById(pathologyId);
		pathologyManager.delete(pathologyToDelete);

	}

	private static void medicalPersonnelSubMenu3Patient() throws Exception {

		System.out.println("3. Patient: \n");
		int exitSubmenu3Patient = 0;
		while (exitSubmenu3Patient == 0) {
			System.out.println("Select action \n");
			System.out.println("1. Add \n");
			System.out.println("2. Update \n");
			System.out.println("3. Check \n");
			System.out.println("4. Delete \n");
			System.out.println("5. Exit \n");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:
				addPatient();
				break;
			case 2:

				System.out.println("Introduce the patient's id: ");
				int id = Integer.parseInt(reader.readLine());
				updatePatient(id);
				break;
			case 3:
				searchMenu();
				System.out.println("search by pathology id");
				int option;
				do {
					System.out.println("choose an option:");
					option = Integer.parseInt(reader.readLine());
				} while (option > 3 || option <= 0);

				if (option == 1) {
					searchPatientById();
					break;
				} else {
					if (option == 2) {
						searchPatientByName();
						break;
					} else {
						searchPatientByPathologyId();
						break;
					}
				}

			case 4:
				System.out.println("Introduce the patient's id: ");
				int patientid = Integer.parseInt(reader.readLine());
				deletePatient(patientid);
				break;

			case 5:
				System.out.println("Patient operations terminated \n");
				exitSubmenu3Patient = 1;
				break;
			default:
				break;
			}
		}
	}

	private static void medicalPersonnelSubMenu4ClinicalHistory() throws Exception {
		int exitSubmenu4ClinicalHistory = 0;
		while (exitSubmenu4ClinicalHistory == 0) {
			System.out.println("Select action \n");
			System.out.println("1. Add \n");
			System.out.println("2. Update \n");
			System.out.println("3. Check \n");
			System.out.println("4. Delete \n");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:
				addClinicalHistory();
				break;
			case 2:
				System.out.println("Introduce the Clinical History's id: ");
				int clinicalHistoryToUpdateId = Integer.parseInt(reader.readLine());
				updateClinicalHistory(clinicalHistoryToUpdateId);
				break;
			case 3:
				searchClinicalHistoryById();
				break;

			case 4:
				System.out.println("Introduce the Clinical History's id: ");
				int clinicalHistoryToDeleteId = Integer.parseInt(reader.readLine());
				deleteClinicalHistory(clinicalHistoryToDeleteId);
				break;
			case 5:
				System.out.println("Clinical History operations terminated \n");
				exitSubmenu4ClinicalHistory = 1;
				break;
			default:
				break;
			}
		}
	}

	private static void medicalPersonnelSubMenu5Allergy() throws Exception {
		int exitSubmenu5MedicalPersonnel = 0;
		while (exitSubmenu5MedicalPersonnel == 0) {
			System.out.println("Select action \n");
			System.out.println("1. Add \n");
			System.out.println("2. Check \n");
			System.out.println("3. Exit \n");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:
				addAllergy();
				break;
			case 2:
				searchMenu();
				int choiceSearch = Integer.parseInt(reader.readLine());
				while (choiceSearch != 1 || choiceSearch != 2) {
					System.out.println("Select a valid option, please");
				}
				if (choiceSearch == 1) {
					searchAllergyById();
				}
				if (choiceSearch == 2) {
					searchAllergyByName();
				}
				break;
			case 3:
				System.out.println("Allergy operations terminated \n");
				exitSubmenu5MedicalPersonnel = 1;
				break;
			default:
				break;
			}
		}
	}

	private static void medicalPersonnelSubMenu6Symptom() throws Exception {
		int exitSubmenu6Symptom = 0;
		System.out.println("6. Symptom \n");
		while (exitSubmenu6Symptom == 0) {

			System.out.println("Select an action: \n");
			System.out.println("1. Add \n");
			System.out.println("2. Check \n");
			System.out.println("3. Exit \n");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:
				addSymptom();
				break;

			case 2:
				System.out.println("1. Search by id \n");
				System.out.println("2. Search by manifestation \n");
				int option;
				do {
					System.out.println("Choose an option:\n");
					option = Integer.parseInt(reader.readLine());
				} while (option > 2 || option <= 0);

				if (option == 1) {
					searchSymptomById();
					break;
				} else {

					searchSymptomByManifestation();
					break;
				}

			case 3:
				System.out.println("Symptom operations terminated \n");
				exitSubmenu6Symptom = 1;
				break;
			default:

				break;

			}

		}
	}

	private static void medicalPersonnelSubMenu7MedicalPersonnel() throws Exception {

		System.out.println("7. Medical Personnel");

		int exitSubmenu7MedicalPersonnel = 0;
		while (exitSubmenu7MedicalPersonnel == 0) {

			searchMenu();
			System.out.println("3. Search by Pathology Id \n");
			System.out.println("4. Exit");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:

				System.out.println("1. Search Medical Personnel by Id \n");

				searchMedicalPersonnelById();
				break;

			case 2:

				System.out.println("2. Search Medical Personnel by Name \n");

				searchMedicalPersonnelByName();
				break;

			case 3:

				System.out.println("3. Search Medical Personnel by Pathology Id \n");

				searchMedicalPersonnelByPathologyId();
				break;

			case 4:

				System.out.println("4. Exit");
				exitSubmenu7MedicalPersonnel = 1;

			default:
				break;
			}

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

		pathologyManager.showPathologies();
		System.out.println("Pathology id: \n");
		int pathologyId = Integer.parseInt(reader.readLine());

		System.out.println("Clinical History id: \n");
		int clinicalHistoryId = Integer.parseInt(reader.readLine());

		Patient patient = new Patient(name, gender, state, Date.valueOf(dateOfBirth), pathologyId, clinicalHistoryId);

		// Patient added

		// para insertarlo:
		patientManager.add(patient);

		// patient inserted

	}

	private static void searchPatientById() throws Exception {
		System.out.print("Insert the id: ");
		int id = Integer.parseInt(reader.readLine());

		// para buscar en la base de datos:
		if (patientManager.searchPatientById(id) != null) {
			Patient patient = patientManager.searchPatientById(id);
			System.out.println(patient);
		} else {
			System.out.println("The patient does not exist \n");
		}
	}

	private static void searchPatientByName() throws Exception {

		System.out.print("Insert the name: ");
		String name = reader.readLine();

		// para buscar en la base de datos:
		if (patientManager.searchPatientByName(name) != null) {
			List<Patient> patients = patientManager.searchPatientByName(name);
			// para mistrar por pantalla
			for (Patient patient : patients) {
				System.out.println(patient);
			}
		} else {
			System.out.println("The patient does not exist \n");
		}
	}

	private static void deletePatient(int patientId) throws Exception {
		Patient patient = patientManager.searchPatientById(patientId);
		patientManager.delete(patient);

	}

	private static void updatePatient(int patientId) throws Exception {

		Patient toBeModified = patientManager.searchPatientById(patientId);

		System.out.println("Actual name: " + toBeModified.getName());
		System.out.println("Type the new name or press enter to leave it as it is");
		String newName = reader.readLine();
		if (newName.equals("")) {
			newName = toBeModified.getName();
		}

		System.out.println("Actual gender: " + toBeModified.getGender());
		System.out.println("Type the new gender or press enter to leave it as it is");
		String newGender = reader.readLine();
		if (newGender.equals("")) {
			newGender = toBeModified.getGender();
		}

		System.out.println("Actual state: " + toBeModified.getState());
		System.out.println("Type the new state or press enter to leave it as it is");
		String newState = reader.readLine();
		if (newState.equals("")) {
			newState = toBeModified.getState();
		}

		System.out.println("Actual date of birth: " + toBeModified.getDob());
		System.out.println("Type the new date of birth or press enter to leave it as it is");
		String newDob = reader.readLine();
		Date newDOB;
		if (newDob.equals("")) {
			newDOB = toBeModified.getDob();
		} else {
			newDOB = Date.valueOf(LocalDate.parse(newDob, formatter));
		}

		System.out.println("Actual pathology id: " + toBeModified.getName());
		System.out.println("Type the new pathology id or press enter to leave it as it is");
		String newPathologyId = reader.readLine();
		int newPathology_id;
		if (newPathologyId.equals("")) {
			newPathology_id = toBeModified.getPathology_id();
		} else {
			newPathology_id = Integer.parseInt(newPathologyId);
		}

		System.out.println("Actual clinical history id: " + toBeModified.getName());
		System.out.println("Type the new clinical history id or press enter to leave it as it is");
		String newCHId = reader.readLine();
		int newClinicalHistory_id;
		if (newCHId.equals("")) {
			newClinicalHistory_id = toBeModified.getClinicalhistory_id();
		} else {
			newClinicalHistory_id = Integer.parseInt(newCHId);
		}

		Patient updatedPatient = new Patient(newName, newGender, newState, newDOB, newPathology_id,
				newClinicalHistory_id);

		patientManager.update(updatedPatient);

	}

	private static void searchPatientByPathologyId() throws Exception {

		System.out.print("Insert the pathology id: ");
		int pathologyId = Integer.parseInt(reader.readLine());
		// para buscar en la base de datos:
		if (patientManager.searchPatientByPathologyId(pathologyId) != null) {
			List<Patient> patients = patientManager.searchPatientByPathologyId(pathologyId);
			// para mostrar por pantalla
			for (Patient patient : patients) {
				System.out.println(patient);
			}
		} else {
			System.out.println("The patient does not exist \n");
		}
	}

	private static void addSymptom() throws Exception {

		System.out.println("So do it!");

		System.out.print("Manifestation: ");
		String manifestation = reader.readLine();

		Symptom symptom = new Symptom(manifestation);
		// symptom added

		// para insertar en base de datos:
		symptomManager.add(symptom);
		// symptom inserted :)
	}

	private static void searchSymptomById() throws Exception {
		System.out.print("Insert the id: ");
		int id = Integer.parseInt(reader.readLine());

		// para buscar en la base de datos:
		if (symptomManager.searchSymptomById(id) != null) {
			Symptom symptom = symptomManager.searchSymptomById(id);
			System.out.println(symptom);
		} else {
			System.out.println("The symptom does not exist \n");
		}
	}

	private static void searchSymptomByManifestation() throws Exception {
		System.out.print("Insert the manifestation: ");
		String manifestation = reader.readLine();
		if (symptomManager.searchSymptomByManifestation(manifestation) != null) {
			// para buscar en la base de datos:
			List<Symptom> symptoms = symptomManager.searchSymptomByManifestation(manifestation);
			// para mostrar por pantalla:
			for (Symptom symptom : symptoms) {
				System.out.println(symptom);
			}
		} else {
			System.out.println("The symptom does not exist \n");
		}
	}

	private static void addPathology() throws Exception {

		System.out.println("So do it! \n");

		System.out.print("Name: \n");
		String name = reader.readLine();

		System.out.print("Start Date (yyyy-MM-dd): \n");
		String startdate = reader.readLine();
		LocalDate startDate = LocalDate.parse(startdate, formatter);

		System.out.print("Ending Date (yyyy-MM-dd): \n");
		String endingdate = reader.readLine();
		LocalDate endingDate = LocalDate.parse(endingdate, formatter);
		// We use a LocalDate because depending on where you are on the globe, the date
		// might vary.

		System.out.print("Treatment id: \n");
		int treatmentId = Integer.parseInt(reader.readLine());

		Pathology pathology = new Pathology(name, Date.valueOf(startDate), Date.valueOf(endingDate), treatmentId);

		pathologyManager.add(pathology);

	}

	private static void medicalPersonnelBossMenu() throws Exception {
		int exitSubmenuMedicalPersonnelBoss = 0;
		while (exitSubmenuMedicalPersonnelBoss == 0) {
			System.out.println("Select area \n");
			System.out.println("1. Pathology \n");
			System.out.println("2. Medical Personnel \n");
			System.out.println("3. Exit \n");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:

				System.out.println("1. Pathology \n");

				medicalPersonnelBossSubMenu1Pathology(); // Menu para pathology
				break;

			case 2:

				System.out.println("2. Medical Personnel \n");

				medicalPersonnelBossSubMenu2MedicalPersonnel(); // Menu para medical personnel
				break;
			case 3:
				System.out.println("See you soon, Boss! \n");
				exitSubmenuMedicalPersonnelBoss = 1;
				break;
			default:
				break;
			}
		}
	}

	private static void medicalPersonnelBossSubMenu1Pathology() throws Exception { // Pathology

		System.out.println("1. Pathology");

		int submenu1Pathology = 0;
		while (submenu1Pathology == 0) {

			searchMenu();
			System.out.println("3. Exit \n");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:

				System.out.println("1. Search Pathology by Id \n");

				searchPathologyById();
				break;

			case 2:

				System.out.println("2. Search Pathology by Name \n");

				searchPathologyByName();
				break;

			case 3:

				System.out.println("3. Exit");

				submenu1Pathology = 1;

			default:
				break;
			}
		}

	}

	private static void searchPathologyById() throws Exception {

		System.out.println("Type! \n");
		System.out.println("Id: \n");
		int id = Integer.parseInt(reader.readLine());
		if (pathologyManager.searchPathologyById(id) != null) {
			Pathology pathology = pathologyManager.searchPathologyById(id);
			System.out.println(pathology);
		} else {
			System.out.println("The pathology does not exist \n");
		}
	}

	private static void searchPathologyByName() throws Exception {

		System.out.println("Type! \n");
		System.out.println("Name: \n");
		String name = reader.readLine();
		if (pathologyManager.searchPathologyByName(name) != null) {
			List<Pathology> pathologies = pathologyManager.searchPathologyByName(name);
			// Different pathologies may have the same name
			for (Pathology pathology : pathologies) {
				System.out.println(pathology);
			}
		} else {
			System.out.println("The pathology does not exist \n");
		}
	}

	private static void medicalPersonnelBossSubMenu2MedicalPersonnel() throws Exception { // Medical Personnel

		System.out.println("2. Medical Personnel \n");

		int subMenu2MedicalPersonnel = 0;
		while (subMenu2MedicalPersonnel == 0) {

			System.out.println("Select action \n");

			System.out.println("1. Add Medical Personnel \n");
			System.out.println("2. Update Medical Personnel\n");
			System.out.println("3. Check Medical Personnel \n");
			System.out.println("4. Delete Medical Personnel \n");
			System.out.println("5. Exit");

			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {

			case 1:

				System.out.println("1. Add Medical Personnel \n");

				addMedicalPersonnel();
				break;

			case 2:

				System.out.println("2. Update Medical Personnel \n");

				System.out.println("Write Medical Personnel id");
				int medicalPersonnelId = Integer.parseInt(reader.readLine());

				updateMedicalPersonnel(medicalPersonnelId);

				break;

			case 3:

				System.out.println("3. Check Medical Personnel \n");

				break;
			case 4:

				System.out.println("4. Delete Medical Personnel \n");
				System.out.println("Write MedicalP Personnel Id");
				int medicalPersonnel_Id = Integer.parseInt(reader.readLine());

				deleteMedicalPersonnel(medicalPersonnel_Id);

				break;

			case 5:

				System.out.println("5. Exit");
				subMenu2MedicalPersonnel = 1;

				break;

			default:
				break;

			}
		}
	}

	private static void searchMedicalPersonnelById() throws Exception {

		System.out.println("Instert the id: \n");
		int id = Integer.parseInt(reader.readLine());
		if (medicalPersonnelManager.searchMedicalPersonnelById(id) != null) {
			MedicalPersonnel medicalPersonnel = medicalPersonnelManager.searchMedicalPersonnelById(id);
			System.out.println(medicalPersonnel);
		} else {
			System.out.println("The medical personnel does not exist \n");
		}
	}

	private static void searchMedicalPersonnelByName() throws Exception {

		System.out.println("Insert the name: \n");
		String name = reader.readLine();
		if (medicalPersonnelManager.searchMedicalPersonnelByName(name) != null) {
			List<MedicalPersonnel> medicalPersonnels = medicalPersonnelManager.searchMedicalPersonnelByName(name);
			// we search in our database for all the treatments which have the same name as
			// the one specified
			// we show the treatments who matched the required name
			for (MedicalPersonnel medicalPersonnel : medicalPersonnels) {
				System.out.println(medicalPersonnel);
			}
		} else {
			System.out.println("The medical personnel does not exist \n");
		}
	}

	private static void searchMedicalPersonnelByPathologyId() throws Exception {

		System.out.println("Type! \n");
		System.out.println("Pathology id: \n");
		int pathologyId = Integer.parseInt(reader.readLine());
		if (medicalPersonnelManager.searchMedicalPersonnelByPathologyId(pathologyId) != null) {
			List<MedicalPersonnel> medicalPersonnels = medicalPersonnelManager
					.searchMedicalPersonnelByPathologyId(pathologyId);
			for (MedicalPersonnel medicalPersonnel : medicalPersonnels) {
				System.out.println(medicalPersonnel);
			}
		} else {
			System.out.println("The medical personnel does not exist \n");
		}
	}

	private static void addMedicalPersonnel() throws Exception {

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

		medicalPersonnelManager.add(medicalPersonnel);

	}

	private static void updateMedicalPersonnel(int medicalPersonnelId) throws Exception {

		// We get the pathology
		MedicalPersonnel modifiedMedicalPersonnel = medicalPersonnelManager
				.searchMedicalPersonnelById(medicalPersonnelId);

		System.out.println("Actual Name" + modifiedMedicalPersonnel.getName());
		System.out.println("Type the new Name or press enter to leave it as it is");
		String newName = reader.readLine();
		if (newName.equals("")) {

			newName = modifiedMedicalPersonnel.getName();

		}

		System.out.println("Actual Department" + modifiedMedicalPersonnel.getDepartment());
		System.out.println("Type the new Department or press enter to leave it as it is");
		String newDepartment = reader.readLine();
		if (newDepartment.equals("")) {

			newDepartment = modifiedMedicalPersonnel.getDepartment();

		}

		System.out.println("Actual Position" + modifiedMedicalPersonnel.getPosition());
		System.out.println("Type the new Position or press enter to leave it as it is");
		String newPosition = reader.readLine();
		if (newPosition.equals("")) {

			newPosition = modifiedMedicalPersonnel.getPosition();

		}

		System.out.println("Actual Pathology Id" + modifiedMedicalPersonnel.getPathologyId());
		System.out.println("Type the new Pathology Id or press enter to leave it as it is");
		String newPathologyId = reader.readLine();
		int intNewPathologyId = 0;
		if (newPathologyId.equals("")) {

			intNewPathologyId = modifiedMedicalPersonnel.getPathologyId();

		} else {

			intNewPathologyId = Integer.parseInt(newPathologyId);

		}

		MedicalPersonnel newMedicalPersonnel = new MedicalPersonnel(medicalPersonnelId, newName, newDepartment,
				newPosition, intNewPathologyId);

		medicalPersonnelManager.update(newMedicalPersonnel);

	}

	private static void deleteMedicalPersonnel(int medicalPersonnel_id) throws Exception {

		MedicalPersonnel medicalPersonnelToDelete = medicalPersonnelManager
				.searchMedicalPersonnelById(medicalPersonnel_id);
		medicalPersonnelManager.delete(medicalPersonnelToDelete);

	}

	private static void addTreatment() throws Exception {
		System.out.println("So do it! \n");

		System.out.print("Name: \n");
		String name = reader.readLine();

		System.out.print("Medication: \n");
		String medication = reader.readLine();

		System.out.print("Description: \n");
		String description = reader.readLine();

		Treatment treatment = new Treatment(name, medication, description);
		treatmentManager.add(treatment);
	}

	private static void updateTreatment(int treatmentId) throws Exception {

		Treatment oldTreatment = treatmentManager.searchTreatmentById(treatmentId);

		System.out.println("Current name of the treatment: " + oldTreatment.getName());
		System.out.println("Press enter to maintain the current name, or type the new name of the treatment");
		String updatedName = reader.readLine();

		if (updatedName.equals("")) {
			updatedName = oldTreatment.getName();
		}

		System.out.println("Current medication of the treatment: " + oldTreatment.getMedication());
		System.out
				.println("Press enter to maintain the current medication, or type the new medication of the treatment");
		String updatedMedication = reader.readLine();

		if (updatedMedication.equals("")) {
			updatedMedication = oldTreatment.getMedication();
		}

		System.out.println("Current description: " + oldTreatment.getDescription());
		System.out.println(
				"Press enter to maintain the current description, or type the new description of the treatment");
		String updatedDescription = reader.readLine();

		if (updatedDescription.equals("")) {
			updatedDescription = oldTreatment.getDescription();
		}

		Treatment newTreatment = new Treatment(updatedName, updatedMedication, updatedDescription);
		treatmentManager.update(newTreatment);

	}

	private static void searchTreatmentByName() throws Exception {

		System.out.println("Type! \n");
		System.out.println("Name: \n");
		String name = reader.readLine();
		if (treatmentManager.searchTreatmentByName(name) != null) {
			List<Treatment> treatments = treatmentManager.searchTreatmentByName(name);
			for (Treatment treatment : treatments) {
				System.out.println(treatment);
			}

		} else {
			System.out.println("The treatment does not exist \n");
		}

	}

	private static void searchTreatmentById() throws Exception {

		System.out.println("Type! \n");
		System.out.println("Id: \n");
		int id = Integer.parseInt(reader.readLine());
		if (treatmentManager.searchTreatmentById(id) != null) {
			Treatment treatment = treatmentManager.searchTreatmentById(id);
			System.out.println(treatment);
		} else {
			System.out.println("The treatment does not exist \n");
		}
	}

	private static void deleteTreatment(int treatmentid) throws Exception {
		Treatment treatmentToDelete = treatmentManager.searchTreatmentById(treatmentid);
		treatmentManager.delete(treatmentToDelete);
	}

	private static void searchMenu() throws Exception {
		System.out.println("Select an option \n");
		System.out.println("1. Search by id \n");
		System.out.println("2. Search by name \n");

	}

	// METHODS FOR CLINICAL HISTORY

	private static void addClinicalHistory() throws Exception {
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

		System.out.println("Allergy id: \n");
		int allergyId = Integer.parseInt(reader.readLine());

		ClinicalHistory clinicalHistory = new ClinicalHistory(Date.valueOf(doe), Date.valueOf(dod), bloodType,
				extraInfo, allergyId);
		clinicalHistoryManager.add(clinicalHistory);
	}

	private static void searchClinicalHistoryById() throws Exception {
		System.out.println("Type! \n");
		System.out.println("Clinical History id: \n");
		int clinicalHistoryId = Integer.parseInt(reader.readLine());
		if (clinicalHistoryManager.searchClinicalHistoryById(clinicalHistoryId) != null) {
			ClinicalHistory clinicalHistoryFound = clinicalHistoryManager.searchClinicalHistoryById(clinicalHistoryId);
			System.out.println(clinicalHistoryFound);
		} else {
			System.out.println("The clinical history does not exist \n");
		}
	}

	private static void updateClinicalHistory(int clinicalHistoryToUpdateId) throws Exception {
		// get the clinical history
		ClinicalHistory toBeModified = clinicalHistoryManager.searchClinicalHistoryById(clinicalHistoryToUpdateId);

		System.out.println("Actual date of entry: " + toBeModified.getDoe());
		System.out.println("Type the new date of entry (yyyy-MM-dd) or press enter to leave it as is:");
		String newDateOfEntry = reader.readLine();
		Date newDoe;
		// if the user does not type anything, the doe stays the same
		if (newDateOfEntry.equals("")) {
			newDoe = toBeModified.getDoe();
		} else {
			newDoe = Date.valueOf(LocalDate.parse(newDateOfEntry, formatter));
		}

		System.out.println("Actual date of discharge: " + toBeModified.getDod());
		System.out.println("Type the new date of entry (yyyy-MM-dd) or press enter to leave it as is:");
		String newDateOfDischarge = reader.readLine();
		Date newDod;
		// if the user does not type anything, the dod stays the same
		if (newDateOfDischarge.equals("")) {
			newDod = toBeModified.getDod();
		} else {
			newDod = Date.valueOf(LocalDate.parse(newDateOfDischarge, formatter));
		}

		System.out.println("Actual blood type: " + toBeModified.getBloodType());
		System.out.println("Type the new blood type or press enter to leave it as is:");
		String newBloodType = reader.readLine();
		// if the user does not type anything, the bloodType stays the same
		if (newBloodType.equals("")) {
			newBloodType = toBeModified.getBloodType();
		}

		System.out.println("Actual extra information: " + toBeModified.getExtraInfo());
		System.out.println("Type the new extra information or press enter to leave it as is:");
		String newExtraInfo = reader.readLine();
		// if the user does not type anything, the extraInfo stays the same
		if (newExtraInfo.equals("")) {
			newExtraInfo = toBeModified.getExtraInfo();
		}

		System.out.println("Actual allergy id: " + toBeModified.getExtraInfo());
		System.out.println("Type the new allergy id or press enter to leave it as is:");
		String newAllergyId = reader.readLine();
		int intNewAllergyId = 0;
		// if the user does not type anything, the allergyId stays the same
		if (newAllergyId.equals("")) {

			intNewAllergyId = toBeModified.getAllergyId();

		} else {

			intNewAllergyId = Integer.parseInt(newAllergyId);

		}

		ClinicalHistory updatedClinicalHistory = new ClinicalHistory(newDoe, newDod, newBloodType, newExtraInfo,
				intNewAllergyId);
		clinicalHistoryManager.update(updatedClinicalHistory);
	}

	private static void deleteClinicalHistory(int clinicalHistoryToDeleteId) throws Exception {
		// first I get the clinical history
		ClinicalHistory clinicalHistoryToDelete = clinicalHistoryManager
				.searchClinicalHistoryById(clinicalHistoryToDeleteId);

		clinicalHistoryManager.delete(clinicalHistoryToDelete);
	}
	// METHODS FOR ALLERGY

	private static void addAllergy() throws Exception {
		System.out.println("Great, you seem nice, you can do it: \n");

		System.out.println("Allergy: \n");
		String allergyName = reader.readLine();

		System.out.println("Degree: \n");
		int degree = Integer.parseInt(reader.readLine());

		Allergy allergy = new Allergy(allergyName, degree);
		allergyManager.add(allergy);
	}

	private static void searchAllergyById() throws Exception {
		System.out.println("Type! \n");
		System.out.println("Allergy id: \n");
		int id = Integer.parseInt(reader.readLine());
		if (allergyManager.searchAllergyById(id) != null) {
			Allergy allergy = allergyManager.searchAllergyById(id);
			System.out.println(allergy);
		} else {
			System.out.println("The allergy does not exist \n");
		}
	}

	private static void searchAllergyByName() throws Exception {

		System.out.println("Type! \n");
		System.out.println("Name: \n");
		String name = reader.readLine();
		if (allergyManager.searchAllergyByName(name) != null) {
			List<Allergy> allergies = allergyManager.searchAllergyByName(name);
			for (Allergy allergy : allergies) {
				System.out.println(allergy);
			}
		} else {
			System.out.println("The allergy does not exist \n");
		}
	}

	private static void generateXMLPatient(int patientId) throws Exception {
		Patient patient = patientManager.searchPatientById(patientId);
		// Create a JAXB Context
		JAXBContext context = (JAXBContext) JAXBContext.newInstance(Patient.class);
		// Get the marshaller
		Marshaller marshal = context.createMarshaller();
		// Pretty formating
		marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// Marshall the dog to a file
		String path = System.getProperty("user.dir");
		System.out.println("Please, input the name for your XML file \n");
		String fileName = reader.readLine();
		File file = new File(path + "/src/xmls/" + fileName + ".xml");
		marshal.marshal(patient, file);
		// Marshall the dog to the screen
		marshal.marshal(patient, System.out);

	}

	private static void admitPatientXML() throws Exception {
		// Create JAXBCOntext
		javax.xml.bind.JAXBContext context = JAXBContext.newInstance(Patient.class);
		// Get the unmarshaller
		Unmarshaller unmarshal = context.createUnmarshaller();
		// Unmarshall dog from a file
		String path = System.getProperty("user.dir");
		System.out.println("Type the filename for the XML document:");
		String fileName = reader.readLine();
		File file = new File(path + "/src/xmls/" + fileName + ".xml");
		Patient patient = (Patient) unmarshal.unmarshal(file);
		// print the patient
		System.out.println(patient);
		// insert the patient
		patientManager.add(patient);
		// finish
	}
}
