package com.jiandao.medtronic.app.payload.auth;

import com.jiandao.medtronic.app.payload.MessageInterface;
import com.jiandao.medtronic.app.payload.user.UserProfile;

public class SignInResponse implements MessageInterface {
  private UserProfile user;

  private String jwtToken;

  public SignInResponse(UserProfile user, String jwtToken){
    this.user = user;
    this.jwtToken = jwtToken;
  }


  public UserProfile getUser() {
    return user;
  }

  public void setUser(UserProfile user) {
    this.user = user;
  }

  public String getJwtToken() {
    return jwtToken;
  }

  public void setJwtToken(String jwtToken) {
    this.jwtToken = jwtToken;
  }
}
