package br.albalbino.sicredi.api.simulation.suite.get;

import static io.restassured.RestAssured.given;
import org.junit.Test;

/**
 * @author albalbino Test Class - GET
 */
public class ValidatesReturnAllSimulationsIndexedSuccessTest {
	private String url = "http://localhost:8080/api/v1/simulacoes";
	
	/**
	 * Cenário 01: Requisitar todos os registros de simulações de crédito cadastrados| GET <host>/api/v1/simulacoes 
	 * Dado que esteja conectado na API - Simulação de Crédito
	 * E requisitar todos os registros cadastrados para a API - Simulação de Crédito  
	 * Então deve retornar a listagem com todos as simulações cadastradas
	 * E deve ser retornado HTTP Status "200 OK"
	 */
	
	@Test
//	@DisplayName("[GET] <host>/api/v1/simulacoes => RETORNAR TODAS AS SIMULAÇÕES CADASTRADAS COM SUCESSO | ")
	public void validatesReturnAllSimulationsIndexedSuccess() {
		given()
		.when()
			.get(url)
		.then()
			.log().all()
			.statusCode(200);
	}
	

}
