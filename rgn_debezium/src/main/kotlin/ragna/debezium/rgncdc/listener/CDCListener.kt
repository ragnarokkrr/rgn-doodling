package ragna.debezium.rgncdc.listener

import io.debezium.config.Configuration
import io.debezium.data.Envelope.FieldName.*
import io.debezium.embedded.EmbeddedEngine
import org.apache.commons.lang3.tuple.Pair
import org.apache.kafka.connect.data.Struct
import org.apache.kafka.connect.source.SourceRecord
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import ragna.debezium.rgncdc.elasticsearch.service.StudentService
import ragna.debezium.rgncdc.utils.Operation
import java.util.concurrent.Executors

@Component
class CDCListener(private val studentConfiguration: Configuration,
                  private val studentService: StudentService) {
    private val LOGGER = LoggerFactory.getLogger(CDCListener::class.java)
    private val executor = Executors.newSingleThreadExecutor()
    private val engine: EmbeddedEngine = EmbeddedEngine
            .create()
            .using(studentConfiguration)
            .notifying(this::handleEvent)
            .build()

    fun handleEvent(sourceRecord: SourceRecord?): Unit {
        if (sourceRecord == null) {
            LOGGER.debug("null sourceRecord: no-op")
            return
        }

        val sourceRecordValue: Struct = sourceRecord.value() as Struct
        val operation = Operation.forCode(sourceRecordValue.get(OPERATION) as String)

        //Only if this is a transactional operation
        if (operation != Operation.READ) {
            val message: Map<String, Any>?
            var record = AFTER //For Update & Insert operations.

            if (operation == Operation.DELETE) {
                record = BEFORE //For Delete operations.
            }
            //Build a map with all row data received.
            val struct = sourceRecordValue.get(record) as Struct

            message = struct.schema().fields().map {
                it::name
            }.filter { fieldName -> struct.get(fieldName as String) != null }
                    .map { fieldName -> fieldName }


        }

    }
}