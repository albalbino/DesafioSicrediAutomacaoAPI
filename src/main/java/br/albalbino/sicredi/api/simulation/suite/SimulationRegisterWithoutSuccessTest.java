package br.albalbino.sicredi.api.simulation.suite;

import static io.restassured.RestAssured.given;

import org.junit.Test;

/**
 * @author albalbino Test Class - POST
 */

/**
 * Cenário 01: Tentar registrar simulação de crédito sem sucesso | POST <host>/api/v1/simulacoes + {.JSON} 
 * Dado que esteja conectado na API - Simulação de Crédito
 * E passe ao menos um dado inválido no corpo da requisição 
 * Quando realizar o envio do corpo para API - Simulação de Crédito  
 * Então não deve permitir inserir uma nova simulação
 * E deve ser retornado HTTP Status "400 BAD REQUEST"
 */

public class SimulationRegisterWithoutSuccessTest {
	
	private String url = "http://localhost:8080/api/v1/simulacoes";
	
	@Test
//	@DisplayName("[POST] <host>/api/v1/simulacoes + {.JSON} => CADASTRAR UMA SIMULAÇÃO SEM SUCESSO | ")
	public void validateSimulationsRegisteredUnsuccessfully() {
		given()
			.log().all()
			.contentType("application/json")
			.body("{ \"nome\": \"Joana Darc Lopes de Oliveira\", \"email\": \"joana@email.com\", \"valor\": 1600, \"parcelas\": 10, \"seguro\": true }")
		.when()
			.post(url)
		.then()
			.log().all()
			.statusCode(400);
	}

}
