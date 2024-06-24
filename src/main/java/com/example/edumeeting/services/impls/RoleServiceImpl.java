package com.example.edumeeting.services.impls;

import com.example.edumeeting.dtos.roledtos.RoleDto;
import com.example.edumeeting.models.Role;
import com.example.edumeeting.repositories.RoleRepository;
import com.example.edumeeting.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RoleDto> getRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> roleDtos = roles.stream().map(role->modelMapper.map(role,RoleDto.class)).collect(Collectors.toList());
        return roleDtos;
    }
}
