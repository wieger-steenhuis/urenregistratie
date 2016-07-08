package com.sx.service;

import com.sx.models.User_roles;
import com.sx.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by udr013 on 8-7-2016.
 */
@Service
public class UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;
    public User_roles save(User_roles userrole){
        return userRoleRepository.save(userrole);
    }
}
