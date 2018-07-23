package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tbl_result_order_management database table.
 * 
 */
@Entity
@Table(name="tbl_result_order_management")
@NamedQuery(name="TblResultOrderManagement.findAll", query="SELECT t FROM TblResultOrderManagement t")
public class TblResultOrderManagement implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblResultOrderManagementPK id;

	@Column(name="timestamp", insertable=false, updatable=false)
	private String timestamp;

	@Column(name="user", insertable=false, updatable=false)
	private String user;
	
	@Column(name="agent_cd")
	private String agentCd;

	@Column(name="delivery_date")
	private String deliveryDate;

	@Column(name="order_quantity")
	private int orderQuantity;

	@Column(name="order_remaining_quantity")
	private int orderRemainingQuantity;

	@Column(name="order_status")
	private short orderStatus;

	@Column(name="product_cd")
	private String productCd;

	private BigDecimal size;

	public TblResultOrderManagement() {
	}

	public TblResultOrderManagementPK getId() {
		return this.id;
	}

	public void setId(TblResultOrderManagementPK id) {
		this.id = id;
	}

	public String getAgentCd() {
		return this.agentCd;
	}

	public void setAgentCd(String agentCd) {
		this.agentCd = agentCd;
	}

	public String getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getOrderQuantity() {
		return this.orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public int getOrderRemainingQuantity() {
		return this.orderRemainingQuantity;
	}

	public void setOrderRemainingQuantity(int orderRemainingQuantity) {
		this.orderRemainingQuantity = orderRemainingQuantity;
	}

	public short getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(short orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getProductCd() {
		return this.productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public BigDecimal getSize() {
		return this.size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
	}

}