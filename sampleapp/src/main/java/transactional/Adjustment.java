package transactional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import model.TblMSupplier;
import model.TblOrder;
import model.TblOrderPK;
import model.TblProduct;
import model.TblResult;
import model.TblResultOrderLineItem;
import model.TblResultOrderLineItemPK;
import model.TblResultOrderManagement;
import model.TblResultOrderManagementPK;
import model.TblResultPK;
import model.TblResultStock;
import model.TblResultStockPK;
import model.TblStock;
import repository.TblOrderRepository;
import repository.TblResultOrderRepository;
import repository.TblResultRepository;
import repository.TblResultStockRepository;
import repository.TblStockRepository;
import struct.OrderLineItemStruct;
import struct.OrderStruct;
import struct.StockStruct;


@Component
public class Adjustment {

	@Autowired
	private LocalContainerEntityManagerFactoryBean factory;
	
	@Autowired
	private TblStockRepository stockRep;

	@Autowired
	private TblOrderRepository orderRep;

	@Autowired
	private TblResultStockRepository stockResultRep;
	
	@Autowired
	private TblResultRepository resultRep;
	
	@Autowired
	private TblResultOrderRepository rOrderRep;

	
	
	private EntityManager manager;
	private EntityTransaction transaction;

	public Adjustment(){
//		this.manager =
//	 			factory.getNativeEntityManagerFactory().createEntityManager();
//	 	this.transaction =manager.getTransaction();		
	}
	
