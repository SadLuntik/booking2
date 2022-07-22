package com.sberschool.booking.service.impl;

import com.sberschool.booking.controller.EventController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("userTest")
public class UserAuthTest {
    @Autowired
    private EventController eventController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void mainPage() throws Exception {
        this.mockMvc.perform(get("/event")).
                andDo(print()).
                andExpect(authenticated()).
                andExpect(content().string(containsString("userTest")));
    }
}
