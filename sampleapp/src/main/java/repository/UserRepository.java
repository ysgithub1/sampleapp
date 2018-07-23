package repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	public List<User> findById(int id);
	public List<User> findByIdAndName(int id, String Name);
	public List<User> findByIdAndPassword(int id, String password);
}
