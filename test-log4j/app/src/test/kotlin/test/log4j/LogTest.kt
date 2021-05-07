package test.log4j

import org.apache.logging.log4j.LogManager.getLogger
import org.apache.logging.log4j.kotlin.logger
import java.util.*
import kotlin.system.measureTimeMillis
import kotlin.test.Test
import kotlin.test.assertFalse

class LogTest {

    val ITERARTIONS: Int = 100_000_000

    @Test
    fun logString() {
        val log = getLogger("logString")
        assertFalse { log.isTraceEnabled }

        val time = measureTimeMillis {
            repeat(ITERARTIONS) {
                log.trace("it=$it random=${Random()}")
                if (it == ITERARTIONS - 1) {
                    log.error("it=$it random=${Random()}")
                }
            }
        }
        log.error("logString iterations: {} time: {} ms", ITERARTIONS, time)
    }

    @Test
    fun logTemplate() {
        val log = getLogger("logTemplate")

        assertFalse { log.isTraceEnabled }

        val time = measureTimeMillis {
            repeat(ITERARTIONS) {
                log.trace("it={} random={}", it, Random())
                if (it == ITERARTIONS - 1) {
                    log.error("it={} random={}", it, Random())
                }
            }
        }
        log.error("logTemplate iterations: {} time: {} ms", ITERARTIONS, time)
    }

    @Test
    fun logLambda() {
        val log = logger("logLambda")
        val time = measureTimeMillis {
            repeat(ITERARTIONS) {
                log.trace { "it=${it} random=${Random()}" }
                if (it == ITERARTIONS - 1) {
                    log.error { "it=${it} random=${Random()}" }
                }
            }
        }
        log.error { "logLambda iterations: ${ITERARTIONS} time: ${time} ms" }
    }
}
