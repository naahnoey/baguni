package com.baguni.baguni.security.services;

import com.baguni.baguni.domain.user.BasicUser;
import com.baguni.baguni.domain.user.BasicUserRepository;
import com.baguni.baguni.domain.user.WelfareUserRepository;
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

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (basicUserRepository.existsByUsername(username)) {
            BasicUser user = basicUserRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

            return UserDetailsImpl.build(user);
        } else { // 추후 welfare 유저 추가
            BasicUser user = basicUserRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

            return UserDetailsImpl.build(user);
        }
    }
}
