package com.gateway;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
public class SwaggerConfig implements SwaggerResourcesProvider {

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<SwaggerResource>();
		resources.add(swaggerResource("demo-gateway","/demo-gateway/v2/api-docs","2.0"));
		resources.add(swaggerResource("demo-app","http://localhost:8045/v2/api-docs","2.0")); //"/demo-app/v2/api-docs"
		return resources;
	}
	
	@Bean
	public Docket AppSwaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(generateAPIInfo())
				.groupName("demo-gateway")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.gateway"))
				.build();
	}

	private ApiInfo generateAPIInfo() {
		return new ApiInfo("Demo Gateway Swagger","Implementing Swagger","1.0", "", getContacts(), "", "", new ArrayList());
	}

	private Contact getContacts() {
		return new Contact("Kiran Thota", "", "kiranthota17@gmail.com");
	}

	
	private SwaggerResource swaggerResource(String name, String location, String version)
	{
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}

	
}
