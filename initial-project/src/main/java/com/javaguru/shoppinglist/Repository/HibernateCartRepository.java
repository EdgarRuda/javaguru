package com.javaguru.shoppinglist.Repository;

import com.javaguru.shoppinglist.Domain.Product;
import com.javaguru.shoppinglist.Domain.ShoppingCart;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional public class HibernateCartRepository implements CartRepository {

    private final SessionFactory sessionFactory;
@Autowired
    public HibernateCartRepository(SessionFactory sessionFactory) {this.sessionFactory=sessionFactory;}


    public void add(ShoppingCart cart) { sessionFactory.getCurrentSession().save(cart); }

    public void update(ShoppingCart shoppingCart){
        sessionFactory.getCurrentSession().update(shoppingCart);
    }

    public void deleteCart(Long cartId){ sessionFactory.getCurrentSession().delete(findCartById(cartId)); }


    public Optional<ShoppingCart> findCartById(Long id) {
        ShoppingCart shoppingCart =(ShoppingCart) sessionFactory.getCurrentSession().get(ShoppingCart.class, id);
        return Optional.ofNullable(shoppingCart);
    }

    public boolean existByName(String name){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ShoppingCart.class);
        return  criteria.add(Restrictions.eq("cartName", name)).list().isEmpty();
    }

    public List<Product> getList(Long id){
    ShoppingCart cart = findCartById(id)
            .orElseThrow(() -> new IllegalArgumentException("Product by id: " + id + " wasn't found"));
    Hibernate.initialize(cart.getList());
    return cart.getList();
}

}
