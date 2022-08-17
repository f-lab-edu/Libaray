package flab.library.user.service;

import flab.library.user.domain.entity.LibUser;
import flab.library.user.repository.LibUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final LibUserRepository libUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String usernameTrim = username.trim();

        return libUserRepository.findById(usernameTrim)
                .map(user -> createUser(usernameTrim, user))
                .orElseThrow(() -> {
                    log.info(usernameTrim + " -> 없는 아이디 입니다.");
                    throw new UsernameNotFoundException(usernameTrim + " -> 없는 아이디 입니다.");
                });
    }

    private User createUser(String username, LibUser user) throws UsernameNotFoundException{
        if (!user.isActive()) {
            log.info(username + " -> 사용하지 않는 아이디입니다.");
            throw new UsernameNotFoundException(username + " -> 사용하지 않는 아이디입니다.");
        }

        //TODO: give permission here
        List<String> totalPermissions = new ArrayList<>();

        List<GrantedAuthority> grantedAuthorities = totalPermissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(user.getId(),
                user.getPassword(),
                grantedAuthorities);
    }
}
