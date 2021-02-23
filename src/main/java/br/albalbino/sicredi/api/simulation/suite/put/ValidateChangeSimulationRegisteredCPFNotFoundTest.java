package br.albalbino.sicredi.api.simulation.suite.put;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ValidateChangeSimulationRegisteredCPFNotFoundTest {
	private String url = "http://localhost:8080/api/v1/simulacoes";
	
	/**
	 * Cenário 01: Validar simulação não encontrada ao tentar alterar uma simulação de crédito pelo CPF | PUT <host>/api/v1/simulacoes/ + {CPF} 
	 * Dado que esteja conectado na API - Simulação de Crédito
	 * E passe novos dados válidos no corpo da requisição para um CPF não cadastrado  
	 * Quando realizar o envio do corpo para API - Simulação de Crédito  
	 * Então deve ser retornado o HTTP Status "404 NOT FOUND"
	 * E deve ser verificado o retorno da mensagem "Simulação não encontrada"
	 */
	
	@Test
//	@DisplayName("[PUT] <host>/api/v1/simulacoes/ + {CPF} => ALTERAR UMA SIMULAÇÃO CPF NÃO ENCONTRADO | ")
	public void validateChangeSimulationRegisteredCPFNotFound() {		
		String cpf = "XXXXXXXXXXX";
		String expected = "CPF " + cpf + " não encontrado";
		given()
			.log().all()
			.contentType("application/json")
			.body("{ \"nome\": \"Deltrano da Silva Nascimento\", \"email\": \"deltrano.nascimento@gmail.com\", \"valor\": 10000.00, \"parcelas\": 10, \"seguro\": true }")
		.when()
			.put(url + "/" + cpf)
		.then()
			.log().all()
			.statusCode(404)
			.body("mensagem", equalTo(expected));
	}

}
