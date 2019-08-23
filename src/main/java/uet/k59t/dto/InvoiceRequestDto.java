package uet.k59t.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class InvoiceRequestDto {
    @NotBlank
    private String paymentMethod;

    @NotBlank
    private float paymentAmount;

    @NotBlank
    private Date paymentDate;
}
