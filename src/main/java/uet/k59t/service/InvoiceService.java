package uet.k59t.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import uet.k59t.dto.InvoiceDto;
import uet.k59t.dto.InvoiceProductDto;
import uet.k59t.dto.InvoiceRequestDto;

import uet.k59t.model.Invoice;
import uet.k59t.model.InvoiceProduct;
import uet.k59t.repository.InvoiceProductRepository;
import uet.k59t.repository.InvoiceRepository;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceProductRepository invoiceProductRepository;

    @Autowired
    private ContractService contractService;

    @Autowired
    private ProductService productService;

    @Autowired
    ModelMapper modelMapper;

    public Page<InvoiceDto> findAllInvoice(Pageable pageable) {
        Page<Invoice> leadPage = invoiceRepository.findAll(pageable);
        return leadPage.map(e -> {
            InvoiceDto invoiceDto = modelMapper.map(e, InvoiceDto.class);
            invoiceDto.setContractId(e.getContract().getId());
            invoiceDto.setProducts(e.getInvoiceProduct().stream()
                    .map(this::convertToDto).collect(Collectors.toSet()));
            return invoiceDto;
        });
    }

    public InvoiceDto findById(Long id) {
        Invoice invoice = findExistedInvoice(id);
        InvoiceDto invoiceDto = modelMapper.map(invoice, InvoiceDto.class);
        invoiceDto.setContractId(invoice.getContract().getId());
        invoiceDto.setProducts(invoice.getInvoiceProduct().stream()
                .map(this::convertToDto).collect(Collectors.toSet()));
        return invoiceDto;
    }

    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }

    public void createNewInvoice(InvoiceRequestDto invoiceRequestDto, @NotNull Long contractId) {
        Invoice invoice = modelMapper.map(invoiceRequestDto, Invoice.class);
        invoice.setContract(contractService.findContract(contractId));
        Invoice invoice1 = invoiceRepository.save(invoice);
        invoiceRequestDto.getProducts().forEach(e -> invoiceProductRepository.save(convertFromDto(e, invoice1.getId())));
    }

    public void updateInvoice(InvoiceRequestDto invoiceRequestDto, Long id) {
        Invoice invoice = findExistedInvoice(id);
        modelMapper.map(invoiceRequestDto, invoice);
        invoiceRepository.save(invoice);
        invoiceRequestDto.getProducts().forEach(e -> invoiceProductRepository.save(convertFromDto(e, invoice.getId())));
    }

    private Invoice findExistedInvoice(Long id) {
        return invoiceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error()));
    }

    private InvoiceProductDto convertToDto(InvoiceProduct invoiceProduct) {
        InvoiceProductDto invoiceProductDto = new InvoiceProductDto();
        invoiceProductDto.setProductDto(productService.convertToDto(invoiceProduct.getProduct()));
        invoiceProductDto.setQuantity(invoiceProduct.getQuantity());
        return invoiceProductDto;
    }

    private InvoiceProduct convertFromDto(InvoiceProductDto invoiceProductDto, Long invoiceId) {
        InvoiceProduct invoiceProduct = new InvoiceProduct();
        invoiceProduct.setInvoice(findExistedInvoice(invoiceId));
        invoiceProduct.setProduct(productService.findExistedProduct(invoiceProductDto.getProductDto().getId()));
        invoiceProduct.setQuantity(invoiceProductDto.getQuantity());
        return invoiceProduct;
    }
}
