package net.ink.scheduler;

import net.ink.SchedulerBasePackage;
import net.ink.CoreBasePackage;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableBatchProcessing
@SpringBootApplication(scanBasePackageClasses = { CoreBasePackage.class, SchedulerBasePackage.class })
public class InkSchedulerApplication {

	@PostConstruct
	public void initialize() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}

	public static void main(String[] args) {
		SpringApplication.run(InkSchedulerApplication.class, args);
	}

}
