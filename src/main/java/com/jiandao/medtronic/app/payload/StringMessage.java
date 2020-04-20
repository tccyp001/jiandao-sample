package com.jiandao.medtronic.app.payload;

public class StringMessage implements MessageInterface {
  private String message;

  public StringMessage(String s){
    this.message = s;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
