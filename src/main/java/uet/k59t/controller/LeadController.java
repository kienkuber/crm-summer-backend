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

    //lay toan bo lead
    //nó nhảy đến method này
    //và thực hiện hàm bên trong
    @GetMapping
    public ResponseEntity<?> getAllLeads(@SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        //trong hàm này có sử dụng leadService
        return ResponseEntity.ok(leadService.findAllLead(pageable));
        //sau đấy controller sẽ gửi về client với dạng httpresponse với content là data được trả về ở tầng service
    }

    //lay 1 lead
    //lay theo id???
    //yep
    //GetMapping -> Get la method, Mapping thi anh k biet giai nghĩa thế nào? =)), nhưng không quan trọng, ví dụ nhé
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(leadService.findById(id));
    }

    //tao lead moi

    @PostMapping
    public ResponseEntity<?> createLead(@RequestBody @Valid LeadRequestDto leadRequestDto) {
        leadService.createNewLead(leadRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //xoa lead
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLead(@PathVariable @NotNull Long id) {
        leadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //sua lead
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid LeadRequestDto leadRequestDto, @PathVariable Long id) {
        leadService.updateLead(leadRequestDto, id);
        return ResponseEntity.ok().build();
    }

    //cai nay khac =))
    @PutMapping("/convert/{id}")
    public ResponseEntity<?> convertLead(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(leadService.convertLead(id));
    }
}
