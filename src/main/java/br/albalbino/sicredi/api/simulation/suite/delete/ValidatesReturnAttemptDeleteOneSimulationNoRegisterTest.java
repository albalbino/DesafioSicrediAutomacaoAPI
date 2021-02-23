package br.albalbino.sicredi.api.simulation.suite.delete;

import static io.restassured.RestAssured.given;

import org.junit.Test;

/**
 * Cenário 01: Deletar um registro de simulação de crédito cadastrado com sucesso | DELETE <host>/api/v1/simulacoes/{id} 
 * Dado que esteja conectado na API - Simulação de Crédito
 * Quando tentar deletar um registro de simulação de crédito pelo id
 * E não houver nenhum id relacionado com  algum registro de simulação de crédito cadastrado  
 * Então deve ser retornado HTTP Status "404 NOT FOUND"
 */


/**
 * @author albalbino Test Class - DELETE
 */

public class ValidatesReturnAttemptDeleteOneSimulationNoRegisterTest {
	private String url = "http://localhost:8080/api/v1/simulacoes";

	@Test
//	@DisplayName("DELETE <host>/api/v1/simulacoes/{id} => DELETAR UMA SIMULAÇÃO CADASTRADA COM SUCESSO | ")
	public void validatesReturnAttemptDeleteOneSimulationNoRegister() {
		String id = "99";
		given()
			.log().all()
			.contentType("application/json")
			.when()
				.delete(url + "/" + id)
			.then()
				.log().all()
				.statusCode(200); // STATUS CODE MAIS ADEQUADO => 404 Not Found . O servidor não pode encontrar o recurso solicitado. //
	}



}
