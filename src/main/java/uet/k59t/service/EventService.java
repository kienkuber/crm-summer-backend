package uet.k59t.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uet.k59t.dto.EventDto;
import uet.k59t.dto.EventRequestDto;
import uet.k59t.model.Event;
import uet.k59t.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    public Page<EventDto> findAllByAccountId(Long accountId) {
        return eventRepository.findAllByAccountId(accountId).map(this::convertToDto);
    }

    public EventDto findById(Long id) {
        Event event =  eventRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error()));
        return convertToDto(event);
    }

    public void createEvent(EventRequestDto eventDto) {
        Event event = modelMapper.map(eventDto, Event.class);
        event.setAccount(accountService.findAccount(eventDto.getAccountId()));
        eventRepository.save(event);
    }

    public void updateEvent(EventRequestDto eventDto, Long id) {
        Event event =  eventRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error()));
        modelMapper.map(eventDto, event);
        eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        Event event =  eventRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error()));
        eventRepository.delete(event);
    }

    private EventDto convertToDto(Event event) {
        EventDto eventDto = modelMapper.map(event, EventDto.class);
        eventDto.setAccountDto(accountService.convertAccount(event.getAccount()));
        return eventDto;
    }
}
