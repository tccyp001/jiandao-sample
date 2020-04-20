package com.jiandao.medtronic.app.controller;

import com.jiandao.medtronic.app.annotation.security.AuthenticatedController;
import com.jiandao.medtronic.app.annotation.security.CurrentUser;
import com.jiandao.medtronic.app.payload.JiandaoMedResponse;
import com.jiandao.medtronic.app.payload.user.UserProfile;
import com.jiandao.medtronic.app.repository.UserRepository;
import com.jiandao.medtronic.app.security.UserPrincipal;
import com.jiandao.medtronic.app.util.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;

@AuthenticatedController("/user")
public class UserController {

  private UserRepository userRepository;

  @GetMapping("")
  public JiandaoMedResponse me(@CurrentUser UserPrincipal userPrincipal){
    return new JiandaoMedResponse(true, ErrorCode.SUCCESS, UserProfile.create(userPrincipal));
  }

  @GetMapping("getEmail") // for sample purpose
  public JiandaoMedResponse getEmail(@CurrentUser UserPrincipal userPrincipal){
    return new JiandaoMedResponse(true, ErrorCode.SUCCESS, UserProfile.create(userPrincipal));
  }

}
