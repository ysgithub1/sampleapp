package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_result_order_line_item database table.
 * 
 */
@Entity
@Table(name="tbl_result_order_line_item")
@NamedQuery(name="TblResultOrderLineItem.findAll", query="SELECT t FROM TblResultOrderLineItem t")
public class TblResultOrderLineItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblResultOrderLineItemPK id;

	@Column(name="reserved_product_no")
	private int reservedProductNo;

	@Column(name="reserved_quantity")
	private int reservedQuantity;

	@Column(name="reserved_status")
	private short reservedStatus;

	public TblResultOrderLineItem() {
	}

	public TblResultOrderLineItemPK getId() {
		return this.id;
	}

	public void setId(TblResultOrderLineItemPK id) {
		this.id = id;
	}

	public int getReservedProductNo() {
		return this.reservedProductNo;
	}

	public void setReservedProductNo(int reservedProductNo) {
		this.reservedProductNo = reservedProductNo;
	}

	public int getReservedQuantity() {
		return this.reservedQuantity;
	}

	public void setReservedQuantity(int reservedQuantity) {
		this.reservedQuantity = reservedQuantity;
	}

	public short getReservedStatus() {
		return this.reservedStatus;
	}

	public void setReservedStatus(short reservedStatus) {
		this.reservedStatus = reservedStatus;
	}

}