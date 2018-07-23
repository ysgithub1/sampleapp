package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_stock database table.
 * 
 */
@Embeddable
public class TblStockPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="product_no")
	private int productNo;

	@Column(name="warehouse_cd", insertable=false, updatable=false)
	private String warehouseCd;

	public TblStockPK() {
	}
	public int getProductNo() {
		return this.productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getWarehouseCd() {
		return this.warehouseCd;
	}
	public void setWarehouseCd(String warehouseCd) {
		this.warehouseCd = warehouseCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblStockPK)) {
			return false;
		}
		TblStockPK castOther = (TblStockPK)other;
		return 
			(this.productNo == castOther.productNo)
			&& this.warehouseCd.equals(castOther.warehouseCd);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.productNo;
		hash = hash * prime + this.warehouseCd.hashCode();
		
		return hash;
	}
}