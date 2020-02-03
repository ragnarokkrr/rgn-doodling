package ragna.debezium.rgncdc.elasticsearch.entity

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "student", shards = 1, replicas = 0, refreshInterval = "-1")
data class Student(
        @Id
        val id: Int,
        @Field(type = FieldType.Text)
        val name: String,
        @Field(type = FieldType.Text)
        val address: String,
        @Field(type = FieldType.Text)
        val email: String
)