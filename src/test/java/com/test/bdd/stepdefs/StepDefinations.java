package com.test.bdd.stepdefs;

import com.test.dto.request.UserRequestDto;
import io.cucumber.java8.En;
import io.restassured.response.Response;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StepDefinations extends AbstractSteps implements En {

    public StepDefinations() {

        Given("User can access the API", () -> {

            testContext().reset();
        });

        When("User fill the field id with {string}", (String value) -> {
            UserRequestDto userRequestDto = new UserRequestDto();
            userRequestDto.setId(Long.parseLong(value));
            super.testContext().setPayload(userRequestDto);
        });

        When("User wants to delete the account with id: {string}", (String value) -> {
            super.testContext().set("id", Long.parseLong(value));
        });

        And("User fill the field {string} with {string}", (String field, String value) -> {

            UserRequestDto payload = super.testContext()
                    .getPayload(UserRequestDto.class);
            if(field.equals("phone")){
                payload.setPhone(Long.parseLong(value));
            }else if(field.equals("name")){
                payload.setName(value);
            }else if(field.equals("email")){
                payload.setEmail(value);
            }else if(field.equals("address")){
                payload.setAddress(value);
            }else if(field.equals("country")){
                payload.setCountry(value);
            }else if(field.equals("department")){
                payload.setDepartment(value);
            }
        });

        And("User send this data to API \\({})", (String action) -> {

            if(action.equals("CREATE")){
                String createUserAccountUrl = "/api/v1/user";
                executePost(createUserAccountUrl);
            }else if(action.equals("UPDATE")){
                String createUserAccountUrl = "/api/v1/user";
                executePut(createUserAccountUrl);
            }else if(action.equals("DELETE")){
                String createUserAccountUrl = "/api/v1/user/"+super.testContext().get("id");
                executeDelete(createUserAccountUrl);
            }
        });

        Then("API should return {} with {}", (String expectedResult, String content) -> {
            Response response = testContext().getResponse();

            System.out.print(response);
            switch (expectedResult) {
                case "Ok Created":
                    assertThat(response.statusCode()).isIn(200, 201);
                    break;
                case "Ok":
                    assertThat(response.statusCode()).isIn(200);
                    break;
                case "no content":
                    assertThat(response.statusCode()).isIn(204);
                    break;
                case "error":
                    assertThat(response.statusCode()).isBetween(400, 504);
                    break;
                default:
                    fail("Unexpected error");
            }
        });

    }
}
