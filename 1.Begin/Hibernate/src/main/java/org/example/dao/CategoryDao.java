package org.example.dao;

import org.example.entities.CategoryEntity;
import java.util.List;

public interface CategoryDao {
    Integer create(CategoryEntity category);
    CategoryEntity read(Integer id);
    void update(CategoryEntity category);
    void delete(CategoryEntity category);
    void deleteById(Integer id);
    List<CategoryEntity> listAll();
}
