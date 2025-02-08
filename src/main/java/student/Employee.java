package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Abstract base class representing an Employee.
 * Implements the IEmployee interface and provides common functionality
 * for different types of employees.
 */
public abstract class Employee implements IEmployee {
    /** The name of the employee. */
    private final String name;
    /** The unique identifier for the employee. */
    private final String id;
    /** The pay rate for the employee (hourly for hourly employees, annual for salaried employees). */
    private final double payRate;
    /** The pretax deductions for the employee (e.g., healthcare, retirement contributions). */
    private final double pretaxDeductions;
    /** The year-to-date earnings of the employee. */
    private double ytdEarnings;
    /** The year-to-date taxes paid by the employee. */
    private double ytdTaxesPaid;

    /**
     * Constructs an Employee object with the given details.
     *
     * @param name             The name of the employee.
     * @param id               The unique identifier for the employee.
     * @param payRate          The pay rate for the employee.
     * @param ytdEarnings      The year-to-date earnings of the employee.
     * @param ytdTaxesPaid     The year-to-date taxes paid by the employee.
     * @param pretaxDeductions The pretax deductions for the employee (e.g., healthcare, retirement contributions).
     */
    public Employee(String name, String id, double payRate, double ytdEarnings,
                    double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    /**
     * Gets the employee's name.
     *
     * @return the name of the employee
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the employee's ID.
     *
     * @return the ID of the employee
     */
    @Override
    public String getID() {
        return id;
    }

    /**
     * Gets the employee's pay rate.
     *
     * @return the pay rate of the employee
     */
    @Override
    public double getPayRate() {
        return payRate;
    }

    /**
     * Gets the YTD earnings of the employee.
     *
     * @return the YTD earnings of the employee
     */
    @Override
    public double getYTDEarnings() {
        return ytdEarnings;
    }

    /**
     * Gets the YTD taxes paid by the employee.
     *
     * @return the YTD taxes paid by the employee
     */
    @Override
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid;
    }

    /**
     * Gets pretax deductions for the employee. Yes, on a normal paycheck this varies as either set
     * amounts or percents, and often more than one type of deduction.
     * <p>
     * For now, you can just assume a single pretax deduction as a whole dollar amount.
     *
     * @return the pretax deductions for the employee
     */
    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions;
    }

    /**
     * Runs payroll for the employee, updating YTD earnings and taxes.
     * Calculates pay based on the employee type (HOURLY or SALARY).
     *
     * @param hoursWorked The hours worked for the pay period.
     * @return An IPayStub object representing the pay stub for the current pay period, or null if hoursWorked < 0.
     */
    @Override
    public IPayStub runPayroll(double hoursWorked) {
//        if (hoursWorked < 0) {
//            return null; // Skip payroll for negative hours
//        }

        if (hoursWorked <= 0) {
            // Return a pay stub with zero values but don't update YTD
            return new PayStub(getID(), 0.0, 0.0, getYTDEarnings(), getYTDTaxesPaid());
        }

        // Calculate gross pay
        double grossPay = calculateGrossPay(hoursWorked);
//        System.out.println("Gross Pay: " + grossPay);

        // Deduct pretax deductions
        BigDecimal netPay = BigDecimal.valueOf(grossPay)
                .subtract(BigDecimal.valueOf(pretaxDeductions))
                .setScale(2, RoundingMode.HALF_UP);
//        System.out.println("Net Pay Before Taxes: " + netPay);

        // Calculate taxes
        BigDecimal taxes = netPay.multiply(BigDecimal.valueOf(0.2265))
                .setScale(2, RoundingMode.HALF_UP);
//        System.out.println("Taxes: " + taxes);

        // Update YTD earnings and taxes
        netPay = netPay.subtract(taxes);
        ytdEarnings += netPay.doubleValue();
        System.out.println(name + " " + ytdEarnings);
        ytdTaxesPaid += taxes.doubleValue();
//        System.out.println("Taxes: " + taxes);


//        System.out.println("Final Net Pay: " + netPay);

        // Return the pay stub
        return new PayStub(name, netPay.doubleValue(), taxes.doubleValue(), ytdEarnings, ytdTaxesPaid);
    }

    /**
     * Abstract method to calculate gross pay for the employee.
     * Each subclass (HourlyEmployee, SalaryEmployee) must implement this.
     *
     * @param hoursWorked The hours worked for the pay period.
     * @return The gross pay.
     */
    protected abstract double calculateGrossPay(double hoursWorked);

    @Override
    public abstract String getEmployeeType();

    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f",
                getEmployeeType(), name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }
}
