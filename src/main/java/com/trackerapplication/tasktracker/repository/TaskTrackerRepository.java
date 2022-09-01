package com.trackerapplication.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trackerapplication.tasktracker.models.TaskTracker;

@Repository
public interface TaskTrackerRepository extends JpaRepository<TaskTracker,Long>{
    
}
