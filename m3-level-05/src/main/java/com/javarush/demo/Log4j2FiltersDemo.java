package com.javarush.demo;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.message.StringMapMessage;

public class Log4j2FiltersDemo {
    private static LoggerContext configure(String filter) throws Exception {
        String xml = "<Configuration status='OFF'><Appenders>"
                + "<Console name='CONSOLE' target='SYSTEM_OUT'>"
                + "<PatternLayout pattern='%level %msg%n'/>" + filter
                + "</Console></Appenders><Loggers><Root level='trace'>"
                + "<AppenderRef ref='CONSOLE'/></Root></Loggers></Configuration>";
        return Configurator.initialize(null, new ConfigurationSource(
                new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8))));
    }

    public static void main(String[] args) throws Exception {
        System.out.println("RegexFilter");
        try (LoggerContext context = configure(
                "<RegexFilter regex='.*payment.*' onMatch='ACCEPT' onMismatch='DENY'/>")) {
            Logger log = context.getLogger("filters");
            log.info("payment accepted");
            log.info("catalog loaded");
        }
        System.out.println("MarkerFilter");
        try (LoggerContext context = configure(
                "<MarkerFilter marker='AUDIT' onMatch='ACCEPT' onMismatch='DENY'/>")) {
            Logger log = context.getLogger("filters");
            log.info(MarkerManager.getMarker("AUDIT"), "audit event");
            log.info("ordinary event");
        }
        System.out.println("MapFilter");
        try (LoggerContext context = configure(
                "<MapFilter onMatch='ACCEPT' onMismatch='DENY'>"
                + "<KeyValuePair key='type' value='payment'/></MapFilter>")) {
            Logger log = context.getLogger("filters");
            log.info(new StringMapMessage().with("type", "payment"));
            log.info(new StringMapMessage().with("type", "catalog"));
        }
        System.out.println("DynamicThresholdFilter");
        try (LoggerContext context = configure(
                "<DynamicThresholdFilter key='user' defaultThreshold='ERROR' "
                + "onMatch='ACCEPT' onMismatch='DENY'>"
                + "<KeyValuePair key='mentor' value='DEBUG'/></DynamicThresholdFilter>")) {
            Logger log = context.getLogger("filters");
            ThreadContext.put("user", "mentor");
            log.debug("mentor diagnostic");
            ThreadContext.put("user", "student");
            log.debug("student diagnostic");
            log.error("student failure");
        } finally {
            ThreadContext.clearAll();
        }
        System.out.println("CompositeFilter");
        try (LoggerContext context = configure(
                "<Filters><MarkerFilter marker='AUDIT' onMatch='NEUTRAL' onMismatch='DENY'/>"
                + "<RegexFilter regex='.*payment.*' onMatch='NEUTRAL' onMismatch='DENY'/></Filters>")) {
            Logger log = context.getLogger("filters");
            log.info(MarkerManager.getMarker("AUDIT"), "payment accepted");
            log.info(MarkerManager.getMarker("AUDIT"), "catalog loaded");
            log.info("payment without marker");
        }
        System.out.println("BurstFilter");
        try (LoggerContext context = configure(
                "<BurstFilter level='INFO' rate='0.000001' maxBurst='1' "
                + "onMatch='NEUTRAL' onMismatch='DENY'/>")) {
            Logger log = context.getLogger("filters");
            log.info("first event");
            log.info("second event");
            log.error("important failure");
        }
    }
}
