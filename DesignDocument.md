# Payroll Generator Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram

Place your class diagram below. Make sure you check the fil in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)

```mermaid
classDiagram
    direction TB
%% Relationships
    IEmployee <|.. Employee
    Employee <|-- HourlyEmployee
    Employee <|-- SalaryEmployee
    IPayStub <|.. PayStub
    PayrollGenerator --> FileUtil
    PayrollGenerator --> Builder
    PayrollGenerator --> Employee
    PayrollGenerator --> PayStub
    Builder --> Employee
    Builder --> TimeCard
    TimeCard --> Employee
    PayStub --> Employee
    %% Interfaces
    class IEmployee {
        <<interface>>
        +String getName()
        +String getId()
        +double getPayRate()
        +double getYTDEarnings()
        +double getYTDTaxesPaid()
        +double getPretaxDeductions()
        +double calculatePay()
    }

    class IPayStub {
        <<interface>>
        +String getEmployeeId()
        +double getGrossPay()
        +double getTaxesWithheld()
        +double getNetPay()
    }

    %% Abstract Class
    class Employee {
        <<abstract>>
        -String name
        -String id
        -double payRate
        -double ytdEarnings
        -double ytdTaxesPaid
        -double pretaxDeductions
        +String getName()
        +String getId()
        +double getPayRate()
        +double getYTDEarnings()
        +double getYTDTaxesPaid()
        +double getPretaxDeductions()
        +abstract double calculatePay()
    }

    %% Concrete Classes
    class HourlyEmployee {
        -double hoursWorked
        +double calculatePay()
    }
    
    class SalaryEmployee {
        +double calculatePay()
    }

    class TimeCard {
        -String employeeId
        -double hoursWorked
    }

    class PayStub {
        +String employeeId
        +double grossPay
        +double taxesWithheld
        +double netPay
    }

    class PayrollGenerator {
        +main(String[] args)
    }

    class FileUtil {
        +List<String> readFile(String filename)
        +void writeFile(String filename, List<String> content)
    }

    class Builder {
        +List<Employee> parseEmployees(String filename)
        +List<TimeCard> parseTimeCards(String filename)
    }
```



## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test that the `Employee` class properly returns `name` from `getName()`
2. Test that the `Employee` class properly returns `id` from `getId()`
---
3. Test that SalaryEmployee calculates pay correctly based on fixed payRate.
4. Confirms that getEmployeeType() returns the correct type for a salaried employee.
5. Test that PayrollGenerator correctly processes employee.csv and time_cards.csv into pay_stubs.csv.
6. Confirms that salaried employees are paid even if they work 0 hours.
7. Ensures that the program gracefully handles negative hours by returning null.
8. Validates the toCSV() method to ensure it outputs the correct string format.
9. Ensures correct payroll calculations for employees with high salaries.



## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.
```mermaid
classDiagram
    direction TB
%% Relationships
    IEmployee <|.. Employee
    Employee <|-- HourlyEmployee
    Employee <|-- SalaryEmployee
    IPayStub <|.. PayStub
    PayrollGenerator --> FileUtil
    PayrollGenerator --> Builder
    PayrollGenerator --> Employee
    PayrollGenerator --> PayStub
    Builder --> Employee
    Builder --> TimeCard
    TimeCard --> Employee
    PayStub --> Employee
    %% Interfaces
    class IEmployee {
        <<interface>>
        +String getName()
        +String getId()
        +double getPayRate()
        +double getYTDEarnings()
        +double getYTDTaxesPaid()
        +double getPretaxDeductions()
        +double calculatePay()
    }

    class IPayStub {
        <<interface>>
        +String getEmployeeId()
        +double getGrossPay()
        +double getTaxesWithheld()
        +double getNetPay()
    }

    %% Abstract Class
    class Employee {
        <<abstract>>
        -String name
        -String id
        -double payRate
        -double ytdEarnings
        -double ytdTaxesPaid
        -double pretaxDeductions
        +String getName()
        +String getId()
        +double getPayRate()
        +double getYTDEarnings()
        +double getYTDTaxesPaid()
        +double getPretaxDeductions()
        +abstract double calculatePay()
    }

    %% Concrete Classes
    class HourlyEmployee {
        -double hoursWorked
        +double calculatePay()
    }
    
    class SalaryEmployee {
        +double calculatePay()
    }

    class TimeCard {
        -String employeeId
        -double hoursWorked
    }

    class PayStub {
        +String employeeId
        +double grossPay
        +double taxesWithheld
        +double netPay
    }

    class PayrollGenerator {
        +main(String[] args)
    }

    class FileUtil {
        +List<String> readFile(String filename)
        +void writeFile(String filename, List<String> content)
    }

    class Builder {
        +List<Employee> parseEmployees(String filename)
        +List<TimeCard> parseTimeCards(String filename)
    }
```




## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning new information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 

---
During the development of the payroll generator, my design evolved significantly. One key change was switching from a List to a Map for time card data, which made lookups more efficient and simplified the payroll calculations. I also had to adjust the toCSV() method in the PayStub class to ensure the output matched the required format, including trailing zeroes, which taught me the importance of precise formatting.

The biggest challenge was debugging discrepancies between expected and actual outputs, which required careful testing and detailed understanding of the logic. From this process, I learned the importance of thorough testing and attention to detail. If I were to do this again, I would focus more on designing better test cases upfront to catch issues earlier.