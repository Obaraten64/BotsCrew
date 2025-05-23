package com.bots.crew.botscrew;

import com.bots.crew.botscrew.model.Department;
import com.bots.crew.botscrew.model.Lector;
import com.bots.crew.botscrew.model.Position;
import com.bots.crew.botscrew.repository.DepartmentRepository;
import com.bots.crew.botscrew.repository.LectorRepository;
import com.bots.crew.botscrew.service.BotsCrewService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BotsCrewServiceTest {
    @Mock
    DepartmentRepository departmentRepository;
    @Mock
    LectorRepository lectorRepository;

    @InjectMocks
    BotsCrewService botsCrewService;

    @Test
    @DisplayName("Incorrect department name")
    public void testIncorrectDepartmentName() {
        String departmentName = "test";

        when(departmentRepository.findDepartmentByName(departmentName))
                .thenReturn(Optional.empty());

        assertThat(botsCrewService.getHeadOfDepartment(departmentName))
                .isEqualTo("Department not found");
    }

    @Test
    @DisplayName("Get head of department")
    public void testGetHeadOfDepartment() {
        String departmentName = "test";
        Lector lector = Lector.builder()
                .fullName("Vadim Liakh")
                .build();
        Department department = Department.builder()
                .name(departmentName)
                .head(lector)
                .build();

        when(departmentRepository.findDepartmentByName(departmentName))
                .thenReturn(Optional.of(department));

        assertThat(botsCrewService.getHeadOfDepartment(departmentName))
                .isEqualTo("Head of test department is " + lector.getFullName());
    }

    @Test
    @DisplayName("Get statistics")
    public void testGetStatistics() {
        String departmentName = "test";
        Department department = Department.builder()
                .name(departmentName)
                .build();

        when(departmentRepository.findDepartmentByName(departmentName))
                .thenReturn(Optional.of(department));
        when(lectorRepository.countLectorsByPositionAndDepartment(Position.ASSISTANT, department))
                .thenReturn(0);
        when(lectorRepository.countLectorsByPositionAndDepartment(Position.ASSOCIATE_PROFESSOR, department))
                .thenReturn(1);
        when(lectorRepository.countLectorsByPositionAndDepartment(Position.PROFESSOR, department))
                .thenReturn(2);

        assertThat(botsCrewService.getStatistics(departmentName))
                .isEqualTo("Assistants: - 0" +
                        "\nAssociate Professors - 1" +
                        "\nProfessors: - 2");
    }

    @Test
    @DisplayName("Get average salary")
    public void testAverageSalary() {
        String departmentName = "test";
        Lector lector1 = Lector.builder()
                .salary(30.0)
                .build();
        Lector lector2 = Lector.builder()
                .salary(10.0)
                .build();
        Department department = Department.builder()
                .name(departmentName)
                .build();

        when(departmentRepository.findDepartmentByName(departmentName))
                .thenReturn(Optional.of(department));
        when(lectorRepository.searchLectorsByDepartment(department))
                .thenReturn(List.of(lector1, lector2));

        assertThat(botsCrewService.getAVGSalary(departmentName))
                .isEqualTo(String.format("The average salary of %s is %f\n", departmentName, 20.0));
    }

    @Test
    @DisplayName("Count employees")
    public void testCountEmployees() {
        String departmentName = "test";
        Department department = Department.builder()
                .name(departmentName)
                .build();

        when(departmentRepository.findDepartmentByName(departmentName))
                .thenReturn(Optional.of(department));
        when(lectorRepository.countLectorsByDepartment(department))
                .thenReturn(2);

        assertThat(botsCrewService.countEmployees(departmentName))
                .isEqualTo("2");
    }

    @Test
    @DisplayName("Search employees")
    public void testSearchEmployees() {
        String template = "test";
        Lector lector1 = Lector.builder()
                .fullName("test Vadim")
                .build();
        Lector lector2 = Lector.builder()
                .fullName("Vadim test")
                .build();
        var lectors = List.of(lector1, lector2);

        when(lectorRepository.searchLectorsByFullNameContaining(template))
                .thenReturn(lectors);

        assertThat(botsCrewService.search(template))
                .isEqualTo(lectors.stream().map(Lector::getFullName).toList().toString());
    }
}
