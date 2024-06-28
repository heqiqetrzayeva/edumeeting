package com.example.edumeeting.services.impls;


import com.example.edumeeting.dtos.authdtos.RegisterDto;
import com.example.edumeeting.dtos.userdtos.UserAddRoleDto;
import com.example.edumeeting.dtos.userdtos.UserCreateDto;
import com.example.edumeeting.dtos.userdtos.UserDashboardDto;
import com.example.edumeeting.dtos.userdtos.UserDto;
import com.example.edumeeting.models.Role;
import com.example.edumeeting.models.UserEntity;
import com.example.edumeeting.repositories.RoleRepository;
import com.example.edumeeting.repositories.UserRepository;
import com.example.edumeeting.services.EmailService;
import com.example.edumeeting.services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;


    @Override
    public boolean register(RegisterDto register) {
//
        UserEntity user = userRepository.findByEmail(register.getEmail());
        if (user != null){
            return false;
        }
        String hashPassword = bCryptPasswordEncoder.encode(register.getPassword());
        String token = bCryptPasswordEncoder.encode(register.getEmail());
        UserEntity newUser = modelMapper.map(register, UserEntity.class);
        newUser.setEmailConfirmed(true);
//        newUser.setEmailConfirmed(false);
        newUser.setConfirmationToken(token);
        newUser.setPassword(hashPassword);
        userRepository.save(newUser);
        emailService.sendConfirmationEmail(register.getEmail(),token);
        return true;
    }

    @Override
    public UserEntity findByEmail(String email) {
            UserEntity user = userRepository.findByEmail(email);
            return user;
    }

//    @Override
//    public List<UserDto> getAllRegisters() {
//        List<UserDto> users = userRepository.findAll().stream()
//                .map(UserEntity->modelMapper.map(UserEntity, UserDto.class))
//                .collect(Collectors.toList());
//        return users;
//    }


    @Override
    public void addUser(UserCreateDto userCreateDto) {
        UserEntity user = new UserEntity();
        user.setEmail(userCreateDto.getEmail());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        userRepository.save(user);

    }

    @Override
    public List<UserDashboardDto> getDashboardUsers() {
        List<UserEntity> findUsers = userRepository.findAll();
        List<UserDashboardDto> users = findUsers.stream().map(user->modelMapper.map(user,UserDashboardDto.class)).collect(Collectors.toList());
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

    @Override
    public void addRole(UserAddRoleDto userAddRoleDto) {
        UserEntity findUser=userRepository.findByEmail(userAddRoleDto.getEmail());
        List<Role> roles = roleRepository.findAll().stream().filter(x->x.getId()==userAddRoleDto.getRoleId()).collect(Collectors.toList());
        findUser.setRoles(roles);
        userRepository.save(findUser);
    }

    public void requestPasswordReset(String email) {
        UserEntity user = userRepository.findByEmail(email);
//                .orElseThrow(() -> new IllegalArgumentException("Bu e-posta adresine sahip bir kullanıcı bulunamadı"));

        String token = UUID.randomUUID().toString();
        user.setConfirmationToken(token);
        user.setResetTokenExpiry(LocalDateTime.now().plusHours(24));
        userRepository.save(user);

        emailService.sendConfirmationEmail(user.getEmail(), token);
    }

    @Transactional
    public void resetPassword(String token, String newPassword) {
        UserEntity user = userRepository.findByConfirmationToken(token);
//                .orElseThrow(() -> new IllegalArgumentException("Geçersiz Token"));

        if (user.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token süresi doldu");
        }

        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        user.setConfirmationToken(null); // Token'i geçersiz kıl
        user.setResetTokenExpiry(null);
        userRepository.save(user);
    }
}
