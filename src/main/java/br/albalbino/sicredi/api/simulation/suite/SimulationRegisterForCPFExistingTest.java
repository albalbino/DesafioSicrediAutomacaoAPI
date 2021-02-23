package br.albalbino.sicredi.api.simulation.suite;

import static io.restassured.RestAssured.given;

import org.junit.Test;

/**
 * @author albalbino Test Class - POST
 */


/**
 * Cenário 01: Tentar registrar simulação de crédito CPF já cadastrado | POST <host>/api/v1/simulacoes + {.JSON} 
 * Dado que esteja conectado na API - Simulação de Crédito
 * E que eu tenha uma simulação previamente cadastrada com o mesmo CPF 
 * Quando realizar o envio do corpo para API - Simulação de Crédito  
 * Então não deve permitir inserir a simulação para o mesmo CPF
 * E deve ser retornado HTTP Status "409 CONFLICT"
 */


public class SimulationRegisterForCPFExistingTest {
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
//	@DisplayName("[POST] <host>/api/v1/simulacoes + {.JSON} => CADASTRAR UMA SIMULAÇÃO CPF DUPLICADO | ")
	public void validateSimulationsRegisteredCPFDuplicated() {
		given()
			.log().all()
			.contentType("application/json")
			.body("{ \"nome\": \"Joana Darc Lopes de Oliveira\", \"cpf\": 11111111111, \"email\": \"joana@email.com\", \"valor\": 1600, \"parcelas\": 10, \"seguro\": true }")
		.when()
			.post(url)
		.then()
			.log().all()
			.statusCode(400); // STATUS CODE MAIS ADEQUADO => 409 Conflict. Esta resposta será enviada quando uma requisição conflitar com o estado atual do servidor. //
	}

}
