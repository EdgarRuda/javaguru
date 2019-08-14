package com.javaguru.shoppinglist.Repository;

import com.javaguru.shoppinglist.Domain.Product;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class HibernateProductRepository implements ProductRepository {
    private final SessionFactory sessionFactory;
@Autowired
    public HibernateProductRepository(SessionFactory sessionFactory)
    {
        this.sessionFactory =sessionFactory;
    }

    public Product add(Product product){

        sessionFactory.getCurrentSession().save(product);
        return product;
    }

    public void update(Product product){
        sessionFactory.getCurrentSession().update(product);
    }


    public Optional<Product> findProductById(Long id){
        Product product = (Product) sessionFactory.getCurrentSession().get(Product.class, (Long)id);
    return Optional.ofNullable(product);
    }

    public boolean existByName(String name){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
        return  criteria.add(Restrictions.eq("name", name)).list().isEmpty();
    }
}
