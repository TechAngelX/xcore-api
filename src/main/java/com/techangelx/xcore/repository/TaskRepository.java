// src/main/java/com/techangelx/xcore/repository/TaskRepository.java

package com.techangelx.xcore.repository;

import com.techangelx.xcore.entity.Task;
import com.techangelx.xcore.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    
    Page<Task> findByUser(User user, Pageable pageable);
}
