package com.kp.securewithjwt.services;

import com.kp.securewithjwt.Model.UserApp;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    List<UserApp> userList = new ArrayList<>();
   public UserApp createUser(UserApp user){
        userList.add(user);
        return user ;
    }

    @Override
    public int getBenefice(String type) {
       if(type.equalsIgnoreCase("fin")){
           return 100;
       }
        return 0;
    }
}
