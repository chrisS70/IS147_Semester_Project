package com.company;
//package project;
import java.util.Scanner;
import java.util.Random;
//new classes


/*
Scanner Class                                       CHECK
2 Variables,Constants                               CHECK
3 switch statement                                  CHECK
4 conditional operator                              CHECK (OVERTIME HOURS)
5 Operators(logical, arithmetical,assignment)       CHECK
6 String class method                               CHECK (SUBSTR)
7 Math class method                                 CHECK (RANDOM)
8 Loop                                              CHECK (WHILE)
9 Method passing argument by values                 CHECK
9 Return value from method                          CHECK (PASSWORD)
10 Method overloading                               CHECK
11 Array                                            CHECK (EMPLOYEES)
12 Add multiple classes                             CHECK
13 Create an Object                                 CHECK
14 Constructor                                      CHECK
15 Super Keyword                                    CHECK
16 Abstraction
17 Inheritance                                      CHECK
18 Encapsulation                                    CHECK
19 Using Java libarary class                        CHECK
20 Static variable and Static methods               CHECK
21 using this keyword                               CHECK
22 Modifier                                         CHECK
23 Println ,printf methods                          CHECK
 */
//Need:
    //Switch Statement
    //Conditional Operator
    //Overloaded METHOD (not constructor)
    //Abstraction
    //Encapsulation
    //



abstract class Bank{

    private double m_interestRate;

    Bank(){m_interestRate = 0.0;}

    abstract double getInterestRate();
}

class CapitalOne extends Bank{
    private double m_interestRate;

    CapitalOne(){m_interestRate = .05;}
    double getInterestRate(){return m_interestRate;}
}

class PNC extends Bank{
    private double m_interestRate;

    PNC(){m_interestRate = .05;}
    double getInterestRate(){return m_interestRate;}
}

//parent class Bank Account
class BankAccount{
    String m_name;
    int m_accountNum;
    int m_total = 0;

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

    //setters
    void setName( String name){
        m_name = name;
    }

    void setAccNum(int accNum){
        m_accountNum = accNum;
    }

    void deposit ( int deposit){
        m_total += deposit;
    }

    void withdraw (int withdraw){
        m_total -= withdraw;
    }
}

//child class of BankAccount named employeeAccount
class EmployeeAccount extends BankAccount{

    static final int overtimeRate = 2;

    private int m_socialNum;
    private int m_EmployerID;
    private int m_hours;
    private int m_age;
    private double m_tax;
    private double m_payRate;
    //private Bank m_myBank;

    public int getSSN(){ return this.m_socialNum;}
    public int getEmpID(){ return m_EmployerID;}
    public int getHours(){return m_hours;}
    public double getTax(){ return m_tax;}
    public double getPayRate(){ return m_payRate;}

    //default constructor
    EmployeeAccount(){
        //call the parent class constructor
        super();
        m_socialNum = 0;
        m_EmployerID = 0;
        m_hours = 0;
        m_tax = 0;
        m_payRate = 0;
        //m_myBank = null;
    }

    //overloaded constructor
    EmployeeAccount(String name, int age, int accNum, int social, int id, int hours, double tax, double rate){

        super (name,accNum);
        m_socialNum = social;
        m_EmployerID = id;
        m_hours = hours;
        m_tax = tax;
        m_payRate = rate;
        m_age = age;
        //m_myBank = myBank;
    }

    double calcWeeklyPay(){
        return m_hours * m_payRate;
    }
    double calcWeeklyPay(int overtimeHours){return m_hours * m_payRate + overtimeRate * overtimeHours;}
}



public class Main {

