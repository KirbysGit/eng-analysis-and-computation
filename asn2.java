import java.util.ArrayList;

public class DriverClass {
    public static void main(String[] args) {
        String fullName = "Erika T. Jones";
        String employeeNumber = "ej789";
        double payRate = 100.0, hoursWorked = 1.0;

        // TA will change the pay rate and the hours worked to test your code
        Employee e = new Employee(fullName, employeeNumber, payRate, hoursWorked);

        System.out.println("\n");
        System.out.println(e); // Test toString method
        System.out.println("\n");
        e.printCheck(); // Print check of Erika T. Jones

        Company company = new Company();
        company.hire(new Employee("Saeed Happy", "sh895", 2, 200));
        company.hire(e);
        company.printCompanyInfo();
        company.hire(new Employee("Enrico Torres", "et897", 3, 150));

        // You may add as many employees to the company as you want.
        // TAs will add their own employees; ensure unique employee numbers.
        company.printCheck("ab784");
        company.deleteEmployeesBySalary(256.36);
        company.reverseEmployees();
        System.out.println(company.SearchByName("WaLiD WiLLiAms"));
        company.printEmployees();
        System.out.println("Bye!");
        System.out.println("\n");
    }
}

class Employee {
    private String fullName;
    private String employeeNumber;
    private double payRate;
    private double hoursWorked;

    public Employee(String fullName, String employeeNumber, double payRate, double hoursWorked) {
        this.fullName = fullName;
        this.employeeNumber = employeeNumber;
        this.payRate = payRate;
        this.hoursWorked = hoursWorked;
    }

    public String toString() {
        return "[" + employeeNumber + "/" + fullName + ", " + hoursWorked + " Hours @ " + payRate + " per hour]";
    }

    private double netPay() {
        double grossPay = hoursWorked * payRate;
        double tax = grossPay * 0.6;
        return grossPay - tax;
    }

    public void printCheck() {
        double netPay = netPay();
        System.out.println("----------------------------------------------------");
        System.out.println("\tEmployee's name:\t" + fullName);
        System.out.println("\tEmployee's number:\t" + employeeNumber);
        System.out.println("\tHourly rate of pay:\t" + payRate);
        System.out.println("\tHours worked:\t\t" + hoursWorked);
        System.out.println("\n");
        System.out.println("\tTotal Gross Pay: \t$" + (payRate * hoursWorked));
        System.out.println("\tDeductions");
        System.out.println("\tTax (6%):\t\t" + (payRate * hoursWorked * 0.6));
        System.out.println("\n");
        System.out.println("\tNet Pay:\t\t" + netPay + " Dollars");
        System.out.println("----------------------------------------------------");
    }

    public String getFN() {
        return fullName;
    }

    public String getEN() {
        return employeeNumber;
    }

    public double getPR() {
        return payRate;
    }

    public double getHW() {
        return hoursWorked;
    }

    public void setFN(String fullName) {
        this.fullName = fullName;
    }

    public void setEN(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setPR(double payRate) {
        this.payRate = payRate;
    }

    public void setHW(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
}

class Company {
    private ArrayList<Employee> employeeList;
    private String companyName;
    private static String companyTaxId;

    public Company() {
        employeeList = new ArrayList<>();
        companyName = "People's Place";
        companyTaxId = "v1rtua7C0mpan1";
    }

    public static String getCTI() {
        return companyTaxId;
    }

    public String getCN() {
        return companyName;
    }

    public static void setCTI(String newTI) {
        companyTaxId = newTI;
    }

    public void setCN(String newCN) {
        companyName = newCN;
    }

    public boolean hire(Employee employee) {
        for (Employee e : employeeList) {
            if (e.getEN().equalsIgnoreCase(employee.getEN())) {
                return false;
            }
        }
        employeeList.add(employee);
        return true;
    }

    public void printCompanyInfo() {
        System.out.println("\n----------------------------------------------------");
        System.out.println("\tCompany Name: " + companyName);
        System.out.println("\tCompany Tax ID: " + companyTaxId);
        System.out.println("\tNumber of Employees: " + employeeList.size());
        System.out.println("----------------------------------------------------");
    }

    public void printEmployees() {
        System.out.println("----------------------------------------------------");
        System.out.println("Employee List:");
        for (Employee employee : employeeList) {
            System.out.println("\t" + employee);
        }
        System.out.println("----------------------------------------------------");
    }

    public int countEmployees(double maxSalary) {
        int counter = 0;
        for (Employee employee : employeeList) {
            if (employee.getPR() * employee.getHW() < maxSalary) {
                counter++;
            }
        }
        return counter;
    }

    public boolean SearchByName(String fullName) {
        for (Employee employee : employeeList) {
            if (employee.getFN().equalsIgnoreCase(fullName)) {
                return true;
            }
        }
        return false;
    }

    public void reverseEmployees() {
        int base = 0;
        int temp = employeeList.size() - 1;
        while (base < temp) {
            Employee tmp = employeeList.get(base);
            employeeList.set(base, employeeList.get(temp));
            employeeList.set(temp, tmp);
            base++;
            temp--;
        }
    }

    public void deleteEmployeesBySalary(double targetSalary) {
        employeeList.removeIf(employee -> (employee.getPR() * employee.getHW()) == targetSalary);
    }

    public void printCheck(String employeeNumber) {
        for (Employee employee : employeeList) {
            if (employee.getEN().equalsIgnoreCase(employeeNumber)) {
                employee.printCheck();
                return;
            }
        }
        System.out.println("NO SUCH EMPLOYEE EXISTS");
    }
}
