package app.Services;

import app.Persistance.Entities.Category;
import app.Persistance.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAllCategory(){
        return repository.findAll();
    }

    public void addCategory(Category category){
        repository.save(category);
    }

    public void deleteCategory(UUID id){
        repository.deleteById(id);
    }
    public Optional<Category> updateCategory(UUID id){
        return repository.findById(id);
    }
}
