package student;

public class SalaryEmployee extends Employee {
    /**
     * Constructor for SalaryEmployee.
     *
     * @param name             The employee's name.
     * @param id               The employee's unique ID.
     * @param payRate          The annual salary (divided by pay periods for bi-weekly pay).
     * @param ytdEarnings      Year-to-date earnings.
     * @param ytdTaxesPaid     Year-to-date taxes paid.
     * @param pretaxDeductions Pretax deductions.
     */
    public SalaryEmployee(String name, String id, double payRate,
                          double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
    }

    /**
     * Abstract method to calculate gross pay for the employee.
     * Each subclass (HourlyEmployee, SalaryEmployee) must implement this.
     *
     * @param hoursWorked The hours worked for the pay period.
     * @return The gross pay.
     */
    @Override
    protected double calculateGrossPay(double hoursWorked) {
        if (hoursWorked <= 0) {
            return 0;
        }
        return getPayRate() / 24;
    }

    /**
     * @return the type of the employee
     */
    @Override
    public String getEmployeeType() {
        return "SALARY";
    }
}
