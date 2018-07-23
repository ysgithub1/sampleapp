package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_m_suppliers database table.
 * 
 */
@Entity
@Table(name="tbl_m_suppliers")
@NamedQuery(name="TblMSupplier.findAll", query="SELECT t FROM TblMSupplier t")
public class TblMSupplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="suppliers_cd")
	private String suppliersCd;

	@Column(name="suppliers_name")
	private String suppliersName;

	public TblMSupplier() {
	}

	public String getSuppliersCd() {
		return this.suppliersCd;
	}

	public void setSuppliersCd(String suppliersCd) {
		this.suppliersCd = suppliersCd;
	}

	public String getSuppliersName() {
		return this.suppliersName;
	}

	public void setSuppliersName(String suppliersName) {
		this.suppliersName = suppliersName;
	}

}