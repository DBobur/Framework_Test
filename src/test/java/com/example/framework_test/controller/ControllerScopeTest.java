package com.example.framework_test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerScopeTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testControllerIsSingleton() throws Exception {
        // Birinchi request
        MvcResult result1 = mockMvc.perform(get("/controller"))
                .andExpect(status().isOk())
                .andReturn();
        String id1 = result1.getResponse().getContentAsString();

        // Ikkinchi request
        MvcResult result2 = mockMvc.perform(get("/controller"))
                .andExpect(status().isOk())
                .andReturn();
        String id2 = result2.getResponse().getContentAsString();

        // Controller singleton bo'lgani uchun ID bir xil bo'lishi kerak
        assertThat(id1).isEqualTo(id2);
    }

    @Test
    public void testControllerIsPrototype() throws Exception {
        // Birinchi request
        MvcResult result1 = mockMvc.perform(get("/prototype-controller"))
                .andExpect(status().isOk())
                .andReturn();
        String id1 = result1.getResponse().getContentAsString();

        // Ikkinchi request
        MvcResult result2 = mockMvc.perform(get("/prototype-controller"))
                .andExpect(status().isOk())
                .andReturn();
        String id2 = result2.getResponse().getContentAsString();

        // Controller prototype bo'lsa, ID lar farq qilishi kerak
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    public void testRequestScopeBeanInsideSingletonController() throws Exception {
        // Birinchi request
        MvcResult result1 = mockMvc.perform(get("/request-scope"))
                .andExpect(status().isOk())
                .andReturn();
        String id1 = result1.getResponse().getContentAsString();

        // Ikkinchi request
        MvcResult result2 = mockMvc.perform(get("/request-scope"))
                .andExpect(status().isOk())
                .andReturn();
        String id2 = result2.getResponse().getContentAsString();

        // Har bir request yangi instance yaratishi kerak
        assertThat(id1).isNotEqualTo(id2);
    }


}
