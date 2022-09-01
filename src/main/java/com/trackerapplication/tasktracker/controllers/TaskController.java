package com.trackerapplication.tasktracker.controllers;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trackerapplication.tasktracker.models.TaskTracker;
import com.trackerapplication.tasktracker.repository.TaskTrackerRepository;
import com.trackerapplication.tasktracker.repository.UserRepository;



@RestController
@RequestMapping("/task")
public class TaskController {


    private EntityManagerFactory managerFactory; 
    private UserRepository userRepository;
    private TaskTrackerRepository trackerRepository;


    @Autowired
    public TaskController(UserRepository employeeRepository, TaskTrackerRepository trackerRepository, EntityManagerFactory managerFactory) {
        this.userRepository = employeeRepository;
        this.trackerRepository = trackerRepository;
        this.managerFactory = managerFactory;
    }

//   @PostMapping("/tasktracker/{employeeId}")
//     public ResponseEntity<TaskTracker> createTask(@PathVariable(value = "employeeId") Long employeeId, @RequestBody TaskTracker taskTracker) {
//         TaskTracker tracker = userRepository.findById(employeeId).map(user -> {
//         taskTracker.setUser(user);
//         taskTracker.setDate(new Date());
//         return trackerRepository.save(taskTracker);
//     }).orElseThrow(() -> new ResourceNotFoundException("Not found employee with id = " + employeeId));

//        return new ResponseEntity<>(tracker, HttpStatus.CREATED);
//  }

// @PostMapping("enterTask/{employeeId}")
// public ResponseEntity<TaskTracker> createTask(@PathVariable(value = "employeeId") Long employeeId, @RequestBody TaskTracker taskTracker) {
//     TaskTracker tracker = userRepository.findById(employeeId).map(user -> {
//     taskTracker.setUser(user);
//     taskTracker.setDate(new Date());
//     return trackerRepository.save(taskTracker);
// }).orElseThrow(() -> new ResourceNotFoundException("Not found employee with id = " + employeeId));

//    return new ResponseEntity<>(tracker, HttpStatus.CREATED);
// }

    @GetMapping("/headId/{verticleHeadId}")
    public ResponseEntity<List<TaskTracker>> findAllByVerticleHeadId(@PathVariable(value = "verticleHeadId") Long verticleHeadId){
        EntityManager manager = managerFactory.createEntityManager();
        Query query = manager.createQuery("select " +"e.userName, "+ "t.taskDetails from User e, " + "TaskTracker t where e.id = t.user "  + "and e.verticleHeadId = "+verticleHeadId+"");
        // if(employeeRepository.findByVerticleHeadId(verticleHeadId).equals(null)){
        //   throw new ResourceNotFoundException("Not found with head ID  : " + verticleHeadId);
        // }
        List<TaskTracker> trackers = (List<TaskTracker>)query.getResultList();
        manager.close();
       return new ResponseEntity<>(trackers, HttpStatus.OK);
    }

    @GetMapping("/bydate/{date}")
    public ResponseEntity<List<TaskTracker>> findAllByDate(@PathVariable(value = "date") @DateTimeFormat(pattern = "dd-MMM-yy") Date date) throws ParseException{
      // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
      DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy"); 
      //date = Calendar.getInstance().getTime();  
     
      //dateFormat.format(date); 
      EntityManager manager = managerFactory.createEntityManager();
      Query query = manager.createQuery("select " +"e.userName, "+ "t.taskDetails from User e, " + "TaskTracker t where e.id = t.user "  + "and to_char(t.date, 'dd-Mon-yy') = '" +  dateFormat.format(date) +"'");
      // if(employeeRepository.findByVerticleHeadId(verticleHeadId).equals(null)){
      //   throw new ResourceNotFoundException("Not found with head ID  : " + verticleHeadId);
      // }
      List<TaskTracker> trackers = (List<TaskTracker>)query.getResultList();
      manager.close();
        // SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd");  
      // formatter6.; 
     return new ResponseEntity<>(trackers, HttpStatus.OK);


    }

    @GetMapping("/currentdate")
    @PreAuthorize("hasRole('MANAGER') or hasRole('EMPLOYEE')")
    public ResponseEntity<List<TaskTracker>> getTaskBycurrentDate() throws ParseException{
      // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
      DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy"); 
      //date = Calendar.getInstance().getTime();  
     
      //dateFormat.format(date); 
      EntityManager manager = managerFactory.createEntityManager();
      Query query = manager.createQuery("select " +"e.userName, "+ "t.taskDetails from User e, " + "TaskTracker t where e.id = t.user "  + "and to_char(t.date, 'dd-Mon-yy') = to_char(sysdate, 'dd-Mon-yy')");
      // if(employeeRepository.findByVerticleHeadId(verticleHeadId).equals(null)){
      //   throw new ResourceNotFoundException("Not found with head ID  : " + verticleHeadId);
      // }
      List<TaskTracker> trackers = (List<TaskTracker>)query.getResultList();
      manager.close();
        // SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd");  
      // formatter6.; 
     return new ResponseEntity<>(trackers, HttpStatus.OK);


    }
    
}
