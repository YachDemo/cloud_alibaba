package com.common

import java.sql.Timestamp
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

/**
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-25 16:05
 **/
enum class SystemClock(period: Long) {
    INSTANCE(1);

    private val period: Long = 0
    private var nowTime: AtomicLong? = null
    private var started = false
    private lateinit var executorService: ScheduledExecutorService

    constructor() : this(1) {
        nowTime = AtomicLong(System.currentTimeMillis())
    }

    /**
     * The initialize scheduled executor service
     */
    open fun initialize() {
        if (started) {
            return
        }
        executorService = ScheduledThreadPoolExecutor(1) { r: Runnable? ->
            val thread = Thread(r, "system-clock")
            thread.isDaemon = true
            thread
        }
        executorService.scheduleAtFixedRate(
            { nowTime!!.set(System.currentTimeMillis()) },
            period, period, TimeUnit.MILLISECONDS
        )
        Runtime.getRuntime().addShutdownHook(Thread { destroy() })
        started = true
    }

    /**
     * The get current time milliseconds
     *
     * @return long time
     */
    open fun currentTimeMillis(): Long {
        return if (started) nowTime!!.get() else System.currentTimeMillis()
    }

    /**
     * The get string current time
     *
     * @return string time
     */
    open fun currentTime(): String? {
        return Timestamp(currentTimeMillis()).toString()
    }

    /**
     * The destroy of executor service
     */
    open fun destroy() {
        executorService.shutdown()
    }
}