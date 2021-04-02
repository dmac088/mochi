package io.nzbee.util.promotion.regular;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.entity.promotion.PromotionEntity;
import io.nzbee.entity.promotion.attribute.PromotionAttributeEntity;
import io.nzbee.entity.promotion.level.IPromotionLevelService;
import io.nzbee.entity.promotion.level.PromotionLevelEntity;
import io.nzbee.entity.promotion.mechanic.IPromotionMechanicService;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicEntity;
import io.nzbee.entity.promotion.product.PromotionProductEntity;
import io.nzbee.exceptions.CustomException;
import io.nzbee.Constants;
import io.nzbee.entity.promotion.IPromotionService;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class PromotionProductMasterService {

	private static final Logger logger = LoggerFactory.getLogger(PromotionProductMasterService.class);

	@Autowired
	private IPromotionService promotionService;
	
	@Autowired
	private IPromotionMechanicService promotionMechanicService;
	
	@Autowired
	private IPromotionLevelService promotionLevelService;

	@Autowired
	private FileStorageServiceUpload fileStorageServiceUpload;

	
	@Transactional
	public void writePromotionRegularMaster(String fileName) {
		logger.debug("called writePromotionRegularMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator('\t')
					.withQuoteChar('"');

			CsvMapper mapper = new CsvMapper();
			MappingIterator<PromotionProductSchema> readValues = mapper.readerFor(PromotionProductSchema.class)
					.with(bootstrapSchema).readValues(file);

			readValues.readAll().stream().forEach(c -> {
				this.persistPromotionProductMaster(c);
			});

		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	public void persistPromotionProductMaster(PromotionProductSchema pms) {
		logger.debug("called persistPromotionProductMaster() ");

		Optional<PromotionEntity> op = promotionService.findByCode(pms.get_PROMOTION_CODE());
										

		PromotionProductEntity p = 	(op.isPresent()) 
									? (PromotionProductEntity) op.get() 
									: new PromotionProductEntity();
	
		LocalDateTime psd = LocalDateTime.parse(pms.get_PROMOTION_START_DATE(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime ped = LocalDateTime.parse(pms.get_PROMOTION_END_DATE(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		Optional<PromotionMechanicEntity> pm = promotionMechanicService.findByCode(pms.get_PROMOTION_MECHANIC_CODE());
		
		Optional<PromotionLevelEntity> pl = promotionLevelService.findByCode(pms.get_PROMOTION_LEVEL_CODE());
		
		p.setPromotionCode(pms.get_PROMOTION_CODE());
		PromotionAttributeEntity paEN = mapAttribute(p, pms.get_PROMOTION_DESC_EN(), Constants.localeENGB);
		PromotionAttributeEntity paCN = mapAttribute(p, pms.get_PROMOTION_DESC_HK(), Constants.localeZHHK);
		paEN.setPromotion(p);
		paCN.setPromotion(p);
		p.addAttribute(paEN);
		p.addAttribute(paCN);
		p.setPromotionStartDate(psd);
		p.setPromotionEndDate(ped);
		p.setPromotionMechanic(pm.orElseThrow(() -> new CustomException("Promotion mechanic " + pms.get_PROMOTION_MECHANIC_CODE() + " not found!" )));
		p.setPromotionLevel(pl.orElseThrow(() -> new CustomException("Promotion level " + pms.get_PROMOTION_LEVEL_CODE() + " not found!" )));
		p.setPromotionActive(pms.get_PROMOTION_ACTIVE());

		promotionService.save(p);
	}
	
	private PromotionAttributeEntity mapAttribute(PromotionEntity p, String promotionDesc, String locale) {
		logger.debug("called mapAttribute() ");

		Optional<PromotionAttributeEntity> opa = p.getAttributes().stream()
					.filter(a -> a.getLocale().equals(locale)).findFirst();

		PromotionAttributeEntity pa = (opa.isPresent()) ? opa.get() : new PromotionAttributeEntity();
		pa.setLocale(locale);
		pa.setPromotionDesc(promotionDesc);
		
		return pa;
	}

}
