package com.test.bdd.stepdefs;

import com.test.dto.request.UserRequestDto;
import io.cucumber.java8.En;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
public class StepDefinations extends AbstractSteps implements En {

    public StepDefinations() {

        Given("User can access the API", () -> testContext().reset());

        When("User fill the field id with {string}", (String value) -> {
            UserRequestDto userRequestDto = new UserRequestDto();
            userRequestDto.setId(Long.parseLong(value));
            super.testContext().setPayload(userRequestDto);
        });

        When("User wants to delete the account with id: {string}", this::accept);

        And("User fill the field {string} with {string}", (String field, String value) -> {

            UserRequestDto payload = super.testContext()
                    .getPayload(UserRequestDto.class);
            switch (field) {
                case "phone":
                    payload.setPhone(Long.parseLong(value));
                    break;
                case "name":
                    payload.setName(value);
                    break;
                case "email":
                    payload.setEmail(value);
                    break;
                case "address":
                    payload.setAddress(value);
                    break;
                case "country":
                    payload.setCountry(value);
                    break;
                case "department":
                    payload.setDepartment(value);
                    break;
            }
        });

        And("User send this data to API \\({})", (String action) -> {

            switch (action) {
                case "CREATE": {
                    String createUserAccountUrl = "/api/v1/user";
                    executePost(createUserAccountUrl);
                    break;
                }
                case "UPDATE": {
                    String createUserAccountUrl = "/api/v1/user";
                    executePut(createUserAccountUrl);
                    break;
                }
                case "DELETE": {
                    String createUserAccountUrl = "/api/v1/user/" + super.testContext().get("id");
                    executeDelete(createUserAccountUrl);
                    break;
                }
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

    private void accept(String value) {
        super.testContext().set("id", Long.parseLong(value));
    }
}
