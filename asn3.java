import java.util.Scanner;

public class u6_hw4 {
    public static void main(String[] args) {
        int numRows, numColumns;
        int index, start, end;
        char charAtIndex;
        int length;
        String subSequence;

        Scanner myScan = new Scanner(System.in);
        System.out.print("Enter how many rows and how many columns to load: ");
        numRows = myScan.nextInt();
        numColumns = myScan.nextInt();

        Code codeObject = new Code(numRows, numColumns);
        codeObject.loadCodeArray(numRows, numColumns);

        // Commented out for the sake of the sample output
        // codeObject.printCodeArray(numRows, numColumns);

        System.out.print("\n\nTesting charAt: Enter your index [a number greater or equal to 0 and less or equal to ");
        System.out.print((numRows * numColumns - 1) + "]: ");
        index = myScan.nextInt();
        charAtIndex = codeObject.charAt(index);
        System.out.println("The character located at index " + index + " is: " + charAtIndex);

        // Testing length
        length = numRows * numColumns;
        System.out.println("\nTesting length: there are " + length + " characters.");

        // Testing subSequence
        System.out.print("\n\nTest subSequence: Enter start and end indexes: ");
        start = myScan.nextInt();
        end = myScan.nextInt();
        subSequence = codeObject.subSequence(start, end).toString();
        System.out.println("The subsequence is: " + subSequence);

        System.out.print("\nGoodbye!");
    }

    static class Code implements CharSequence {
        private int[][] codeArray;
        private int numRows, numColumns;

        public Code(int numRows, int numColumns) {
            this.numRows = numRows;
            this.numColumns = numColumns;
            codeArray = new int[numRows][numColumns];
        }

        public void loadCodeArray(int numRows, int numColumns) {
            Scanner myScan = new Scanner(System.in);
            for (int i = 0; i < numRows; i++) {
                System.out.print("Enter row " + (i + 1) + ": ");
                for (int j = 0; j < numColumns; j++) {
                    codeArray[i][j] = myScan.nextInt();
                }
            }
        }

        // Commented out for sample input comparison
        /* 
        public void printCodeArray(int numRows, int numColumns) {
            System.out.println("\n_________________\n");
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numColumns; j++) {
                    System.out.print(codeArray[i][j] + "\t");
                }
                System.out.println();
            }
        }
        */

        @Override
        public char charAt(int index) {
            int row = index / numColumns;
            int col = index % numColumns;
            return (char) codeArray[row][col];
        }

        @Override
        public int length() {
            return numRows * numColumns;
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            StringBuilder sb = new StringBuilder();
            for (int i = start; i <= end; i++) {
                sb.append(charAt(i));
            }
            return sb.toString();
        }
    }
}