    public static void main(String[] args) {
//Ade Requirements: Scanner Class, Variables and Constants, Switch Statement, Condition Operator

        final int maxEmployees = 100;

        //array of employeeBank accounts IOT keep track of all info with a max at 100;
        EmployeeAccount[] allEmployees = new EmployeeAccount[maxEmployees];

        //this variable keeps track of what index is open in the array
        int index = 0;
        Scanner input = new Scanner(System.in);

        double netPay;
        double grossPay;
        double TaxibleIncome;   //????

        final double Payrate = 9;
        final double StateTax = 0.2;
        final double FederalTax = 0.10;
        String firstName = "";
        String lastName = "";
        String password = "";

        int enter;
        int age = 0;
        int SSS = 0;
        int ID = 0;
        int overtimeHours = 0;
        int hoursWorked;


        //Bank myBank;
        final String systemName = "Akurate";


        System.out.printf("Welcome to %s payroll system, please follow the instructions carefully and enter the appropriate values, enter 1 to begin, enter any other value to cancel. \n", systemName);
        enter = input.nextInt();
        do {
            switch (enter) {
                case (1):
                    System.out.print("Enter your First name: ");
                    firstName = input.next();
                    System.out.print("Enter your Last name: ");
                    lastName = input.next();

                    //calling password method
                    password = password(firstName, lastName);

                    System.out.print("Age: ");
                    age = input.nextInt();

                    System.out.print("Enter your Social Security Number: ");
                    SSS = input.nextInt();

                    System.out.print("Enter your Employer ID: ");
                    ID = input.nextInt();


                    /* Abstraction****


                    System.out.println("Enter your Bank: 1- Capital One, 2- PNC");
                    int bankNum;
                    do {
                        bankNum = input.nextInt();
                    }while(bankNum < 1 || bankNum > 2);
                    if(bankNum == 1) CapitalOne myBank = new Bank();
                    else PNC myBank = new Bank();

                    ******/


                    break;

                default:
                    System.out.println("Thank you, Have a nice day");
                    System.exit(1);
                    break;
            }
            //Chris Requirements: Operators, String Class Method, Math Class Method, Loop, Method Passing, Return Value from Method

            //calling method
            sayHello(firstName, lastName);

            System.out.println("Enter total biweekly hours worked: ");
            hoursWorked = input.nextInt();
            while (hoursWorked <= 0) {
                System.out.println("Enter positive number of hours: ");
                hoursWorked = input.nextInt();
                hoursWorked = hoursWorked + 1;      //????????
            }
            //Uses conditional operator to assign number of overtime hours
            overtimeHours = (hoursWorked > 80) ? hoursWorked - 80 : 0;

            //creating Employee Object for this Account
            String name = firstName + " " + lastName;
            EmployeeAccount employee1 = new EmployeeAccount(name, age, index, SSS, ID, hoursWorked, StateTax, Payrate);
            allEmployees[index] = employee1;

            //Calculates gross pay
            if(overtimeHours == 0) grossPay = allEmployees[index].calcWeeklyPay();
            else grossPay = allEmployees[index].calcWeeklyPay(overtimeHours);


            //Calculate net pay
            netPay = grossPay*(1 - (FederalTax + StateTax));

            //Outputs payments for pay period
            System.out.printf("Your gross pay for this pay period is %f\n", grossPay);
            System.out.printf("Your net pay for this pay period is %f\n", netPay);

            //Increments index, asks if user wants to continue
            index = index + 1;
            System.out.println("Would you like to add another account? (1 - yes, 2-no)");
            enter = input.nextInt();

        } while (enter == 1);

        System.out.println("Thank you, Have a nice day");
    }

    static String password(String firstName, String lastName) {
        Random rand = new Random();
        int randomNumber = (int)Math.ceil(rand.nextInt(250)) + 40; //+ () (Math.random() * 250);
        System.out.println("Random Number: " + randomNumber);
        String pass = firstName.substring(firstName.length() - 1).toUpperCase() + randomNumber + lastName.substring(0, lastName.length()).toLowerCase();
        System.out.println("Password: " + pass);

        return pass;
    }
    static void sayHello(String firstName, String lastName) {
        System.out.println("Hello, " + (firstName + " " +lastName));
    }
}