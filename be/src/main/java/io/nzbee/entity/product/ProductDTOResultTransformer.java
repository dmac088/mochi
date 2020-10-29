package io.nzbee.entity.product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.product.CategoryProduct;

public class ProductDTOResultTransformer implements ResultTransformer {

	private Map<Long, CategoryProduct> categoryProductDTOMap = new LinkedHashMap<>();

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {

		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);

		Long postId = longValue(tuple[aliasToIndexMap.get(CaetgoryDTO.ID_ALIAS)]);

		ProductEntity postDTO = postDTOMap.computeIfAbsent(postId, id -> new ProductDTO(tuple, aliasToIndexMap));

		postDTO.getComments().add(new ProductDTO(tuple, aliasToIndexMap));

		return postDTO;
	}

	@Override
	public List transformList(List collection) {
		return new ArrayList<>(productDTOMap.values());
	}

}
