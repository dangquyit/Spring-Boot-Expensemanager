package com.junior.expensemanager.service;

import com.junior.expensemanager.dto.UserDTO;
import com.junior.expensemanager.entity.User;
import com.junior.expensemanager.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    public void save(UserDTO userDTO) {
        User user = mapToEntity(userDTO);
        userRepository.save(user);
    }

    private User mapToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO mapToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    public List<UserDTO> findByEmail(String email) {
        List<User> users = userRepository.findByEmail(email);
        return users.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
