package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_order database table.
 * 
 */
@Embeddable
public class TblOrderPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="order_no")
	private int orderNo;

	private int jurisdiction;

	public TblOrderPK() {
	}
	public int getOrderNo() {
		return this.orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getJurisdiction() {
		return this.jurisdiction;
	}
	public void setJurisdiction(int jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblOrderPK)) {
			return false;
		}
		TblOrderPK castOther = (TblOrderPK)other;
		return 
			(this.orderNo == castOther.orderNo)
			&& (this.jurisdiction == castOther.jurisdiction);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orderNo;
		hash = hash * prime + this.jurisdiction;
		
		return hash;
	}
}