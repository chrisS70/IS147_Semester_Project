package com.company;
import java.util.Scanner;
import java.util.Random;

//parent class Bank Account
class BankAccount{
    String m_name;
    int m_accountNum;
    double m_total = 0;

    //default constructor
    BankAccount(){
        this.m_name = " ";
        this.m_accountNum = 0;
    }

    //OVERLOADED constructor
    BankAccount(String name, int accNum){
        this.m_name = name;
        this.m_accountNum = accNum;
    }

    //getters
    public double getBalance(){ return m_total;}
    public int getAccountNumber(){ return m_accountNum;}

    //setters
    public void setName( String name){m_name = name;}
    public void setAccNum(int accNum){m_accountNum = accNum;}
    public void deposit ( double deposit){m_total += deposit;}
    public void withdraw (double withdraw){m_total -= withdraw;}
}

//child class of BankAccount named employeeAccount
class EmployeeAccount extends BankAccount{

    static final int overtimeRate = 2;
    static final private double m_tax = .3;

    private int m_socialNum;
    private int m_EmployerID;
    private int m_hours;
    private int m_age;
    private double m_payRate;
    private int m_overtimeHours;
    private String m_password;

    //default constructor
    EmployeeAccount(){
        super();
        m_socialNum = 0;
        m_EmployerID = 0;
        m_hours = 0;
        m_payRate = 0;
        m_overtimeHours = 0;
        m_password = "";
    }

    //overloaded constructor
    EmployeeAccount(String name, int age, int accNum, int social, int id, int hours, int overtimeHours, double rate, String password){
        super (name,accNum);
        m_socialNum = social;
        m_EmployerID = id;
        m_hours = hours;
        m_payRate = rate;
        m_age = age;
        m_overtimeHours = overtimeHours;
        m_password = password;
    }


    //Getters
    public String getName(){ return m_name;}
    public int getSSN(){ return this.m_socialNum;}
    public int getEmpID(){ return this.m_EmployerID;}
    public int getHours(){return this.m_hours;}
    public double getTax(){ return this.m_tax;}
    public double getPayRate(){ return this.m_payRate; }
    public int getOvertime(){ return m_overtimeHours; }

    //Misc functions

    //OVERLOADED FUNCTIONS
    public double calcWeeklyPay(){return m_hours * m_payRate;}
    public double calcWeeklyPay(int overtimeHours){return m_hours * m_payRate + overtimeRate * overtimeHours;}

    public Boolean checkPassword(String pass){
        if(m_password.equals(pass))
            return true;
        return false;
    }

    public void printEmployee(){
        System.out.println("Name: " + m_name);
        System.out.println("Age: " + m_age);
        System.out.println("SSN: " + m_socialNum);
        System.out.println("Account Number: " + m_accountNum);
        System.out.println("Employer ID: " + m_EmployerID);
        System.out.println("");
        System.out.println("This employee worked " + m_hours + " during this period, with " + m_overtimeHours + " overtime hours.");
    }
}

public class Main {

