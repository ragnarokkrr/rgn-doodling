package ragna.debezium.rgncdc.elasticsearch.repository

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository
import ragna.debezium.rgncdc.elasticsearch.entity.Student

@Repository
interface StudentRepository : ElasticsearchRepository<Student, Int>