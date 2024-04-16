package com.baguni.baguni.security.services;

import com.baguni.baguni.domain.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    BasicUserRepository basicUserRepository;

    @Autowired
    WelfareUserRepository welfareUserRepository;

    @Autowired
    AdminRepository adminRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (welfareUserRepository.existsByUsername(username)) {
            WelfareUser user = welfareUserRepository.findByUsername(username).get();
            return UserDetailsImpl.build(user);
        } else if (basicUserRepository.existsByUsername(username)) {
            BasicUser user = basicUserRepository.findByUsername(username).get();
            return UserDetailsImpl.build(user);
        } else {
            Admin admin = adminRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

            return UserDetailsImpl.build(admin);
        }
    }
}
