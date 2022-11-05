package com.junior.expensemanager.service;

import com.junior.expensemanager.dto.UserDTO;
import com.junior.expensemanager.entity.User;
import com.junior.expensemanager.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelmapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    private User mapToEntity(UserDTO userDTO) {
        return modelmapper.map(userDTO, User.class);
    }

    private UserDTO mapToDTO(User user) {
        return modelmapper.map(user, UserDTO.class);
    }

    public void save(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(mapToEntity(userDTO));
    }

    public List<UserDTO> findByEmail(String email) {
        List<User> userList = userRepository.findAllByEmail(email);
        List<UserDTO> userDTOs = new ArrayList<>();
        for(User user : userList) {
            userDTOs.add(mapToDTO(user));
        }
        return userDTOs;
    }

    public User getLoggedInUser() {
        User user = new User();
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = auth.getName();
        List<User> userList =  userRepository.findAllByEmail(loggedInUserEmail);
        if(!userList.isEmpty()) {
            user =  userList.get(0);
        }
        return user;
    }

}
