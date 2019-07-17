//
//package io.nzbee.util;
//
//import java.io.IOException;
//import javax.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api")
//public class ProductMasterController {
//
//	private static final Logger logger = LoggerFactory.getLogger(ProductMasterController.class);
//	
//    @Autowired
//    private ProductMasterService productMasterService;
//	
//	@Autowired
//	private FileStorageProperties fileStorageProperties;
//    
//    public ProductMasterController() {
//        super();
//    }
//    
//    @GetMapping("/Product/Download/product_master.csv")
//    public void downloadProductMaster(HttpServletResponse response) {
//    	try {
//	    	response.setContentType("text/csv");
//	        response.setHeader("Content-Disposition", "attachment; file=" + fileStorageProperties.getDownloadDir() + fileStorageProperties.getProductMasterFile());
//	    	productMasterService.writeProductMaster(response.getWriter());
//    	} catch (IOException e)  {
//    		logger.error(e.toString());
//    	}
//    }
//    
//}
//
