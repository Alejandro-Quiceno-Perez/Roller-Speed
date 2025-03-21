package com.rollerspeed.rollerspeed.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registro_aspirantes")

public class RegistroAspirantes {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       private String name;
       private String lastName;
       private String email;
       private String password;

       
       public Long getId() {
              return id;
       }
       public void setId(Long id) {
              this.id = id;
       }
       public String getName() {
              return name;
       }
       public void setName(String name) {
              this.name = name;
       }
       public String getLastName() {
              return lastName;
       }
       public void setLastName(String lastName) {
              this.lastName = lastName;
       }
       public String getEmail() {
              return email;
       }
       public void setEmail(String email) {
              this.email = email;
       }
       public String getPassword() {
              return password;
       }
       public void setPassword(String password) {
              this.password = password;
       }


       @Override
       public String toString() {
              return "RegistroAspirantes [id=" + id + ", name=" + name + ", lastName=" + lastName + ", email=" + email
                            + ", password=" + password + "]";
       }

       
}
