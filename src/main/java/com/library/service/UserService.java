package com.library.service;

import com.library.dao.entity.User;
import com.library.dao.repository.UserRepository;
import com.library.dto.UserDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setRoles(userDTO.getRoles());
        user.setPassword(userDTO.getPassword());

        return userRepository.save(user);
    }
}
