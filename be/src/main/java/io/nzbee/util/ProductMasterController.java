package io.nzbee.util;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductMasterController {

    @Autowired
    private ProductMasterService productMasterService;
	
	@Autowired
	private Environment env;
    
    public ProductMasterController() {
        super();
    }
    
    @GetMapping("/Product/Download/product_master.csv")
    public void downloadProductMaster(HttpServletResponse response) {
    	try {
	    	File tempFile = new ClassPathResource("data/" + env.getProperty("util.product-master-output-file")).getFile();
	    	response.setContentType("text/csv");
	        response.setHeader("Content-Disposition", "attachment; file=" + tempFile.getAbsolutePath());
	    	productMasterService.writeProductMaster(response.getWriter());
    	} catch (Exception e)  {
    		System.out.println(e);
    	}
    }
    
}
