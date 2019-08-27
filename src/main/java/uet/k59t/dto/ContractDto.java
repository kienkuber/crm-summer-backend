package uet.k59t.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ContractDto {
    private Long id;

    private boolean deleted;

    private Long accountId;

    private Date startDate;

    private String status;

    private float contractValue;

    private float discountAmount;

    private float discountDuration;

    private Date contractEndDate;
}
