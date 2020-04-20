package com.jiandao.medtronic.app.controller;

import com.jiandao.medtronic.app.annotation.security.PublicController;
import com.jiandao.medtronic.app.model.User;
import com.jiandao.medtronic.app.payload.JiandaoMedResponse;
import com.jiandao.medtronic.app.payload.auth.SignInRequest;
import com.jiandao.medtronic.app.payload.auth.SignInResponse;
import com.jiandao.medtronic.app.payload.user.UserProfile;
import com.jiandao.medtronic.app.repository.UserRepository;
import com.jiandao.medtronic.app.security.JwtAuthenticationService;
import com.jiandao.medtronic.app.util.ErrorCode;
import com.jiandao.medtronic.app.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

@PublicController("/auth")
public class AuthController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtAuthenticationService authenticationService;

  @Value(("${environment}"))
  private String env;


  @PostMapping("/signin")
  public JiandaoMedResponse signin(@Valid @RequestBody SignInRequest request){
    Optional<User> optionalUser
            = userRepository.findByEmail(request.getEmail());

    if (!optionalUser.isPresent()){
      return ResponseUtil.getResponseByErrorCode(ErrorCode.JIANDAO_SIGN_IN_FAILED);
    }

    if (!passwordEncoder.matches(request.getPassword(), optionalUser.get().getPassword())){
      return ResponseUtil.getResponseByErrorCode(ErrorCode.JIANDAO_SIGN_IN_FAILED);
    }

    UserProfile userProfile = UserProfile.create(optionalUser.get());
    SignInResponse response = new SignInResponse(
            userProfile,
            authenticationService.generateJwtToken(userProfile.getId()
            )
    );

    return new JiandaoMedResponse(true, ErrorCode.SUCCESS, response);
  }

}
