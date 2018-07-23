package form;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import validation.GroupOrder1;
import validation.GroupOrder2;
public class Display2Form {
	@NotEmpty(groups={GroupOrder1.class},message="納期を入力してください。")
	@Pattern(regexp="[0-9]*",groups={GroupOrder1.class},message="文字は入力できません。")
	private String deliveryDateFrom;
	@Pattern(regexp="[0-9]*",groups={GroupOrder1.class},message="文字は入力できません。")
	private String deliveryDateTo;
	
	public String getDeliveryDateFrom() {
	return deliveryDateFrom;
	}
	 
	public void setDeliveryDateFrom(String deliveryDateFrom) {
	this.deliveryDateFrom = deliveryDateFrom;
	}

	public String getDeliveryDateTo() {
	return deliveryDateTo;
	}
	 
	public void setDeliveryDateTo(String deliveryDateTo) {
	this.deliveryDateTo = deliveryDateTo;
	}
	
}
