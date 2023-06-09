package com.emt.userback.component.api.user;

import co.edu.icesi.emt.auth.domain.repository.user.UserRepository;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteUserTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @Test
    @SneakyThrows
    public void deleteUserSuccessfully() {

        mockMvc.perform(delete("/users/diego"))
                .andExpect(status().isOk());
    }
}
