package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_result_order_line_item database table.
 * 
 */
@Embeddable
public class TblResultOrderLineItemPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int jurisdiction;

	@Column(name="order_no")
	private int orderNo;

	@Column(name="line_item_no")
	private int lineItemNo;

	private String timestamp;

	private String user;

	public TblResultOrderLineItemPK() {
	}
	public int getJurisdiction() {
		return this.jurisdiction;
	}
	public void setJurisdiction(int jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public int getOrderNo() {
		return this.orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getLineItemNo() {
		return this.lineItemNo;
	}
	public void setLineItemNo(int lineItemNo) {
		this.lineItemNo = lineItemNo;
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblResultOrderLineItemPK)) {
			return false;
		}
		TblResultOrderLineItemPK castOther = (TblResultOrderLineItemPK)other;
		return 
			(this.jurisdiction == castOther.jurisdiction)
			&& (this.orderNo == castOther.orderNo)
			&& (this.lineItemNo == castOther.lineItemNo)
			&& this.timestamp.equals(castOther.timestamp)
			&& this.user.equals(castOther.user);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.jurisdiction;
		hash = hash * prime + this.orderNo;
		hash = hash * prime + this.lineItemNo;
		hash = hash * prime + this.timestamp.hashCode();
		hash = hash * prime + this.user.hashCode();
		
		return hash;
	}
}