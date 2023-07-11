package com.example.springmockito.service;

import com.example.springmockito.exception.EmployeeAlreadyAddedException;
import com.example.springmockito.exception.EmployeeNotFoundException;
import com.example.springmockito.exception.EmployeeStoragelsFullException;
import com.example.springmockito.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {

    private static final int SIZE_LIMIT = 5; // константа мах колл чел.
    private final Map<String, Employee> employees = new HashMap<>(SIZE_LIMIT);

   public void EmployeeService() {
       // Employee employee1 = new Employee("ivan", "ivanov", 1, 10000);
       // Employee employee2 = new Employee("oleg", "olegov", 1, 20000);
       // Employee employee3 = new Employee("nastya", "novikova", 2, 15000);
       // Employee employee4 = new Employee("alex", "fedorov", 2, 25000);
       // employees.put(createKey(employee1), employee1);
       // employees.put(createKey(employee2), employee2);
       // employees.put(createKey(employee3), employee3);
       // employees.put(createKey(employee4), employee4);

   }

    public Collection<Employee> getAll() {
        return employees.values();
    }

    public Employee add(Employee employee) { // добавим сотр.
        if (employees.size() >= SIZE_LIMIT) {
            throw new EmployeeStoragelsFullException();
        }

        if (employees.containsKey(createKey(employee))) {     // добавляем метод который нам выдаст ключ
            throw new EmployeeAlreadyAddedException();                // если такой ключ есть , то выкидыв исключение
        }

        employees.put(createKey(employee), employee); // если его нет, то кладем ключ
        return employee; // вернем сотрудника который пришел.
    }

    public Employee remove(String firstName, String lastName) {

        return employees.remove(createKey(firstName, lastName));
    }

    public Employee find(String firstName, String lastName) {

        Employee employee = employees.get(createKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException();

        }
        return employee;
    }

    // public List<Employee> getAll() {
    //    return Collections.unmodifiableList(new ArrayList<>(employees.values()));
    // }

    private static String createKey(Employee employee) {

        return createKey(employee.getFirstName(), employee.getLastName());

    }

    private static String createKey(String firstName, String lastName) {

        return (firstName + lastName).toLowerCase(); // приводим к нижнему регистру

    }

}