package com.jiandao.medtronic.app.controller;

import com.jiandao.medtronic.app.annotation.security.PublicController;
import com.jiandao.medtronic.app.payload.JiandaoMedResponse;
import com.jiandao.medtronic.app.repository.UserRepository;
import com.jiandao.medtronic.app.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;

@PublicController("/query")
public class QueryController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("patient")
  public JiandaoMedResponse getMentor(@NotBlank @RequestParam("id") String alias){
    return ResponseUtil.stringResponse("not done yet");
  }
}
