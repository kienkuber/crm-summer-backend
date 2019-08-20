package uet.k59t.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import uet.k59t.dto.CategoryDto;
import uet.k59t.dto.CategoryRequestDto;

import uet.k59t.model.Category;
import uet.k59t.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    ModelMapper modelMapper;

    public Page<CategoryDto> findAllCategory(Pageable pageable) {
        Page<Category> leadPage = categoryRepository.findAll(pageable);
        return leadPage.map(e -> modelMapper.map(e, CategoryDto.class));
    }

    public CategoryDto findById(Long id) {
        return modelMapper.map(findExistedCategory(id), CategoryDto.class);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public void createNewCategory(CategoryRequestDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(category);
    }

    public void updateCategory(CategoryRequestDto categoryRequestDto, Long id) {
        Category category = findExistedCategory(id);
        modelMapper.map(categoryRequestDto, category);
        categoryRepository.save(category);
    }

    private Category findExistedCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error()));
        return category;
    }

    public Category findCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error()));
    }
}