	public String run(String strDatetime, String id
						,String deliveryDateFrom
						,String deliveryDateTo
						,String stockType
						,Boolean bubun
						,Boolean delete
					) {

//		factory= new LocalContainerEntityManagerFactoryBean();
		this.manager =
	 			factory.getNativeEntityManagerFactory().createEntityManager();
	 	this.transaction =manager.getTransaction();		

//			結果TBLへ書き込み		
			 
		try {	
			this.transaction.begin();
			TblResultPK tblResultPK =new TblResultPK();
			tblResultPK.setDatetime(strDatetime);
			tblResultPK.setUser(id);
			
			List<TblResult> tblResultList =resultRep.findByid(tblResultPK);
			
			//調整ロジック
			//結果テーブルへ追加
			if(tblResultList.size()<=0) {
				TblResult tblResult = new TblResult();
				tblResultPK = new TblResultPK();
				tblResultPK.setDatetime(strDatetime);
				tblResultPK.setUser(id);
				tblResult.setId(tblResultPK);
				this.manager.persist(tblResult);
				this.manager.flush();
				
				//在庫引当結果テーブルへ追加
				TblResultStock tblResultStock =new TblResultStock();
				TblResultStockPK tblResultStockPK =new TblResultStockPK();
				TblMSupplier tblMSupplier = new TblMSupplier();
				TblProduct tblProduct = new TblProduct();
				List<TblStock> stockList = stockRep.findAll();
				
				for(TblStock stock : stockList) {					
					System.out.println("キー項目設定");
					tblResultStockPK.setProductNo(stock.getId().getProductNo());
					tblResultStockPK.setTimestamp(strDatetime);
					tblResultStockPK.setUser(id);
					System.out.println(tblResultStockPK.getProductNo()
							+ ":" + tblResultStockPK.getTimestamp()
							+ ":" +tblResultStockPK.getUser()
							);
					
					
					System.out.println("在庫引当項目設定");
					tblResultStock.setId(tblResultStockPK);				
		//				tblResultStock.setTblMSupplier(tblMSupplier);
		//				tblResultStock.setTblProduct(tblProduct);
					tblResultStock.setProductCd(stock.getTblProduct().getProductCd());
					tblResultStock.setWarehouseCd(stock.getId().getWarehouseCd());
					tblResultStock.setStockType(stock.getStockType());
					tblResultStock.setSize(stock.getSize());
					tblResultStock.setQuantityBeforeReserve(stock.getFreeQuantity());
					tblResultStock.setQuantityAfterReserve(stock.getFreeQuantity());
					System.out.println("追加処理");
					this.manager.persist(tblResultStock);
					this.manager.flush();
					tblResultStock =new TblResultStock();
					tblResultStockPK =new TblResultStockPK();
				}
			}
			
			
			//注文調整結果
			//構造体作成
			//注文結果構造体
			ArrayList<OrderStruct> orderStructList = new ArrayList<OrderStruct>();
			//注文結果明細構造体
			ArrayList<OrderLineItemStruct> orderLIStructList = new ArrayList<OrderLineItemStruct>();
			
			if(tblResultList.size()<=0) {
				System.out.println("□□□□初回□□□□");
				List<TblOrder> orderList= orderRep.findBydeliveryDateBetween(deliveryDateFrom
																		, deliveryDateTo);
				
				for(TblOrder order : orderList) {
					OrderStruct orderStruct =new OrderStruct();
					OrderLineItemStruct orderLIStruct =new OrderLineItemStruct();
					orderStruct.jurisdiction=order.getId().getJurisdiction();
					orderLIStruct.jurisdiction=order.getId().getJurisdiction();
					orderStruct.orderNo=order.getId().getOrderNo();
					orderLIStruct.orderNo=order.getId().getOrderNo();
	
					orderStruct.agentCd=order.getAgentCd();
					orderLIStruct.agentCd=order.getAgentCd();
					orderStruct.productCd=order.getTblProduct().getProductCd();
					orderLIStruct.productCd=order.getTblProduct().getProductCd();
					orderStruct.size =order.getSize();
					orderLIStruct.size =order.getSize();
					orderLIStruct.lineItemNo=1;
					orderStruct.deliveryDate=order.getDeliveryDate();
					orderStruct.orderQuantity=order.getOrderQuantity();
					orderStruct.remainingQuantity=order.getOrderQuantity();
					orderStruct.orderStatus=0;
					orderLIStruct.reservedQuantity=0;
					orderLIStruct.reservedStatus=0;
					orderStructList.add(orderStruct);
					orderLIStructList.add(orderLIStruct);
	//				System.out.println(orderStruct.orderNo);
				}
			}else {
				System.out.println("□□□□複数回目□□□□");
				List<TblResultOrderManagement> orderManagements = 
						rOrderRep.findByTimestampAndUserAndOrderStatusNot(strDatetime,id,(short)2);
				for(TblResultOrderManagement order : orderManagements) {
					OrderStruct orderStruct =new OrderStruct();
					OrderLineItemStruct orderLIStruct =new OrderLineItemStruct();
					orderStruct.jurisdiction=order.getId().getJurisdiction();
					orderLIStruct.jurisdiction=order.getId().getJurisdiction();
					orderStruct.orderNo=order.getId().getOrderNo();
					orderLIStruct.orderNo=order.getId().getOrderNo();
	
					orderStruct.agentCd=order.getAgentCd();
					orderLIStruct.agentCd=order.getAgentCd();
					orderStruct.productCd=order.getProductCd();
					orderLIStruct.productCd=order.getProductCd();
					orderStruct.size =order.getSize();
					orderLIStruct.size =order.getSize();
					orderLIStruct.lineItemNo=1;
					orderStruct.deliveryDate=order.getDeliveryDate();
					orderStruct.orderQuantity=order.getOrderQuantity();
					orderStruct.remainingQuantity=order.getOrderQuantity();
					orderStruct.orderStatus=0;
					orderLIStruct.reservedQuantity=0;
					orderLIStruct.reservedStatus=0;
					orderStructList.add(orderStruct);
					orderLIStructList.add(orderLIStruct);				
				}
			}
			
			
			//在庫引当
			System.out.println(stockType +":在庫");
			ArrayList<StockStruct> stockStructList = new ArrayList<StockStruct>();

			String ReservedResult;
//			ここのループを変更変数が変わったことを反映する
			for(int i=0; i < orderStructList.size(); i++) {
				OrderStruct orderStruct =orderStructList.get(i);
				OrderLineItemStruct orderLIStruct = new OrderLineItemStruct();
				
//				ループを最終まで回すことで最後の明細NOが取得できている。
				int lineItemIndex=0;
				for(int j=0;j < orderLIStructList.size();j++) {
					OrderLineItemStruct orderLI=orderLIStructList.get(j);
					if ((orderStruct.jurisdiction == orderLI.jurisdiction)
						&& (orderStruct.orderNo == orderLI.orderNo)
						){
						orderLIStruct= orderLI;
						lineItemIndex=j;
					}
				}
				
				System.out.println("注文明細：" + orderLIStruct.orderNo
						+ ":" + orderLIStruct.jurisdiction
						+ ":" + orderLIStruct.lineItemNo
						);
				System.out.println("引当予定注文：" + orderStruct.orderNo);
				
				if(stockStructList.size()>0) {
					ReservedResult = Match(stockType, stockStructList, orderStruct,orderLIStruct,bubun);
					System.out.println("ループ引当結果:"+ ReservedResult);
					if (ReservedResult == "ng"){
						System.out.println("在庫配列にないため在庫テーブル検索");
						setStockList(stockType, stockStructList, orderStruct,strDatetime,id);
						ReservedResult = Match(stockType, stockStructList, orderStruct,orderLIStruct,bubun);
					}						
				}else {
					setStockList(stockType, stockStructList, orderStruct,strDatetime,id);
					ReservedResult = Match(stockType, stockStructList, orderStruct,orderLIStruct,bubun);
					System.out.println("初回引当結果:"+ ReservedResult);
						
				}
//				注文状態反映
				orderStatusUpdate(orderStruct);
				
//				注文結果反映
				orderStructList.set(i, orderStruct);
				orderLIStructList.set(lineItemIndex, orderLIStruct);
				

				System.out.println("*** ループ終点 ***");				
			}
			System.out.println("*** ループ終了 ***");
			if(tblResultList.size()<=0) {
				resultOrderInsert(strDatetime,id,orderStructList,orderLIStructList);
				resultOrderLIinsert(strDatetime,id,orderLIStructList);
			}else {
				resultOrderUpdate(strDatetime,id,orderStructList,orderLIStructList);
				resultOrderLIinsert(strDatetime,id,orderLIStructList);
			}
			resultStockUpdate(strDatetime,id,stockStructList);
			
			
			
////			例外
////		コミットが消えないことのテスト
//		System.out.println("コミット");
//		transaction.commit();
//		System.out.println("トランザクション再開");
//		transaction.begin();
////		コミットする時はここまで
//			
//		System.out.println("スタート");			
//		TblResult tblResult2 = new TblResult();
//		TblResultPK tblResultPK2 = new TblResultPK();
//		System.out.println("null追加");
//		tblResultPK2.setDatetime(null);
//		tblResultPK2.setUser(null);
//		System.out.println("キー追加");	
//		tblResult2.setId(tblResultPK2);
//		System.out.println("追加処理");	
//		manager.persist(tblResult2);
//		System.out.println("フラッシュ前");	
//		manager.flush(); //フラッシュ時点で例外となる。
//		System.out.println("コミット前");			
			


			this.transaction.commit();
//			System.out.println("ok");
			return "ok";
		}catch(RuntimeException e) {
			e.printStackTrace();
			this.transaction.rollback();
		  	System.out.println("ランタイム例外");
			return "ng";
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("その他例外");
			this.transaction.rollback();
			return "ng";			
		}
	//トランザクション終了地点
	}
	
