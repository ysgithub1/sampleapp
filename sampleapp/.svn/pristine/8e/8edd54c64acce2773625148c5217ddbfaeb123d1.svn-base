package repository;

import org.springframework.stereotype.Repository;

import model.TblProduct;
import model.TblResultStock;
import model.TblStock;
import model.TblStockPK;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TblResultStockRepository extends JpaRepository<TblResultStock
																								,TblProduct>{
	public List<TblResultStock> findByIdAndStockTypeAndSize( String id
			,String stockType
			,String size
			);

	public List<TblResultStock> findByIdAndStockTypeAndProductCdAndSize(
			String id
			,String stockType
			,String ProductCd
			,String Size
			);
	public List<TblResultStock> findByStockTypeAndProductCdAndSize(
			String stockType
			,String ProductCd
			,String Size
			);

	
	public List<TblResultStock> findByTimestampAndUserAndStockTypeAndProductCdAndSize(
			String strDatetime
			,String user
			,String stockType
			,String productCd
			,String size
			);	
}
