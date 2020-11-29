package io.nzbee.util.promotion.category;

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
import io.nzbee.entity.promotion.PromotionEntity;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.promotion.IPromotionService;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class CategoryPromotionMasterService {

	private static final Logger logger = LoggerFactory.getLogger(CategoryPromotionMasterService.class);

	@Autowired
	private IPromotionService promotionService;
	
	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private FileStorageServiceUpload fileStorageServiceUpload;

	@Transactional
	public void writeCategoryPromotionMaster(String fileName) {
		logger.debug("called writePromotionMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator('\t')
					.withQuoteChar('"');

			CsvMapper mapper = new CsvMapper();
			MappingIterator<CategoryPromotionMasterSchema> readValues = mapper.readerFor(CategoryPromotionMasterSchema.class)
					.with(bootstrapSchema).readValues(file);

			readValues.readAll().stream().forEach(c -> {
				this.persistCategoryPromotionMaster(c);
			});

		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	public void persistCategoryPromotionMaster(CategoryPromotionMasterSchema pms) {
		logger.debug("called persistCategoryPromotionMaster() ");

		Optional<PromotionEntity> op = promotionService.findByCode(pms.get_PROMOTION_CODE());
		Optional<CategoryEntity> oc = categoryService.findByCode(pms.get_CATEGORY_CODE());

		op.get().setPromotionCode(pms.get_PROMOTION_CODE());
		op.get().addCategory(oc.get());

		promotionService.save(op.get());
	}
	

}
