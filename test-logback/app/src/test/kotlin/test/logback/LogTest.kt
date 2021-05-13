package test.logback

import org.slf4j.LoggerFactory
import java.util.*
import kotlin.system.measureTimeMillis
import kotlin.test.Test
import kotlin.test.assertFalse

class LogTest {

    val ITERARTIONS: Int = 100_000_000

    @Test
    fun logString() {
        val log = LoggerFactory.getLogger("logString")
        assertFalse { log.isTraceEnabled }

        val time = measureTimeMillis {
            repeat(ITERARTIONS) {
                log.trace("it=$it random=${Random()}")
            }
        }
        log.error("logString iterations: $ITERARTIONS time: $time ms")
    }

    @Test
    fun logStringIf() {
        val log = LoggerFactory.getLogger("logStringIf")
        assertFalse { log.isTraceEnabled }

        val time = measureTimeMillis {
            repeat(ITERARTIONS) {
                if (log.isTraceEnabled) {
                    log.trace("it=$it random=${Random()}")
                }
            }
        }
        log.error("iterations: $ITERARTIONS time: $time ms")
    }

    @Test
    fun logTemplate() {
        val log = LoggerFactory.getLogger("logTemplate")
        assertFalse { log.isTraceEnabled }

        val time = measureTimeMillis {
            repeat(ITERARTIONS) {
                log.trace("it={} random={}", it, Random())
            }
        }
        log.error("logTemplate iterations: {} time: {} ms", ITERARTIONS, time)
    }
}
