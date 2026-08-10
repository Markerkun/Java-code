package org.example.dao;

import org.example.entities.CategoryEntity;
import java.util.List;

public interface CategoryDao {
    Long create(CategoryEntity category);
    CategoryEntity read(Long id);
    void update(CategoryEntity category);
    void delete(CategoryEntity category);
    void deleteById(Long id);
    List<CategoryEntity> listAll();
}