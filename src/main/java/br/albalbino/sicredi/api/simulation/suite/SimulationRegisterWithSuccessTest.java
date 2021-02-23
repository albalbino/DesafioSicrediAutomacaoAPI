package br.albalbino.sicredi.api.simulation.suite;

import static io.restassured.RestAssured.given;

import org.junit.Test;

/**
 * @author albalbino Test Class - POST
 */


public class SimulationRegisterWithSuccessTest {
	
	private String url = "http://localhost:8080/api/v1/simulacoes";
	
	/**
	 * Cenário 01: Cadastrar uma única simulação de crédito com sucesso | POST <host>/api/v1/simulacoes + {.JSON} 
	 * Dado que esteja conectado na API - Simulação de Crédito
	 * E passe dados válidos no corpo da requisição 
	 * Quando realizar o envio do corpo para API - Simulação de Crédito  
	 * Então deve inserir uma nova simulação
	 * E deve retornar a listagem com todas as simulações inseridas
	 * E deve ser retornado HTTP Status "201 CREATED"
	 */
	
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
	

}
