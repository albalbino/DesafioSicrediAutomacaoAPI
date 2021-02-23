package br.albalbino.sicredi.api.simulacoes.suite;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.Ignore;
import io.restassured.http.Method;

/**
 * @author albalbino Test Class - POST
 */

//@DisplayName("| CONSULTA SIMULAÇÕES POR CPF - SIMULAÇÃO DE CRÉDITO - API | ")
public class ConsultationSimulationCPFTest {
	
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
	
	@Ignore
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
	
	/**
	 * Cenário 02: Tentar registrar simulação de crédito sem sucesso | POST <host>/api/v1/simulacoes + {.JSON} 
	 * Dado que esteja conectado na API - Simulação de Crédito
	 * E passe ao menos um dado inválido no corpo da requisição 
	 * Quando realizar o envio do corpo para API - Simulação de Crédito  
	 * Então não deve permitir inserir uma nova simulação
	 * E deve ser retornado HTTP Status "400 BAD REQUEST"
	 */
	
	@Ignore	
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
	
	
	
	
	
	/**
	 * Cenário 03: Tentar registrar simulação de crédito CPF já cadastrado | POST <host>/api/v1/simulacoes + {.JSON} 
	 * Dado que esteja conectado na API - Simulação de Crédito
	 * E que eu tenha uma simulação previamente cadastrada com o mesmo CPF 
	 * Quando realizar o envio do corpo para API - Simulação de Crédito  
	 * Então não deve permitir inserir a simulação para o mesmo CPF
	 * E deve ser retornado HTTP Status "409 CONFLICT"
	 */
	
	@Ignore
	@Test
//	@DisplayName("[POST] <host>/api/v1/simulacoes + {.JSON} => CADASTRAR UMA SIMULAÇÃO CPF DUPLICADO | ")
	public void validateSimulationsRegisteredCPFDuplicated() {
		String expected = "CPF já existente";	
		given()
			.log().all()
			.contentType("application/json")
			.body("{ \"nome\": \"Joana Darc Lopes de Oliveira\", \"cpf\": 11111111111, \"email\": \"joana@email.com\", \"valor\": 1600, \"parcelas\": 10, \"seguro\": true }")
		.when()
			.post(url)
		.then()
			.log().all()
			.statusCode(409) // STATUS CODE MAIS ADEQUADO => 409 Conflict. Esta resposta será enviada quando uma requisição conflitar com o estado atual do servidor. //
			.body("message", equalTo(expected));
	}
	
	
	/**
	 * Cenário 04: Alterar uma simulação de crédito já cadastrada | PUT <host>/api/v1/simulacoes/ + {CPF} 
	 * Dado que esteja conectado na API - Simulação de Crédito
	 * E passe novos dados válidos no corpo da requisição para um CPF já cadastrado  
	 * Quando realizar o envio do corpo para API - Simulação de Crédito  
	 * Então deve alterar os parâmetros inseridos para o CPF já cadastrado
	 * E deve ser retornado o HTTP Status "200 OK"
	 * E deve ser verificado o retorno da mensagem "Simulação alterada com sucesso"
	 */
	
	@Test
//	@DisplayName("[PUT] <host>/api/v1/simulacoes/ + {CPF} => ALTERAR UMA SIMULAÇÃO CADASTRADA | ")
	public void validateChangeSimulationRegistered() {		
		String cpf = "17822386034";
		String expected = "Simulação alterada com sucesso";
		given()
			.log().all()
			.contentType("application/json")
			.body("{ \"nome\": \"Deltrano da Silva Nascimento\", \"email\": \"deltrano.nascimento@gmail.com\", \"valor\": 10000.00, \"parcelas\": 10, \"seguro\": true }")
		.when()
			.put(url + "/" + cpf)
		.then()
			.log().all()
			.statusCode(200)
			.body("message", equalTo(expected));
	}
	
	/**
	 * Cenário 05: Validar simulação não encontrada ao tentar alterar uma simulação de crédito pelo CPF | PUT <host>/api/v1/simulacoes/ + {CPF} 
	 * Dado que esteja conectado na API - Simulação de Crédito
	 * E passe novos dados válidos no corpo da requisição para um CPF não cadastrado  
	 * Quando realizar o envio do corpo para API - Simulação de Crédito  
	 * Então deve ser retornado o HTTP Status "404 NOT FOUND"
	 * E deve ser verificado o retorno da mensagem "Simulação não encontrada"
	 */
	
	@Ignore
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
	
	/**
	 * Cenário 06: Requisitar todos os registros de simulações de crédito cadastrados| GET <host>/api/v1/simulacoes 
	 * Dado que esteja conectado na API - Simulação de Crédito
	 * E requisitar todos os registros cadastrados para a API - Simulação de Crédito  
	 * Então deve retornar a listagem com todos as simulações cadastradas
	 * E deve ser retornado HTTP Status "200 OK"
	 */
	
	@Ignore
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
	
	/**
	 * Cenário 07: Validar retorno sem nenhum registro de simulação de crédito cadastrado| GET <host>/api/v1/simulacoes 
	 * Dado que esteja conectado na API - Simulação de Crédito
	 * E requisitar registros cadastrados para a API - Simulação de Crédito  
	 * Quando não houver simulações cadastradas
	 * Então deve retornar a lista vazia
	 * E deve ser retornado HTTP Status "204 NO CONTENT"
	 */
	
	
	@Ignore
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
	
	/**
	 * Cenário 08: Deletar um registro de simulação de crédito cadastrado com sucesso | DELETE <host>/api/v1/simulacoes/{id} 
	 * Dado que esteja conectado na API - Simulação de Crédito
	 * Quando deletar um registro de simulação de crédito pelo id  
	 * Então deve ser retornado HTTP Status "200 OK"
	 * E deve ser retornada a mensagem "Simulação removida com sucesso"
	 */
	
	@Ignore
	@Test
//	@DisplayName("DELETE <host>/api/v1/simulacoes/{id} => DELETAR APENAS UMA SIMULAÇÃO CADASTRADA COM SUCESSO | ")
	public void validatesReturnDeletingOneSimulationRegisterWithSuccess() {
		String id = "53";
		String expected = "Simulação removida com sucesso";
		given()
			.log().all()
			.contentType("application/json")
			.when()
				.delete(url + "/" + id)
			.then()
				.log().all()
				.statusCode(200)
				.body("message", equalTo(expected));
	}
	
	/**
	 * Cenário 09: Deletar um registro de simulação de crédito cadastrado com sucesso | DELETE <host>/api/v1/simulacoes/{id} 
	 * Dado que esteja conectado na API - Simulação de Crédito
	 * Quando tentar deletar um registro de simulação de crédito pelo id
	 * E não houver nenhum id relacionado com  algum registro de simulação de crédito cadastrado  
	 * Então deve ser retornado HTTP Status "404 NOT FOUND"
	 */
	
	
	@Ignore
	@Test
//	@DisplayName("DELETE <host>/api/v1/simulacoes/{id} => DELETAR UMA SIMULAÇÃO CADASTRADA COM SUCESSO | ")
	public void validatesReturnAttemptDeleteOneSimulationNoRegister() {
		String id = "99";
		String expected = "Simulação não encontrada";
		given()
			.log().all()
			.contentType("application/json")
			.when()
				.delete(url + "/" + id)
			.then()
				.log().all()
				.statusCode(404) // STATUS CODE MAIS ADEQUADO => 404 Not Found . O servidor não pode encontrar o recurso solicitado. //
				.body("message" , equalTo(expected));
	}



}
