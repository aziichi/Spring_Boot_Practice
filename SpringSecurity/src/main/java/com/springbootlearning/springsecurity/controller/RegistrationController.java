package com.springbootlearning.springsecurity.controller;

import com.springbootlearning.springsecurity.entity.User;
import com.springbootlearning.springsecurity.entity.VerificationToken;
import com.springbootlearning.springsecurity.event.RegistrationCompleteEvent;
import com.springbootlearning.springsecurity.model.PasswordModel;
import com.springbootlearning.springsecurity.model.UserModel;
import com.springbootlearning.springsecurity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;

    public RegistrationController(UserService userService, ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.publisher = publisher;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserModel userModel,
                                             final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(
                new RegistrationCompleteEvent(
                        user,
                        applicationUrl(request)
                ));
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")){
            return "User Verified Successfully";
        }
        else{
            return "Bad User";
        }
    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken,
                                          HttpServletRequest request)
    {
        VerificationToken verificationToken = userService.generateVerificationToken(oldToken);

        User user = verificationToken.getUser();

        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return "Verification Link Sent";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel,
                                final HttpServletRequest request){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        String url = "";
        if(user!=null){
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            url = passwordResetTokenMail(user, applicationUrl(request), token);
        }

        return url;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token,
                               @RequestBody PasswordModel passwordModel)
    {
        String result = userService.validatePasswordResetToken(token);

        if(!result.equalsIgnoreCase("valid")){
            return "Invalid Token";
        }

        Optional<User> user = userService.getUserByPasswordByPasswordResetToken(token);

        if(user.isPresent()){
            userService.changePassword(user.get(), passwordModel.getNewPassword());
            return "Password Reset Successful";
        }
        else{
            return "Invalid Token";
        }
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        if(!userService.checkIfValidOldPassword(user, passwordModel.getOldPassword())){
            return "Invalid Old Password";
        }

        // Save new password
        userService.changePassword(user, passwordModel.getNewPassword());
        return "Password changed successfully";
    }

    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url = applicationUrl + "/savePassword?token="+token;
        log.info("Click the link below to reset your password {}", url);
        return url;
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
        String url = applicationUrl + "/verifyRegistration?token="+verificationToken.getToken();
        log.info("Click the link to verify your account {}", url);
    }

    public String applicationUrl(HttpServletRequest request){
        return "http://" +
                request.getServerName() +
                ":" + request.getServerPort() +
                request.getContextPath();
    }

}
