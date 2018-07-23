package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.TblResult;
import model.TblResultPK;

@Repository
@Transactional(readOnly = true
               ,rollbackFor=Exception.class)
public interface TblResultRepository extends JpaRepository<TblResult, TblResultPK>{
	public List<TblResult> findByid(TblResultPK id); 
	
}
