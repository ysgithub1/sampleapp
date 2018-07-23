package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
//import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;

import org.hamcrest.core.IsNull;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Bootstrap_container;
import com.Head;
import com.Script;

import form.Display2Form;

import model.TblOrder;
import model.TblOrderPK;
import model.TblResult;
import model.TblResultOrderManagement;
import model.TblResultPK;
import repository.TblOrderRepository;
import repository.TblResultOrderRepository;
import repository.TblResultRepository;

import transactional.Adjustment;
import validation.GroupOrder;

@Controller
//@RequestMapping("display2")
public class Display2Controller {

	@Autowired
	HttpSession session;
	
	private Script script=new Script();
	private Head head;
	private Bootstrap_container container;	
	
//	@Autowired
//	private static ApplicationContext context;
	
	@Autowired
	private LocalContainerEntityManagerFactoryBean factory;
	
	@Autowired
	private Adjustment adjust;
	
//	@PersistenceContext
//	private static EntityManager manager;
	
	@Autowired
	TblOrderRepository orderRep;
	@Autowired
	TblResultOrderRepository rOrderRep;
	@Autowired
	TblResultRepository resultRep;

	
	 @RequestMapping(value = "order",params="orderGet", method = RequestMethod.POST)
	 public String orderget(Model model, @Validated(GroupOrder.class) @ModelAttribute("Display2Form") 
			 			Display2Form display2Form
		 				, BindingResult result	 
		 				, HttpSession session
		 				) {
//		 classCom(model, session.getAttribute("session2").toString());
		 model.addAttribute("script", script.getScript());
		 head = new Head("表示2");
		 model.addAttribute("head",head.getHead());
		 container = new Bootstrap_container("表示2",session.getAttribute("session2").toString()); 
		 modelmake(model);

		 //		 if(result.hasErrors()) {
//			 return "display2";
//		 }
		 List<TblOrder> orderList ;
		 if ( display2Form.getDeliveryDateTo().isEmpty()){
		 	orderList=orderRep.findBydeliveryDateGreaterThanEqual(display2Form.getDeliveryDateFrom());
		 }else {
			 orderList=orderRep.findBydeliveryDateBetween(display2Form.getDeliveryDateFrom()
					 										, display2Form.getDeliveryDateTo());
		 }
		 String orderResult ="";
		 if(orderList.size()>0) {
			 orderResult += "<table class=\"table\">"+"\n";
			 orderResult += "<thead>"+"\n";
			 orderResult += "<tr>"+"\n";
//			 orderResult += "<th>#</th>"+"\n";
			 orderResult += "<th>管轄</th>"+"\n";
			 orderResult += "<th>注文NO</th>"+"\n";
			 orderResult += "<th>納期</th>"+"\n";
			 orderResult += "<th>商品名</th>"+"\n";
			 orderResult += "<th>サイズ</th>"+"\n";
			 orderResult += "<th>注文数</th>"+"\n";
			 orderResult += "</tr>"+"\n";
			 orderResult += "</thead>"+"\n";
			 orderResult += "<tbody>"+"\n";
			 for(TblOrder order : orderList) {
				 orderResult += "<tr>"+"\n";
				 orderResult += "<td>"+ order.getId().getJurisdiction() +"</td>\n";
				 orderResult += "<td>"+ order.getId().getOrderNo() +"</td>\n";
				 orderResult += "<td>"+ order.getDeliveryDate() +"</td>\n";
				 orderResult += "<td>"+ order.getTblProduct().getProductName() +"</td>\n";
				 orderResult += "<td>"+ order.getSize() +"</td>\n";
				 orderResult += "<td>"+ order.getOrderQuantity() +"</td>\n";
				 orderResult += "</tr>"+"\n";
		    	}
			 orderResult += "</tbody>"+"\n";
			 orderResult += "</table>"+"\n";
			 model.addAttribute("orderlist", orderResult);
			 
			 
		 }else {
			  model.addAttribute("orderlist", "指定期間の注文はありません。");
		 }
		 
		 
		 return "display2";
	 }
	 
