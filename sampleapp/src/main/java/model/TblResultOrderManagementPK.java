package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_result_order_management database table.
 * 
 */
@Embeddable
public class TblResultOrderManagementPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String timestamp;

	private String user;

	@Column(name="order_no")
	private int orderNo;

	private int jurisdiction;

	public TblResultOrderManagementPK() {
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
		if (!(other instanceof TblResultOrderManagementPK)) {
			return false;
		}
		TblResultOrderManagementPK castOther = (TblResultOrderManagementPK)other;
		return 
			this.timestamp.equals(castOther.timestamp)
			&& this.user.equals(castOther.user)
			&& (this.orderNo == castOther.orderNo)
			&& (this.jurisdiction == castOther.jurisdiction);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.timestamp.hashCode();
		hash = hash * prime + this.user.hashCode();
		hash = hash * prime + this.orderNo;
		hash = hash * prime + this.jurisdiction;
		
		return hash;
	}
}