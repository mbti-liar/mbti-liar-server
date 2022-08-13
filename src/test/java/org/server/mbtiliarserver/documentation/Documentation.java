package org.server.mbtiliarserver.documentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentation;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
public class Documentation {

    @LocalServerPort
    int port;
    protected static final ObjectMapper objectMapper = new ObjectMapper();
    protected RequestSpecification spec;

    protected RequestSpecification given() {
        return RestAssured.given(spec).log().all();
    }

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        RestAssured.port = port;

        spec = new RequestSpecBuilder()
            .addFilter(RestAssuredRestDocumentation
                .documentationConfiguration(restDocumentation)).build();
    }
}
