package uet.k59t.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OpportunityRequestDto {
    private Long id;

    @NotNull
    private Long accountId;

    @NotBlank
    private String name;

    @NotBlank
    private String stageName;

    private Long contractId;

    private boolean isClosed;

    private boolean isWon;
}
