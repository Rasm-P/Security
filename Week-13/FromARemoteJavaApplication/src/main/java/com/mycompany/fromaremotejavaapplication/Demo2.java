/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fromaremotejavaapplication;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rasmu
 */
@Entity
@Table(name = "Demo2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Demo2.findAll", query = "SELECT d FROM Demo2 d"),
    @NamedQuery(name = "Demo2.findByIdDemo2", query = "SELECT d FROM Demo2 d WHERE d.idDemo2 = :idDemo2"),
    @NamedQuery(name = "Demo2.findByDemo2col", query = "SELECT d FROM Demo2 d WHERE d.demo2col = :demo2col")})
public class Demo2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idDemo2")
    private Integer idDemo2;
    @Basic(optional = false)
    @Column(name = "Demo2col")
    private String demo2col;

    public Demo2() {
    }

    public Demo2(Integer idDemo2) {
        this.idDemo2 = idDemo2;
    }

    public Demo2(Integer idDemo2, String demo2col) {
        this.idDemo2 = idDemo2;
        this.demo2col = demo2col;
    }

    public Integer getIdDemo2() {
        return idDemo2;
    }

    public void setIdDemo2(Integer idDemo2) {
        this.idDemo2 = idDemo2;
    }

    public String getDemo2col() {
        return demo2col;
    }

    public void setDemo2col(String demo2col) {
        this.demo2col = demo2col;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDemo2 != null ? idDemo2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Demo2)) {
            return false;
        }
        Demo2 other = (Demo2) object;
        if ((this.idDemo2 == null && other.idDemo2 != null) || (this.idDemo2 != null && !this.idDemo2.equals(other.idDemo2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.fromaremotejavaapplication.Demo2[ idDemo2=" + idDemo2 + " ]";
    }
    
}
