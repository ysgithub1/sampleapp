package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_result database table.
 * 
 */
@Entity
@Table(name="tbl_result")
@NamedQuery(name="TblResult.findAll", query="SELECT t FROM TblResult t")
public class TblResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblResultPK id;

	public TblResult() {
	}

	public TblResultPK getId() {
		return this.id;
	}

	public void setId(TblResultPK id) {
		this.id = id;
	}

}