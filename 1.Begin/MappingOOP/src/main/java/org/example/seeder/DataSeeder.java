package org.example.seeder;

import org.example.entities.CategoryEntity;
import org.example.entities.ProductEntity;
import org.example.utils.HibernateHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;

public class DataSeeder {

    public static void seed() {

        Transaction transaction = null;

        try(Session session = HibernateHelper.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            if (session.createQuery("from CategoryEntity", CategoryEntity.class)
                    .getResultList()
                    .isEmpty()) {
                CategoryEntity category = new CategoryEntity();
                category.setName("Телефони");

                session.persist(category);

                ProductEntity product = new ProductEntity();
                product.setName("iPhone 15");
                product.setPrice(BigDecimal.valueOf(35000));

                product.setCategory(category);

                session.persist(product);
            }



            transaction.commit();

            System.out.println("Seeder виконано успішно!");

        }catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}