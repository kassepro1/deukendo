package com.kp.securewithjwt.services;

import com.kp.securewithjwt.Model.UserApp;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
   UserApp createUser(UserApp user);
   int getBenefice(String type);
}
