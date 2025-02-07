package testcases;

import org.junit.jupiter.api.Test;
import student.Employee;
import student.HourlyEmployee;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  EmployeeTest include testGetName, testGetId
 */
public class EmployeeTest {

    /**
     * test get name
     */
    @Test
    void testGetName() {
        Employee emp = new HourlyEmployee("John Doe", "123", 20.0, 1000.0, 200.0, 50.0);
        assertEquals("John Doe", emp.getName());
    }

    /**
     * test get id
     */
    @Test
    void testGetId() {
        Employee emp = new HourlyEmployee("John Doe", "123", 20.0, 1000.0, 200.0, 50.0);
        assertEquals("123", emp.getID());
    }
}
