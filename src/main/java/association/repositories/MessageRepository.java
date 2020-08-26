package association.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import association.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findByFlatId(Long flatId);

	@Query(nativeQuery = true, value = "SELECT title, type, flatId FROM Message" )
	Page<Message> search(
			String title, 
			String type, 
			Long flatId, 
			Pageable pageRequest);

}
