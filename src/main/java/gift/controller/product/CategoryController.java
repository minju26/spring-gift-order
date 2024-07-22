package gift.controller.product;

import gift.controller.product.dto.CategoryRequest;
import gift.controller.product.dto.CategoryResponse;
import gift.global.auth.Authorization;
import gift.model.member.Role;
import gift.application.product.service.CategoryService;
import gift.application.product.dto.CategoryModel;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<CategoryResponse.InfoList> getCategories() {
        List<CategoryModel.Info> model = categoryService.getCategories();
        return ResponseEntity.ok(CategoryResponse.InfoList.from(model));
    }

    @Authorization(role = Role.ADMIN)
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponse.Info> createCategory(
        @RequestBody @Valid CategoryRequest.Register request
    ) {
        CategoryModel.Info model = categoryService.createCategory(request.toCommand());
        return ResponseEntity.ok(CategoryResponse.Info.from(model));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponse.Info> getCategory(
        @PathVariable("id") Long id
    ) {
        CategoryModel.Info model = categoryService.getCategory(id);
        return ResponseEntity.ok(CategoryResponse.Info.from(model));
    }

    @Authorization(role = Role.ADMIN)
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponse.Info> updateCategory(
        @PathVariable("id") Long id,
        @RequestBody @Valid CategoryRequest.Update request
    ) {
        CategoryModel.Info model = categoryService.updateCategory(id, request.toCommand());
        return ResponseEntity.ok(CategoryResponse.Info.from(model));
    }

    @Authorization(role = Role.ADMIN)
    @DeleteMapping("/categories/{id}")
    public String deleteCategory(
        @PathVariable("id") Long id
    ) {
        categoryService.deleteCategory(id);
        return "Category deleted successfully.";
    }
}
