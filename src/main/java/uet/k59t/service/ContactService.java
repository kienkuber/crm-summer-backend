package uet.k59t.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uet.k59t.dto.ContactDto;
import uet.k59t.dto.ContactRequestDto;
import uet.k59t.model.Account;
import uet.k59t.model.Contact;
import uet.k59t.repository.ContactRepository;

import javax.validation.constraints.NotNull;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    ModelMapper modelMapper;

    public Page<ContactDto> getAllContacts(Pageable page) {
        return contactRepository.findAllByDeletedIsFalse(page).map(e -> modelMapper.map(e, ContactDto.class));
    }

    public ContactDto findById(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error())
        );
        if (contact.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error());
        }

        return modelMapper.map(contact, ContactDto.class);
    }

    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    public void createNewContact(ContactRequestDto contactDto, @NotNull Long accountId) {
        Contact contact = modelMapper.map(contactDto, Contact.class);
        contact.setAccount(accountService.findAccount(accountId));
        contact.setDeleted(false);
        contactRepository.save(contact);
    }

    public void updateContact(Long id, ContactRequestDto contactRequestDto) {
        Contact contact = contactRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error())
        );
        if (contact.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error());
        }
        modelMapper.map(contactRequestDto, contact);
        contactRepository.save(contact);
    }

    Contact findByAccountId(Long accountId) {
        return contactRepository.findByAccountId(accountId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error()));
    }
}
