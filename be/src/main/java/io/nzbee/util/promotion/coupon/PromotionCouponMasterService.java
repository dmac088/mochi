package io.nzbee.util.promotion.coupon;

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
import io.nzbee.entity.promotion.coupon.PromotionCouponEntity;
import io.nzbee.entity.promotion.mechanic.IPromotionMechanicService;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicEntity;
import io.nzbee.Constants;
import io.nzbee.entity.promotion.IPromotionService;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class PromotionCouponMasterService {

	private static final Logger logger = LoggerFactory.getLogger(PromotionCouponMasterService.class);

	@Autowired
	private IPromotionService promotionService;

	@Autowired
	private IPromotionMechanicService promotionMechanicService;

	@Autowired
	private FileStorageServiceUpload fileStorageServiceUpload;

	@Transactional
	public void writePromotionCouponMaster(String fileName) {
		logger.debug("called writePromotionCouponMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator('\t')
					.withQuoteChar('"');

			CsvMapper mapper = new CsvMapper();
			MappingIterator<PromotionCouponSchema> readValues = mapper.readerFor(PromotionCouponSchema.class)
					.with(bootstrapSchema).readValues(file);

			readValues.readAll().stream().forEach(c -> {
				this.persistPromotionCouponMaster(c);
			});

		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	public void persistPromotionCouponMaster(PromotionCouponSchema pms) {
		logger.debug("called persistPromotionCouponMaster() ");

		Optional<PromotionEntity> op = promotionService.findByCode(pms.get_PROMOTION_CODE());

		PromotionCouponEntity p = 	(op.isPresent()) 
									? (PromotionCouponEntity) op.get() 
									: new PromotionCouponEntity();
	
		LocalDateTime psd = LocalDateTime.parse(pms.get_PROMOTION_START_DATE(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime ped = LocalDateTime.parse(pms.get_PROMOTION_END_DATE(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		Optional<PromotionMechanicEntity> pm = promotionMechanicService.findByCode(pms.get_PROMOTION_MECHANIC_CODE());

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
		p.setPromotionCode(pms.get_PROMOTION_COUPON_CODE());

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
