package com.junior.expensemanager.validator;

import com.junior.expensemanager.dto.UserDTO;
import com.junior.expensemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        System.out.println("Validator");
        if (userDTO.getName() == null || userDTO.getName().equals("")) {
            errors.rejectValue("name", "msg.empty");
        }

        if (userDTO.getEmail() != null && !userDTO.getEmail().equals("")) {
            List<UserDTO> userDTOList = userService.findByEmail(userDTO.getEmail());
            if (!userDTOList.isEmpty()) {
                errors.rejectValue("email", "msg.exist");
            } else {
                if(!validateEmail(userDTO.getEmail())) {
                    errors.rejectValue("email", "msg.invalid");
                }
            }
        } else {
            errors.rejectValue("email", "msg.empty");
        }

        if (userDTO.getPassword() != null && !userDTO.getPassword().equals("")) {
            if (userDTO.getPassword().length() < 5) {
                errors.rejectValue("password", "msg.error.password");
            }
        } else {
            errors.rejectValue("password", "msg.empty");
        }


        if (userDTO.getConfirmPassword() != null && !userDTO.getConfirmPassword().equals("")) {
            if (!userDTO.getConfirmPassword().equals(userDTO.getPassword())) {
                errors.reject("confirmPassword", "msg.error.confirm");
            }
        } else {
            errors.rejectValue("confirmPassword", "msg.empty");
        }
    }
}
