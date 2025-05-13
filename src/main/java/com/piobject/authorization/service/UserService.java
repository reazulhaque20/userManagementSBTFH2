package com.piobject.authorization.service;

import com.piobject.authorization.model.RoleInfo;
import com.piobject.authorization.model.UserInfo;
import com.piobject.authorization.repository.RoleInfoRepository;
import com.piobject.authorization.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserInfoRepository userInfoRepository;
    private final RoleInfoRepository roleInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveUser(String username, String password) {
        UserInfo user = new UserInfo();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password));
        Optional<RoleInfo> optionalRoleInfo = roleInfoRepository.findByRoleName("USER");
        RoleInfo roleInfo = null;
        if(optionalRoleInfo.isPresent()){
            roleInfo = optionalRoleInfo.get();
        }
        if (roleInfo == null) {
            roleInfo = RoleInfo.builder().roleId(0L).roleName("USER").roleDescription("user role").isActive(true).build();
            roleInfoRepository.save(roleInfo);
        }
        Set<RoleInfo> roles = new HashSet<>();
        roles.add(roleInfo);
        user.setRoleInfo(roles);
        userInfoRepository.save(user);
    }
}