	private void setStockList(String stockType
								,ArrayList<StockStruct> stockStructList
								,OrderStruct orderStruct
								,String strDatetime
								,String id
								) throws RuntimeException  {
	
//		List<TblResultStock> resultStokList =  				
//				stockResultRep.findByTimestampAndUserAndStockTypeAndProductCdAndSize(
//						strDatetime
//						,id
//						,stockType
//						,orderStruct.productCd
//						,orderStruct.size);
		Query query = manager.createQuery("from TblResultStock " 
				+ " where timestamp = '" + strDatetime + "'"
				+ " and  user = '" + id + "'"
				+ " and  product_cd = '" + orderStruct.productCd + "'"
				+ " and  size = '" + orderStruct.size + "'"
				);
		List<TblResultStock> resultStokList =  findByField(query);
		

		System.out.println( "取得条件:"
						+ "日次:" +strDatetime
						+ "ユーザー" +id
						+ "商品コード" +orderStruct.productCd
						+ "サイズ" +orderStruct.size
				);
		System.out.println( "在庫件数: " + resultStokList.size());
		for(TblResultStock stock : resultStokList) {
			StockStruct stockStruct = new StockStruct();
			stockStruct.timeStamp=stock.getId().getTimestamp();
			stockStruct.productNo= stock.getId().getProductNo();
			stockStruct.warehouseCd=stock.getWarehouseCd();
			stockStruct.stockType=stock.getStockType();
			stockStruct.productCd=stock.getProductCd();
			stockStruct.size=stock.getSize();
			stockStruct.quantityBeforeReserve=stock.getQuantityBeforeReserve();
			stockStruct.quantityAfterReserve=stock.getQuantityAfterReserve();
			stockStruct.quantityBeforeReserve=stock.getQuantityBeforeReserve();
			stockStructList.add(stockStruct);
		}
	}
	//同一トランザクションで検索する。
	@SuppressWarnings({ "unchecked","rawtypes"})
	public List findByField(Query query) {
		return  query.getResultList();
	}
	
//	照合処理
	private String Match(String stockType
						,ArrayList<StockStruct> stockStructList
						,OrderStruct orderStruct
						,OrderLineItemStruct orderLIStruct
						,Boolean bubun
						)throws Exception {

		for(int i=0; i< stockStructList.size(); i++) {	
			
			StockStruct stock = stockStructList.get(i);

			 if ((orderStruct.productCd.equals(stock.productCd)) 
				&& (orderStruct.size.compareTo(stock.size)==0)
				&& (stockType.equals(stock.stockType))
				){
//			 if (orderStruct.size.equals(stock.size)

				System.out.println("規格一致");
//					引当後数量は最初引当前数量である。　
//					引当てるたびに引当後数量を減算するので
//					注文数量>=引当後数量の条件にヒットする時マッチした事となる。

				System.out.println("数量比較："
						+ "　注文:" +orderStruct.orderQuantity
						+ "　在庫:" +stock.quantityAfterReserve
						);
					if (orderStruct.orderQuantity <=
							stock.quantityAfterReserve){
						System.out.println("全量 ");
						stock.quantityAfterReserve -= orderStruct.orderQuantity;
						orderStruct.remainingQuantity -= orderStruct.orderQuantity;
						stockStructList.set(i, stock);
						
						if (stockType=="K"){
							orderLIStruct.reservedStatus=1;
						} else {
							orderLIStruct.reservedStatus=2;
						}
						orderLIStruct.reservedQuantity=orderStruct.orderQuantity;
						orderLIStruct.reservedProductNo=stock.productNo;
							
						return "ok";
					} else if((bubun)
							&& stock.quantityAfterReserve >0) {
						System.out.println("部分");
						orderStruct.remainingQuantity = orderStruct.orderQuantity
														- stock.quantityAfterReserve;
						stock.quantityAfterReserve = 0;
						stockStructList.set(i, stock);

						orderLIStruct.reservedStatus=3;

						orderLIStruct.reservedQuantity=orderStruct.orderQuantity;
						orderLIStruct.reservedProductNo=stock.productNo;
						
					}else if(stockType=="S"){
						continue;
					}else {
						
						return "stockout";
					}
			}
			
			
		}
		
		return "ng";
	}
	private void orderStatusUpdate(OrderStruct orderStruct) {
		if(orderStruct.remainingQuantity == 0) {
			orderStruct.orderStatus=2;
			System.out.println("★★★引当完了★★★:"
					 + orderStruct.orderNo
					 + ":" +orderStruct.productCd
					+ ":" +orderStruct.orderQuantity
					+ ":" +orderStruct.remainingQuantity);
			
		}else if(orderStruct.orderQuantity == orderStruct.remainingQuantity){
			orderStruct.orderStatus=0;	
			System.out.println("☆☆☆引当不可☆☆☆:"
					+ orderStruct.orderNo
					+ ":" +orderStruct.productCd
					+ ":" +orderStruct.orderQuantity
					+ ":" +orderStruct.remainingQuantity);
		}else if((orderStruct.orderQuantity > orderStruct.remainingQuantity)
				&& (orderStruct.remainingQuantity > 0)
				){
			orderStruct.orderStatus=1;
			System.out.println("★★☆残数あり☆★★:"
					+ orderStruct.orderNo
					+ ":" +orderStruct.productCd
					+ ":" +orderStruct.orderQuantity
					+ ":" +orderStruct.remainingQuantity);

		}	
	}
	
