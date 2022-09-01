package com.trackerapplication.tasktracker.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "EMP_MASTER")
public class User {
    @Id
    @Column(name = "EMP_ID")
    private Long id;
  
    @Column(name = "EMP_NAME")
    private String username;

    @Column(name = "EMP_MOBILE")
    private Long mobileNumber;

    @Column(name = "EMP_VERTICLE_HEAD_ID")
    private Long verticleHeadId;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles", 
          joinColumns = @JoinColumn(name="user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(targetEntity = TaskTracker.class, mappedBy = "id", orphanRemoval = false, fetch = FetchType.LAZY)
    private Collection<TaskTracker> taskTrackers = new ArrayList<TaskTracker>();

    public User(String username) {
        this.username = username;
    }


    public User(long id, String username) {
        this.id = id;
        this.username = username;
    }


    public User(Long id, String username, Long mobileNumber, String password) {
        this.id = id;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.password = password;
    }


   


   

    
}
