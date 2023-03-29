package com.bezkoder.spring.jdbc.model;

public class TutorialDto {

  private long id;
  private String titleOfTut;
  private String desc;
  private boolean published;

  public TutorialDto() {

  }

  public TutorialDto(long id, String titleOfTut, String desc, boolean published) {
    this.id = id;
    this.titleOfTut = titleOfTut;
    this.desc = desc;
    this.published = published;
  }

  public TutorialDto(String titleOfTut, String desc, boolean published) {
    this.titleOfTut = titleOfTut;
    this.desc = desc;
    this.published = published;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public long getId() {
    return id;
  }

  public String gettitleOfTut() {
    return titleOfTut;
  }

  public void settitleOfTut(String titleOfTut) {
    this.titleOfTut = titleOfTut;
  }

  public String getdesc() {
    return desc;
  }

  public void setdesc(String desc) {
    this.desc = desc;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean isPublished) {
    this.published = isPublished;
  }

  @Override
  public String toString() {
    return "Tutorial [id=" + id + ", titleOfTut=" + titleOfTut + ", desc=" + desc + ", published=" + published + "]";
  }

}
