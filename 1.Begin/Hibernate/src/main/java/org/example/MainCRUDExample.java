package org.example;

import org.example.dao.CategoryDao;
import org.example.dao.CategoryDaoImpl;
import org.example.entities.CategoryEntity;
import org.example.utils.HibernateHelper;

import java.util.List;

public class MainCRUDExample {
    public static void main(String[] args) {
        CategoryDao dao = new CategoryDaoImpl();

        // CREATE
        CategoryEntity c = new CategoryEntity("Books");
        Integer id = dao.create(c);
        System.out.println("Created Category id = " + id);

        // READ
        CategoryEntity read = dao.read(id);
        System.out.println("Read: " + read);

        // UPDATE
        read.setName("Updated Books");
        dao.update(read);
        System.out.println("Updated.");

        // LIST
        List<CategoryEntity> all = dao.listAll();
        System.out.println("All categories: " + all);

        // DELETE
        dao.deleteById(id);
        System.out.println("Deleted id = " + id);

        // shutdown SessionFactory
        HibernateHelper.shutDown();
    }
}
