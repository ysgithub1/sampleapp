package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_result database table.
 * 
 */
@Embeddable
public class TblResultPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String datetime;

	private String user;

	public TblResultPK() {
	}
	public String getDatetime() {
		return this.datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
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
		if (!(other instanceof TblResultPK)) {
			return false;
		}
		TblResultPK castOther = (TblResultPK)other;
		return 
			this.datetime.equals(castOther.datetime)
			&& this.user.equals(castOther.user);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.datetime.hashCode();
		hash = hash * prime + this.user.hashCode();
		
		return hash;
	}
}