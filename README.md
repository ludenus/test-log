# test-log

This repository contains time measurements of different ways to call logger methods in Kotlin.

## TL;DR
Calling log with string parameter is the **worst** way to log.
String value is calculated regardless of active log level.
```
log.trace("it=$it random=${Random()}")
```

Calling log method with template and separate parameters is much better. `toString()` method of each provided parameter is called depending on active log level. However object instance itself still has to be created in order to be passed as a parameter.
```
log.trace("it={} random={}", it, Random())
```

Lambda calls like this (note curly braces {} instead of ()) is the **best** way.
Object instances specified inside lambda block are not created if active log level does not match. 
```
log.trace { "it=${it} random=${Random()}" }
```
Unfortunately lambda calls are only available for log4j, not for logback.

## test-logback
https://github.com/ludenus/test-log/blob/master/test-logback/app/src/test/kotlin/test/logback/LogTest.kt
```
22:06:46.817 [Test worker] ERROR logTemplate - logTemplate iterations: 100000000 time: 4853 ms
22:07:08.557 [Test worker] ERROR logString - logString iterations: 100000000 time: 21734 ms
```

## test-log4j
https://github.com/ludenus/test-log/blob/master/test-log4j/app/src/test/kotlin/test/log4j/LogTest.kt
```
22:03:23.342 [Test worker] ERROR logTemplate - iterations: 100000000 time: 4854 ms
22:03:23.718 [Test worker] ERROR logLambda - iterations: 100000000 time: 370 ms
22:03:44.911 [Test worker] ERROR logString - iterations: 100000000 time: 21192 ms
```
