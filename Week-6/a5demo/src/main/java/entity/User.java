/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 45)
  @Column(name = "user_email")
  private String userEmail;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "user_pass")
  private String userPass;
  @Size(max = 255)
  @Column(name = "users_data")
  private String usersData;
  @JoinTable(name = "user_roles", joinColumns = {
    @JoinColumn(name = "user_email", referencedColumnName = "user_email")}, inverseJoinColumns = {
    @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
  @ManyToMany
  private Collection<Role> rolesCollection;

  public User() {
  }

  public User(String userEmail) {
    this.userEmail = userEmail;
  }

  public User(String userEmail, String userPass) {
    this.userEmail = userEmail;
    this.userPass = userPass;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getUserPass() {
    return userPass;
  }

  public void setUserPass(String userPass) {
    this.userPass = userPass;
  }

  public String getUsersData() {
    return usersData;
  }

  public void setUsersData(String usersData) {
    this.usersData = usersData;
  }

  public Collection<Role> getRolesCollection() {
    return rolesCollection;
  }

  public void setRolesCollection(Collection<Role> rolesCollection) {
    this.rolesCollection = rolesCollection;
  }
public void addRole(Role userRole) {
    this.rolesCollection.add(userRole);
  }
  
//TODO Change when password is hashed
   public boolean verifyPassword(String pw){
        return(pw.equals(userPass));
    }
  
}
