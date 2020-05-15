package ragna.rgnordinary2;

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
public class RgnOrdinary2Application {

    public static void main(String[] args) {
        SpringApplication.run(RgnOrdinary2Application.class, args);
    }

}


@RestController
@Slf4j
class EmployeeController {
    @Autowired
    DeptRepository deptRepository;

    @GetMapping("/depts/{id}")
    public Dept findById(@PathVariable Long id) throws InterruptedException {
        log.info("Dept - finding by id ({})", id);
        log.info("Waiting 2 secs...");
        TimeUnit.SECONDS.sleep(2L);
        return deptRepository.findById(id);
    }
}

@Repository
class DeptRepository {
    private Map<Long, Dept> DEPTS = Map.of(
            1L, Dept.builder().id(1L).name("Finances").build(),
            2L, Dept.builder().id(2L).name("HR").build(),
            3L, Dept.builder().id(3L).name("IT").build()
    );

    Dept findById(Long id) {
        return DEPTS.getOrDefault(id, Dept.DEFAULT);
    }

}


@Data
@Builder
class Dept {
    public static Dept DEFAULT = Dept.builder().id(0L).name("").build();

    private Long id;
    private String name;
}