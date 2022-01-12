package com.example.javascip;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class ScipControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ScipController scipController;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(scipController).build();
	}

	@Test
	void solve() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/solve"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
