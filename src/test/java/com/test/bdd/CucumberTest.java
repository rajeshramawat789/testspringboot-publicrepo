package com.test.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "json:target/cucumber-report.json"}
        )
public class CucumberTest extends CucumberSpringContextConfiguration{

}
