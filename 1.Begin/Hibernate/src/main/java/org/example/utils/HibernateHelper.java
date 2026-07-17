package org.example.utils;

import lombok.Getter;
import org.example.entities.CategoryEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateHelper {
    @Getter
    public static SessionFactory sessionFactory ;

    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .build();

        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(CategoryEntity.class)
                    .buildMetadata().buildSessionFactory();
        }
        catch (Exception e){
            System.out.println("ERROR" + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    public static Session getSession(){
        return sessionFactory. openSession();
    }
    public static void shutDown(){
        if(sessionFactory != null)
            sessionFactory.close();
    }
}