    public static void main(String[] args) {
//Ade Requirements: Scanner Class, Variables and Constants, Switch Statement, Condition Operator

        final int maxEmployees = 100;
        final double StateTax = 0.2;
        final double FederalTax = 0.10;
        final String systemName = "Akurate";

        //array of employeeBank accounts IOT keep track of all info with a max at 100;
        EmployeeAccount[] allEmployees = new EmployeeAccount[maxEmployees];
        //this variable keeps track of what index is open in the array
        int index = 0;
        Scanner input = new Scanner(System.in);
        double netPay;
        double grossPay;
        int enter;
        int overtimeHours = 0;

        do {
            System.out.printf("\nWelcome to %s payroll system, please follow the instructions carefully and enter the appropriate values.\n", systemName);
            System.out.println("Enter 1 to create new employee, enter 2 to check or modify an employees bank statement, or enter any other value to cancel. ");
            enter = input.nextInt();
            switch (enter) {

                case (1): // Create new employee account
                    allEmployees[index] = CreateEmployee(index);

                    overtimeHours = allEmployees[index].getOvertime();
                    //Calculates gross pay
                    if(overtimeHours == 0) grossPay = allEmployees[index].calcWeeklyPay();
                    else grossPay = allEmployees[index].calcWeeklyPay(overtimeHours);

                    //Calculate net pay and deposits into their bank account
                    netPay = grossPay*(1 - (FederalTax + StateTax));
                    allEmployees[index].deposit(netPay);

                    //Outputs information for Employee
                    System.out.println("This employee worked " + allEmployees[index].getHours() + " hours during this period, with " + allEmployees[index].getOvertime() + " overtime hours.");
                    System.out.printf("Your gross pay for this pay period is %f\n", grossPay);
                    System.out.printf("Your net pay for this pay period is %f\n", netPay);

                    System.out.println(netPay + " has been successfully deposited in your bank account!\n");

                    index++;
                    break;

                case(2): // Check / Modify existing bank account
                    //Base case where there are no employees
                    if(index == 0){
                        System.out.println("There are currently no employee bank accounts registered. Please create some and come back!");
                        break;
                    }

                    //Prints list of current employees
                    System.out.println("There are currently " + index + " employees in the system.");
                    System.out.println("Which employee would you like to check the bank account of?");
                    int i = 0;
                    while(i < index){
                        int num = i+1;
                        System.out.println(num + ". " + allEmployees[i].getName());
                        i++;
                    }

                    //Employee Selection
                    System.out.println("\nWhich employee would you like to modify? Enter the number before their name.");
                    int choice = input.nextInt();
                    while(choice < 1 || choice > index){
                        System.out.println("Enter a valid number.");
                        choice = input.nextInt();
                    }
                    EmployeeAccount currentEmployee = allEmployees[choice-1];

                    //Password verification
                    System.out.println("We must first verify your access to this account. Please enter your password. (Case sensitive)");
                    String attemptedPassword = input.next();

                    if(currentEmployee.checkPassword(attemptedPassword)){
                        //Let's the user do multiple things within the account
                        do{
                            DisplayOptions();
                            choice = input.nextInt();
                            double amount;
                            switch(choice){
                                case(1): //Check Balance
                                    System.out.println("Current Balance = $" + currentEmployee.getBalance());
                                    break;
                                case(2): //Account number
                                    System.out.println("Account Number: " + currentEmployee.getAccountNumber());
                                    break;
                                case(3): //Deposit
                                    System.out.println("How much would you like to deposit?");
                                    amount = input.nextDouble();
                                    currentEmployee.deposit(amount);
                                    System.out.println("\nSuccessfully deposited $" + amount + " into this employees account");
                                    break;
                                case(4): //Withdraw
                                    System.out.println("How much would you like to withdraw?");
                                    amount = input.nextDouble();
                                    currentEmployee.withdraw(amount);
                                    System.out.println("\nSuccessfully withdrew $" + amount + " from this employees account");
                                    break;
                                case(5): //Exit Account
                                    System.out.println("Logging out of " + currentEmployee.getName() + "'s Account");
                                    break;
                                default:
                                    System.out.println("Incorrect option. Exiting system to avoid hackers!");
                                    choice = 5;
                                    break;
                            }
                        }while(choice!= 5);
                    }
                    //For incorrect password
                    else
                        System.out.println("Incorrect Password. Access Denied.\n");

                    break; //Exits outer switch

                default:
                    System.out.println("Thank you, Have a nice day");
                    System.exit(1);
                    break;
            }//End Switch

            //Asks if user wants to continue
            System.out.println("\nWould you like to continue? (1 - yes, 2-no)");
            enter = input.nextInt();

        } while (enter == 1);

        System.out.println("Thank you, Have a nice day");
    }





    static String password(String firstName, String lastName) {
        Random rand = new Random();
        int randomNumber = (int)Math.ceil(rand.nextInt(250)) + 40; //+ () (Math.random() * 250);
        String pass = firstName.substring(firstName.length() - 1).toUpperCase() + randomNumber + lastName.toLowerCase();
        System.out.println("The password for this account is: " + pass + "\n");

        return pass;
    }
    static void sayHello(String firstName, String lastName) {
        System.out.println("Hello, " + (firstName + " " +lastName) + "\n");
    }

    static void DisplayOptions(){
        System.out.println("1. Check Balance");
        System.out.println("2. Get account number");
        System.out.println("3. Deposit Funds");
        System.out.println("4. Withdraw Funds");
        System.out.println("5. Exit the modification");
        System.out.println("What action would you like to take?  (Be Careful!)");
    }

    static EmployeeAccount CreateEmployee(int index){
        double payrate = 9;
        int age = 0;
        int SSS = 0;
        int ID = 0;
        int overtimeHours = 0;
        int hoursWorked = 0;
        String firstName = "";
        String lastName = "";
        String pass = "";
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your First name: ");
        firstName = input.next();
        System.out.print("Enter your Last name: ");
        lastName = input.next();

        System.out.println("Enter your payrate: ");
        payrate = input.nextDouble();

        System.out.print("Age: ");
        age = input.nextInt();

        System.out.print("Enter your Social Security Number: ");
        SSS = input.nextInt();

        System.out.print("Enter your Employer ID: ");
        ID = input.nextInt();

        pass = password(firstName, lastName);

        //calling sayHello method
        sayHello(firstName, lastName);

        System.out.println("Enter total biweekly hours worked: ");
        hoursWorked = input.nextInt();

        //User inputs correct number of hours
        while (hoursWorked <= 0) {
            System.out.println("Enter positive number of hours: ");
            hoursWorked = input.nextInt();
        }

        //Uses conditional operator to assign number of overtime hours
        overtimeHours = (hoursWorked > 80) ? hoursWorked - 80 : 0;

        //creating Employee Object for this Account
        String name = firstName + " " + lastName;
        EmployeeAccount employee1 = new EmployeeAccount(name, age, index, SSS, ID, hoursWorked, overtimeHours, payrate, pass);
        return employee1;
    }
}