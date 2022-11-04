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
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == UserDTO.class;
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        if(userDTO.getName().equals("") || userDTO.getName() == null) {
            errors.rejectValue("name", "msg.empty");
        }

        if(!userDTO.getEmail().equals("") && userDTO.getEmail() != null) {
            if(!validateEmail(userDTO.getEmail())) {
                errors.rejectValue("email", "msg.invalid");
            } else {
                List<UserDTO> userDTOList = userService.findByEmail(userDTO.getEmail());
                if(!userDTOList.isEmpty()) {
                    errors.rejectValue("email", "msg.exist");
                }
            }


        } else {
            errors.rejectValue("email", "msg.empty");
        }

        if(!userDTO.getPassword().equals("") && userDTO.getPassword() != null) {
            if(userDTO.getPassword().length() < 6) {
                errors.rejectValue("password", "msg.error.password");
            }
        } else {
            errors.rejectValue("password", "msg.empty");
        }

        if(!userDTO.getConfirmPassword().equals(userDTO.getPassword())) {
            errors.rejectValue("confirmPassword", "msg.error.confirm");
        }
    }
}
