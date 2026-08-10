package org.example;

import org.example.entities.CategoryEntity;
import org.example.dao.CategoryDao;
import org.example.utils.HibernateUtil;
import org.example.utils.HibernateHelper;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CategoryDao dao = new CategoryDao();

        // CREATE
        CategoryEntity c = new CategoryEntity();
        // set fields on c according to your CategoryEntity (e.g., setName, setDescription)
        // example: c.setName("Books");
        Long id = dao.create(c);
        System.out.println("Created Category id = " + id);

        // READ
        CategoryEntity read = dao.read(id);
        System.out.println("Read: " + read);

        // UPDATE
        // modify read object fields
        // read.setName("Updated name");
        dao.update(read);
        System.out.println("Updated.");

        // LIST
        List<CategoryEntity> all = dao.listAll();
        System.out.println("All categories: " + all);

        // DELETE
        dao.deleteById(id);
        System.out.println("Deleted id = " + id);

        // shutdown SessionFactory
        HibernateUtil.shutdown();

    }
}