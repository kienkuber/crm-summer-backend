package uet.k59t.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uet.k59t.dto.EventRequestDto;
import uet.k59t.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/all/{accountId}")
    public ResponseEntity<?> findAllByAccountId(@SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @PathVariable @NotNull Long accountId) {
        return ResponseEntity.ok(eventService.findAllByAccountId(accountId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(eventService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody @Valid EventRequestDto eventDto) {
        eventService.createEvent(eventDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable @NotNull Long id, @RequestBody @Valid EventRequestDto eventDto) {
        eventService.updateEvent(eventDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable @NotNull Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
