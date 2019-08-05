package uet.k59t.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uet.k59t.dto.LeadDto;
import uet.k59t.dto.LeadRequestDto;
import uet.k59t.model.Lead;
import uet.k59t.repository.LeadRepository;

@Service
public class LeadService {

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    ModelMapper modelMapper;

    public Page<LeadDto> findAllLead(Pageable pageable) {
        Page<Lead> leadPage =leadRepository.findAllByDeletedIsFalse(pageable);
        return leadPage.map(e -> modelMapper.map(e, LeadDto.class));
    }

    public LeadDto findById(Long id) {
        Lead lead = leadRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error())
        );
        if (lead.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error());
        }
        return modelMapper.map(lead, LeadDto.class);
    }

    public void deleteById(Long id) {
        leadRepository.deleteById(id);
    }

    public void createNewLead(LeadRequestDto leadDto) {
        Lead lead = modelMapper.map(leadDto, Lead.class);
        lead.setDeleted(false);
        lead.setConverted(false);
        lead.setStatus(0);
        leadRepository.save(lead);
    }

    public void updateLead(LeadRequestDto leadRequestDto, Long id) {
        Lead lead = leadRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error()));
        if (lead.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error());
        }
        modelMapper.map(leadRequestDto, lead);
        leadRepository.save(lead);
    }
}