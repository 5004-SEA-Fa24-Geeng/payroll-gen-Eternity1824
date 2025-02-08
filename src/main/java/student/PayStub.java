package student;

/**
 * Represents a pay stub generated during payroll processing.
 */
public class PayStub implements IPayStub {
    /** The name of the employee. */
    private final String employeeName;
    /** The final net pay after deductions and taxes. */
    private final double netPay;
    /** The total taxes deducted. */
    private final double taxesPaid;
    /** The year-to-date earnings. */
    private final double ytdEarnings;
    /** The year-to-date taxes paid. */
    private final double ytdTaxesPaid;

    /**
     * Constructor for PayStub.
     *
     * @param employeeName The name of the employee.
     * @param netPay       The final net pay after deductions and taxes.
     * @param taxesPaid    The total taxes deducted.
     * @param ytdEarnings  The year-to-date earnings.
     * @param ytdTaxesPaid The year-to-date taxes paid.
     */
    public PayStub(String employeeName, double netPay, double taxesPaid, double ytdEarnings, double ytdTaxesPaid) {
        this.employeeName = employeeName;
        this.netPay = netPay;
        this.taxesPaid = taxesPaid;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
    }

    /**
     * Gets the net pay for the current pay period.
     *
     * @return The net pay.
     */
    @Override
    public double getPay() {
        return netPay;
    }

    /**
     * Gets the taxes paid for the current pay period.
     *
     * @return The taxes paid.
     */
    @Override
    public double getTaxesPaid() {
        return taxesPaid;
    }

    /**
     * Converts the PayStub object to a CSV string.
     * Format: "employee_name,net_pay,taxes,ytd_earnings,ytd_taxes_paid"
     *
     * @return The CSV string representation of the pay stub.
     */
    @Override
    public String toCSV() {
        return String.format("%s,%.2f,%.2f,%.2f,%.2f",
                employeeName, netPay, taxesPaid, ytdEarnings, ytdTaxesPaid);
    }
}
