package br.albalbino.sicredi.api.restriction.suite;


import static io.restassured.RestAssured.given;

import org.junit.Test;


import io.restassured.http.Method;

/**
* Cenário 01: Consultar um CPF sem restrição | GET <host>/api/v1/restricoes/{cpf} |}
* Dado que esteja conectado na Simulação de Crédito
* Quando requisitar o cpf cadastrado "10327655429" 
* Então deve ser retornado o HTTP Status "204 No Content"
*/

/**
 * @author albalbino
 * Test Class - GET
 */

public class ValidateCPFWithoutRestrictionTest {
	
	private String url = "http://localhost:8080/api/v1/restricoes/";

	@Test
//	@DisplayName("[GET] <host>/api/v1/restricoes/{cpf} => CONSULTAR UM CPF SEM RESTRIÇÃO | ")
	public void validateCPFWithoutRestriction() {
		String cpf = "97093236010";
		given()
			.log().all()
			.contentType("application/json")
			.when().request(Method.GET, url + cpf)
			.then().log().all().statusCode(204);
	}

}
