package io.nzbee.entity.product.attribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.lucene.analysis.cjk.CJKBigramFilterFactory;
import org.apache.lucene.analysis.cjk.CJKWidthFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.AnalyzerDiscriminator;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import com.fasterxml.jackson.annotation.JsonBackReference;

import io.nzbee.entity.LanguageDiscriminator;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.tag.ProductTag;
import io.nzbee.entity.product.tag.attribute.ProductTagAttribute;
import io.nzbee.variables.CategoryVars;

@Entity
@Indexed
@Table(name = "product_attr_lcl", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prd_lcl_id")
@AnalyzerDef(name = "en-GB",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
	      @Parameter(name = "language", value = "English")
  })
})
@AnalyzerDef(name = "zh-HK",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = CJKWidthFilterFactory.class),
  @TokenFilterDef(factory = CJKBigramFilterFactory.class)
})
public class ProductAttribute {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_lcl_id")
	private Long Id;

	@Column(name="prd_id")
	private Long productId;
	 
	@Field(analyze = Analyze.YES)
	@Column(name="prd_desc")
	private String productDesc; 

	@Column(name="prd_img_pth")
	private String ProductImage;
	
	@Column(name="lcl_cd")
	@Field
	@AnalyzerDiscriminator(impl = LanguageDiscriminator.class)
	private String lclCd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@IndexedEmbedded
	@JoinColumn(name="prd_id", insertable=false, updatable=false)
	@JsonBackReference
	private Product product;
	
	@Transient
	@Facet
	@Field(analyze = Analyze.NO)
	public String getBrandCode() {
		return this.product.getBrand().getCode();
	}
	
//	@Transient
//	@IndexedEmbedded
//	public Category getPrimaryCategory() {
//		return this.getProduct().getCategories().stream().filter(c -> {
//			 return c.getHierarchy().getHierarchyCode().equals(CategoryVars.PRIMARY_HIERARCHY_CODE);
//		 		}).collect(Collectors.toList()).stream().findFirst().get();
//	}
//	
//	@Transient
//	@IndexedEmbedded
//	public Category getSecondaryCategory() {
//		Optional<Collection<Optional<CategoryProduct>>> lc = 
//		Optional.ofNullable(this.getProduct().getCategories().stream().map(a -> {return Optional.ofNullable(a);}).collect(Collectors.toList()));
//		if(lc.isPresent()) {
//			Optional<CategoryProduct> c = lc.get().stream().filter(b -> b.isPresent()).collect(Collectors.toList()).stream()
//					.map(d -> d.get()).collect(Collectors.toList())
//					.stream().filter(e -> e.getHierarchy().getHierarchyCode().equals(CategoryVars.SECONDARY_HIERARCHY_CODE)).findFirst();
//			
//			if(c.isPresent()) { return c.get();}
//		}
//		
//		lc = Optional.ofNullable(new ArrayList<Optional<CategoryProduct>>());
//		CategoryProduct c = new CategoryProduct();
//		CategoryAttribute ca = new CategoryAttribute();
//		c.setCategoryCode("UNK01");
//		ca.setLclCd(this.getLclCd());
//		List<CategoryAttribute> lca = new ArrayList<CategoryAttribute>();
//		lca.add(ca);
//		//c.setAttributes(lca);
//		return c;
//	}
	
	@Transient
	@Field(analyze = Analyze.YES)
	public String getTagA() {
		Optional<List<ProductTag>> lpt = Optional.ofNullable(this.getProduct().getTags());
		if(!lpt.isPresent());
		List<Optional<ProductTagAttribute>> lpa = lpt.get().stream().map(t -> {
			return t.getAttributes().stream().filter(ta -> ta.getLclCd().equals(this.getLclCd())).findFirst();
		}).collect(Collectors.toList());
		Iterator<ProductTagAttribute> i = lpa.stream().filter(ta -> ta.isPresent()).map(t -> { return t.get();}).sorted(Comparator.comparing(ProductTagAttribute::getTagDesc)).iterator();
		if(i.hasNext()) { return i.next().getTagDesc(); }
		return "Empty";
	}
	
	@Transient
	@Field(analyze = Analyze.YES)
	public String getTagB() {
		Optional<List<ProductTag>> lpt = Optional.ofNullable(this.getProduct().getTags());
		if(!lpt.isPresent());
		List<Optional<ProductTagAttribute>> lpa = lpt.get().stream().map(t -> {
			return t.getAttributes().stream().filter(ta -> ta.getLclCd().equals(this.getLclCd())).findFirst();
		}).collect(Collectors.toList());
		Iterator<ProductTagAttribute> i = lpa.stream().filter(ta -> ta.isPresent()).map(t -> { return t.get();}).sorted(Comparator.comparing(ProductTagAttribute::getTagDesc)).iterator();
		if(i.hasNext()) { i.next(); }
		if(i.hasNext()) {  return i.next().getTagDesc(); }
		return "Empty";
	}
	
	@Transient
	@Field(analyze = Analyze.YES)
	public String getTagC() {
		Optional<List<ProductTag>> lpt = Optional.ofNullable(this.getProduct().getTags());
		if(!lpt.isPresent());
		List<Optional<ProductTagAttribute>> lpa = lpt.get().stream().map(t -> {
			return t.getAttributes().stream().filter(ta -> ta.getLclCd().equals(this.getLclCd())).findFirst();
		}).collect(Collectors.toList());
		Iterator<ProductTagAttribute> i = lpa.stream().filter(ta -> ta.isPresent()).map(t -> { return t.get();}).sorted(Comparator.comparing(ProductTagAttribute::getTagDesc)).iterator();
		if(i.hasNext()) { i.next(); }
		if(i.hasNext()) { i.next(); }
		if(i.hasNext()) { return i.next().getTagDesc(); }
		return "Empty";
	}
	
	@Field(analyze = Analyze.YES)
	public String getBrandDesc() {
		List<BrandAttribute> lba = this.getProduct().getBrand().getAttributes();
		Optional<BrandAttribute> brandAttribute = lba.stream().filter(ba -> ba.getLclCd().equals(this.getLclCd())).findFirst();
		if(brandAttribute.isPresent()) { return brandAttribute.get().getBrandDesc(); }
		return "Empty";
	}
	
	@Transient
	@Facet
	@Field(analyze = Analyze.NO)
	public String getTagAFacet() { 
		return this.getTagA();
	}
	
	@Transient
	@Facet
	@Field(analyze = Analyze.NO)
	public String getTagBFacet() { 
		return this.getTagB();
	}
	
	@Transient
	@Facet
	@Field(analyze = Analyze.NO)
	public String getTagCFacet() { 
		return this.getTagC();
	}
	
	public Long getProductId() {
		return productId;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}

	public String getProductImage() {
		return ProductImage;
	}

	public void setProductImage(String productImage) {
		ProductImage = productImage;
	}
}
