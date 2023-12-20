package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "salary"));

    }

    public Optional<Employee> getByID(Long id){
        return employeeRepository.findById(id);
    }

    public Employee getHighestSalaryEmployee() {
        return  getAllEmployees().get(0);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> existingEmployeeOptional = employeeRepository.findById(id);

        if (existingEmployeeOptional.isPresent()) {
            Employee existingEmployee = existingEmployeeOptional.get();
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setPosition(updatedEmployee.getPosition());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            return employeeRepository.save(existingEmployee);
        } else {
            return null;
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
