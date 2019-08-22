package uet.k59t.dto;

import lombok.Data;

@Data
public class OpportunityDto {
    private Long id;

    private boolean deleted;

    private AccountDto accountDto;

    private String name;

    private String stageName;

    private boolean isClosed;

    private boolean isWon;

    private ContactDto contactDto;

    private Long contractId;
}
