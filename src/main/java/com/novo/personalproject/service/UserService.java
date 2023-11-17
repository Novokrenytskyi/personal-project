package com.novo.personalproject.service;

import com.novo.personalproject.dao.UserMapper;
import com.novo.personalproject.dao.UserRepository;
import com.novo.personalproject.dto.UserCreateEditDto;
import com.novo.personalproject.dto.UserReadDto;
import com.novo.personalproject.mapper.UserCreateEditMapper;
import com.novo.personalproject.mapper.UserReadMapper;
import com.novo.personalproject.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserReadMapper userReadMapper;

    @Autowired
    private final UserCreateEditMapper userCreateEditMapper;

    public List<UserReadDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findUserById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    public UserReadDto saveUser(UserCreateEditDto userCreateEditDto) {

        return Optional.of(userCreateEditDto).map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    public Optional<UserReadDto> updateUser(Long id, UserCreateEditDto userCreateEditDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userCreateEditDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }


    public boolean deleteUser(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                }).orElse(false);
    }
}
