package practice.Practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import practice.Practice.global.security.jwt.JwtProperties;

@EnableAsync
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties(JwtProperties.class)
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}
}
