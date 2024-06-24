package com.example.edumeeting.services;

import com.example.edumeeting.dtos.contactdtos.ContactCreateDto;
import com.example.edumeeting.dtos.contactdtos.ContactDto;
import com.example.edumeeting.dtos.userdtos.UserCreateDto;
import com.example.edumeeting.dtos.userdtos.UserDto;

import java.util.List;

public interface ContactService {
    List<ContactDto> getAllContacts();
    void addContact(ContactCreateDto contactCreateDto);


}
