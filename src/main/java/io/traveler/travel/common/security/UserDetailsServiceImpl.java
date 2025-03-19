package io.traveler.travel.common.security;

import io.traveler.travel.user.entity.User;
import io.traveler.travel.user.repository.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            User user = userRepository.findByEmail(email).orElseThrow();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                //TODO: 원래 권한을 애플리케이션 로직으로 구현했었는데 어떻게 해야할지 고민해봐야할듯
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                );
    }
}
