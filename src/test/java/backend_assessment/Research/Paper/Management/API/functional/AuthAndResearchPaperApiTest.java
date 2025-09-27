package backend_assessment.Research.Paper.Management.API.functional;

import backend_assessment.Research.Paper.Management.API.dto.LoginRequest;
import backend_assessment.Research.Paper.Management.API.dto.ResearchPaperRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthAndResearchPaperApiTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testLoginAndCreatePaper() {
        //  Login
        String loginUrl = "http://localhost:" + port + "/auth/login";
        String body = "{\"username\":\"admin\",\"password\":\"1234\"}";

        ResponseEntity<String> loginResp = restTemplate.postForEntity(loginUrl,
                new HttpEntity<>(body, getJsonHeaders()), String.class);

        assertEquals(HttpStatus.OK, loginResp.getStatusCode());
        assertTrue(loginResp.getBody().contains("token"));

        // extract token
        String token = loginResp.getBody().split("\"token\":\"")[1].split("\"")[0];

        // Create paper
        ResearchPaperRequest req = new ResearchPaperRequest();
        req.setName("Functional Paper");
        req.setDescription("Desc");
        req.setAbstractText("Abstract");

        HttpHeaders headers = getJsonHeaders();
        headers.set("Authorization", "Bearer " + token);

        ResponseEntity<String> paperResp = restTemplate.exchange(
                "http://localhost:" + port + "/api/research-papers",
                HttpMethod.POST,
                new HttpEntity<>(req, headers),
                String.class
        );

        assertEquals(HttpStatus.OK, paperResp.getStatusCode());
        assertTrue(paperResp.getBody().contains("Functional Paper"));
    }

    private HttpHeaders getJsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