	private void resultOrderInsert(String strDatetime
									,String id
									,List<OrderStruct> OrderStructList
									,List<OrderLineItemStruct> OrderLIStructList) throws Exception {
		System.out.println("注文調整結果追加");
		for(OrderStruct orderStruct: OrderStructList) {
			TblResultOrderManagement tblROrder = new TblResultOrderManagement();
			TblResultOrderManagementPK tblROrderPK =new TblResultOrderManagementPK();
			TblOrderPK tblOrderPK =new TblOrderPK();
			TblOrder tblOrder =new TblOrder();
			
			tblOrderPK.setJurisdiction(orderStruct.jurisdiction);
			tblOrderPK.setOrderNo(orderStruct.orderNo); 
			tblOrder=orderRep.findByid(tblOrderPK);
			
			tblROrderPK.setTimestamp(strDatetime);
			tblROrderPK.setUser(id);
			tblROrderPK.setJurisdiction(orderStruct.jurisdiction);
			tblROrderPK.setOrderNo(orderStruct.orderNo);
			tblROrder.setId(tblROrderPK);
			tblROrder.setOrderQuantity(orderStruct.orderQuantity);
			tblROrder.setOrderRemainingQuantity(orderStruct.remainingQuantity);
			tblROrder.setOrderStatus(orderStruct.orderStatus);
			tblROrder.setAgentCd(tblOrder.getAgentCd());
			tblROrder.setProductCd(tblOrder.getTblProduct().getProductCd());
			tblROrder.setDeliveryDate(tblOrder.getDeliveryDate());
			tblROrder.setSize(tblOrder.getSize());
			this.manager.persist(tblROrder);
			this.manager.flush();
			
		}
	}
	
