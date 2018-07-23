package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tbl_order database table.
 * 
 */
@Entity
@Table(name="tbl_order")
@NamedQuery(name="TblOrder.findAll", query="SELECT t FROM TblOrder t")
public class TblOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblOrderPK id;

	@Column(name="delivery_date")
	private String deliveryDate;

	@Column(name="order_date")
	private String orderDate;

	@Column(name="order_quantity")
	private int orderQuantity;

	private BigDecimal size;

	//uni-directional many-to-one association to TblMSupplier
	@ManyToOne
	@JoinColumn(name="agent_cd" , insertable=false, updatable=false)
	private TblMSupplier tblMSupplier;
	
	@Column(name="agent_cd")
	private String agentCd;
	
	//uni-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="product_cd")
	private TblProduct tblProduct;

	public TblOrder() {
	}

	public TblOrderPK getId() {
		return this.id;
	}

	public void setId(TblOrderPK id) {
		this.id = id;
	}

	public String getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderQuantity() {
		return this.orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public BigDecimal getSize() {
		return this.size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
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

	public String getAgentCd() {
		// TODO 自動生成されたメソッド・スタブ
		return this.agentCd;
	}

}