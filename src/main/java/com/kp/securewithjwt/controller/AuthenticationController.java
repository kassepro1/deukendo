package com.kp.securewithjwt.controller;

import com.kp.securewithjwt.Model.AuthenticationRequest;
import com.kp.securewithjwt.Model.AuthenticationResponse;
import com.kp.securewithjwt.Model.RoleApp;
import com.kp.securewithjwt.Model.UserApp;
import com.kp.securewithjwt.config.JwtUtil;
import com.kp.securewithjwt.services.MyUserDetailsService;
import com.kp.securewithjwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    UserService userService;

    public static List<RoleApp> roleApps = new ArrayList<>();
    private int id =0;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
      try {
          authenticationManager.
                  authenticate
                          (
                                  new UsernamePasswordAuthenticationToken
                                          (authenticationRequest.getUsername(),authenticationRequest.getPassword()));
      }catch (BadCredentialsException ex){
           throw new Exception("bad username or password "+ex);
      }
     final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
      final String jwt = jwtUtil.generateToken(userDetails);
      return new ResponseEntity<>(new AuthenticationResponse(jwt),HttpStatus.CREATED);
    }

    @PostMapping("/roles")
    public RoleApp createRole(@RequestBody RoleApp roleApp){
        roleApp.setId((long) id);
        id++;
        roleApps.add(roleApp);
        return roleApp;
    }
    @GetMapping("/roles")
    public List<RoleApp> getAllRole(){
        return roleApps;
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserApp userApp){
        UserApp user = userService.createUser(userApp);
        return  new ResponseEntity<>("UserApp is created successfully", HttpStatus.CREATED);

    }
}
