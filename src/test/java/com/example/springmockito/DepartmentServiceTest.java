package com.example.springmockito;

import com.example.springmockito.model.Employee;
import com.example.springmockito.service.DepartmentService;
import com.example.springmockito.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class DepartmentServiceTest {
    public static final Collection<Employee> EMPLOYEES = Arrays.asList(
            new Employee("ivan", "ivanov", 1, 10000),
            new Employee("oleg", "olegov", 1, 20000),
            new Employee("nastya", "novikova", 2, 15000),
            new Employee("alex", "fedorov", 2, 25000),
            new Employee("andrey", "ivanov", 3, 40000)
    );
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void init() {
        when(employeeService.getAll()).thenReturn(EMPLOYEES);
    }

    @Test
    void sum() {
        double actual = departmentService.detEmployeeSalarySum(1);
        assertEquals(30000, actual);// можно занести дельту отвечает за точность значения дельта 0,0000001
    }

    @Test
    void max() {
        double actual = departmentService.detEmployeeMaxSalary(2);
        assertEquals(25000, actual);
    }

    @Test
    void min() {
        double actual = departmentService.detEmployeeMinSalary(2);
        assertEquals(15000, actual);
    }

    @Test
    void getAllByDepartment() {
        List<Employee> actual = departmentService.getALL(3);
        Collection<Employee> excpected = Collections.singletonList(new Employee("andrey",
                "ivanov", 3, 40000));
        assertIterableEquals(excpected, actual);
    }

    @Test
    void getAll() {
        Map<Integer, List<Employee>> actual = departmentService.getAll();

        Employee andrey = new Employee("andrey", "ivanov", 3, 40000);
        assertTrue(actual.get(3).contains(andrey));
        assertFalse(actual.get(2).contains(andrey));
        assertEquals(3, actual.keySet().size());
    }

}



