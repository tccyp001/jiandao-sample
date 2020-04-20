package com.jiandao.medtronic.app.payload.patient;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jiandao.medtronic.app.model.PatientBasicInfo;
import com.jiandao.medtronic.app.payload.MessageInterface;

import static com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;

@JsonNaming(SnakeCaseStrategy.class)
public class PatientInfoResponse implements MessageInterface {
    private String name;
    private String email;

    public PatientInfoResponse(PatientBasicInfo patientBasicInfo) {
        this.name = patientBasicInfo.getName();
        this.email = patientBasicInfo.getEmail();
        // or you can use following to copy all field if there are too many fields
        // JiandaoBeanUtils.copyPropertiesIgnoreNull(patientBasicInfo, this);
    }
}
