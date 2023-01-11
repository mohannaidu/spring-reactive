package com.example.reactive;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.reactive.model.Foo;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoFooStreamTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void testFooStream() {

		List<Foo> foos = webClient
				.get().uri("/foo/stream")
				.accept(MediaType.valueOf(MediaType.TEXT_EVENT_STREAM_VALUE))
				.exchange()
				.expectStatus().isOk()
				.returnResult(Foo.class)
				.getResponseBody()
				.take(20) // take 10 records
				.collectList()
				.block();

		assert foos != null;
		foos.forEach(x -> System.out.println(x.getName()));

		assertEquals(20, foos.size());

	}


}
