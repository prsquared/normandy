package org.prsquared.normandy.model;

import org.prsquared.normandy.enums.Tendencies;
import org.prsquared.normandy.enums.Position;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Player {
 private String firstName;
 private String surname;
 private Integer overall;
 private Position position;
 private List<Tendencies> styles;

 public Player(String firstName, String surname, Integer overall, Position position, List<Tendencies> styles) {
  this.firstName = firstName;
  this.surname = surname;
  this.overall = overall;
  this.position = position;
  this.styles = styles;
 }
 public Player(){

 }

 public String getFirstName() {
  return firstName;
 }

 public void setFirstName(String firstName) {
  this.firstName = firstName;
 }

 public String getSurname() {
  return surname;
 }

 public void setSurname(String surname) {
  this.surname = surname;
 }

 public Integer getOverall() {
  return overall;
 }

 public void setOverall(Integer overall) {
  this.overall = overall;
 }

 public Position getPosition() {
  return position;
 }

 public void setPosition(Position position) {
  this.position = position;
 }

 public List<Tendencies> getStyles() {
  return styles;
 }

 public void setStyles(List<Tendencies> styles) {
  this.styles = styles;
 }
}
