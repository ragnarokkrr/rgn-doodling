package ragna.debezium.rgncdc.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ApplicationConfiguration {
    @Value("\${student.datasource.host}")
    private val studentDBHost: String? = null

    @Value("\${student.datasource.databasename}")
    private val studentDBName: String? = null

    @Value("\${student.datasource.port}")
    private val studentDBPort: String? = null

    @Value("\${student.datasource.username}")
    private val studentDBUserName: String? = null

    @Value("\${student.datasource.password}")
    private val studentDBPassword: String? = null

    private val STUDENT_TABLE_NAME = "public.student"
    @Bean
    fun debeziumStudentConnector(): io.debezium.config.Configuration {
        return io.debezium.config.Configuration.create()
                .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", "~/POC_storage/embedded-debezium/student-cdc-relay/student-offset.dat")
                .with("offset.flush.interval.ms", 60000)
                .with("name", "student-postgres-connector")
                .with("database.server.name", "$studentDBHost-$studentDBName")
                .with("database.hostname", studentDBHost)
                .with("database.port", studentDBPort)
                .with("database.user", studentDBUserName)
                .with("database.password", studentDBPassword)
                .with("database.dbname", studentDBName)
                .with("table.whitelist", STUDENT_TABLE_NAME)
                .build()
    }

}