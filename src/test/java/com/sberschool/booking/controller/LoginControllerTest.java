package com.sberschool.booking.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void startPage() throws Exception {
        this.mockMvc.perform(get("/")).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().string(containsString("Hello!!!")));
    }

    @Test
    void loginPageCheckForOk() throws Exception {
        this.mockMvc.perform(get("/login")).
                andDo(print()).
                andExpect(status().isOk());
    }

    @Test
    void accessDenied() throws Exception {
        this.mockMvc.perform(get("/event")).
                andDo(print()).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void correctLogin() throws Exception {
        this.mockMvc.perform(formLogin().user("userTest").password("1")).
                andDo(print()).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/event"));
    }

    @Test
    public void NotValidLogin() throws Exception {
        this.mockMvc.perform(post("/login").param("user","Viktor")).
                andDo(print()).
                andExpect(status().isForbidden());
    }
}