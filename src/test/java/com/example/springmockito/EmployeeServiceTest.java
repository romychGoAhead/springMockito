package com.example.springmockito;

import com.example.springmockito.exception.EmployeeAlreadyAddedException;
import com.example.springmockito.exception.EmployeeNotFoundException;
import com.example.springmockito.exception.EmployeeStoragelsFullException;
import com.example.springmockito.model.Employee;
import com.example.springmockito.service.EmployeeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();

    @Test
    void whenFullThenThrowException() {
        for (int i = 0; i < 5; i++) {
            char additionlChar = (char) ('a' + i);
            Employee employee = new Employee("name" + additionlChar, "second name",
                    1, 1);
            employeeService.add(employee);
        }

        assertThrows(EmployeeStoragelsFullException.class, () -> employeeService.add(new Employee("Роман",
                "Васильев", 1, 15000)));
    }
// два одинаковых сотрудника

    @Test
    void whenNotUniqThenThrowException() {
        Employee employee = new Employee("name", "last_name", 1, 10000);
        employeeService.add(employee);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add(employee));
    }

    @Test
        // в целом метод работает
    void addPositive() {
        Employee employee = new Employee("name", "last_name", 1, 10000);
        employeeService.add(employee);
        assertTrue(employeeService.getAll().contains(employee));
    }

    @Test
    void findPositive() {
        Employee employee = new Employee("name", "last_name", 1, 10000);
        employeeService.add(employee);
        Employee actual = employeeService.find("name", "last_name");
        assertNotNull(actual);
        assertEquals(employee, actual);
    }

    @Test
    void findNegative() {
        Employee employee = new Employee("name", "last_name", 1, 10000);
        employeeService.add(employee);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("not_name", "not_last_name"));
    }

    @Test
    void removePositive() {
        Employee employee = new Employee("name", "last_name", 1, 10000);
        employeeService.add(employee);
        employeeService.remove("name", "last_name");
        assertFalse(employeeService.getAll().contains(employee));
    }

    @Test
    void removeNegative() {
        Employee employee = new Employee("name", "last_name", 1, 10000);
        employeeService.add(employee);
        Employee actual = employeeService.remove("not_name", "not_last_name");
        assertNull(actual);
    }
}


