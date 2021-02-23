package br.albalbino.sicredi.api.simulation.suite.put;

import static io.restassured.RestAssured.given;

import org.junit.Test;

/**
 * Cenário 01: Alterar uma simulação de crédito já cadastrada | PUT <host>/api/v1/simulacoes/ + {CPF} 
 * Dado que esteja conectado na API - Simulação de Crédito
 * E passe novos dados válidos no corpo da requisição para um CPF já cadastrado  
 * Quando realizar o envio do corpo para API - Simulação de Crédito  
 * Então deve alterar os parâmetros inseridos para o CPF já cadastrado
 * E deve ser retornado o HTTP Status "200 OK"
 * E deve ser verificado o retorno da mensagem "Simulação alterada com sucesso"
 */

public class ValidateChangeInOneSimulationExistingTest {
	
	private String url = "http://localhost:8080/api/v1/simulacoes";
	
	@Test
//	@DisplayName("[POST] <host>/api/v1/simulacoes + {.JSON} => CADASTRAR UMA SIMULAÇÃO COM SUCESSO | ")
	public void validateSimulationsRegisteredSuccessfully() {
		given()
			.log().all()
			.contentType("application/json")
			.body("{ \"nome\": \"Joana Darc Lopes de Oliveira\", \"cpf\": 11111111111, \"email\": \"joana@email.com\", \"valor\": 1600, \"parcelas\": 10, \"seguro\": true }")
		.when()
			.post(url)
		.then()
			.log().all()
			.statusCode(201);
	}
	
	
	@Test
//	@DisplayName("[PUT] <host>/api/v1/simulacoes/ + {CPF} => ALTERAR UMA SIMULAÇÃO CADASTRADA | ")
	public void validateChangeSimulationRegistered() {		
		String cpf = "11111111111";
		given()
			.log().all()
			.contentType("application/json")
			.body("{ \"nome\": \"Joana Darc Lopes de Oliveira\", \"email\": \"joana@email.com\", \"valor\": 2000, \"parcelas\": 15, \"seguro\": false }")
		.when()
			.put(url + "/" + cpf)
		.then()
			.log().all()
			.statusCode(200);
	}
	
	

}
