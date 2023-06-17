package com.inclusion.cloud.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inclusion.cloud.CloudApplicationTests;
import com.inclusion.cloud.util.FileMapper;
import com.inclusion.cloud.util.ParametersInput;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MaximumControllerTest extends CloudApplicationTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private FileMapper fileMapper;
    TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
    };

    @Test
    void givenXAndYAndNWhenCalledToGetMaximumEndpointThenItShouldReturnResultOk() throws Exception {
        //given
        List<List<Long>> tests = ParametersInput.getTests();
        for (List<Long> test : tests) {
            //when
            createResult(test.get(ParametersInput.PARAMETER_X), test.get(ParametersInput.PARAMETER_Y), test.get(ParametersInput.PARAMETER_N));

            String uri = MaximumController.BASE_URL +
                    MaximumController.ENDPOINT_GET_RESULT
                            .replace("{x}", test.get(ParametersInput.PARAMETER_X).toString())
                            .replace("{y}", test.get(ParametersInput.PARAMETER_Y).toString())
                            .replace("{n}", test.get(ParametersInput.PARAMETER_N).toString());
            String response = mvc.perform(get(uri)
                            .contentType("application/json"))
                    .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
            HashMap<String, Object> responseDto = new ObjectMapper().readValue(response, typeRef);
            //then
            assertTrue((boolean) responseDto.get("status"));
            assertEquals(test.get(ParametersInput.PARAMETER_RESULT).longValue(), ((Number) responseDto.get("data")).longValue());
        }
    }


    @Test
    void givenXAndYAndNWhenResultNoExistsInDataBaseThenItShouldReturnResultOk() throws Exception {
        //given
        Long x = 10L;
        Long y = 5L;
        Long n = 12347L;
        Long result = 12345L;
        //when

        String uri = MaximumController.BASE_URL +
                MaximumController.ENDPOINT_GET_RESULT
                        .replace("{x}", x.toString())
                        .replace("{y}", y.toString())
                        .replace("{n}", n.toString());
        String response = mvc.perform(get(uri)
                        .contentType("application/json"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        HashMap<String, Object> responseDto = new ObjectMapper().readValue(response, typeRef);
        //then
        assertTrue((boolean) responseDto.get("status"));
        assertEquals(result.longValue(), ((Number) responseDto.get("data")).longValue());
    }

    @Test
    void givenXAndYAndNWhenTheParametersAreInvalidThenItShouldReturnError() throws Exception {
        //given
        Long x = 1L;
        Long y = 5L;
        Long n = 4L;
        Long result = 12345L;
        //when
        String uri = MaximumController.BASE_URL +
                MaximumController.ENDPOINT_GET_RESULT
                        .replace("{x}", x.toString())
                        .replace("{y}", y.toString())
                        .replace("{n}", n.toString());
        String response = mvc.perform(get(uri)
                        .contentType("application/json"))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
        HashMap<String, Object> responseDto = new ObjectMapper().readValue(response, typeRef);
        //then
        assertFalse((boolean) responseDto.get("status"));
        assertNotNull(responseDto.get("errors"));
    }


    @Test
    void givenXAndYAndNWhenTheParametersAreInvalidThenItShouldReturnErrorForSecondTime() throws Exception {
        //given
        Long x = 10000000010L;
        Long y = -5L;
        Long n = 20000000010L;
        Long result = 12345L;
        //when
        String uri = MaximumController.BASE_URL +
                MaximumController.ENDPOINT_GET_RESULT
                        .replace("{x}", x.toString())
                        .replace("{y}", y.toString())
                        .replace("{n}", n.toString());
        String response = mvc.perform(get(uri)
                        .contentType("application/json"))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
        HashMap<String, Object> responseDto = new ObjectMapper().readValue(response, typeRef);
        //then
        assertFalse((boolean) responseDto.get("status"));
        assertNotNull(responseDto.get("errors"));
    }

    void createResult(Long x, Long y, Long n) throws Exception {
        //given
        String body = this.fileMapper.loadTestJsonToString("result/createResult.json");
        body = body.replace("{x}", x.toString()).replace("{y}", y.toString()).replace("{n}", n.toString());
        //when
        String uri = MaximumController.BASE_URL;
        String response = mvc.perform(post(uri)
                        .contentType("application/json").content(body))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        HashMap<String, Object> responseDto = new ObjectMapper().readValue(response, typeRef);
        //then
        assertTrue((boolean) responseDto.get("status"));
    }
}
