package com.tourism.khvtourism.service;

import com.tourism.khvtourism.model.Contact;
import com.tourism.khvtourism.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void sendContact(Contact contact) {
        contactRepository.save(contact);
    }
}
