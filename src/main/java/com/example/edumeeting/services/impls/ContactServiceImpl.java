package com.example.edumeeting.services.impls;

import com.example.edumeeting.dtos.contactdtos.ContactCreateDto;
import com.example.edumeeting.dtos.contactdtos.ContactDto;
import com.example.edumeeting.dtos.userdtos.UserDto;
import com.example.edumeeting.models.Contact;
import com.example.edumeeting.models.UserEntity;
import com.example.edumeeting.repositories.ContactRepository;
import com.example.edumeeting.services.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ContactDto> getAllContacts() {
        List<ContactDto> contacts = contactRepository.findAll().stream()
                .map(Contact->modelMapper.map(Contact, ContactDto.class))
                .collect(Collectors.toList());
        return contacts;
    }

    @Override
    public void addContact(ContactCreateDto contactCreateDto) {
        Contact contact = new Contact();
        contact.setEmail(contactCreateDto.getEmail());
        contact.setName(contactCreateDto.getName());
        contact.setMessage(contactCreateDto.getMessage());
        contactRepository.save(contact);
    }
}
