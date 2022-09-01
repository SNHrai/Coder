package com.trackerapplication.tasktracker.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
    @Id
@Column(name = "ID")
@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Integer id;
  
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
  
    public Role() {
  
    }
  
    public Role(ERole name) {
      this.name = name;
    }
  
    public Integer getId() {
      return id;
    }
  
    public void setId(Integer id) {
      this.id = id;
    }
  
    public ERole getName() {
      return name;
    }
  
    public void setName(ERole name) {
      this.name = name;
    }
}
