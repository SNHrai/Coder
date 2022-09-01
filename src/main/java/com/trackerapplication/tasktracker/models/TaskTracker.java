package com.trackerapplication.tasktracker.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "TASK_TRACKER")
public class TaskTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TASK_NUMBER",nullable = false, updatable = false)
	private Long id;

    // @Temporal(TemporalType.TIMESTAMP)
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    // @Builder.Default
    // @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:mm:ss")
    // @Temporal(TemporalType.DATE)
    @Column(name = "DATE_TIME")
    private Date date;

    @Column(name = "TASK_DETAILS", length = 5000)
    private String taskDetails;
  
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employeeid", nullable = false)
    @JsonIgnore
    private User user;



    




    public TaskTracker() {
    }








    public TaskTracker(String taskDetails, User user) {
        this.taskDetails = taskDetails;
        this.user = user;
    } 
}
