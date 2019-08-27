package uet.k59t.dto;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class InvoiceDto {
    private Long id;

    private Long contractId;

    private String paymentMethod;

    private float paymentAmount;

    private Date paymentDate;

    private Set<InvoiceProductDto> products;
}
