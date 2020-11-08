package io.nzbee.test.integration.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.io.InputStream;
import java.util.HashSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import io.nzbee.domain.product.IProductService;
import io.nzbee.security.WebSecurityConfig;
import io.nzbee.util.FileController;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(/*addFilters = false*/)
@WebMvcTest(FileController.class)
@Import(WebSecurityConfig.class)
@ActiveProfiles(profiles = "tst")
public class IT_ProductUploadDownloadRestControllerTest {

	private InputStream is;
	
    private MockMvc mockMvc;
    
    @MockBean
	private JavaMailSender mailSender;
    
    @Mock
    private IProductService productService;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Spy
    @InjectMocks
    private FileController controller = new FileController();
 
    
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
        is = controller.getClass().getClassLoader().getResourceAsStream("food_master.txt");
    }
    
    
    @SuppressWarnings("deprecation")
	@Test
    public void testUploadFile() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "food_master.txt", "multipart/form-data", is);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/Product/Upload/").file(mockMultipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertNotNull(result.getResponse().getContentAsString());
        //Assert.assertEquals("food_master.txt", result.getResponse().get("fileName"));
        //{"fileName":"food_master.txt","fileUri":"http://localhost/data/uploads/food_master.txt","fileType":"multipart/form-data","size":0}
    }
    
    
    @Test
    public void testDownloadFile() throws Exception {
        Mockito.when(productService.findAll("en-GB", "HKD"))
        						   .thenReturn(new HashSet<>());
        
        
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/Product/Download/food_master.txt")
        						  .contentType(MediaType.APPLICATION_JSON))
        						  .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        
        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertEquals(5011, result.getResponse().getContentAsByteArray().length);
        Assert.assertEquals("text/plain", result.getResponse().getContentType());
    }
	
}
