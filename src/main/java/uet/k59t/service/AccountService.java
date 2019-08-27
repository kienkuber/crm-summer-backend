package uet.k59t.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uet.k59t.dto.AccountDto;
import uet.k59t.model.Account;
import uet.k59t.model.Lead;
import uet.k59t.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AccountDto createNewAccount(Lead lead) {
        Account account = new Account();
        account.setDeleted(false);
        account.setLead(lead);
        accountRepository.save(account);
        return convertAccount(accountRepository.getAccountByDeletedAndLead(false, lead)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error())));
    }

    public Account findAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error()));
    }

    public AccountDto convertAccount(Account account) {
        return modelMapper.map(account, AccountDto.class);
    }
}
