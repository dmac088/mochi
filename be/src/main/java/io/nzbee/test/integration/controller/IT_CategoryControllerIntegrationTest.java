package io.nzbee.test.integration.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import io.nzbee.domain.category.CategoryServiceImpl;
import io.nzbee.resources.controllers.CategoryController;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
//@ContextConfiguration(classes = {CategoryController.class, 
//							     CategoryServiceImpl.class})
@WebMvcTest
public class IT_CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
	
    @Test
    public void testFindAll() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/Category/en-GB/HKD")
                //.with(user(TEST_USER_ID))
               //.with(csrf())
                //.content(birthday)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        
        
        String response = result.getResponse().getContentAsString();
        
        
        assertNotNull(response);
       // assertEquals(dow, resultDOW);
    }
}
