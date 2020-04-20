package com.jiandao.medtronic.app.util;

import com.jiandao.medtronic.app.payload.JiandaoMedResponse;
import com.jiandao.medtronic.app.payload.StringMessage;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
  public static Map<ErrorCode, String> errorCodeMsgMapping = new HashMap<>();

  static {
    errorCodeMsgMapping.put(ErrorCode.SUCCESS, "ok");
    errorCodeMsgMapping.put(ErrorCode.JIANDAO_SIGN_IN_FAILED, "Sign in Failed");
    errorCodeMsgMapping.put(ErrorCode.REGISTER_WRONG_TOKEN, "The verification token is not valid");
    errorCodeMsgMapping.put(ErrorCode.REGISTER_USER_EXIST, "The user with the same phone number exists");
    errorCodeMsgMapping.put(ErrorCode.ALIAS_EXIST, "The user with the same alias exists");
    errorCodeMsgMapping.put(ErrorCode.NO_SUCH_USER, "No Such User");
    errorCodeMsgMapping.put(ErrorCode.HTTP_ERROR, "External Source currently unavailable");
  }

  public static JiandaoMedResponse getResponseByErrorCode(ErrorCode code){
    return getResponseByErrorCode(code, "");
  }


  public static JiandaoMedResponse getResponseByErrorCode(ErrorCode code, String fieldName){
    String message = errorCodeMsgMapping.getOrDefault(code, "Server Error");

    if (message.contains("%s")){
      message = String.format(message, fieldName);
    }
    return new JiandaoMedResponse(
            code == ErrorCode.SUCCESS,
            code,
            new StringMessage(message));
  }

  public static JiandaoMedResponse stringResponse(String s){
    return new JiandaoMedResponse(
            true,
            ErrorCode.SUCCESS,
            new StringMessage(s)
    );
  }
}
