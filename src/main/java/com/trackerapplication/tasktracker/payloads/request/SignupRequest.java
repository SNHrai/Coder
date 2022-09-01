package com.trackerapplication.tasktracker.payloads.request;
import java.util.Set;

import javax.validation.constraints.*;


public class SignupRequest {
    private Long id;


    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
  
  
    private Long mobileNumber;



    private Set<String> role;
  
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
  
    public String getUsername() {
      return username;
    }
  
    public void setUsername(String username) {
      this.username = username;
    }

  
    public String getPassword() {
      return password;
    }
  
    public void setPassword(String password) {
      this.password = password;
    }
  
    public Set<String> getRole() {
      return this.role;
    }
  
    public void setRole(Set<String> role) {
      this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

}
