package io.nzbee.util.product.shipping;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.Constants;
import io.nzbee.entity.product.shipping.ShippingProductEntity;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class ShippingProductMasterService {

	private static final Logger logger = LoggerFactory.getLogger(ShippingProductMasterService.class);
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
    
    @Autowired
    private IShippingProductService ShippingProductService;
    
	@Transactional
	public void writeShippingProductMaster(String fileName) {
		logger.debug("called writeShippingProductMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<ShippingProductMasterSchema> readValues =
	        	mapper.readerFor(ShippingProductMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(sdms -> {
	        	this.persistShippingProductMaster(sdms);
	        });
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public void persistShippingProductMaster(ShippingProductMasterSchema sdms) {
		logger.debug("called persistShippingProductMaster");

	}
	
	
	private ShippingProductEntity mapToShippingProduct() {
		
		return null;
		
	}
}
