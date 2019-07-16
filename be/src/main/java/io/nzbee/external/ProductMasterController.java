package io.nzbee.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductMasterController {

    @Autowired
    private ProductMasterService productMasterService;

    public ProductMasterController() {
        super();
    }
    
    @GetMapping("/Product/Load")
    public void writeProducts() {
    	System.out.println("writeProducts");
    	 productMasterService.writeProductMaster();
    }
    
}
