package io.nzbee.util.brand;

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
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.util.FileStorageServiceUpload;

@Service
@Transactional
public class BrandMasterService {

	private static final Logger logger = LoggerFactory.getLogger(BrandMasterService.class);
	
	@Autowired
	private IBrandService brandService; 
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
	
    @Transactional
	public void writeBrandMaster(String fileName) {
		logger.debug("called writeBrandMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<BrandMasterSchema> readValues =
	        	mapper.readerFor(BrandMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(c -> {
	        	this.persistBrandMaster(c);
	        });
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public void persistBrandMaster(BrandMasterSchema b) {
		logger.debug("called persistBrandMaster() ");
		
		BrandEntity bEN = mapToBrand( b.get_BRAND_CODE(),
								b.get_BRAND_DESC_EN(),
								Constants.localeENGB);
				
		brandService.save(bEN);
		
		BrandEntity bCN = mapToBrand(	 b.get_BRAND_CODE(),
				 				 b.get_BRAND_DESC_HK(),
				 				 Constants.localeZHHK);
		
		brandService.save(bCN);
	}
	
	
	private BrandEntity mapToBrand(String brandCode,
							 String brandDesc,
							 String locale) {
		
		Optional<BrandEntity> ob = brandService.findByCode(brandCode);
						
		io.nzbee.entity.brand.BrandEntity b = 
		(ob.isPresent())
		? ob.get() 
		: new io.nzbee.entity.brand.BrandEntity();
		
		io.nzbee.entity.brand.attribute.BrandAttributeEntity ba = new io.nzbee.entity.brand.attribute.BrandAttributeEntity();
		if(ob.isPresent()) {
			Optional<io.nzbee.entity.brand.attribute.BrandAttributeEntity> oba =
					ob.get().getAttributes().stream().filter(a -> a.getLclCd().equals(locale)).findFirst();
			
			ba = (oba.isPresent()) 
			? oba.get()
			: new io.nzbee.entity.brand.attribute.BrandAttributeEntity();
		}
							
		b.setBrandCode(brandCode);
		
		ba.setBrandDesc(brandDesc);
		ba.setLclCd(locale);
		b.addAttribute(ba);
		ba.setBrand(b);
		
		return b;
	}
	
//	public void extractBrandMaster(Resource resource) {
//		logger.debug("called extractBrandMaster() ");
//		List<BrandMasterSchema> lpms = new ArrayList<BrandMasterSchema>();
//	    try {
//		    	List<BrandEntity> brandList = brandService.findAll(Constants.localeENGB)
//		    							  .stream().collect(Collectors.toList());
//		    	
//		    	brandList.stream().forEach(b -> {
//		    		System.out.println(((BrandEntity) b).getBrandCode());
//		    	});
//		    	
//		    	//create a map of brands (full list)
//		    	Map<String, BrandMasterSchema> map = brandList
//		    					.stream().collect(Collectors.toMap(b -> b.getBrandCode(), b -> new BrandMasterSchema()));
//		 
//		    	
//		    	brandList.addAll(brandService.findAll(Constants.localeZHHK)
//		    											.stream()
//										    			.map(p -> (BrandEntity) p)
//														.collect(Collectors.toList()));
//		    	
//		    	lpms.addAll(brandList.stream().map(b -> {
//		    		
//		    		BrandMasterSchema bms = map.get(b.getBrandCode());
//		    		
//		    		Optional<BrandEntity> bnd = brandService.findByCode(b.getLocale(),
//													          	b.getBrandCode()); 
//			    	
//				    bms.set_BRAND_CODE(bnd.get().getBrandCode());
//				    bms.set_BRAND_DESC_EN(bnd.get().getBrandDescENGB());
//				    bms.set_BRAND_DESC_HK(bnd.get().getBrandDescZHHK());
//			    	
//				    return bms;
//		    	}).collect(Collectors.toSet()));
//	    	
//		    	CsvMapper mapper = new CsvMapper(); 
//		    	CsvSchema schema = mapper.schemaFor(BrandMasterSchema.class)
//	        		.withHeader()
//	        		.withColumnSeparator('\t')
//	        		.withQuoteChar('"');
//	        
//		    	ObjectWriter myObjectWriter = mapper.writer(schema);
//		    	String ow = myObjectWriter.writeValueAsString(lpms);
//		    	PrintWriter out = new PrintWriter(resource.getFile().getAbsolutePath());
//		    	out.write(ow);
//		    	out.flush();
//		    	out.close();
//	        
//	    } catch (Exception e) {
//	        logger.error("Error occurred while loading object list from file " + resource.getFilename(), e);
//	    }
//	}
	
}
