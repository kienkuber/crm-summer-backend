package uet.k59t.dto;

import lombok.Data;

@Data
public class InvoiceProductDto {
    private ProductDto productDto;

    private int quantity;
}
