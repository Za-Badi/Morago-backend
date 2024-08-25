package com.habsida.morago;

import com.habsida.morago.model.dto.CategoryDTO;
import com.habsida.morago.model.entity.Category;
import com.habsida.morago.model.inputs.CreateCategoryInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.repository.CategoryRepository;
import com.habsida.morago.serviceImpl.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = MoragoBackendApplication.class)
class CategoryServiceTest {
    private final String testName = "Grocery";
    private final Boolean testIsActive = true;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PagingInput pagingInput;
    @Mock
    private PageRequest pageRequest;
    private AutoCloseable autoCloseable;

    @Mock
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

//    @Test
//    void getAllCategories() {
//        Category category = new Category();
//        Pageable pageable = PageRequest.of(1,6);
//        Page<Category> categories = Mockito.mock(Page.class);
//        Mockito.when(categoryRepository.findAll(pageable)).thenReturn(categories);
//        assertNotNull(categories);
//    }
    @Test
    void getCategoryById() throws EntityNotFoundException {
        Category category = Category.builder()
                .id(1L)
                .name(testName)
                .isActive(testIsActive)
                .build();
        CategoryDTO categoryDTO = new CategoryDTO();
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(modelMapper.map(category, CategoryDTO.class)).thenReturn(categoryDTO);
        CategoryDTO result = categoryService.getCategoryById (1L);
        verify(categoryRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(category, CategoryDTO.class);
    }

    @Test
    @Disabled
    void getCategoryByName() {
        Category category = Category.builder()
                .id(1L)
                .name(testName)
                .isActive(testIsActive)
                .build();
        CategoryDTO categoryDTO = new CategoryDTO();
        when(categoryRepository.findByName(testName)).thenReturn(Optional.of(category));
        when(modelMapper.map(category, CategoryDTO.class)).thenReturn(categoryDTO);
        CategoryDTO result = categoryService.getCategoryByName (testName);
        verify(categoryRepository, times(1)).findByName(testName);
        verify(modelMapper, times(1)).map(category, CategoryDTO.class);
    }

    @Test
    @Disabled
    void updateCategory() {
    }

    @Test
    void deleteCategoryById() {
        Long categoryId = 1L;
        String languageName = "English";
        Category category = new Category();
        category.setId(categoryId);
        category.setName(languageName);
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).delete(category);
        categoryService.deleteCategoryById(categoryId);
        verify(categoryRepository, times(1)).findById(categoryId);
        verify(categoryRepository, times(1)).delete(category);
    }

    @Test
    @Disabled
    void existsUserByName() {

    }

    @Test
    @Disabled
    void getCategoryByStatusIsActive() {
    }

    @Test
    void changeCategoryStatus() {
    }
}