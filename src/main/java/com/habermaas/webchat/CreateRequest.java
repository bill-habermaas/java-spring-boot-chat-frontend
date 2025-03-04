package com.habermaas.webchat;

@lombok.Getter
@lombok.Setter
public class CreateRequest {
   private String username;
   private String password;
   private String password2;
   private String aliasname;
   private String phonenumber;
   private String emailaddress;
   private String role;
}
