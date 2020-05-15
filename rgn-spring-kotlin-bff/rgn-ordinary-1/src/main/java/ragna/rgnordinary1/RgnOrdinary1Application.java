package ragna.rgnordinary1;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class RgnOrdinary1Application {

    public static void main(String[] args) {
        SpringApplication.run(RgnOrdinary1Application.class, args);
    }

}

@RestController
@Slf4j
class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable Long id) throws InterruptedException {
        log.info("Employee - finding by id ({})", id);
        log.info("Waiting 5secs...");
        TimeUnit.SECONDS.sleep(5L);
        return employeeRepository.findById(id);
    }
}

@Repository
class EmployeeRepository {
    private Map<Long, Employee> EMPLOYEES = Map.of(
            1L, Employee.builder().id(1L).name("John Connor").deptId(20L).build(),
            2L, Employee.builder().id(2L).name("Sarah Connor").deptId(20L).build(),
            3L, Employee.builder().id(3L).name("Arnie").deptId(50L).build()
    );

    Employee findById(Long id) {
        return EMPLOYEES.getOrDefault(id, Employee.DEFAULT);
    }

}


@Data
@Builder
class Employee {
    public static Employee DEFAULT = Employee.builder().id(0L).name("").deptId(0L).build();

    private Long id;
    private String name;
    private Long deptId;
}