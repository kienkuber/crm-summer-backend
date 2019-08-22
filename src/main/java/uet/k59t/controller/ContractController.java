package uet.k59t.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uet.k59t.dto.ContractRequestDto;
import uet.k59t.service.ContractService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    @Autowired
    ContractService contractService;

    @GetMapping
    public ResponseEntity<?> getAllContracts(@SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(contractService.findAllContract(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(contractService.findById(id));
    }

    @PostMapping("/{accountId}")
    public ResponseEntity<?> createContract(@RequestBody @Valid ContractRequestDto contractRequestDto, @PathVariable @NotNull Long accountId, @PathVariable @NotNull Long ownerId) {
        contractService.createNewContract(contractRequestDto, accountId, ownerId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContract(@PathVariable @NotNull Long id) {
        contractService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContract(@RequestBody @Valid ContractRequestDto contractRequestDto, @PathVariable Long id) {
        contractService.updateContract(id, contractRequestDto);
        return ResponseEntity.ok().build();
    }
}
