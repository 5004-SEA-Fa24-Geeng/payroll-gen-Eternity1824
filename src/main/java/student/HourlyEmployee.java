package student;

/**
 * class for HourlyEmployee.
 */
public class HourlyEmployee extends Employee {
    /** workers worked hours. */
    private double hoursWorked;

    /**
     * Constructor for HourlyEmployee.
     *
     * @param name             The employee's name.
     * @param id               The employee's unique ID.
     * @param payRate          The hourly pay rate.
     * @param ytdEarnings      Year-to-date earnings.
     * @param ytdTaxesPaid     Year-to-date taxes paid.
     * @param pretaxDeductions Pretax deductions.
     */
    public HourlyEmployee(String name, String id, double payRate,
                          double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
        this.hoursWorked = 0; // Default hours worked is 0
    }

    /**
     * Calculates the gross pay for an hourly employee.
     * - Regular pay: `payRate * hoursWorked` (up to 40 hours)
     * - Overtime pay: `payRate * 1.5 * overtimeHours`
     *
     * @param hoursWorked The hours worked for the pay period.
     * @return The gross pay.
     */
    @Override
    protected double calculateGrossPay(double hoursWorked) {
        if (hoursWorked <= 40) {
            return hoursWorked * getPayRate();
        } else {
            double overtimeHours = hoursWorked - 40;
            return (40 * getPayRate()) + (overtimeHours * getPayRate() * 1.5);
        }
    }

    /**
     * @return the type of employee
     */
    @Override
    public String getEmployeeType() {
        return "HOURLY";
    }

    /**
     * Sets the hours worked for the employee.
     *
     * @param hoursWorked The number of hours worked.
     */
    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
}
