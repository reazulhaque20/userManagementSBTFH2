package com.piobject.authorization.service;

import com.piobject.authorization.model.RoleInfo;
import com.piobject.authorization.model.UserInfo;
import com.piobject.authorization.repository.RoleInfoRepository;
import com.piobject.authorization.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserInfoRepository userInfoRepository;
    private final RoleInfoRepository roleInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserInfoRepository userInfoRepository, RoleInfoRepository roleInfoRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.roleInfoRepository = roleInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void saveUser(String username, String password, String email) {
        UserInfo user = new UserInfo();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setFullName(username);
        user.setActive(true);
        Optional<RoleInfo> optionalRoleInfo = roleInfoRepository.findByRoleName("USER");
        RoleInfo roleInfo = null;
        if(optionalRoleInfo.isPresent()){
            roleInfo = optionalRoleInfo.get();
        }
        if (roleInfo == null) {
            RoleInfo rI = new RoleInfo();
            rI.setRoleName("USER");
            rI.setRoleDescription("User Role");
            rI.setActive(true);
//            roleInfo = RoleInfo.builder().roleId(0L).roleName("USER").roleDescription("user role").isActive(true).build();
            roleInfoRepository.save(rI);
        }
        Set<RoleInfo> roles = new HashSet<>();
        roles.add(roleInfo);
        user.setRoleInfo(roles);
        userInfoRepository.save(user);
    }
}
