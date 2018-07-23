package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the tbl_stock database table.
 * 
 */
@Entity
@Table(name="tbl_stock")
@NamedQuery(name="TblStock.findAll", query="SELECT t FROM TblStock t")
public class TblStock implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblStockPK id;

	@Column(name="free_quantity")
	private int freeQuantity;

	private BigDecimal size;

	@Column(name="stock_type")
	private String stockType;

	//uni-directional many-to-one association to TblMSupplier
	@ManyToOne
	@JoinColumn(name="warehouse_cd", insertable=false, updatable=false)
	private TblMSupplier tblMSupplier;

	//uni-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="product_cd", insertable=false, updatable=false)
	private TblProduct tblProduct;

	public TblStock() {
	}

	public TblStockPK getId() {
		return this.id;
	}

	public void setId(TblStockPK id) {
		this.id = id;
	}

	public int getFreeQuantity() {
		return this.freeQuantity;
	}

	public void setFreeQuantity(int freeQuantity) {
		this.freeQuantity = freeQuantity;
	}

	public BigDecimal getSize() {
		return this.size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
	}

	public String getStockType() {
		return this.stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public TblMSupplier getTblMSupplier() {
		return this.tblMSupplier;
	}

	public void setTblMSupplier(TblMSupplier tblMSupplier) {
		this.tblMSupplier = tblMSupplier;
	}

	public TblProduct getTblProduct() {
		return this.tblProduct;
	}

	public void setTblProduct(TblProduct tblProduct) {
		this.tblProduct = tblProduct;
	}

}