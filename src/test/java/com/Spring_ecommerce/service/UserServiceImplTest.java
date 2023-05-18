package com.Spring_ecommerce.service;

import com.Spring_ecommerce.model.User;
import com.Spring_ecommerce.repository.UserRepository;
import com.Spring_ecommerce.service.user.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void add() {
        User user = new User();
        user.setUserName("Test-Name");
        user.setPassword("Test12345");
        user.setEmail("test@gmail.com");

        User user1 = userService.add(user);
        assertEquals(user1.getEmail(), user.getEmail());
        System.out.println(user1);
        System.out.println("Test passed !");
    }

    @Test
    void getAll() {
        User user = mock(User.class);
        user.setUserName("Test-name");
        user.setPassword("test12247487");
        user.setEmail("test123@gmail.com");

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        List<User> userList = this.userService.getAll();
        assertEquals(userList.size(), 1);
        System.out.println(user);
    }

    @Test
    void getById() {
        int id = 1;
        userService.getById(1);
        verify(userRepository).getById(id);
    }

    @Test
    void slice() {
    }

    @Test
    void deleteById() {
        int id = 1;
        userService.deleteById(id);
        verify(userRepository).deleteById(id);
    }

    @Test
    void getUserViewDto() {
        userService.getUserViewDto();
        verify(userRepository).findAll();
        System.out.println("Test-passed");
    }

    @Test
    void getByUserName() {
        String userName = "Test-Name";
        userService.getByUserName(userName);
        verify(userRepository).findByUserName(userName);
        System.out.println("Test-passed");
    }

    @Test
    void authDeleteByUser() {
    }

    @Test
    void findByEMail() {
    }

    @Test
    void updateByUserName() {
    }

    @Test
    void updateByNotificationPermission() {
    }
}