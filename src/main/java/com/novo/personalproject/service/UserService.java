package com.novo.personalproject.service;

import com.novo.personalproject.dao.ShoppingCartRepository;
import com.novo.personalproject.dao.UserRepository;
import com.novo.personalproject.dto.UserCreateEditDto;
import com.novo.personalproject.dto.UserEditDto;
import com.novo.personalproject.dto.UserReadDto;
import com.novo.personalproject.error.EmailAlreadyExistsException;
import com.novo.personalproject.mapper.UserCreateEditMapper;
import com.novo.personalproject.mapper.UserEditDtoMapper;
import com.novo.personalproject.mapper.UserReadMapper;
import com.novo.personalproject.model.entity.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserReadMapper userReadMapper;

    @Autowired
    private final UserCreateEditMapper userCreateEditMapper;

    @Autowired
    private final UserEditDtoMapper userEditDtoMapper;

    @Autowired
    private final ShoppingCartRepository shoppingCartRepository;

    public List<UserReadDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findUserById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }


    @Transactional
    public  Optional<UserReadDto> saveUser(UserCreateEditDto userCreateEditDto) throws EmailAlreadyExistsException {
        if(userRepository.findByEmail(userCreateEditDto.getEmail()).isPresent()) {
            String message = String.format("Email %s already exists", userCreateEditDto.getEmail());
            throw new EmailAlreadyExistsException(message);
        }

        ShoppingCart newShoppingCart = new ShoppingCart();
        com.novo.personalproject.model.entity.User newUser = userCreateEditMapper.map(userCreateEditDto);

        newUser.setShoppingCart(newShoppingCart);
        newShoppingCart.setUser(newUser);

        return Optional.ofNullable(newUser)
                .map(userRepository::save)
                .map(userReadMapper::map);
    }

    @Transactional
    public Optional<UserReadDto> updateUser(Long id, UserCreateEditDto userCreateEditDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userCreateEditDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public boolean deleteUser(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                }).orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(user -> new User(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user:" + username));
    }

    public Optional<UserReadDto> findUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(userReadMapper::map);

    }

    @Transactional
    public Optional<UserReadDto> updateProfile(Long id, UserEditDto userEditDto) {
        return userRepository.findById(id)
                .map(entity -> userEditDtoMapper.map(userEditDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }
}
