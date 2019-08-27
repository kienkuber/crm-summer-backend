package uet.k59t.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import uet.k59t.dto.ContractDto;
import uet.k59t.dto.ContractRequestDto;
import uet.k59t.model.Account;
import uet.k59t.model.Contract;
import uet.k59t.repository.ContractRepository;

import javax.validation.constraints.NotNull;

@Service
public class ContractService {
    @Autowired
    ContractRepository contractRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    ModelMapper modelMapper;

    public Page<ContractDto> findAllContract(Pageable pageable) {
        Page<Contract> leadPage = contractRepository.findAllByDeletedIsFalse(pageable);
        return leadPage.map(e -> {
            ContractDto contractDto = modelMapper.map(e, ContractDto.class);
            contractDto.setAccountId(e.getAccount().getId());
            return contractDto;
        });
    }

    private Contract findExistedContract(Long id) {
        return contractRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error()));
    }

    public ContractDto findById(Long id) {
        Contract contract = contractRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error())
        );
        if (contract.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error());
        }
        return modelMapper.map(contract, ContractDto.class);
    }

    public void deleteById(Long id) {
        contractRepository.deleteById(id);
    }

    public void createNewContract(ContractRequestDto contractRequestDto, @NotNull Long accountId, @NotNull Long ownerId) {
        Contract contract = modelMapper.map(contractRequestDto, Contract.class);
        contract.setAccount(accountService.findAccount(accountId));
        contract.setDeleted(false);
        contractRepository.save(contract);
    }

    public void updateContract(Long id, ContractRequestDto contractRequestDto) {
        Contract contract = findExistedContract(id);
        modelMapper.map(contractRequestDto, contract);
        contractRepository.save(contract);
    }

    public Contract findContract(Long id) {
        return contractRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error()));
    }
}
