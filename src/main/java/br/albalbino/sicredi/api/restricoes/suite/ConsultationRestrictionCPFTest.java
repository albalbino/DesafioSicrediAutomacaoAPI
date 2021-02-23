package br.albalbino.sicredi.api.restricoes.suite;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
import org.junit.Test;


import io.restassured.http.Method;

/**
 * @author albalbino
 * Test Class - GET
 */
//@DisplayName("| CONSULTA RESTRIÇÕES POR CPF - SIMULAÇÃO DE CRÉDITO - API | ")
public class ConsultationRestrictionCPFTest {
	
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
	
	 /**
     * Cenário 02: Consultar um CPF sem restrição | GET <host>/api/v1/restricoes/{cpf} |}
     * Dado que esteja conectado na Simulação de Crédito
     * Quando requisitar o cpf cadastrado "10327655429" 
     * Então deve ser retornado o HTTP Status "204 No Content"
     */
	
	
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
