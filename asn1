import java.util.Scanner;

public class u1hw1 {
    public static void main(String[] args) {
        String Id; // Variable Declarations
        String FullName;
        String FirstClass;
        int FirstCrn;
        int FirstHrs;
        String SecondClass;
        int SecondCrn;
        int SecondHrs;
        String[] Filler1;
        String[] Filler2;
        double CH = 120.25;

        // Prompts user for input
        System.out.print("Enter the Student's Id: ");
        Id = (new Scanner(System.in)).nextLine(); // Takes input and saves into Id

        System.out.print("Enter the Student's full name: ");
        FullName = (new Scanner(System.in)).nextLine(); // Takes input and saves into FullName

        System.out.print("\n"); // Prints newline
        System.out.print("Enter the crn/credit hours for the first class (like 5665/3): ");
        FirstClass = (new Scanner(System.in)).nextLine(); // Takes input and saves into FirstClass

        System.out.print("Enter the crn/credit hours for the second class (like 5665/3): ");
        SecondClass = (new Scanner(System.in)).nextLine(); // Takes input and saves into SecondClass

        // Splits input for the first class
        Filler1 = FirstClass.split("/");
        FirstCrn = Integer.parseInt(Filler1[0]); // Saves first array item to FirstCrn
        FirstHrs = Integer.parseInt(Filler1[1]); // Saves second array item to FirstHrs

        // Splits input for the second class
        Filler2 = SecondClass.split("/");
        SecondCrn = Integer.parseInt(Filler2[0]); // Saves first array item to SecondCrn
        SecondHrs = Integer.parseInt(Filler2[1]); // Saves second array item to SecondHrs

        // Output formatted fee invoice
        System.out.print("\nThank you!\n");
        System.out.print("Fee Invoice prepared for: " + FullName + "\n");
        System.out.print("\n\n");
        System.out.print("\t\tSIMPLE COLLEGE\n");
        System.out.print("\t\tORLANDO FL 10101\n");
        System.out.print("\t\t*************************\n\n");
        System.out.print("\t\tFee Invoice Prepared for:\n");
        System.out.print("\t\t[" + FullName + "][" + Id + "]\n\n");
        System.out.print("\t\t1 Credit Hour = $120.25\n\n");
        System.out.print("\t\tCRN \tCREDIT HOURS\n");
        System.out.print("\t\t" + FirstCrn + "\t" + FirstHrs + "\t\t\t" + "$" + (FirstHrs * CH) + "\n");
        System.out.print("\t\t" + SecondCrn + "\t" + SecondHrs + "\t\t\t" + "$" + (SecondHrs * CH) + "\n\n");
        System.out.print("\t\t\tHealth & id fees\t$35.00\n\n");
        Sy
