package com.locally.locallyoffersbff;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LocallyOffersBffApplicationTests {

	@Test
	void contextLoads() {}

	@Autowired
	private MockMvc mockMvc;

	// testy integracyjne
	@Test
	void shouldReturnOffer() throws Exception {
		System.out.println("\uD83E\uDD8D test działa !!!!!!!!!!!!!!!!!!!! \uD83E\uDD8D");
		// when
		mockMvc.perform(get("/offers/{id}", "some-id"))

				// then
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("some-id"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.category").isEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.seller").isEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.views").isEmpty());
	}

	@Test
	void shouldReturn404OnUnknownPath() throws Exception {
		// when
		mockMvc.perform(get("/offers/"))

				// then
				.andExpect(status().isNotFound());
	}

}
