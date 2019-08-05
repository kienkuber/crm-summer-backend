package uet.k59t.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uet.k59t.dto.LeadRequestDto;
import uet.k59t.service.LeadService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/leads")
public class LeadController {

    @Autowired
    LeadService leadService;

    @GetMapping
    public ResponseEntity<?> getAllLeads(@SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(leadService.findAllLead(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(leadService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createLead(@RequestBody @Valid LeadRequestDto leadRequestDto) {
        leadService.createNewLead(leadRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLead(@PathVariable @NotNull Long id) {
        leadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid LeadRequestDto leadRequestDto, @PathVariable Long id) {
        leadService.updateLead(leadRequestDto, id);
        return ResponseEntity.ok().build();
    }
}
