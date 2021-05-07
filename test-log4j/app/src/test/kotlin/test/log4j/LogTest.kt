package test.log4j

import org.apache.logging.log4j.LogManager.getLogger
import java.util.*
import kotlin.system.measureTimeMillis
import kotlin.test.Test
import kotlin.test.assertFalse

class LogTest {
    val log = getLogger(javaClass)

    val ITERARTIONS: Int = 100_000_000

    @Test
    fun logString() {
        assertFalse { log.isTraceEnabled }

        val time = measureTimeMillis {
            repeat(ITERARTIONS) {
                log.trace("it=$it random=${Random()}")
            }
        }
        log.error("logString iterations: {} time: {} ms", ITERARTIONS, time)
    }

    @Test
    fun logTemplate() {
        assertFalse { log.isTraceEnabled }

        val time = measureTimeMillis {
            repeat(ITERARTIONS) {
                log.trace("it={} random={}", it, Random())
            }
        }
        log.error("logTemplate iterations: {} time: {} ms", ITERARTIONS, time)
    }

}
