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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.lucene.analysis.cjk.CJKBigramFilterFactory;
import org.apache.lucene.analysis.cjk.CJKWidthFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
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
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.nzbee.entity.LanguageDiscriminator;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.tag.attribute.TagAttribute;

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
	@Field(store=Store.YES)
	private Long productId;

	@Field(analyze = Analyze.YES, store=Store.YES)
	@Column(name="prd_desc")
	@SortableField
	private String productDesc; 

	@Column(name="prd_img_pth")
	@Field(store=Store.YES)
	private String ProductImage;
	
	@Column(name="lcl_cd")
	@Field(store=Store.YES)
	@AnalyzerDiscriminator(impl = LanguageDiscriminator.class)
	private String lclCd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prd_id", insertable=false, updatable=false)
	@JsonBackReference
	private Product product;
	
	
	@Transient
	@IndexedEmbedded(prefix="product.categories.")
	public Set<CategoryAttribute> getCategories() {
				return this.getProduct().getCategories().stream().flatMap(
					c -> c.getAttributes().stream()).collect(Collectors.toSet())
						.stream().filter(ca -> ca.getLclCd().equals(this.getLclCd())
					).collect(Collectors.toSet());
	}
	
	@Transient
	@IndexedEmbedded(prefix="product.tags.")
	public Set<TagAttribute> getTags() {
		return this.getProduct().getTags().stream().flatMap(
				t -> t.getAttributes().stream()).collect(Collectors.toSet())
					.stream().filter(ta -> ta.getLclCd().equals(this.getLclCd())
				).collect(Collectors.toSet());
	}
	
	@Transient
	@IndexedEmbedded(prefix="product.brand.")
	public BrandAttribute getBrand() {
		return this.getProduct().getBrand().getAttributes()
				.stream().filter(ba -> ba.getLclCd().equals(this.getLclCd())).findFirst().get();
	}

	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getProductId() {
		return productId;
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
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
	
	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(product.getUPC());
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
	      eb.append(product.getUPC(), that.product.getUPC());
	      eb.append(this.getLclCd(), that.lclCd);
	      return eb.isEquals();
	}
}
