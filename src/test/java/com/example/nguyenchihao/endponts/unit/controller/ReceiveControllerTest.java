package com.example.nguyenchihao.endponts.unit.controller;

import com.example.nguyenchihao.entity.ReceiveEntity;
import com.example.nguyenchihao.service.ReceiveService;
import net.minidev.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
public class ReceiveControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceiveService service;

    @Test
    public void createReceive_whenPostMethod() throws Exception {
        ReceiveEntity receive = new ReceiveEntity();
        receive.setId(1);
        receive.setName("Mobile phone");
        receive.setUnit(5);
        receive.setQuantity(10);
        receive.setPrice(50);
//        given(service.save(receive)).willReturn(receive);
//        mockMvc.perform(post("/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(receive))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name", is(user.getName())));
    }

    @Test
    public void listAllUsers_whenGetMethod()
            throws Exception {

        ReceiveEntity receive = new ReceiveEntity();
        receive.setId(1);
        receive.setName("Mobile phone");
        receive.setUnit(5);
        receive.setQuantity(10);
        receive.setPrice(50);

        List<ReceiveEntity> allReceives = asList(receive);

        given(service
                .getAll(1, 5))
                .willReturn(allReceives);

        mockMvc.perform(get("/receives")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(receive.getId())))
                .andExpect(jsonPath("$[0].name", is(receive.getName())))
                .andExpect(jsonPath("$[0].unit", is(receive.getUnit())))
                .andExpect(jsonPath("$[0].quantity", is(receive.getQuantity())))
                .andExpect(jsonPath("$[0].price", is(receive.getPrice())));
    }
}
