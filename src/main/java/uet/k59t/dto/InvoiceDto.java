package uet.k59t.dto;

import lombok.Data;

import java.util.Date;

@Data
public class InvoiceDto {
    private Long id;

    private Long contractId;

    private String paymentMethod;

    private float paymentAmount;

    private Date paymentDate;
}
