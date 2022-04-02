package com.waa.lab.service;

import com.waa.lab.domain.User;
import com.waa.lab.domain.dto.UserDto;
import com.waa.lab.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(UserDto u) {
        User user = new User();
        modelMapper.map(u, user);
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.findById(id)
            .ifPresent( pst -> userRepository.delete(pst));

    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id).orElse(null);

    }


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(long id, UserDto u) {
        userRepository.findById(id)
                .ifPresent(user -> {
                    modelMapper.map(u, user);
                    user.setId(id);
                    userRepository.save(user);
                });


    }

    @Override
    public List<User> findUserWithPosts(int numberOfPosts) {
        return userRepository.findUserWithPosts(numberOfPosts);
    }

    @Override
    public List<User> findByPostTitle(String title) {
        return userRepository.findByPostTitle(title);
    }

}
