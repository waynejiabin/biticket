package com.biticket.project.wallet.member.controller;

import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CommonControllerTest {
    private String prefix = "/member";
    private MockMvc mvc;



    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new CommonControllerTest()).build();
    }
}