package br.albalbino.sicredi.api.simulation.suite.get;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

/**
 * Cenário 01: Validar retorno sem nenhum registro de simulação de crédito cadastrado| GET <host>/api/v1/simulacoes 
 * Dado que esteja conectado na API - Simulação de Crédito
 * E requisitar registros cadastrados para a API - Simulação de Crédito  
 * Quando não houver simulações cadastradas
 * Então deve retornar a lista vazia
 * E deve ser retornado HTTP Status "204 NO CONTENT"
 */

/**
 * @author albalbino Test Class - GET
 */

public class ValidatesReturnWithoutNoneSimulationRegisterTest {
	private String url = "http://localhost:8080/api/v1/simulacoes";

	@Test
//	@DisplayName("[GET] <host>/api/v1/simulacoes => VALIDAR RETORNO PARA NENHUMA SIMULAÇÃO CADASTRADA COM SUCESSO | ")
	public void validatesReturnWithoutNoneSimulationRegister() {
		given()
			.log().all()
			.contentType("application/json")
			.when()
				.get(url)
			.then()
				.log().all()
				.statusCode(204)
				.body("id", is(notNullValue()));
	}

}
