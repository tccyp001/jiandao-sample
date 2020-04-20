package com.jiandao.medtronic.app.payload.user;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jiandao.medtronic.app.model.User;
import com.jiandao.medtronic.app.payload.MessageInterface;
import org.springframework.beans.BeanUtils;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties("password")
public class UserProfile extends User implements MessageInterface {

  public static UserProfile create(User user){
    UserProfile userProfile = new UserProfile();

    BeanUtils.copyProperties(user, userProfile, User.class);

    return userProfile;
  }

  @JsonGetter("email")
  public String getMyEmail(){
    return this.getEmail();
  }

}
