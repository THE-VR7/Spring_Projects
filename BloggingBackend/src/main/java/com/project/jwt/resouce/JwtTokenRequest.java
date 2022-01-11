package com.project.jwt.resouce;

import java.io.Serializable;

public class  JwtTokenRequest implements Serializable {
  
  private static final long serialVersionUID = -5616176897013108345L;

  private String username;
    private String password;

    public JwtTokenRequest() {
        super();
    }

    public JwtTokenRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


//{
//    "token": 
//"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpbjI4bWludXRlcyIsImV4cCI6MTYzNDY2MTA4MSwiaWF0IjoxNjM0MDU2MjgxfQ.exfTAtb1RkqJQcl7HkqCLn4Dd09icPehLKrNlniTmm5R7sK09wwXgTjTgy-ydPvYsLKlPc2fRuOTGW05o0vNGw"
//}


