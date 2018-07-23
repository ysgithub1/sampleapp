package repository;

import org.springframework.stereotype.Repository;

import model.TblOrder;
import model.TblResultOrderManagement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TblResultOrderRepository extends JpaRepository<TblResultOrderManagement, String>{
	public List<TblResultOrderManagement> findByTimestampAndUser(String timestamp ,String User);
	public List<TblResultOrderManagement> findByTimestampAndUserAndOrderStatusNot(
											String timestamp 
											,String User
											,short orderStatus
											);
}
