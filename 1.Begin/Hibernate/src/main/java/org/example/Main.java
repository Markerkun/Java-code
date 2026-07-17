package org.example;

import org.example.entities.CategoryEntity;
import org.example.utils.HibernateHelper;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Dungeon Heroes");
        System.out.println("==RPG Series==");
        try(var session = HibernateHelper.getSession()){
            session.beginTransaction();
//            CategoryEntity cat =
//                    new CategoryEntity("Ice cream");
//            session.persist(cat);
//
            var list = session.createQuery("from CategoryEntity",
                    CategoryEntity.class).getResultList();
            for(var item : list)
                System.out.printf("%d\t%s\n", item.getId(), item.getName());
            session.getTransaction().commit();
        } catch(Exception e){
            System.out.println("Error "+e);
        }

    }
}