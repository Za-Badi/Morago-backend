package com.habsida.morago;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.CategoryDTO;
import com.habsida.morago.model.entity.Category;
import com.habsida.morago.model.inputs.CreateCategoryInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.repository.CategoryRepository;
import com.habsida.morago.serviceImpl.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.meta.When;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = MoragoBackendApplication.class)
class CategoryServiceTest {
    private final String testName = "Grocery";
    private final Boolean testIsActive = true;

    //    @Autowired
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PagingInput pagingInput;
    @Mock
    private PageRequest pageRequest;
    private AutoCloseable autoCloseable;
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks((this));
        categoryService = new CategoryService(categoryRepository, modelMapper);

   }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }


    @Test
    void createCategoryTest() {
        CreateCategoryInput categoryInput = new CreateCategoryInput();
        categoryInput.setName(testName);
        categoryInput.setIsActive(testIsActive);
        Category category = Category.builder().name(testName).isActive(testIsActive).build();
        CategoryDTO categoryDTO = new CategoryDTO();
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(modelMapper.map(any(Category.class), eq(CategoryDTO.class))).thenReturn(categoryDTO);
        CategoryDTO result = categoryService.createCategory(categoryInput);
        assertNotNull(result);
        assertEquals(category.getName(), categoryInput.getName());
        assertEquals(category.getIsActive(), categoryInput.getIsActive());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    @Disabled
    void getAllCategories() {
    }
    @Test
    void getCategoryById() {

//        categoryService.getCategoryById();
    }

    @Test
    void getCategoryByName() {
    }

    @Test
    void updateCategory() {
    }

    @Test
    void deleteCategoryById() {
    }

    @Test
    void existsUserByName() {
    }

    @Test
    void getCategoryByStatusIsActive() {
    }

    @Test
    void changeCategoryStatus() {
    }
}