package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_m_code_type database table.
 * 
 */
@Entity
@Table(name="tbl_m_code_type")
@NamedQuery(name="TblMCodeType.findAll", query="SELECT t FROM TblMCodeType t")
public class TblMCodeType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="code_type")
	private String codeType;

	public TblMCodeType() {
	}

	public String getCodeType() {
		return this.codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

}