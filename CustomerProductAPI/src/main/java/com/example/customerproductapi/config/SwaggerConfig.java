package com.example.customerproductapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig
{

	@Bean
	public OpenAPI customOpenAPI()
	{
		return new OpenAPI().info(new Info().title("Customer and Product API").version("1.0")
				.description("API for managing customers and products"));
	}
}
