package com.example.javascip;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HelloController helloController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }

    @Test
    void hello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Hello, World")));
    }

}
