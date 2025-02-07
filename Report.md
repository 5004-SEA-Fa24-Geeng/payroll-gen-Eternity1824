# Report for Payroll Generator

This report helps you demonstrate your understanding of the concepts. You should write this report after you have completed the project. 

## Technical Questions

1. What does CSV stand for?
* CSV stands for Comma-Separated Values, a file format used to store tabular data in plain text. Each line represents a row, and columns are separated by commas.

2. Why would you declare `List<IEmployee>` instead of `ArrayList<HourlyEmployee>`?
* Declaring List<IEmployee> allows for flexibility and polymorphism. You can store any type of IEmployee (e.g., HourlyEmployee, SalaryEmployee) in the list. This makes the code more reusable and less tightly coupled to specific implementations. If you use ArrayList<HourlyEmployee>, you restrict the list to only HourlyEmployee objects.

3. When you have one class referencing another object, such as storing that object as one of the attributes of the first class - what type of relationship is that called (between has-a and is-a)?
* This is called a has-a relationship (composition or aggregation). It describes a scenario where one class owns or uses another as part of its structure.

4. Can you provide an example of a has-a relationship in your code (if one exists)?
* In your code, the PayStub class has-a relationship with the Employee it represents because the employee’s name, net pay, taxes, and other details are stored as attributes within PayStub.

5. Can you provide an example of an is-a relationship in your code (if one exists)?
* The HourlyEmployee and SalaryEmployee classes have an is-a relationship with IEmployee because they implement the IEmployee interface. This signifies that they are specific types of employees.

6. What is the difference between an interface and an abstract class?
* Interface:  
  •	Defines only method signatures (no implementations, except for default methods in modern Java).  
  •	A class can implement multiple interfaces.
* Abstract class:  
  •	Can have both abstract methods and concrete methods.  
  •	A class can only inherit from one abstract class (single inheritance).  

7. What is the advantage of using an interface over an abstract class?  
The main advantage of an interface is multiple inheritance: a class can implement multiple interfaces, allowing for greater flexibility in design. It is also useful for defining a contract or behavior that multiple unrelated classes can implement.  
fix: ``List<Integer> numbers = new ArrayList<>();``  

8. Is the following code valid or not? `List<int> numbers = new ArrayList<int>();`, explain why or why not. If not, explain how you can fix it.  
   The PayrollGenerator class serves as the driver for your application. Its main method initializes the program, processes employee and time card data, and generates pay stubs.  

9. Which class/method is described as the "driver" for your application?  
   The PayrollGenerator class serves as the driver for your application. Its main method initializes the program, processes employee and time card data, and generates pay stubs.  


10. How do you create a temporary folder for JUnit Testing?  
    Use the TemporaryFolder rule from JUnit 4 or @TempDir annotation in JUnit 5.  
```java
@TempDir
Path tempDir;

@Test
public void testUsingTempDir() throws IOException {
Path tempFile = Files.createFile(tempDir.resolve("testfile.txt"));
assertTrue(Files.exists(tempFile));
}
```
## Deeper Thinking 

Salary Inequality is a major issue in the United States. Even in STEM fields, women are often paid less for [entry level positions](https://www.gsb.stanford.edu/insights/whats-behind-pay-gap-stem-jobs). However, not paying equal salary can hurt representation in the field, and looking from a business perspective, can hurt the company's bottom line has diversity improves innovation and innovation drives profits. 

Having heard these facts, your employer would like data about their salaries to ensure that they are paying their employees fairly. While this is often done 'after pay' by employee surveys and feedback, they have the idea that maybe the payroll system can help them ensure that they are paying their employees fairly. They have given you free reign to explore this idea.

Think through the issue / making sure to cite any resources you use to help you better understand the topic. Then write a paragraph on what changes you would need to make to the system. For example, would there be any additional data points you would need to store in the employee file? Why? Consider what point in the payroll process you may want to look at the data, as different people could have different pretax benefits and highlight that. 

The answer to this is mostly open. We ask that you cite at least two sources to show your understanding of the issue. The TAs will also give feedback on your answer, though will be liberal in grading as long as you show a good faith effort to understand the issue and making an effort to think about how your design to could help meet your employer's goals of salary equity. 


---
To address salary inequality within the payroll system, the first change would be to include additional data points in the employee file, such as gender, years of experience, education level, and job role or department. These data points would allow the system to analyze salaries across different demographic groups to identify disparities. For example, during the payroll process, we could calculate average salaries by job role and gender, flagging significant pay gaps. Additionally, a report could be generated after payroll processing to highlight potential inequities in compensation for employees with similar qualifications and roles.