package com.examly.springapp;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;




public interface TaskRepo extends JpaRepository<Task,Integer>{
	
	Optional<Task> findBytaskHolderNameLike(String name);
	
	@Modifying
	@Transactional
	@Query(value="update Task set task_status = 'Completed' where task_id = ?1",nativeQuery=true	)
	void updateTaskStatus(int id);

}