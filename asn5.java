// Importing Necessary Utilities
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class FinalProject {
    public static void main(String[] args) throws Exception {
        ArrayList<Person> UCFppl = new ArrayList<>();
        Scanner myScanner = new Scanner(System.in);

        System.out.println("\n\t\t\tWelcome to the Personal Management Program\n");
        int input = 0;

        while (input != 8) {
            boolean goodInput = false;
            while (!goodInput) {
                callMenu();
                if (myScanner.hasNextInt()) {
                    input = myScanner.nextInt();
                    myScanner.nextLine();
                    goodInput = true;
                } else {
                    System.out.println("\n\tInvalid entry- please try again.\n");
                    myScanner.next();
                }
            }

            switch (input) {
                case 1 -> handleFacultyInput(myScanner, UCFppl);
                case 2 -> handleStudentInput(myScanner, UCFppl);
                case 3 -> printStudentInvoice(myScanner, UCFppl);
                case 4 -> printFacultyInfo(myScanner, UCFppl);
                case 5 -> handleStaffInput(myScanner, UCFppl);
                case 6 -> printStaffInfo(myScanner, UCFppl);
                case 7 -> deletePerson(myScanner, UCFppl);
                default -> System.out.println("Goodbye!");
            }
        }

        generateReport(myScanner, UCFppl);
    }

    private static void callMenu() {
        System.out.println("1. Enter the information of a faculty");
        System.out.println("2. Enter the information of a student");
        System.out.println("3. Print tuition invoice for a student");
        System.out.println("4. Print faculty information");
        System.out.println("5. Enter the information of a staff member");
        System.out.println("6. Print the information of a staff member");
        System.out.println("7. Delete a person");
        System.out.println("8. Exit Program\n");
        System.out.print("\tEnter your selection: ");
    }

    private static boolean isPossibleID(String id) {
        if (id.length() != 6) return false;
        for (int i = 0; i < 2; i++) if (!Character.isLetter(id.charAt(i))) return false;
        for (int i = 2; i < 6; i++) if (!Character.isDigit(id.charAt(i))) return false;
        return true;
    }

    private static boolean notDupID(String id, ArrayList<Person> UCFppl) {
        for (Person person : UCFppl) {
            if (person.getId().equalsIgnoreCase(id)) return true;
        }
        return false;
    }

    private static boolean isRank(String rank) {
        return rank.equalsIgnoreCase("Professor") || rank.equalsIgnoreCase("Adjunct");
    }

    private static boolean isDepartment(String department) {
        return List.of("Mathematics", "Engineering", "English").contains(department);
    }

    private static boolean isStatus(String status) {
        return status.equalsIgnoreCase("f") || status.equalsIgnoreCase("p");
    }

    private static Person searchById(String id, ArrayList<Person> UCFppl) {
        for (Person person : UCFppl) {
            if (person.getId().equalsIgnoreCase(id)) return person;
        }
        return null;
    }

    private static String formatString(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    private static void handleFacultyInput(Scanner scanner, ArrayList<Person> UCFppl) {
        System.out.println("\nEnter the faculty info:");
        System.out.print("\tName: ");
        String name = scanner.nextLine();
        System.out.print("\tID: ");
        String id = scanner.nextLine();

        while (!isPossibleID(id) || notDupID(id, UCFppl)) {
            System.out.println("\tInvalid ID. Must be LetterLetterDigitDigitDigitDigit");
            System.out.print("\tID: ");
            id = scanner.nextLine();
        }

        System.out.print("\tRank: ");
        String rank = scanner.nextLine();
        while (!isRank(rank)) {
            System.out.println("\tInvalid rank.");
            System.out.print("\tRank: ");
            rank = scanner.nextLine();
        }

        System.out.print("\tDepartment: ");
        String department = scanner.nextLine();
        while (!isDepartment(department)) {
            System.out.println("\tInvalid department.");
            System.out.print("\tDepartment: ");
            department = scanner.nextLine();
        }

        UCFppl.add(new Faculty(name, id, department, rank));
        System.out.println("\nFaculty added!");
    }

    private static void handleStudentInput(Scanner scanner, ArrayList<Person> UCFppl) {
        System.out.println("\nEnter the student info:");
        System.out.print("\tName: ");
        String name = scanner.nextLine();
        System.out.print("\tID: ");
        String id = scanner.nextLine();

        while (!isPossibleID(id) || notDupID(id, UCFppl)) {
            System.out.println("\tInvalid ID. Must be LetterLetterDigitDigitDigitDigit");
            System.out.print("\tID: ");
            id = scanner.nextLine();
        }

        System.out.print("\tGPA: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("\tInvalid GPA. Enter a valid number.");
            scanner.next();
        }
        double gpa = scanner.nextDouble();

        System.out.print("\tCredit Hours: ");
        while (!scanner.hasNextInt()) {
            System.out.println("\tInvalid credit hours. Enter a valid integer.");
            scanner.next();
        }
        int cHours = scanner.nextInt();
        scanner.nextLine();

        UCFppl.add(new Student(name, id, gpa, cHours));
        System.out.println("\nStudent added!");
    }

    private static void printStudentInvoice(Scanner scanner, ArrayList<Person> UCFppl) {
        System.out.print("\tEnter the student's ID: ");
        String id = scanner.nextLine();
        Person person = searchById(id, UCFppl);

        if (person instanceof Student) {
            person.print();
        } else {
            System.out.println("\tNo student matched!");
        }
    }

    private static void printFacultyInfo(Scanner scanner, ArrayList<Person> UCFppl) {
        System.out.print("\tEnter the faculty's ID: ");
        String id = scanner.nextLine();
        Person person = searchById(id, UCFppl);

        if (person instanceof Faculty) {
            person.print();
        } else {
            System.out.println("\tNo faculty matched!");
        }
    }

    private static void handleStaffInput(Scanner scanner, ArrayList<Person> UCFppl) {
        System.out.print("\tName: ");
        String name = scanner.nextLine();
        System.out.print("\tID: ");
        String id = scanner.nextLine();

        while (!isPossibleID(id) || notDupID(id, UCFppl)) {
            System.out.println("\tInvalid ID. Must be LetterLetterDigitDigitDigitDigit");
            System.out.print("\tID: ");
            id = scanner.nextLine();
        }

        System.out.print("\tDepartment: ");
        String department = scanner.nextLine();
        while (!isDepartment(department)) {
            System.out.println("\tInvalid department.");
            System.out.print("\tDepartment: ");
            department = scanner.nextLine();
        }

        System.out.print("\tStatus (P for Part Time, F for Full Time): ");
        String status = scanner.nextLine();
        while (!isStatus(status)) {
            System.out.println("\tInvalid status.");
            System.out.print("\tStatus: ");
            status = scanner.nextLine();
        }

        UCFppl.add(new Staff(name, id, department, status));
        System.out.println("\nStaff member added!");
    }

    private static void printStaffInfo(Scanner scanner, ArrayList<Person> UCFppl) {
        System.out.print("\tEnter the staff's ID: ");
        String id = scanner.nextLine();
        Person person = searchById(id, UCFppl);

        if (person instanceof Staff) {
            person.print();
        } else {
            System.out.println("\tNo staff member matched!");
        }
    }

    private static void deletePerson(Scanner scanner, ArrayList<Person> UCFppl) {
        System.out.print("\tEnter the ID of the person to delete: ");
        String id = scanner.nextLine();
        Person person = searchById(id, UCFppl);

        if (person != null) {
            UCFppl.remove(person);
            System.out.println("\n\t" + person.fullName + " removed successfully.");
        } else {
            System.out.println("\tNo such person exists.");
        }
    }

    private static void generateReport(Scanner scanner, ArrayList<Person> UCFppl) {
        System.out.print("\nWould you like to print the report? (Y/N): ");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Generating report...");
            // Report generation logic here...
        } else {
            System.out.println("Report not generated.");
        }
    }
}

