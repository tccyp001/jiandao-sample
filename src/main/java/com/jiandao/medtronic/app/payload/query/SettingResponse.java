package com.jiandao.medtronic.app.payload.query;

import com.jiandao.medtronic.app.payload.MessageInterface;

import java.util.List;

public class SettingResponse implements MessageInterface {
  private List<String> schools;

  private List<String> majors;

  private List<String> degrees;

  public List<String> getSchools() {
    return schools;
  }

  public void setSchools(List<String> schools) {
    this.schools = schools;
  }

  public List<String> getMajors() {
    return majors;
  }

  public void setMajors(List<String> majors) {
    this.majors = majors;
  }

  public List<String> getDegrees() {
    return degrees;
  }

  public void setDegrees(List<String> degrees) {
    this.degrees = degrees;
  }
}
