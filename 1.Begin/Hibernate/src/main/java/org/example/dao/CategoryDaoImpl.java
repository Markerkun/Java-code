package org.example.dao;

import org.example.entities.CategoryEntity;
import org.example.utils.HibernateHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public Integer create(CategoryEntity category) {
        Transaction tx = null;
        Integer id = null;
        try (Session session = HibernateHelper.getSession()) {
            tx = session.beginTransaction();
            Object generated = session.save(category);
            if (generated instanceof Number) {
                id = ((Number) generated).intValue();
            } else if (generated != null) {
                id = Integer.valueOf(generated.toString());
            }
            tx.commit();
            return id;
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public CategoryEntity read(Integer id) {
        try (Session session = HibernateHelper.getSession()) {
            return session.get(CategoryEntity.class, id);
        }
    }

    @Override
    public void update(CategoryEntity category) {
        Transaction tx = null;
        try (Session session = HibernateHelper.getSession()) {
            tx = session.beginTransaction();
            session.update(category);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(CategoryEntity category) {
        Transaction tx = null;
        try (Session session = HibernateHelper.getSession()) {
            tx = session.beginTransaction();
            session.delete(category);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void deleteById(Integer id) {
        CategoryEntity c = read(id);
        if (c != null) delete(c);
    }

    @Override
    public List<CategoryEntity> listAll() {
        try (Session session = HibernateHelper.getSession()) {
            Query<CategoryEntity> q = session.createQuery("from org.example.entities.CategoryEntity", CategoryEntity.class);
            return q.list();
        }
    }
}
