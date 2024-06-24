package com.example.edumeeting.services.impls;


import com.example.edumeeting.dtos.authdtos.RegisterDto;
import com.example.edumeeting.dtos.userdtos.UserCreateDto;
import com.example.edumeeting.dtos.userdtos.UserDto;
import com.example.edumeeting.models.UserEntity;
import com.example.edumeeting.repositories.UserRepository;
import com.example.edumeeting.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    private EmailService emailService;


    @Override
    public boolean register(RegisterDto register) {
//
//        UserEntity user = userRepository.findByEmail(register.getEmail());
//        if (user != null){
//            return false;
//        }
//        String hashPassword = bCryptPasswordEncoder.encode(register.getPassword());
//        String token = bCryptPasswordEncoder.encode(register.getEmail());
//        UserEntity newUser = modelMapper.map(register, UserEntity.class);
//        newUser.setEmailConfirmed(false);
//        newUser.setConfirmationToken(token);
//        newUser.setPassword(hashPassword);
//        userRepository.save(newUser);
//        emailService.sendConfirmationEmail(register.getEmail(),token);
        return true;
    }

    @Override
    public List<UserDto> getAllRegisters() {
        List<UserDto> users = userRepository.findAll().stream()
                .map(UserEntity->modelMapper.map(UserEntity, UserDto.class))
                .collect(Collectors.toList());
        return users;
    }


    @Override
    public void addUser(UserCreateDto userCreateDto) {
        UserEntity user = new UserEntity();
        user.setEmail(userCreateDto.getEmail());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        userRepository.save(user);

    }

    @Override
    public List<UserDto> getDashboardUsers() {
        List<UserEntity> findUsers = userRepository.findAll();
        List<UserDto> users = findUsers.stream().map(user->modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return users;
    }

    @Override
    public UserDto getUserById(Long id) {
        UserEntity findUser = userRepository.findById(id).orElseThrow();
        UserDto user = modelMapper.map(findUser, UserDto.class);
        return user;
    }

    @Override
    public boolean confirmEmail(String email, String token) {

        UserEntity findUser = userRepository.findByEmail(email);
        if (findUser.getConfirmationToken().equals(token) && findUser!=null)
        {
            findUser.setEmailConfirmed(true);
            userRepository.saveAndFlush(findUser);
            return true;
        }
        return false;
    }
}
