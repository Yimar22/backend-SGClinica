package com.emt.userback.component.api.user;

import co.edu.icesi.emt.auth.domain.repository.user.UserRepository;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateUserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @SneakyThrows
    public void UpdateUserSuccessfully() {
        String userJson = "{\"name\":\"John Doe\",\"password\":\"hola\"}";

        var result = mockMvc.perform(put("/users/diego", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        JSONObject responseJson = new JSONObject(responseBody);
    }
}
