/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.soft.sqlinject;

import static dk.cphbusiness.soft.sqlinject.PlaceHolders.field;
import static dk.cphbusiness.soft.sqlinject.PlaceHolders.string;
import static dk.cphbusiness.soft.sqlinject.PlaceHolders.stringList;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AKA
 */
public class PlaceHoldersTest {

  @Test
  public void testLegalField() {
    String result = field("price", "brand", "price", "pickup");
    assertThat(result, is("price"));
    }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalField() {
    String result = field("price; drop table cars; --", "brand", "price", "pickup");
    }

  @Test
  public void testDefaultField() {
    String result = field(null, "brand", "price", "pickup");
    assertThat(result, is("brand"));
    }

  @Test
  public void testSimpleString() {
    String result = string("Kurt Hansen");
    assertThat(result, is("'Kurt Hansen'"));
    }
  
  @Test
  public void testComplexString() {
    String result = string("Sarah O'Hara");
    assertThat(result, is("'Sarah O\\'Hara'"));
    }
  
  @Test
  public void testEmptyStringList() {
    String result = stringList();
    assertThat(result, is("()"));
    }

  @Test
  public void testSimpleStringList() {
    String result = stringList("GPS", "Diesel", "AutoPilot");
    assertThat(result, is("('GPS', 'Diesel', 'AutoPilot')"));
    }

  @Test
  public void testEvilStringList() {
    String result = stringList("GPS", "Diesel'); drop table cars; --", "AutoPilot");
    assertThat(result, is("('GPS', 'Diesel\\'); drop table cars; --', 'AutoPilot')"));
    }

  }
