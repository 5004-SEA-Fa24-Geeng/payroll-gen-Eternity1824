package testcases;

import org.junit.jupiter.api.Test;
import student.HourlyEmployee;
import student.IPayStub;


import static org.junit.jupiter.api.Assertions.*;

/**
 * testcase for HourlyEmployee
 */
public class HourlyEmployeeTest {

    /**
     * testcase: workers worked without overtime
     */
    @Test
    public void testHourlyEmployeeGrossPayWithoutOvertime() {
        HourlyEmployee emp = new HourlyEmployee("John Doe", "123", 20.0, 1000.0, 200.0, 50.0);
        IPayStub stub = emp.runPayroll(40); // runPayroll internally calls calculateGrossPay
        assertNotNull(stub);

        // Calculations:
        // Gross pay = 40 hours × $20 = $800
        // After pretax deductions = $800 - $50 = $750
        // Taxes (22.65%) = $750 × 0.2265 = $169.88
        // Net pay = $750 - $169.88 = $580.12

        assertEquals(580.13, stub.getPay(), 0.01);
        assertEquals(169.87, stub.getTaxesPaid(), 0.01); // 22.65% tax assumption
    }

    /**
     * testcase: workers work overtime
     */
    @Test
    public void testHourlyEmployeeGrossPayWithOvertime() {
        HourlyEmployee emp = new HourlyEmployee("Jane Doe", "124", 20.0, 1000.0, 200.0, 50.0);
        IPayStub stub = emp.runPayroll(50); // Includes 10 overtime hours
        assertNotNull(stub);

        // Calculations:
        // Regular pay = 40 hours × $20 = $800
        // Overtime pay = 10 hours × $20 × 1.5 = $300
        // Gross pay = $800 + $300 = $1100
        // After pretax deductions = $1100 - $50 = $1050
        // Taxes (22.65%) = $1050 × 0.2265 = $237.83
        // Net pay = $1050 - $237.83 = $812.17

        assertEquals(812.17, stub.getPay(), 0.01); // (40 * 20) + (10 * 30)
        assertEquals(237.83, stub.getTaxesPaid(), 0.01); // 22.65% tax assumption
    }

    /**
     * testcase: workers work 0 time
     */
    @Test
    public void testRunPayrollWithZeroHours() {
        HourlyEmployee emp = new HourlyEmployee("John Doe", "123", 20.0, 1000.0, 200.0, 50.0);
        IPayStub stub = emp.runPayroll(0);

        // With 0 hours, should return stub with 0 pay and 0 taxes
        assertNotNull(stub);
        assertEquals(0.0, stub.getPay(), 0.01);
        assertEquals(0.0, stub.getTaxesPaid(), 0.01);
    }

    /**
     * negative work hour
     */
    @Test
    public void testRunPayrollWithNegativeHours() {
        HourlyEmployee emp = new HourlyEmployee("John Doe", "123", 20.0, 1000.0, 200.0, 50.0);
        IPayStub stub = emp.runPayroll(-5);
        assertNull(stub);  // Should return null for negative hours
    }

    /**
     * test employee type
     */
    @Test
    public void testEmployeeType() {
        HourlyEmployee emp = new HourlyEmployee("John Doe", "123", 20.0, 1000.0, 200.0, 50.0);
        assertEquals("HOURLY", emp.getEmployeeType());
    }

    /**
     * test for turning into CSV
     */
    @Test
    public void testToCSV() {
        HourlyEmployee emp = new HourlyEmployee("John Doe", "123", 20.0, 1000.0, 200.0, 50.0);
        String expected = "HOURLY,John Doe,123,20.00,50.00,1000.00,200.00";
        assertEquals(expected, emp.toCSV());
    }
}
