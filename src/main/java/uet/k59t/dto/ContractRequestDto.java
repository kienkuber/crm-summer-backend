package uet.k59t.dto;

import java.util.Date;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ContractRequestDto {
    @NotBlank
    private Date startDate;

    @NotBlank
    private String status;

    @NotBlank
    private float contractValue;

    @NotBlank
    private float discountAmount;

    @NotBlank
    private float discountDuration;

    @NotBlank
    private Date contractEndDate;
}
