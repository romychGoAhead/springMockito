package com.example.springmockito;

import com.example.springmockito.model.Employee;
import com.example.springmockito.service.EmployeeService;
import org.junit.jupiter.api.Test;

public class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();

    @Test
   void whenFullThenThrowException () {
for (int i = 0; i < 5; i++) {
    employeeService.add(new Employee("name","second name", 1,1));
}

    }




}
