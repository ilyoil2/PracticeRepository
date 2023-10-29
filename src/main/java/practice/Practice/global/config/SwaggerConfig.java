package practice.Practice.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info =
        @Info(
                title = "테스트 : 테스트용 API 명세서",
                description = "안녕하세요"))
@Configuration
public class SwaggerConfig {}
