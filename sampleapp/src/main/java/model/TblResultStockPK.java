package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_result_stock database table.
 * 
 */
@Embeddable
public class TblResultStockPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String timestamp;

	private String user;

	@Column(name="product_no")
	private int productNo;

	public TblResultStockPK() {
	}
	public String getTimestamp() {
		return this.timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getUser() {
		return this.user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getProductNo() {
		return this.productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblResultStockPK)) {
			return false;
		}
		TblResultStockPK castOther = (TblResultStockPK)other;
		return 
			this.timestamp.equals(castOther.timestamp)
			&& this.user.equals(castOther.user)
			&& (this.productNo == castOther.productNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.timestamp.hashCode();
		hash = hash * prime + this.user.hashCode();
		hash = hash * prime + this.productNo;
		
		return hash;
	}
}