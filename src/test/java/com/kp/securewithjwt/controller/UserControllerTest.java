package com.kp.securewithjwt.controller;

import com.kp.securewithjwt.Model.AuthenticationRequest;
import com.kp.securewithjwt.Model.UserApp;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    protected String obtainAccessToken(String username,String password) throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(username,password);
        String content = super.mapToJson(authenticationRequest);
        String uri ="/authenticate";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201,status);
        String result = mvcResult.getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(result).get("jwttoken").toString();
    }

    @Test
    public void getUsers() throws Exception {
        String uri ="/roles";
        String token = obtainAccessToken("foo","foo");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .header("Authorization","Bearer "+token)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String users = mvcResult.getResponse().getContentAsString();
        UserApp [] userAppList = super.mapFromJson(users,UserApp[].class);

    }
    @Test
    public void createser() throws Exception {
        String uri ="/users";
        UserApp userApp = new UserApp();
        userApp.setId(1L);
        userApp.setPassword("passer");
        userApp.setUsername("kassepro");
        userApp.setAdresse("THIAIS");
        userApp.setEmail("momartallakasse@gmail.com");
        userApp.setNom("KASSE");
        userApp.setPrenom("Momar Talla");
        userApp.setTel("+33 0751085725");
        String content = super.mapToJson(userApp);
        String token = obtainAccessToken("foo","foo");
        System.out.println("Authorization"+" Bearer "+token);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .header("Authorization","Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON).content(content)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String response = mvcResult.getResponse().getContentAsString();
        UserApp u = super.mapFromJson(response,UserApp.class);
        assertEquals(response, "UserApp is created successfully");
    }
}
