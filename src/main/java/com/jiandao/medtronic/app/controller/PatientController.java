package com.jiandao.medtronic.app.controller;

import com.jiandao.medtronic.app.annotation.security.PublicController;
import com.jiandao.medtronic.app.model.PatientBasicInfo;
import com.jiandao.medtronic.app.payload.JiandaoMedResponse;
import com.jiandao.medtronic.app.payload.patient.PatientInfoResponse;
import com.jiandao.medtronic.app.repository.PatientInfoRepository;
import com.jiandao.medtronic.app.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;

@PublicController("/patient")
public class PatientController {

    @Autowired
    PatientInfoRepository patientInfoRepository;

    @GetMapping("getBasicInfo")
    public JiandaoMedResponse getBasicInfo(@NotBlank @RequestParam("email") String email){
        PatientBasicInfo patientBasicInfo = patientInfoRepository.findByEmail(email);
        return new JiandaoMedResponse(true, ErrorCode.SUCCESS, new PatientInfoResponse(patientBasicInfo));
    }
}
