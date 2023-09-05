package ru.fedotov.endpoint.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.fedotov.jpa.users.entity.User;
import ru.fedotov.jpa.users.repository.UserRepository;
import ru.fedotov.jpa.users.status.Status;

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
