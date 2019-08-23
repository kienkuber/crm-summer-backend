package uet.k59t.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import uet.k59t.dto.InvoiceDto;
import uet.k59t.dto.InvoiceRequestDto;

import uet.k59t.model.Invoice;
import uet.k59t.repository.InvoiceRepository;

import javax.validation.constraints.NotNull;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ContractService contractService;

    @Autowired
    ModelMapper modelMapper;

    public Page<InvoiceDto> findAllInvoice(Pageable pageable) {
        Page<Invoice> leadPage = invoiceRepository.findAll(pageable);
        return leadPage.map(e -> {
            InvoiceDto invoiceDto = modelMapper.map(e, InvoiceDto.class);
            invoiceDto.setContractId(e.getContract().getId());
            return invoiceDto;
        });
    }

    public InvoiceDto findById(Long id) {
        Invoice invoice = findExistedInvoice(id);
        InvoiceDto invoiceDto = modelMapper.map(invoice, InvoiceDto.class);
        invoiceDto.setContractId(invoice.getContract().getId());
        return invoiceDto;
    }

    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }

    public void createNewInvoice(InvoiceRequestDto invoiceRequestDto, @NotNull Long contractId) {
        Invoice invoice = modelMapper.map(invoiceRequestDto, Invoice.class);
        invoice.setContract(contractService.findContract(contractId));
        invoiceRepository.save(invoice);
    }

    public void updateInvoice(InvoiceRequestDto invoiceRequestDto, Long id) {
        Invoice invoice = findExistedInvoice(id);
        modelMapper.map(invoiceRequestDto, invoice);
        invoiceRepository.save(invoice);
    }

    private Invoice findExistedInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error()));
        return invoice;
    }
}
