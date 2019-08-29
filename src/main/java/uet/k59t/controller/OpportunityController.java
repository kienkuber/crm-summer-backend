package uet.k59t.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uet.k59t.dto.OpportunityRequestDto;
import uet.k59t.service.OpportunityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/opportunity")
public class OpportunityController {

    @Autowired
    private OpportunityService opportunityService;

    @GetMapping("/all/{accountId}")
    public ResponseEntity<?> getOpportunityByAccountId(@SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @PathVariable @NotNull Long accountId) {
        return ResponseEntity.ok(opportunityService.findAllByAccountId(accountId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOpportunityById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(opportunityService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createOpportunity(@RequestBody @Valid OpportunityRequestDto opportunityDto) {
        opportunityService.createNewOpportunity(opportunityDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOpportunity(@PathVariable @NotNull Long id, @RequestBody @Valid OpportunityRequestDto opportunityDto) {
        opportunityService.updateOpportunity(opportunityDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOpportunity(@PathVariable @NotNull Long id) {
        opportunityService.deleteOpportunity(id);
        return ResponseEntity.noContent().build();
    }

}
