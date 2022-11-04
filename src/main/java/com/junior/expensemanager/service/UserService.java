package com.junior.expensemanager.service;

import com.junior.expensemanager.dto.UserDTO;
import com.junior.expensemanager.entity.User;
import com.junior.expensemanager.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    private User mapToEntity(UserDTO userDTO) {
        return modelmapper.map(userDTO, User.class);
    }

    private UserDTO mapToDTO(User user) {
        return modelmapper.map(user, UserDTO.class);
    }

    public void save(UserDTO userDTO) {
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

}
