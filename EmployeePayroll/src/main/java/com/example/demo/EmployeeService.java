package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return convertToDTO(employee);
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        employee = employeeRepository.save(employee);
        return convertToDTO(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Conversion methods
    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getName(), employee.getSalary());
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        return new Employee(employeeDTO.getName(), employeeDTO.getSalary());
    }
}
