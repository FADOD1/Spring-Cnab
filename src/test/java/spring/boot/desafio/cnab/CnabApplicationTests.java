package spring.boot.desafio.cnab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CnabApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		// Testa se o contexto da aplicação carrega corretamente
		assertThat(restTemplate).isNotNull();
	}

	@Test
	void uploadFileEndpointShouldReturnSuccess() {
		// Testa o endpoint de upload de arquivo

		String url = "http://localhost:" + port + "/api/cnab/upload";

		// Criando um arquivo simulado (exemplo simples de texto)
		ByteArrayResource fileAsResource = new ByteArrayResource("conteúdo de teste".getBytes()) {
			@Override
			public String getFilename() {
				return "test-file.txt";
			}
		};

		// Criando o corpo da requisição multipart
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", fileAsResource);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "multipart/form-data");

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		// Fazendo uma requisição POST para o endpoint
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

		// Validando o status 200 e a mensagem de sucesso
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isEqualTo("Sucesso!");
	}
}