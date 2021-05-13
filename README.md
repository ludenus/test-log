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

There is  yet another option that works with both log4j and logback - surround log call with `if` statement.
Looks ugly but performs very well.

```
if (log.isTraceEnabled) { log.trace("it=$it random=${Random()}") }
```



## test-logback
https://github.com/ludenus/test-log/blob/master/test-logback/app/src/test/kotlin/test/logback/LogTest.kt
```
11:48:48.816 [Test worker] ERROR logStringIf - iterations: 100000000 time: 95 ms
11:48:53.760 [Test worker] ERROR logTemplate - logTemplate iterations: 100000000 time: 4940 ms
11:49:16.937 [Test worker] ERROR logString - logString iterations: 100000000 time: 23175 ms
```

## test-log4j
https://github.com/ludenus/test-log/blob/master/test-log4j/app/src/test/kotlin/test/log4j/LogTest.kt
```
11:47:11.963 [Test worker] ERROR logStringIf - iterations: 100000000 time: 179 ms
11:47:17.149 [Test worker] ERROR logTemplate - iterations: 100000000 time: 5180 ms
11:47:17.588 [Test worker] ERROR logLambda - iterations: 100000000 time: 435 ms
11:47:39.613 [Test worker] ERROR logString - iterations: 100000000 time: 22024 ms
```