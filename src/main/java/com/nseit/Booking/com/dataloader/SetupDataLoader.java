package com.nseit.Booking.com.dataloader;

import com.nseit.Booking.com.model.BookUser;
import com.nseit.Booking.com.model.Role;
import com.nseit.Booking.com.repository.RoleRepository;
import com.nseit.Booking.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        Role adminRole = createRoleIfNotFound(Role.ADMIN);
        createRoleIfNotFound(Role.HOTEL);
        createRoleIfNotFound(Role.USER);

        BookUser user = createUserIfNotFound("admin", "admin", adminRole);

        alreadySetup = true;
    }

    private BookUser createUserIfNotFound(final String userName, final String password, Role adminRole) {
        BookUser bookUser = userRepository.findByUserName(userName);
        if (bookUser == null) {
            bookUser = new BookUser(userName, bCryptPasswordEncoder.encode(password));
            bookUser.setRoles(Set.of(adminRole));
            bookUser = userRepository.save(bookUser);
        }
        return bookUser;
    }
    @Transactional
    private Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role = roleRepository.save(role);
        }
        return role;
    }
}
