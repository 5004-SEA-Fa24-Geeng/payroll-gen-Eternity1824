package testcases;

import org.junit.jupiter.api.Test;
import student.IPayStub;
import student.SalaryEmployee;

import static org.junit.jupiter.api.Assertions.*;

public class SalaryEmployeeTest     {

    /**
     * normal test worker
     */
    @Test
    public void testSalaryEmployeePayroll() {
        SalaryEmployee emp = new SalaryEmployee("Jane Smith", "456", 52000.0, 20000.0, 4000.0, 100.0);
        IPayStub stub = emp.runPayroll(40); // Hours shouldn't matter for salary

        // Calculations:
        // Gross pay = $52000 ÷ 24 = $2166.67
        // After pretax deductions = $2166.67 - $100 = $2066.67
        // Taxes (22.65%) = $2066.67 × 0.2265 = $468.10
        // Net pay = $2066.67 - $468.10 = $1598.57

        assertNotNull(stub);
        assertEquals(1598.57, stub.getPay(), 0.01);
        assertEquals(468.10, stub.getTaxesPaid(), 0.01);
    }

    /**
     * workers worker 0 hour
     */
    @Test
    public void testRunPayrollWithZeroHours() {
        SalaryEmployee emp = new SalaryEmployee("Jane Smith", "456", 52000.0, 20000.0, 4000.0, 100.0);
        IPayStub stub = emp.runPayroll(0);

        // Should still get paid even with 0 hours
        assertNotNull(stub);
        assertEquals(0.0, stub.getPay(), 0.01);
        assertEquals(0.0, stub.getTaxesPaid(), 0.01);
    }

    /**
     * workers work negative hours
     */
    @Test
    public void testRunPayrollWithNegativeHours() {
        SalaryEmployee emp = new SalaryEmployee("Jane Smith", "456", 52000.0, 20000.0, 4000.0, 100.0);
        // Should return null for negative hours as specified in requirements
        assertNull(emp.runPayroll(-5));
    }

    /**
     * test employee type
     */
    @Test
    public void testEmployeeType() {
        SalaryEmployee emp = new SalaryEmployee("Jane Smith", "456", 52000.0, 20000.0, 4000.0, 100.0);
        assertEquals("SALARY", emp.getEmployeeType());
    }

    /**
     * test turning to CSV
     */
    @Test
    public void testToCSV() {
        SalaryEmployee emp = new SalaryEmployee("Jane Smith", "456", 52000.0, 20000.0, 4000.0, 100.0);
        String expected = "SALARY,Jane Smith,456,52000.00,100.00,20000.00,4000.00";
        assertEquals(expected, emp.toCSV());
    }

    /**
     * test workers with high salary
     */
    @Test
    public void testHighSalaryCalculations() {
        SalaryEmployee emp = new SalaryEmployee("Executive", "789", 120000.0, 50000.0, 10000.0, 500.0);
        IPayStub stub = emp.runPayroll(40);

        // Calculations:
        // Gross pay = $120000 ÷ 24 = $5000
        // After pretax deductions = $5000 - $500 = $4500
        // Taxes (22.65%) = $4500 × 0.2265 = $1019.25
        // Net pay = $4500 - $1019.25 = $3480.75

        assertNotNull(stub);
        assertEquals(3480.75, stub.getPay(), 0.01);
        assertEquals(1019.25, stub.getTaxesPaid(), 0.01);
    }
}
