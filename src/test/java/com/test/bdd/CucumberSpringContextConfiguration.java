package com.test.bdd;

import io.cucumber.spring.CucumberContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, properties = {
        "local.api.host=http://localhost:8080"})
public class CucumberSpringContextConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(CucumberSpringContextConfiguration.class);

    final TestRestTemplate template;

    public CucumberSpringContextConfiguration(TestRestTemplate template) {
        this.template = template;
    }

    /**
     * Need this method so the cucumber will recognize this class as glue and load spring context configuration
     */
    public void setUp() {
        LOG.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
    }

}
