package com.jiandao.medtronic.app.payload;

import com.jiandao.medtronic.app.util.ErrorCode;

public class JiandaoMedResponse {

  private boolean success;

  private int errorCode;

  private MessageInterface data;

  public JiandaoMedResponse(boolean success, ErrorCode errorCode, MessageInterface data){
    this.data = data;
    this.setErrorCode(errorCode.ordinal());
    this.success = success;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public MessageInterface getData() {
    return data;
  }

  public void setData(MessageInterface data) {
    this.data = data;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }
}
