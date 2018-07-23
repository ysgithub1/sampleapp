package repository;

import org.springframework.stereotype.Repository;

import model.TblOrder;
import model.TblOrderPK;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TblOrderRepository extends JpaRepository<TblOrder, String>{
	public TblOrder findByid(TblOrderPK id);
	public List<TblOrder> findBydeliveryDate(String deliveryDate);
	public List<TblOrder> findBydeliveryDateGreaterThanEqual(String deliveryDate);
	public List<TblOrder> findBydeliveryDateBetween(String deliveryDateFrom,String deliveryDateTo);
}
