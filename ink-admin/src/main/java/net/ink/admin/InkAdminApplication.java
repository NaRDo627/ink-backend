package net.ink.admin;

import net.ink.AdminBasePackage;
import net.ink.CoreBasePackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication(scanBasePackageClasses = { CoreBasePackage.class, AdminBasePackage.class })
public class InkAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(InkAdminApplication.class, args);
    }

}
