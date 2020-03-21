package io.nzbee.entity.product.attribute;

import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.cjk.CJKBigramFilterFactory;
import org.apache.lucene.analysis.cjk.CJKWidthFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.AnalyzerDiscriminator;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.SortableField;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import io.nzbee.entity.LanguageDiscriminator;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.tag.attribute.TagAttribute;

@Entity
@Indexed
@Table(name = "product_attr_lcl", schema = "mochi")
@AnalyzerDef(name = "en-GB",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, 
  params = {
	      @Parameter(name = "language", value = "English")
  })
})
@AnalyzerDef(name = "zh-HK",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = CJKWidthFilterFactory.class),
  @TokenFilterDef(factory = CJKBigramFilterFactory.class),
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = StopFilterFactory.class)
}
)
public class ProductAttribute {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_lcl_id")
	private Long Id;

	@Field(analyze = Analyze.YES, store=Store.YES)
	@Column(name="prd_desc")
	private String productDesc; 

	@Column(name="prd_img_pth")
	@Field(store=Store.YES, analyze=Analyze.NO)
	private String ProductImage;
	
	@Column(name="lcl_cd")
	@Field(store=Store.YES, analyze=Analyze.NO)
	@AnalyzerDiscriminator(impl = LanguageDiscriminator.class)
	private String lclCd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prd_id")
	private Product product;
	
	@Transient
	@IndexedEmbedded(prefix="product.categories.", includeEmbeddedObjectId=true)
	public Set<CategoryAttribute> getCategories() {
		return  this.getProduct().getCategories().stream().flatMap(
					c -> c.getAttributes().stream())
				.filter(c -> this.getLclCd().equals(c.getLclCd())).collect(Collectors.toSet());
	}
	
	public void setCategories(Set<CategoryAttribute> categoryAttributes) {
		this.getProduct().getCategories().addAll(categoryAttributes.stream().map(ca -> (CategoryProduct) ca.getCategory()).collect(Collectors.toSet()));		
	}
	
	@Transient
	@IndexedEmbedded(prefix="product.tags.", includeEmbeddedObjectId=true)
	public Set<TagAttribute> getTags() {
		return this.getProduct().getTags().stream().flatMap(
				t -> t.getAttributes().stream())
				.filter(t -> this.getLclCd().equals(t.getLclCd())).collect(Collectors.toSet());
	}
	
	@Transient
	@IndexedEmbedded(prefix="product.brand.")
	public BrandAttribute getBrand() {
		return this.getProduct().getBrand().getAttributes()
				.stream().filter(b -> this.getLclCd().equals(b.getLclCd())).findFirst().get();
	}

	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	
	public Long getProductId() {
		return this.getProduct().getProductId();
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
	
	@Field(analyze=Analyze.NO)
	@SortableField
	public String getProductDescSort() {
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
	
	@Transient
	@Field(store=Store.YES, analyze=Analyze.NO)
	private Double getCurrentRetailPrice() {
		return this.getProduct().getRetailPrice();
	}
	
	@Transient
	@Field(store=Store.YES, analyze=Analyze.NO)
	private Double getCurrentMarkdownPrice() {
		return this.getProduct().getMarkdownPrice();
	}
	
	@Transient
	@Field(store=Store.YES,analyze=Analyze.NO)
	public String getProductUPC() {
		return this.getProduct().getUPC();
	}
	
	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.getProductUPC());
        hcb.append(this.getLclCd());
        return hcb.toHashCode();
    }
 
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
        }
	    if (!(obj instanceof ProductAttribute)) {
	            return false;
	    }
	    ProductAttribute that = (ProductAttribute) obj;
	      EqualsBuilder eb = new EqualsBuilder();
	      eb.append(this.getProductUPC(), that.getProductUPC());
	      eb.append(this.getLclCd(), that.getLclCd());
	      return eb.isEquals();
	}
}
