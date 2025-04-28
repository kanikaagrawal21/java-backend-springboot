package com.example.address_book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<ContactDTO> getAllContacts() {
        log.info("Fetching all contacts");
        List<ContactDTO> contacts = contactRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
        log.debug("Retrieved {} contacts", contacts.size());
        return contacts;
    }

    public ContactDTO getContactById(Long id) {
        log.info("Fetching contact with ID {}", id);
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.map(this::toDTO)
                .orElseThrow(() -> {
                    log.warn("Contact with ID {} not found", id);
                    return new ContactNotFoundException("Contact with ID " + id + " not found");
                });
    }

    public ContactDTO createContact(ContactDTO contactDTO) {
        log.info("Creating new contact: {}", contactDTO);
        Contact contact = toEntity(contactDTO);
        Contact savedContact = contactRepository.save(contact);
        log.debug("Created contact with ID {}", savedContact.getId());
        return toDTO(savedContact);
    }

    public ContactDTO updateContact(Long id, ContactDTO contactDTO) {
        log.info("Updating contact with ID {}", id);
        if (!contactRepository.existsById(id)) {
            log.warn("Contact with ID {} not found for update", id);
            throw new ContactNotFoundException("Contact with ID " + id + " not found");
        }
        Contact updatedContact = toEntity(contactDTO);
        updatedContact.setId(id);
        Contact saved = contactRepository.save(updatedContact);
        log.debug("Updated contact with ID {}", saved.getId());
        return toDTO(saved);
    }

    public void deleteContact(Long id) {
        log.info("Deleting contact with ID {}", id);
        if (!contactRepository.existsById(id)) {
            log.warn("Contact with ID {} not found for deletion", id);
            throw new ContactNotFoundException("Contact with ID " + id + " not found");
        }
        contactRepository.deleteById(id);
        log.debug("Deleted contact with ID {}", id);
    }

    private ContactDTO toDTO(Contact contact) {
        return new ContactDTO(contact.getName(), contact.getPhone(), contact.getCity());
    }

    private Contact toEntity(ContactDTO contactDTO) {
        return new Contact(null, contactDTO.getName(), contactDTO.getPhone(), contactDTO.getCity());
    }
}