	 @RequestMapping(value = "order",params = "orderAdjustment", method = RequestMethod.POST)
	 public String orderAdjustment(Model model, @Validated(GroupOrder.class) @ModelAttribute("Display2Form") 
		Display2Form display2Form
		, BindingResult result	 
		, HttpSession session
		) {
//		 classCom(model, session.getAttribute("session2").toString());

		model.addAttribute("script", script.getScript());
		head = new Head("注文調整結果");
		model.addAttribute("head",head.getHead());
		container = new Bootstrap_container("注文調整結果",session.getAttribute("session2").toString()); 
		modelmake(model);

		 //if(result.hasErrors()) {
		//return "display2";
		//}
		
		String deliveryDateFrom=display2Form.getDeliveryDateFrom();
		
		String deliveryDateTo;		
		if ( display2Form.getDeliveryDateTo().isEmpty()){

			deliveryDateTo="99999999";
		}else {
			deliveryDateTo= display2Form.getDeliveryDateTo();
		}
		
		
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//		時間取得
		String strDatetime = format.format(new Date());
		String id =session.getAttribute("session1").toString();		
		String strResult;
		
//		全量：調整実行
		strResult = adjust.run(strDatetime,id,deliveryDateFrom,deliveryDateTo,"K",false,false);
		
//		部分：調整実行
		strResult = adjust.run(strDatetime,id,deliveryDateFrom,deliveryDateTo,"K",true,false);
		
//		表示
		List<TblResultOrderManagement> orderManagements =rOrderRep.findByTimestampAndUser(strDatetime, id);
		if (strResult == "ok"){
			
			
//				model.addAttribute("resultlist", strDatetime + ":" 
//						+ id
//						+ " : "
//						+ strResult
//						+" 注文調整が完了しました。");
			 String orderResult ="";
			 if(orderManagements.size()>0) {
				 orderResult += "<table class=\"table\">"+"\n";
				 orderResult += "<thead>"+"\n";
				 orderResult += "<tr>"+"\n";
//				 orderResult += "<th>#</th>"+"\n";
				 orderResult += "<th>管轄</th>"+"\n";
				 orderResult += "<th>注文NO</th>"+"\n";
				 orderResult += "<th>納期</th>"+"\n";
				 orderResult += "<th>商品名</th>"+"\n";
				 orderResult += "<th>サイズ</th>"+"\n";
				 orderResult += "<th>注文数</th>"+"\n";
				 orderResult += "<th>注文残数</th>"+"\n";
				 orderResult += "<th>注文状態</th>"+"\n";
				 orderResult += "</tr>"+"\n";
				 orderResult += "</thead>"+"\n";
				 orderResult += "<tbody>"+"\n";
				 for(TblResultOrderManagement tblROrder : orderManagements) {
					 orderResult += "<tr>"+"\n";
					 orderResult += "<td>"+ tblROrder.getId().getJurisdiction() +"</td>\n";
					 orderResult += "<td>"+ tblROrder.getId().getOrderNo() +"</td>\n";
					 TblOrderPK tblOrderPK =new TblOrderPK();
					 TblOrder tblOrder =new TblOrder();
					 tblOrderPK.setJurisdiction(tblROrder.getId().getJurisdiction());
					 tblOrderPK.setOrderNo(tblROrder.getId().getOrderNo());
					 
					 tblOrder=orderRep.findByid(tblOrderPK);
					 orderResult += "<td>"+ tblOrder.getDeliveryDate() +"</td>\n";
					 orderResult += "<td>"+ tblOrder.getTblProduct().getProductName() +"</td>\n";
					 orderResult += "<td>"+ tblOrder.getSize() +"</td>\n";
					 orderResult += "<td>"+ tblROrder.getOrderQuantity() +"</td>\n";
					 orderResult += "<td>"+ tblROrder.getOrderRemainingQuantity() +"</td>\n";
					 String strOrderStatus="";
					 switch (tblROrder.getOrderStatus()) {
					 case 0:
						 strOrderStatus="引当不可";
						 break;
					 case 1:
						 strOrderStatus="残数あり";
						 break;
					 case 2:
						 strOrderStatus="引当完了";
						 break;
					 default:
						 strOrderStatus="その他";
						 break;						 
					 }
					 orderResult += "<td>"+ strOrderStatus +"</td>\n";
					 orderResult += "</tr>"+"\n";
			    	}
				 orderResult += "</tbody>"+"\n";
				 orderResult += "</table>"+"\n";
				 model.addAttribute("resultlist", orderResult);
				 
				 
			 }else {
				  model.addAttribute("resultlist", "指定期間の注文はありません。");
			 }
	
			
		}else {
			model.addAttribute("resultlist", "調整失敗");

		};
		
		
//		完了
		
		return "result";
	}
	 

	 
	 
	 private void modelmake(Model model) {
		 model.addAttribute("containerstart", container.getCotainerStart());
		 model.addAttribute("titleheader", container.getHeader());
		 model.addAttribute("menu", container.getMenu());
		 model.addAttribute("contentsstart", container.getContetntsStart());
		 model.addAttribute("contentsend", container.getContentsEnd());
		 model.addAttribute("containerend", container.getCotainerEnd());
	 }

}
