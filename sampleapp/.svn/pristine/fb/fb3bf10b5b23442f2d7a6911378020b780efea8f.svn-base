package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_product database table.
 * 
 */
@Entity
@Table(name="tbl_product")
@NamedQuery(name="TblProduct.findAll", query="SELECT t FROM TblProduct t")
public class TblProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="product_cd")
	private String productCd;

	@Column(name="product_name")
	private String productName;

	public TblProduct() {
	}

	public String getProductCd() {
		return this.productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}