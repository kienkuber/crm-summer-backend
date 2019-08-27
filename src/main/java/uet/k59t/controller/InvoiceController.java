package uet.k59t.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uet.k59t.dto.InvoiceRequestDto;
import uet.k59t.service.InvoiceService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<?> getAllInvoices(@SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(invoiceService.findAllInvoice(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(invoiceService.findById(id));
    }

    @PostMapping("/{contractId}")
    public ResponseEntity<?> createInvoice(@RequestBody @Valid InvoiceRequestDto invoiceRequestDto, @PathVariable @NotNull Long contractId) {
        invoiceService.createNewInvoice(invoiceRequestDto, contractId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable @NotNull Long id) {
        invoiceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInvoice(@RequestBody @Valid InvoiceRequestDto invoiceRequestDto, @PathVariable Long id) {
        invoiceService.updateInvoice(invoiceRequestDto, id);
        return ResponseEntity.ok().build();
    }
}
