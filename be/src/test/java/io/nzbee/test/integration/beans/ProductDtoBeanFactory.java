package io.nzbee.test.integration.beans;

import io.nzbee.dto.product.Product;

public class ProductDtoBeanFactory {
	
	
	private Product getProductDtoBean() {
		Product pDto = new Product();
		
		//Set the brand
		pDto.setBrandCode("GLO01");
		pDto.setBrandDesc("Glorys");
		
		//Set the category
		pDto.setDepartmentCode("FOO01");
		pDto.setDepartmentDesc("Food");

		pDto.setLclCd("en-GB");
		pDto.setCurrency("HKD");

		pDto.setProductStatusCode("ACT01");
		pDto.setProductUPC("3254354673");
		pDto.setProductDesc("Test Product Description");
		
		pDto.setProductImage("test_image.jpg");
		pDto.setProductMarkdown(new Double(78));
		pDto.setProductRetail(new Double(71));
		
		
		
		return null;
	}
		
	
	
}
