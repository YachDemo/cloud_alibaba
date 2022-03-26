package com.example.zuul.filter

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * 前置过滤器
 * 适用于如权限校验 日志打印
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-26 11:23
 **/
@Component
class PreLogFilter : ZuulFilter(){
    private val LOGGER: Logger = LoggerFactory.getLogger(this.javaClass)


    /**
     * 是否执行过滤
     */
    override fun shouldFilter(): Boolean {
        return true
    }

    /**
     * 自定义的过滤逻辑 当shouldFilter()返回true时会执行。
     */
    override fun run(): Any? {
        val requestContext = RequestContext.getCurrentContext()
        val request = requestContext.request
        val host = request.remoteHost
        val method = request.method
        val requestURI = request.requestURI
        LOGGER.info("Remote host:{},method:{},uri:{}", host, method, requestURI)
        return null
    }

    /**
     * 过滤类型 有pre、routing、post、error四种。
     */
    override fun filterType(): String {
        return "pre"
    }

    /**
     * 过滤器执行顺序，数值越小优先级越高。
     */
    override fun filterOrder(): Int {
        return 1
    }
}