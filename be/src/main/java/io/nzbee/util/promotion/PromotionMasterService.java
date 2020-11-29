package io.nzbee.util.promotion;

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
import io.nzbee.entity.promotion.PromotionBNGNPCT;
import io.nzbee.entity.promotion.mechanic.IPromotionMechanicService;
import io.nzbee.entity.promotion.mechanic.PromotionMechanic;
import io.nzbee.Constants;
import io.nzbee.entity.promotion.IPromotionService;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class PromotionMasterService {

	private static final Logger logger = LoggerFactory.getLogger(PromotionMasterService.class);

	@Autowired
	private IPromotionService promotionService;

	@Autowired
	private IPromotionMechanicService promotionMechanicService;

	@Autowired
	private FileStorageServiceUpload fileStorageServiceUpload;

	@Transactional
	public void writePromotionMaster(String fileName) {
		logger.debug("called writePromotionMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator('\t')
					.withQuoteChar('"');

			CsvMapper mapper = new CsvMapper();
			MappingIterator<PromotionMasterSchema> readValues = mapper.readerFor(PromotionMasterSchema.class)
					.with(bootstrapSchema).readValues(file);

			readValues.readAll().stream().forEach(c -> {
				this.persistPromotionMaster(c);
			});

		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	public void persistPromotionMaster(PromotionMasterSchema pms) {
		logger.debug("called persistPromotionMaster() ");

		Optional<PromotionEntity> op = promotionService.findByCode(pms.get_PROMOTION_CODE());

		PromotionBNGNPCT p = 	(op.isPresent()) 
								? (PromotionBNGNPCT) op.get() 
								: new PromotionBNGNPCT();
	
		LocalDateTime psd = LocalDateTime.parse(pms.get_PROMOTION_START_DATE(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime ped = LocalDateTime.parse(pms.get_PROMOTION_END_DATE(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		Optional<PromotionMechanic> pm = promotionMechanicService.findByCode(pms.get_PROMOTION_MECHANIC_CODE());

		p.setPromotionCode(pms.get_PROMOTION_CODE());
		PromotionAttributeEntity paEN = mapAttribute(p, pms.get_PROMOTION_DESC_EN(), Constants.localeENGB);
		PromotionAttributeEntity paCN = mapAttribute(p, pms.get_PROMOTION_DESC_HK(), Constants.localeZHHK);
		paEN.setPromotion(p);
		paCN.setPromotion(p);
		p.addAttribute(paEN);
		p.addAttribute(paCN);
		p.setPromotionStartDate(psd);
		p.setPromotionEndDate(ped);
		p.setPromotionMechanic(pm.get());
		p.setPromotionActive(pms.get_PROMOTION_ACTIVE());
		p.setBuyQty(Integer.parseInt(pms.get_BUY_QUANTITY()));
		p.setPctDisc(Double.parseDouble(pms.get_BUY_QUANTITY()));

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
