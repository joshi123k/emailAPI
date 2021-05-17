package com.email.controller;

import com.email.entity.Email;
import com.email.entity.EmailResponse;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

  @GetMapping("/welcome")
    public String welcome()
  {
      return "Welcome to this site.";
  }

  @PostMapping("/sendEmail")
  public ResponseEntity<?> sendEmail(@RequestBody Email email)
  {
      boolean result=this.emailService.sendEmail(email.getMessage(),email.getSubject(),email.getTo());

      if (result)
      {
          return ResponseEntity.ok(new EmailResponse("Email Send Successfully...."));
      }else
      {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not send"));
      }




  }

}
