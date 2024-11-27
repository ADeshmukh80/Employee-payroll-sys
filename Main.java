import java.util.ArrayList;
import java.util.Scanner;
// Abstract class to define the blueprint of an Employee
//Abstraction - The Employee class is abstract and hides implementation details for `calculateSalary()`.
abstract class Employee {
    private String name;
    private int id;

    // Constructor to initialize Employee details
    public Employee(String name,int id){
        this.name=name;
        this.id=id;
    }
    // Getter and setter method to get the employee's name and id
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
    // Abstract method to calculate salary, must be implemented by subclasses
    //Polymorphism- Subclasses must provide their own implementation of this method.
    public abstract double calculateSalary();

    // Override the toString method to display Employee details
    //Encapsulation - Private fields (`name`, `id`) are accessed via methods.
    @Override
    public String toString(){
        return "name="+name+" \nid="+id+" \nSalary="+calculateSalary();
        
    }
}

//`fullTimeEmp` inherits from the `Employee` class(Inheritance).
class fullTimeEmp extends Employee{
    private double mSalary;

    // Constructor to initialize full-time employee details
    public fullTimeEmp(String name,int id,double mSalary){
        super(name, id);  // Call the parent class constructor
        this.mSalary=mSalary;
    }

    // Implement the calculateSalary method for full-time employees
    @Override
    public double calculateSalary(){
        return mSalary;
    }
}
class partTimeEmp extends Employee{
    private int hrs;          // Number of hours worked by the part-time employee
    private double hrsRate;   // Hourly rate for the part-time employee

    // Constructor to initialize part-time employee details
    public partTimeEmp(String name,int id,int hrs,double hrsRate){
        super(name, id);      // Call the parent class constructor
        this.hrs=hrs;
        this.hrsRate=hrsRate;
    }

    // Implement the calculateSalary method for part-time employees
    @Override
    public double calculateSalary(){
        return hrs*hrsRate;
    }
}

// Payroll system to manage employees
// Encapsulation - The list of employees is private and accessed via methods.
class payrollSys{
    private ArrayList<Employee>emp;   // List to store Employee objects

    // Constructor to initialize the payroll system
    public payrollSys(){
        emp=new ArrayList<>();
    }
    public void addEmp(Employee e){  //add employee
        emp.add(e);
    }
    public void removeEmp(int id){ 
        Employee empRemove=null;  // Variable to store the employee to be removed

        for(Employee e:emp){
            if(e.getId()==id){
                empRemove=e;
                break;
            }
        }
        if(empRemove!=null){         //remove employee if found
            emp.remove(empRemove);
        }
    }

    //to display all employees
    public void displayEmp(){
        for(Employee e:emp){
            System.out.println(e);
            System.out.println("-----");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        payrollSys payroll = new payrollSys();

        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println("\nChoose an Option:");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Display Employees");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the leftover newline character

            switch (choice) {
                case 1: // Add Employee
                    System.out.println("Enter Employee Type:");
                    System.out.println("1. Full-Time");
                    System.out.println("2. Part-Time");
                    System.out.print("Enter your choice: ");
                    int empType = sc.nextInt();
                    sc.nextLine(); // Consume the leftover newline

                    if (empType == 1) { // Full-Time Employee
                        System.out.print("Enter Employee Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Employee ID: ");
                        int id = sc.nextInt();

                        System.out.print("Enter Monthly Salary: ");
                        double mSalary = sc.nextDouble();

                        fullTimeEmp ft = new fullTimeEmp(name, id, mSalary);
                        payroll.addEmp(ft);
                        System.out.println("Full-Time Employee added successfully!");

                    } else if (empType == 2) { // Part-Time Employee
                        System.out.print("Enter Employee Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Employee ID: ");
                        int id = sc.nextInt();

                        System.out.print("Enter Number of Hours Worked: ");
                        int hrs = sc.nextInt();

                        System.out.print("Enter Hourly Rate: ");
                        double hrsRate = sc.nextDouble();

                        partTimeEmp pt = new partTimeEmp(name, id, hrs, hrsRate);
                        payroll.addEmp(pt);
                        System.out.println("Part-Time Employee added successfully!");
                    } else {
                        System.out.println("Invalid Employee Type.");
                    }
                    break;

                case 2: // Remove Employee
                    System.out.print("Enter Employee ID to remove: ");
                    int empIdToRemove = sc.nextInt();
                    payroll.removeEmp(empIdToRemove);
                    break;

                case 3: // Display Employees
                    System.out.println("\n---- Employee Details ----");
                    payroll.displayEmp();
                    break;

                case 4: // Exit
                    continueProgram = false;
                    System.out.println("Exiting the program...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            // Ask if the user wants to continue (only if not exiting)
            if (continueProgram) {
                System.out.print("\nDo you want to continue? (y/n): ");
                String res = sc.next();
                if (res.equalsIgnoreCase("n")) {
                    continueProgram = false;
                }
            }
        }

        sc.close();

    }
    
}