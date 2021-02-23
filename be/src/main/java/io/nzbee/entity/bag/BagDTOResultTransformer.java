package io.nzbee.entity.bag;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;

import io.nzbee.Constants;
import io.nzbee.entity.bag.BagDTO;
import io.nzbee.entity.bag.item.BagItemDTO;
import io.nzbee.entity.brand.BrandDTO;
import io.nzbee.entity.category.CategoryDTO;
import io.nzbee.entity.category.product.CategoryProductDTO;
import io.nzbee.entity.party.person.PersonDTO;
import io.nzbee.entity.product.ProductDTO;
import io.nzbee.entity.product.department.DepartmentDTO;
import io.nzbee.entity.product.physical.PhysicalProductDTO;
import io.nzbee.entity.product.shipping.ShippingProductDTO;
import io.nzbee.entity.promotion.PromotionDTO;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicDTO;

public class BagDTOResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 1L;

	private Map<Long, BagDTO> bagDTOMap = new LinkedHashMap<>();

	private Map<Long, BagItemDTO> bagItemDTOMap = new LinkedHashMap<>();

	private Map<Long, ProductDTO> productDTOMap = new LinkedHashMap<>();

	private Map<Long, BrandDTO> brandDTOMap = new LinkedHashMap<>();

	private Map<Long, DepartmentDTO> departmentDTOMap = new LinkedHashMap<>();

	private Map<Long, PersonDTO> customerDTOMap = new LinkedHashMap<>();

	private Map<Long, CategoryProductDTO> categoryDTOMap = new LinkedHashMap<>();

	private Map<Long, PromotionDTO> promotionDTOMap = new LinkedHashMap<>();

	private Map<Long, PromotionMechanicDTO> promotionMechanicDTOMap = new LinkedHashMap<>();

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {

		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);

		Long bagId = ((Number) tuple[aliasToIndexMap.get(BagDTO.ID_ALIAS)]).longValue();

		BagDTO bagDTO = bagDTOMap.computeIfAbsent(bagId, bId -> {
			BagDTO b = new BagDTO(tuple, aliasToIndexMap);
			return b;
		});

		Long customerId = ((Number) tuple[aliasToIndexMap.get(PersonDTO.ID_ALIAS)]).longValue();

		PersonDTO customerDTO = customerDTOMap.computeIfAbsent(customerId, cId -> {
			PersonDTO c = new PersonDTO(tuple, aliasToIndexMap);
			return c;
		});

		bagDTO.setCustomer(customerDTO);

		if (!(tuple[aliasToIndexMap.get(BagItemDTO.ID_ALIAS)] == null)) {

			Long bagItemId = ((Number) tuple[aliasToIndexMap.get(BagItemDTO.ID_ALIAS)]).longValue();

			BagItemDTO bagItemDTO = bagItemDTOMap.computeIfAbsent(bagItemId, biId -> {
				BagItemDTO bi = new BagItemDTO(tuple, aliasToIndexMap);

				Long productId = ((Number) tuple[aliasToIndexMap.get(ProductDTO.ID_ALIAS)]).longValue();

				Long departmentId = ((Number) tuple[aliasToIndexMap.get(DepartmentDTO.ID_ALIAS)]).longValue();

				DepartmentDTO departmentDTO = departmentDTOMap.computeIfAbsent(departmentId, dId -> {
					DepartmentDTO d = new DepartmentDTO(tuple, aliasToIndexMap);
					return d;
				});

				Long brandId = ((Number) tuple[aliasToIndexMap.get(BrandDTO.ID_ALIAS)]).longValue();

				BrandDTO brandDTO = brandDTOMap.computeIfAbsent(brandId, id -> {
					BrandDTO bnd = new BrandDTO(tuple, aliasToIndexMap);
					return bnd;
				});

				ProductDTO productDTO = productDTOMap.computeIfAbsent(productId, pId -> {
					ProductDTO pDto = null;
					switch (departmentDTO.getDepartmentCode()) {
					case Constants.physicalProductDepartmentCode:
						pDto = new PhysicalProductDTO(tuple, aliasToIndexMap);
						break;
					case Constants.shippingProductDepartmentCode:
						pDto = new ShippingProductDTO(tuple, aliasToIndexMap);
						break;
					default:
						pDto = new PhysicalProductDTO(tuple, aliasToIndexMap);
						break;
					}

					pDto.setBrand(brandDTO);
					pDto.setDepartment(departmentDTO);

					Long categoryId = ((Number) tuple[aliasToIndexMap.get(CategoryDTO.ID_ALIAS)]).longValue();

					CategoryProductDTO categoryDTO = categoryDTOMap.computeIfAbsent(categoryId, cId -> {
						CategoryProductDTO c = new CategoryProductDTO(tuple, aliasToIndexMap);
						return c;
					});

					pDto.getCategories().add(categoryDTO);

					if (!(tuple[aliasToIndexMap.get(PromotionDTO.ID_ALIAS)] == null)) {
						Long promotionId = ((Number) tuple[aliasToIndexMap.get(PromotionDTO.ID_ALIAS)]).longValue();

						PromotionDTO promotionDTO = promotionDTOMap.computeIfAbsent(promotionId, promoId -> {
							PromotionDTO promoDto = new PromotionDTO(tuple, aliasToIndexMap);

							Long promotionMechanicId = ((Number) tuple[aliasToIndexMap
									.get(PromotionMechanicDTO.ID_ALIAS)]).longValue();

							PromotionMechanicDTO promotionMechanic = promotionMechanicDTOMap
									.computeIfAbsent(promotionMechanicId, pMechanicId -> {
										PromotionMechanicDTO promoMechDto = new PromotionMechanicDTO(tuple,
												aliasToIndexMap);
										return promoMechDto;
									});

							promoDto.setMechanicDTO(promotionMechanic);

							return promoDto;
						});

						pDto.getPromotions().add(promotionDTO);
					}

					return pDto;
				});

				bi.setProduct(productDTO);

				return bi;
			});
			bagDTO.getBagItems().add(bagItemDTO);
		}

		return bagDTO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return new ArrayList<>(bagDTOMap.values());
	}

	public Map<String, Integer> aliasToIndexMap(String[] aliases) {

		Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();

		for (int i = 0; i < aliases.length; i++) {
			aliasToIndexMap.put(aliases[i], i);
		}

		return aliasToIndexMap;
	}

}
