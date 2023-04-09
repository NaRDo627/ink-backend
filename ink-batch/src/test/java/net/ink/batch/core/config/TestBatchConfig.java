package net.ink.batch.core.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableAutoConfiguration
@EnableBatchProcessing
@ComponentScan(basePackages = {"net.ink.core", "net.ink.push", "net.ink.batch.core"})
public class TestBatchConfig {

}