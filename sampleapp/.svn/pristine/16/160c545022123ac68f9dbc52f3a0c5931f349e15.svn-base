package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the tbl_result_stock database table.
 * 
 */
@Entity
@Table(name="tbl_result_stock")
@NamedQuery(name="TblResultStock.findAll", query="SELECT t FROM TblResultStock t")
public class TblResultStock implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblResultStockPK id;

	@Column(name="timestamp", insertable=false, updatable=false)
	private String timestamp;
	
	@Column(name="user", insertable=false, updatable=false)
	private String user;

	
	@Column(name="quantity_after_reserve")
	private int quantityAfterReserve;

	@Column(name="quantity_before_reserve")
	private int quantityBeforeReserve;

	private BigDecimal size;

	@Column(name="stock_type")
	private String stockType;

	//uni-directional many-to-one association to TblMSupplier
	@ManyToOne
	@JoinColumn(name="warehouse_cd", insertable=false, updatable=false)
	private TblMSupplier tblMSupplier;
	@Column(name="warehouse_cd")
	private String warehouseCd;
	
	
	//uni-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="product_cd", insertable=false, updatable=false)
	private TblProduct tblProduct;
	
	@Column(name="product_cd")
	private String productCd;

	
	public TblResultStock() {
	}

	public TblResultStockPK getId() {
		return this.id;
	}

	public void setId(TblResultStockPK id) {
		this.id = id;
	}

	public int getQuantityAfterReserve() {
		return this.quantityAfterReserve;
	}

	public void setQuantityAfterReserve(int quantityAfterReserve) {
		this.quantityAfterReserve = quantityAfterReserve;
	}

	public int getQuantityBeforeReserve() {
		return this.quantityBeforeReserve;
	}

	public void setQuantityBeforeReserve(int quantityBeforeReserve) {
		this.quantityBeforeReserve = quantityBeforeReserve;
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

	public String getProductCd() {
		return this.productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public String getWarehouseCd() {
		return this.warehouseCd;
	}

	public void setWarehouseCd(String warehouseCd) {
		this.warehouseCd = warehouseCd;
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