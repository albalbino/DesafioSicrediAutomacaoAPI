package br.albalbino.sicredi.api.restriction.suite;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;


import io.restassured.http.Method;

/**
 * @author albalbino
 * Test Class - GET
 */

public class ValidateCPFWithRestrictionTest {
	 /**
     * Cenário 01: Consultar um CPF com restrição | GET <host>/api/v1/restricoes/{cpf}
     * Dado que esteja conectado na Simulação de Crédito
     * Quando requisitar o cpf cadastrado "97093236014" 
     * Então deve ser retornado o HTTP Status "200 OK"
     * E deve ser verificada o retorno da mensagem "O CPF 97093236014 tem problema"
     */
	
	private String url = "http://localhost:8080/api/v1/restricoes/";
	
	@Test
//	@DisplayName("[GET] <host>/api/v1/restricoes/{cpf} => CONSULTAR UM CPF COM RESTRIÇÃO | ")
	public void validateCPFWithRestriction() {
		String cpf = "97093236014";
		String expected = "O CPF " + cpf + " tem problema";
		given()
			.log().all()
			.contentType("application/json")
			.when().request(Method.GET, url + cpf)
			.then().log().all().statusCode(200)
			.body("mensagem", equalTo(expected));

	}

}
