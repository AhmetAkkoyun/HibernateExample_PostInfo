package com.ahmetakkoyun.repository;

import com.ahmetakkoyun.repository.entity.User;
import com.ahmetakkoyun.utility.HibernateUtility;
import com.ahmetakkoyun.utility.ICrud;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UserRepository implements ICrud<User> {

    Session session;
    Transaction transaction;
    @Override
    public User save(User user) {
        System.out.println("userRepository çalışıyor...");
        try {
            session = HibernateUtility.getSessionFactory().openSession();   // veritabanı bağlantısı açmak için
            transaction = session.beginTransaction();   // veri transferinde veri kaybını önler (mesela para aktarmada hata olursa kayıp olmaz)
            session.save(user);
            transaction.commit();
            System.out.println("User kaydı başarılı...");
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            System.out.println("User kaydı başarısız!!!!");
        } finally{
            System.out.println("oturum kapanıyor");
            session.close();
        }
        System.out.println("UserRepository -> UserService");
        return user;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<User> findAll() {
        String hql = "SELECT u FROM User AS u";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
        List<User> userList = typedQuery.getResultList();
        return userList;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    public Optional<User> findByUsername(String username){
        System.out.println("userRepository çalışıyor...");
        String hql = "SELECT u FROM User AS u WHERE u.username=:myusername";
        String hql2 = "SELECT u FROM User AS u WHERE u.username="+username;  // veya böyle

        session = HibernateUtility.getSessionFactory().openSession();

        // TypedQuery yöntemi - bu yöntem daha iyi
        TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
        typedQuery.setParameter("myusername", username);
        User user = null;
        try {
            user = typedQuery.getSingleResult();  // getSingleResult bulamazsa Exception fırlatır. bu yüzden try catch yaptık.
        } catch (Exception e){
            System.out.println("BİR PROBLEM VAR!!!");
            System.out.println("PROBLEM => "+e.toString());
        }
//        System.out.println("typedQuery ==> "+user1);

//        // Query yöntemi
//        Query query = session.createQuery(hql);  // queryli yöntem
//        query.setParameter("myusername", username);
//        User user2 = (User)query.getSingleResult();
//        System.out.println("query ==> "+user2);

        return Optional.ofNullable(user);
    }
}
