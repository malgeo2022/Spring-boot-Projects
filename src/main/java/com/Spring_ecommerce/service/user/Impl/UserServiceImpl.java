package com.Spring_ecommerce.service.user.Impl;

import com.Spring_ecommerce.exception.NotFoundException;
import com.Spring_ecommerce.requests.UserDeleteRequest;
import com.Spring_ecommerce.dto.viewDto.UserViewDto;
import com.Spring_ecommerce.model.User;
import com.Spring_ecommerce.repository.UserRepository;
import com.Spring_ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User add(User userCreateDto) {
        this.userRepository.save(new User(userCreateDto.getUserName(),
                userCreateDto.getPassword(), userCreateDto.getEmail(), userCreateDto.getUserCreateDate(), userCreateDto.isNotificationPermission()));
        return userCreateDto;
    }

    @Override
    public List<User> getAll() {
       final List<User> users = this.userRepository.findAll();
       return users;
    }

    @Override
    public User getById(int id) {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User could not be found by the given id: " + id));
    }

    @Override
    public List<User> slice(Pageable pageable) {
        final List<User> users = this.userRepository.findAll(pageable).stream().collect(Collectors.toList());
        return users;
    }

    @Override
    public void deleteById(int id) {
        this.userRepository.deleteById(id);

    }

    @Override
    public List<UserViewDto> getUserViewDto() {
        final List<UserViewDto> users = this.userRepository.findAll().stream().map(UserViewDto::of).collect(Collectors.toList());
        return users;
    }

    @Override
    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void authDeleteByUser(UserDeleteRequest userDeleteRequest) {
        User user = userRepository.findByEmail(userDeleteRequest.getEmail());
        userRepository.deleteById(user.getId());
    }

    @Override
    public User findByEMail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void updateByUserName(int userId, String userName) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            user.get().setUserName(userName);
            userRepository.save(user.get());
        }
    }

    @Override
    public void updateByNotificationPermission(int userId, boolean permission) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()){
            user.get().setNotificationPermission(permission);
            userRepository.save(user.get());
        }
    }
}
