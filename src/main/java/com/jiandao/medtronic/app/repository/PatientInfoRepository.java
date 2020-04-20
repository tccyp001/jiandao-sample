package com.jiandao.medtronic.app.repository;

import com.jiandao.medtronic.app.model.PatientBasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientInfoRepository  extends JpaRepository<PatientBasicInfo, Long> {
    PatientBasicInfo findByEmail(String email);
}
