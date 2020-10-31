package org.twino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.twino.model.Operator;
import org.twino.repository.OperatorRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private OperatorRepository operatorRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Operator user = operatorRepository.findByUsername(s);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username wasn't found");
        }

        return user;
    }
}
