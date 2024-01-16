package com.peponapis.finalproject.serviceTests;

import com.peponapis.finalproject.model.UserEntity;
import com.peponapis.finalproject.repository.UserRepo;
import com.peponapis.finalproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserEntityServiceTests {

    @Mock
    private UserRepo userRepo;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void testSaveUser() {  // Need to fix test 1/7/2024, methods changed with user auth --- I gotchu bro 1/8/2024
        // Arrange
        UserEntity userEntityToSave = new UserEntity("John Doe", "johndoe", "password");
        when(userRepo.save(userEntityToSave)).thenReturn(userEntityToSave);

        // Act
        UserEntity savedUserEntity = userService.saveUser(userEntityToSave);

        // Assert
        assertEquals(userEntityToSave, savedUserEntity);
        verify(userRepo, times(1)).save(userEntityToSave);
    }

    @Test
    void testFindByName() {
        // Arrange
        String name = "John Doe";
        UserEntity expectedUserEntity = new UserEntity(name, "johndoe", "password");
        when(userRepo.findByName(name)).thenReturn(expectedUserEntity);

        // Act
        UserEntity foundUserEntity = userService.findByName(name);

        // Assert
        assertEquals(expectedUserEntity, foundUserEntity);
        verify(userRepo, times(1)).findByName(name);
    }

    @Test
    void testFindByUserName() {
        // Arrange
        String username = "johndoe";
        Optional<UserEntity> expectedUser = Optional.of(new UserEntity("John Doe", username, "password"));
        when(userRepo.findByUserName(username)).thenReturn(expectedUser);

        // Act
        Optional<UserEntity> foundUser = userService.findByUserName(username);

        // Assert
        assertEquals(expectedUser, foundUser);
        verify(userRepo, times(1)).findByUserName(username);
    }

    @Test
    void testFindByPassword() {  // Need to fix test 1/7/2024, methods change due to user auth
        // Arrange
        String password = "password";
        UserEntity expectedUserEntity = new UserEntity("John Doe", "johndoe", password);
        when(userRepo.findByPassword(password)).thenReturn(expectedUserEntity);
        when(this.passwordEncoder.encode(password)).thenReturn(password);

        // Act
        UserEntity foundUserEntity = userService.findByPassword(password);

        // Assert
        assertEquals(expectedUserEntity, foundUserEntity);
        verify(userRepo, times(1)).findByPassword(password);
    }
}
