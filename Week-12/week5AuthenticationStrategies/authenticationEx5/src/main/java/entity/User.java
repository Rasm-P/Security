/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Plaul
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByUserEmail", query = "SELECT u FROM User u WHERE u.userEmail = :userEmail")
    , @NamedQuery(name = "User.findByUserPass", query = "SELECT u FROM User u WHERE u.userPass = :userPass")
    , @NamedQuery(name = "User.findByUsersData", query = "SELECT u FROM User u WHERE u.usersData = :usersData")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
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
    private List<Role> roleList;

    public User() {
    }

    public User(String userEmail) {
        this.userEmail = userEmail;
    }

    public User(String userEmail, String userPass) {
        this.userEmail = userEmail;
        this.userPass = userPass;
    }

    //UserName is email
    public String getUserName() {
        return userEmail;
    }

    public void setUserName(String userEmail) {
        this.userEmail = userEmail;
    }
    
     //TODO Change when password is hashed/salted
    public boolean verifyPassword(String pw){
        return(pw.equals(userPass));
    }
    
    public List<String> getRolesAsStrings() {
        if (roleList.isEmpty()) {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList();
        for(Role role : roleList){
            rolesAsStrings.add(role.getRoleName());
        }
        return rolesAsStrings;
    }


//    public String getUserPass() {
//        return userPass;
//    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUsersData() {
        return usersData;
    }

    public void setUsersData(String usersData) {
        this.usersData = usersData;
    }

    @XmlTransient
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userEmail != null ? userEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userEmail == null && other.userEmail != null) || (this.userEmail != null && !this.userEmail.equals(other.userEmail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.User[ userEmail=" + userEmail + " ]";
    }
    
}
