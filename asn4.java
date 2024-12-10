import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class unit7_hw5 {
    public static void main(String[] args) throws Exception {
        // Declare Lists
        ArrayList<String> online = new ArrayList<>();
        ArrayList<String> noLab = new ArrayList<>();
        ArrayList<String> classInRoom = new ArrayList<>();

        // Declare File to Read From
        File file = new File("lec.txt");
        Scanner myScan = new Scanner(file);

        // Temporary Variables
        String[] items;
        String line;
        int counter = 0;

        // Count Online Classes
        while (myScan.hasNextLine()) {
            line = myScan.nextLine();
            items = line.split(",");
            if (items.length > 4 && items[4].trim().equalsIgnoreCase("online")) {
                counter++;
            }
        }

        // Display Number of Online Classes
        System.out.println("- There are " + counter + " online lectures offered");

        // Close and Reopen Scanner to Restart File Read
        myScan.close();
        myScan = new Scanner(file);

        Scanner classScan = new Scanner(System.in);
        // Accept Input to Search for Classroom
        System.out.print("- Enter the classroom: ");
        String classSearch = classScan.nextLine();

        // Categorize Classes
        while (myScan.hasNextLine()) {
            line = myScan.nextLine();
            items = line.split(",");

            if (items.length > 4 && items[4].trim().equalsIgnoreCase("online")) {
                online.add(line);
            }
            if (items.length > 5 && items[6].trim().equalsIgnoreCase("No")) {
                noLab.add(line);
            }
            if (line.contains(classSearch)) {
                classInRoom.add(items[0]);
            }
        }

        // Display Classes Held in Specified Room
        System.out.println("\tThe CRNs held in " + classSearch + " are " + String.join(", ", classInRoom));

        // Write Results to File
        PrintWriter writer = new PrintWriter("lecturesOnly.txt");

        // Add Online Lectures to File
        writer.write("Online Lectures\n");
        for (String lecture : online) {
            writer.write(lecture + "\n");
        }

        // Add Lectures with No Lab to File
        writer.write("\nLectures with No Lab\n");
        for (String lecture : noLab) {
            writer.write(lecture + "\n");
        }

        // Close Writer
        writer.close();

        // Notify User of File Creation
        System.out.println("- lecturesOnly.txt is created");

        // Close All Scanners
        myScan.close();
        classScan.close();
    }
}
