package com.jiandao.medtronic.app.controller;

import com.jiandao.medtronic.app.annotation.security.LocalController;
import com.jiandao.medtronic.app.annotation.security.PublicController;
import com.jiandao.medtronic.app.payload.JiandaoMedResponse;
import com.jiandao.medtronic.app.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

@LocalController("/heartbeat")
public class HeartBeatController {

  @Value("${environment}")
  String environment;

  @GetMapping("")
  public JiandaoMedResponse heartbeat(){
    return ResponseUtil.stringResponse(String.format(" Server OK - %s", environment));
  }

}
