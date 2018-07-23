package repository;

import org.springframework.stereotype.Repository;

import model.TblStock;
import model.TblStockPK;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TblStockRepository extends JpaRepository<TblStock, TblStockPK>{

}