abstract class Person {
    String fullName;
    String id;

    public Person(String fullName, String id) {
        this.fullName = fullName;
        this.id = id;
    }

    public abstract void print();

    public String getfullName() {
        return fullName;
    }

    public void setfullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class Student extends Person {
    private double gpa;
    private int cHours;

    public Student(String fullName, String id, double gpa, int cHours) {
        super(fullName, id);
        this.gpa = gpa;
        this.cHours = cHours;
    }

    @Override
    public void print() {
        double creditHourCost = 236.45;
        double adFee = 52;
        double total = cHours * creditHourCost + adFee;
        double discount = (gpa >= 3.85) ? total * 0.25 : 0;
        total -= discount;

        System.out.println("\n---------------------------------------------------------------------------------");
        System.out.println("\t" + fullName + "\t\t\t" + id);
        System.out.println("\tCredit Hours: " + cHours + " ($" + creditHourCost + "/credit hour)");
        System.out.printf("\tFees: $%.0f\n", adFee);
        System.out.printf("\tTotal payment (after discount): $%.2f ($%.0f discount applied)", total, discount);
        System.out.println("\n---------------------------------------------------------------------------------\n");
    }

    public double getGPA() {
        return gpa;
    }

    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

    public int getcHours() {
        return cHours;
    }

    public void setcHours(int cHours) {
        this.cHours = cHours;
    }
}

abstract class Employee extends Person {
    String department;

    public Employee(String fullName, String id, String department) {
        super(fullName, id);
        this.department = formatString(department);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = formatString(department);
    }

    private static String formatString(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}

class Faculty extends Employee {
    private String rank;

    public Faculty(String fullName, String id, String department, String rank) {
        super(fullName, id, department);
        this.rank = formatString(rank);
    }

    @Override
    public void print() {
        System.out.println("\n---------------------------------------------------------------------------------\n");
        System.out.println("\t" + fullName + "\t\t" + id);
        System.out.println("\t" + department + " Department, " + rank);
        System.out.println("\n---------------------------------------------------------------------------------\n");
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = formatString(rank);
    }

    private static String formatString(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}

class Staff extends Employee {
    private String status;

    public Staff(String fullName, String id, String department, String status) {
        super(fullName, id, department);
        this.status = formatString(status);
    }

    @Override
    public void print() {
        String tmpStat = status.equalsIgnoreCase("f") ? "Full Time" : "Part Time";

        System.out.println("\n---------------------------------------------------------------------------------\n");
        System.out.println("\t" + fullName + "\t\t" + id);
        System.out.println("\t" + department + " Department, " + tmpStat);
        System.out.println("\n---------------------------------------------------------------------------------\n");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = formatString(status);
    }

    private static String formatString(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
