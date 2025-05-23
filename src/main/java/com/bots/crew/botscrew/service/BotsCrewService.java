package com.bots.crew.botscrew.service;

import com.bots.crew.botscrew.model.Department;
import com.bots.crew.botscrew.model.Lector;
import com.bots.crew.botscrew.model.Position;
import com.bots.crew.botscrew.repository.DepartmentRepository;
import com.bots.crew.botscrew.repository.LectorRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BotsCrewService {
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    public String getHeadOfDepartment(String departmentName) {
        Optional<Department> departmentOptional = departmentRepository.findDepartmentByName(departmentName);
        return departmentOptional.map(department -> "Head of " + departmentName +
                    " department is " + department.getHead().getFullName())
                .orElse("Department not found");

    }

    public String getStatistics(String departmentName) {
        Optional<Department> departmentOptional = departmentRepository.findDepartmentByName(departmentName);
        if (departmentOptional.isEmpty()) {
            return "Department not found";
        }

        Department department = departmentOptional.get();

        return "Assistants: - " +
                lectorRepository.countLectorsByPositionAndDepartment(Position.ASSISTANT, department) +
                "\nAssociate Professors - " +
                lectorRepository.countLectorsByPositionAndDepartment(Position.ASSOCIATE_PROFESSOR, department) +
                "\nProfessors: - " +
                lectorRepository.countLectorsByPositionAndDepartment(Position.PROFESSOR, department);
    }

    public String getAVGSalary(String departmentName) {
        Optional<Department> departmentOptional = departmentRepository.findDepartmentByName(departmentName);
        if (departmentOptional.isEmpty()) {
            return "Department not found";
        }

        Double averageSalary = lectorRepository.searchLectorsByDepartment(departmentOptional.get())
                .stream()
                .mapToDouble(Lector::getSalary)
                .average().orElse(0.0);

        return String.format("The average salary of %s is %f\n", departmentName, averageSalary);
    }

    public String countEmployees(String departmentName) {
        Optional<Department> departmentOptional = departmentRepository.findDepartmentByName(departmentName);
        return departmentOptional.map(department ->
                        String.valueOf(lectorRepository.countLectorsByDepartment(department)))
                .orElse("Department not found");

    }

    public String search(String template) {
        List<String> lectors = lectorRepository.searchLectorsByFullNameContaining(template)
                .stream()
                .map(Lector::getFullName)
                .toList();

        return lectors.toString();
    }
}
