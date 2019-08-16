package uet.k59t.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uet.k59t.dto.ContactRequestDto;
import uet.k59t.service.ContactService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping
    private ResponseEntity<?> getAllContacts(@SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(contactService.getAllContacts(pageable));
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getContact(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(contactService.findById(id));
    }

    @PostMapping("/{account_id}")
    private ResponseEntity<?> createContact(@RequestBody @Valid ContactRequestDto contactDto, @PathVariable @NotNull Long accountId) {
        contactService.createNewContact(contactDto, accountId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateContact(@RequestBody @Valid ContactRequestDto contactDto, @PathVariable @NotNull Long id) {
        contactService.updateContact(id, contactDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteContact(@PathVariable @NotNull Long id) {
        contactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
