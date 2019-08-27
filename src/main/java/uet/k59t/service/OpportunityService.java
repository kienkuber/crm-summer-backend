package uet.k59t.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uet.k59t.dto.ContactDto;
import uet.k59t.dto.ContractDto;
import uet.k59t.dto.OpportunityDto;
import uet.k59t.dto.OpportunityRequestDto;
import uet.k59t.model.Contract;
import uet.k59t.model.Opportunity;
import uet.k59t.repository.OpportunityRepository;

@Service
public class OpportunityService {
    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private ModelMapper modelMapper;

    public Page<OpportunityDto> findAllByAccountId(Long accountId) {
        accountService.findAccount(accountId);
        return opportunityRepository.findAllByDeletedIsFalseAndAccountId(accountId)
                .map(e -> modelMapper.map(e, OpportunityDto.class));
    }

    public OpportunityDto findById(Long id) {
        return convertOpportunity(opportunityRepository.findByDeletedIsFalseAndId(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error())));
    }

    private OpportunityDto convertOpportunity(Opportunity opportunity) {
        OpportunityDto opportunityDto = modelMapper.map(opportunity, OpportunityDto.class);
        opportunityDto.setAccountDto(accountService.convertAccount(opportunity.getAccount()));
        opportunityDto.setContactDto(modelMapper.map(opportunity.getContact(), ContactDto.class));
        opportunityDto.setContractDto(modelMapper.map(opportunity.getContract(), ContractDto.class));
        return opportunityDto;
    }

    public void createNewOpportunity(OpportunityRequestDto opportunityDto) {
        Opportunity opportunity = new Opportunity();
        opportunity.setAccount(accountService.findAccount(opportunityDto.getAccountId()));
        opportunity.setName(opportunityDto.getName());
        opportunity.setStageName(opportunityDto.getStageName());
        opportunity.setContact(contactService.findByAccountId(opportunityDto.getAccountId()));
        opportunityRepository.save(opportunity);
    }

    public void updateOpportunity(OpportunityRequestDto opportunityDto, Long id) {
        Opportunity opportunity = opportunityRepository.findByDeletedIsFalseAndId(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error()));
        modelMapper.map(opportunityDto, opportunity);
        opportunity.setContract(contractService.findContract(opportunityDto.getContractId()));
        opportunityRepository.save(opportunity);
    }

    public void deleteOpportunity(Long id) {
        findById(id);
        opportunityRepository.deleteById(id);
    }
}
