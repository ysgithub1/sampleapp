package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_m_code database table.
 * 
 */
@Entity
@Table(name="tbl_m_code")
@NamedQuery(name="TblMCode.findAll", query="SELECT t FROM TblMCode t")
public class TblMCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblMCodePK id;

	public TblMCode() {
	}

	public TblMCodePK getId() {
		return this.id;
	}

	public void setId(TblMCodePK id) {
		this.id = id;
	}

}