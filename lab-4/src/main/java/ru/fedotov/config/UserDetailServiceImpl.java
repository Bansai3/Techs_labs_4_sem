package ru.fedotov.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.fedotov.dao.dao.UserRepository;
import ru.fedotov.dao.entity.User;
import ru.fedotov.dao.status.Status;

@Service("UserDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null)
            throw new UsernameNotFoundException("User does not exist!");
        if (user.getStatus() == Status.BANNED)
            throw new IllegalStateException("User is banned!");
        return SecurityUser.fromUser(user);
    }
}
