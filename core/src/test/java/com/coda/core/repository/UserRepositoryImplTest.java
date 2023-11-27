package com.coda.core.repository;

import com.coda.core.entities.Contact;
import com.coda.core.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserRepositoryImplTest {

    private User user;
    private List<User> userList;

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    @BeforeEach
    void setUp() {
        try (AutoCloseable mockitoAnnotations = MockitoAnnotations.openMocks(this)) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        userRepositoryImpl = new UserRepositoryImpl(userRepository);

        user = new User(1, "Chris",
                new Contact(1, "ee@test.com", "09089777"),
                "password");
        userList = List.of(new User(1, "Chris",
                        new Contact(1, "jj@test.com", "09089777"),
                        "password"),
                new User(3, "Dunn",
                        new Contact(1, "dunn@test.com", "09089736"),
                        "password"),
                new User(2, "John",
                        new Contact(1, "jb@test.com", "09089777"),
                        "password"));

    }

    @Test
    void testUserSave() {

        // Act
        when(userRepository.save(user)).thenReturn(user);

        // Arrange
        User savedUser = userRepositoryImpl.save(user);

        // Assert
        assertEquals(1, savedUser.getId());
        assertEquals("Chris", savedUser.getName());
        assertEquals("password", savedUser.getPassword());
        assertEquals("ee@test.com", savedUser.getContact().getEmail());


    }

    @Test
    void testUserGetById() {

        // Act
        when(userRepository.getById(1)).thenReturn(user);

        // Arrange
        User savedUser = userRepositoryImpl.getById(1);

        // Assert
        assertEquals(1, savedUser.getId());
        assertEquals("Chris", savedUser.getName());
        assertEquals("password", savedUser.getPassword());
        assertEquals("ee@test.com", savedUser.getContact().getEmail());
        assertEquals("09089777", savedUser.getContact().getPhone());
    }

    @Test
    void testFindAll() {

        Example<User> example = Example.of(user);


        // Act
        when(userRepository.findAll(example)).thenReturn(userList);

        // Arrange
        List<User> savedUserList = userRepositoryImpl.findAll(example);

        // Assert

        assertEquals(3, savedUserList.size());
        assertEquals("Chris", savedUserList.get(0).getName());
        assertEquals("Dunn", savedUserList.get(1).getName());
        assertEquals("John", savedUserList.get(2).getName());


    }

    @Test
    void testFindAllWithSort() {

        //
        // create an example object
        Example<User> example = Example.of(user);

        Sort sort = Sort.by(Sort.Direction.ASC, "name");

        when(userRepository.findAll(
                Mockito.argThat(argument -> argument.getProbe().equals(user)),
                Mockito.any(Sort.class))
        ).thenReturn(userList);

        // Act
        List<User> savedUserList = userRepositoryImpl.findAll(example, sort);

        // Assert

        assertEquals(3, savedUserList.size());
        assertEquals("Chris", savedUserList.get(0).getName());
        assertEquals("Dunn", savedUserList.get(1).getName());
        assertEquals("John", savedUserList.get(2).getName());




}

}