package ragna.debezium.rgncdc.elasticsearch.service

import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Service
import ragna.debezium.rgncdc.elasticsearch.entity.Student
import ragna.debezium.rgncdc.elasticsearch.repository.StudentRepository
import ragna.debezium.rgncdc.utils.Operation

@Service
class StudentService(private val studentRepository: StudentRepository) {

    fun maintainReadModel(studentData: Map<String, Any>, operation: Operation): Unit {
        val mapper = jacksonObjectMapper()
        val student: Student = mapper.convertValue(studentData)

        if (Operation.DELETE == operation) {
            studentRepository.deleteById(student.id)
        } else {
            studentRepository.save(student)
        }

    }

}