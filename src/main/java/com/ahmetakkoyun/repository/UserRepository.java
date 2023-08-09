package com.ahmetakkoyun.repository;

import com.ahmetakkoyun.repository.entity.Name;
import com.ahmetakkoyun.repository.entity.User;
import com.ahmetakkoyun.utility.HibernateUtility;
import com.ahmetakkoyun.utility.ICrud;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class UserRepository implements ICrud<User> {

    Session session;
    Transaction transaction;

    public UserRepository(){
        System.out.println("userRepository çalışıyor...");
    }

    @Override
    public User save(User user) {
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
        String hql = "SELECT u FROM User AS u WHERE u.id=:myid";
        session = HibernateUtility.getSessionFactory().openSession();

        TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
        typedQuery.setParameter("myid", id);
        User user = null;
        try {
            user = typedQuery.getSingleResult();
        } catch (Exception e){
            System.out.println("BİR PROBLEM VAR!!!");
            System.out.println("PROBLEM => "+e.toString());
        }
        return Optional.ofNullable(user);
    }

    public Optional<User> findById2(Long id) {
        session = HibernateUtility.getSessionFactory().openSession();
        User user = session.find(User.class, id);
       if (user == null){
           System.out.println("KULLANICI BULUNAMADI!!!");
        }
        return Optional.ofNullable(user);
    }

    public Optional<User> findByUsername(String username){
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

    public List<Name> findAllNames(){
        String hql = "SELECT u.name FROM User AS u";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<Name> typedQuery = session.createQuery(hql, Name.class);
        List<Name> nameList = typedQuery.getResultList();
        return nameList;
    }

    public List<String> findAllFirstNames(){
        String hql = "SELECT u.name.firstname FROM User AS u";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<String> typedQuery = session.createQuery(hql, String.class);
        List<String> firstNameList = typedQuery.getResultList();
        return firstNameList;
    }

    public List<User> findAllFirstnameStartWith(String character){
        String hql = "SELECT u FROM User AS u WHERE u.name.firstname LIKE :x OR u.name.firstname LIKE :y";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
        typedQuery.setParameter("x", character.toLowerCase()+"%");
        typedQuery.setParameter("y", character.toUpperCase()+"%");
        return typedQuery.getResultList();
    }

    public List<User> findAllFirstnameStartWithAndGtPostCount(String character, int postCount){
        String hql = "SELECT u FROM User AS u WHERE (u.name.firstname LIKE :x OR u.name.firstname LIKE :y) AND u.postCount>:z";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
        typedQuery.setParameter("x", character.toLowerCase()+"%");
        typedQuery.setParameter("y", character.toUpperCase()+"%");
        typedQuery.setParameter("z", postCount);
        return typedQuery.getResultList();
    }

    public Long sumPostCount() {
        String hql = "SELECT SUM(u.postCount) FROM User AS u";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<Long> typedQuery = session.createQuery(hql, Long.class);
        return typedQuery.getSingleResult();
    }

    public Double avgPostCount() {
        String hql = "SELECT AVG(u.postCount) FROM User AS u";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<Double> typedQuery = session.createQuery(hql, Double.class);
        return typedQuery.getSingleResult();
    }

    public List<Object[]> groupByPostCount(){
        String hql = "SELECT u.postCount, COUNT(u.username) FROM User AS u GROUP BY u.postCount";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<Object[]> typedQuery = session.createQuery(hql, Object[].class);
        return typedQuery.getResultList();
    }

}
