package com.ahmetakkoyun.repository;


import com.ahmetakkoyun.repository.entity.Post;
import com.ahmetakkoyun.repository.entity.User;
import com.ahmetakkoyun.utility.HibernateUtility;
import com.ahmetakkoyun.utility.ICrud;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class PostRepository implements ICrud<Post> {

    Session session;
    Transaction transaction;

    public PostRepository(){
        System.out.println("postRepository çalışıyor...");
    }

    @Override
    public Post save(Post post) {
        try {
            session = HibernateUtility.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(post);
            transaction.commit();
            System.out.println("post kaydı başarılı...");
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            System.out.println("post kaydı başarısız!!!!");
        } finally{
            System.out.println("oturum kapanıyor");
            session.close();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    public Optional<User> findByUserWithPostId(Long postId){
        String hql = "SELECT u FROM Post p JOIN User u ON u.id=p.userId WHERE p.id=:postId";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
        typedQuery.setParameter("postId", postId);
        User user = null;
        try {
            user = typedQuery.getSingleResult();
        }catch (Exception e){
            System.out.println("KULLANICI BULUNAMADI!!!");
        }
        return Optional.ofNullable(user);

    }
}
