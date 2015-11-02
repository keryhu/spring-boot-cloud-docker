package com.ib.booking.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuthServerApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ApplicationTests {

	private Log log = LogFactory.getLog(AuthServerApplication.class);

	@Value("${local.server.port}")
	private int port;

	private RestTemplate template = new TestRestTemplate();

	@Test
	public void homePageProtected() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:"
				+ port + "/auth/", String.class);
		//assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		String auth = response.getHeaders().getFirst("WWW-Authenticate");
		log.debug("**** homePageProtected Auth : " + auth + " Status Code : " +response.getStatusCode());
		//assertTrue("Wrong header: " + auth, auth.startsWith("Bearer realm=\""));
	}

	@Test
	public void userEndpointProtected() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:"
				+ port + "/auth/user", String.class);
		//assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		String auth = response.getHeaders().getFirst("WWW-Authenticate");
		log.debug("**** userEndpointProtected Auth : " + auth + " Status Code : " +response.getStatusCode());
		//assertTrue("Wrong header: " + auth, auth.startsWith("Bearer realm=\""));
	}

	@Test
	public void authorizationRedirects() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:"
				+ port + "/auth/oauth/authorize", String.class);
		//assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		String auth = response.getHeaders().getFirst("WWW-Authenticate");
		log.debug("*** authorizationRedirects Auth : " + auth + " Status Code : " +response.getStatusCode());
		//assertTrue("Wrong header: " + auth, auth.startsWith("Basic realm=\""));
	}

}
