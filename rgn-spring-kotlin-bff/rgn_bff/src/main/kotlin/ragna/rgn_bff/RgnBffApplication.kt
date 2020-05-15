package ragna.rgn_bff

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class RgnBffApplication

fun main(args: Array<String>) {
    runApplication<RgnBffApplication>(*args)
}

@RestController()
class EmployeeAggregateController(
        @Autowired val deptClient: DeptClient,
        @Autowired val employeeClient: EmployeeClient
) {
    //   private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/ui/v1/employees/{id}")
    fun getAggregateEmployee(@PathVariable id: Long): EmployeeAggregate {

        val dept = deptClient.getById(id) ?: Dept()
        val employee = employeeClient.getById(id) ?: Employee()

        val employeeAggregate = EmployeeAggregate(
                id = employee.id,
                name = employee.name,
                deptId = dept.id,
                deptName = dept.name
        )

        return employeeAggregate;
    }

    @GetMapping("/ui/v1/async/employees/{id}")
    fun getAggregateEmployeeAsync(@PathVariable id: Long): EmployeeAggregate = runBlocking {
        val deptDeferred: Deferred<Dept> = async {
            deptClient.getById(id)!!
        }

        val employeeDeferred: Deferred<Employee> = async {
            employeeClient.getById(id)!!
        }

        val dept = deptDeferred.await()
        val employee = employeeDeferred.await()

        val employeeAggregate = EmployeeAggregate(
                id = employee.id,
                name = employee.name,
                deptId = dept.id,
                deptName = dept.name
        )

        employeeAggregate;
    }

}

@Component
class DeptClient {
    //   private val logger = LoggerFactory.getLogger(javaClass)
    fun getById(id: Long): Dept? {
        return RestTemplate().getForObject("http://127.0.0.1:8082/api/v1/depts/$id", Dept::class.java);
    }
}

@Component
class EmployeeClient {
    //  private val logger = LoggerFactory.getLogger(javaClass)
    fun getById(id: Long): Employee? {
        return RestTemplate().getForObject("http://localhost:8081/api/v1/employees/$id", Employee::class.java);
    }
}

data class EmployeeAggregate(
        val id: Long,
        val name: String,
        val deptId: Long = 0,
        val deptName: String = ""
) {
    companion object Const {
        val EMPLOYEE_AGGREGATE: EmployeeAggregate = EmployeeAggregate(id = 0,
                name = ""
        )
    }
}

data class Employee(
        val id: Long = 0,
        val name: String = "",
        val depId: Long = 0
)

data class Dept(
        val id: Long = 0,
        val name: String = ""
)