	private void resultOrderUpdate(String strDatetime
			,String id
			,List<OrderStruct> OrderStructList
			,List<OrderLineItemStruct> OrderLIStructList) throws Exception {
		System.out.println("注文調整結果更新");
		for(OrderStruct orderStruct: OrderStructList) {
			TblResultOrderManagement tblROrder = new TblResultOrderManagement();
			TblResultOrderManagementPK tblROrderPK =new TblResultOrderManagementPK();
			TblOrderPK tblOrderPK =new TblOrderPK();
			TblOrder tblOrder =new TblOrder();
			
			tblOrderPK.setJurisdiction(orderStruct.jurisdiction);
			tblOrderPK.setOrderNo(orderStruct.orderNo); 
			tblOrder=orderRep.findByid(tblOrderPK);
			
			tblROrderPK.setTimestamp(strDatetime);
			tblROrderPK.setUser(id);
			tblROrderPK.setJurisdiction(orderStruct.jurisdiction);
			tblROrderPK.setOrderNo(orderStruct.orderNo);
			tblROrder.setId(tblROrderPK);
			tblROrder.setOrderQuantity(orderStruct.orderQuantity);
			tblROrder.setOrderRemainingQuantity(orderStruct.remainingQuantity);
			tblROrder.setOrderStatus(orderStruct.orderStatus);
			tblROrder.setAgentCd(tblOrder.getAgentCd());
			tblROrder.setProductCd(tblOrder.getTblProduct().getProductCd());
			tblROrder.setDeliveryDate(tblOrder.getDeliveryDate());
			tblROrder.setSize(tblOrder.getSize());
			this.manager.merge(tblROrder);
			this.manager.flush();
			
		}

		
	}
	private void resultOrderLIinsert(String strDatetime
			,String id
			,List<OrderLineItemStruct> OrderLIStructList) throws Exception {
		System.out.println("注文調整結果明細追加");
		for(OrderLineItemStruct orderLIStruct: OrderLIStructList) {
			if(orderLIStruct.reservedStatus > (short)0) {
				TblResultOrderLineItem tblROrderLineItem =new  TblResultOrderLineItem();
				TblResultOrderLineItemPK tblROrderLineItemPK =new TblResultOrderLineItemPK();
				tblROrderLineItemPK.setTimestamp(strDatetime);
				tblROrderLineItemPK.setUser(id);
				tblROrderLineItemPK.setJurisdiction(orderLIStruct.jurisdiction);
				tblROrderLineItemPK.setOrderNo(orderLIStruct.orderNo);
				tblROrderLineItemPK.setLineItemNo(orderLIStruct.lineItemNo);
				
				tblROrderLineItem.setId(tblROrderLineItemPK);
				tblROrderLineItem.setReservedQuantity(orderLIStruct.reservedQuantity);
				tblROrderLineItem.setReservedProductNo(orderLIStruct.reservedProductNo);
				tblROrderLineItem.setReservedStatus(orderLIStruct.reservedStatus);
				this.manager.persist(tblROrderLineItem);
				this.manager.flush();				
			}

		}
		
	}
	
	private void resultStockUpdate(String strDatetime
			,String id
			,List<StockStruct> stockStructList
			) throws Exception {
		System.out.println("在庫引当結果更新");
		for(StockStruct stockStruct: stockStructList) {
			TblResultStock tblResultStock =new  TblResultStock();
			TblResultStockPK tblResultStockPK =new TblResultStockPK();
			tblResultStockPK.setTimestamp(strDatetime);
			tblResultStockPK.setUser(id);
			tblResultStockPK.setProductNo(stockStruct.productNo);
			
			tblResultStock.setWarehouseCd(stockStruct.warehouseCd);
			tblResultStock.setId(tblResultStockPK);
			tblResultStock.setProductCd(stockStruct.productCd);
			tblResultStock.setSize(stockStruct.size);
			tblResultStock.setStockType(stockStruct.stockType);
			tblResultStock.setQuantityBeforeReserve(stockStruct.quantityBeforeReserve);
			tblResultStock.setQuantityAfterReserve(stockStruct.quantityAfterReserve);
		
			this.manager.merge(tblResultStock);
			this.manager.flush();
			
		}
	}
	
	
}
