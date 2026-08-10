package org.example.dao;

import org.example.entities.CategoryEntity;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public Long create(CategoryEntity category) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Long id = (Long) session.save(category);
            tx.commit();
            return id;
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public CategoryEntity read(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(CategoryEntity.class, id);
        }
    }

    @Override
    public void update(CategoryEntity category) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(category);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void deleteById(Long id) {
        CategoryEntity c = read(id);
        if (c != null) delete(c);
    }

    @Override
    public List<CategoryEntity> listAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<CategoryEntity> q = session.createQuery("from org.example.entities.CategoryEntity", CategoryEntity.class);
            return q.list();
        }
    }
}