package com.ahmetakkoyun;

import com.ahmetakkoyun.repository.entity.Address;
import com.ahmetakkoyun.repository.entity.Name;
import com.ahmetakkoyun.repository.entity.User;
import com.ahmetakkoyun.repository.enums.EAddressType;
import com.ahmetakkoyun.repository.enums.EGender;
import com.ahmetakkoyun.utility.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {

        List<String> interest1 = List.of("Müzik", "Dans");

        Map<EAddressType, Address> map1 = new HashMap<>();
        map1.put(EAddressType.HOME, Address.builder().city("Ankara").country("Türkiye").build());
        map1.put(EAddressType.WORK, Address.builder().city("İstanbul").country("Türkiye").build());

        User user = User.builder()
                .name(Name.builder()
                        .firstname("Ahmet")
                        .lastname("Akkoyun")
                        .build())
                .username("Ahmet")
                .password("12345")
                .gender(EGender.MAN)
                .interests(interest1)    // yukardaki listelerden birini koyduk
                .addresses(map1)
                .age(25)
                .build();

        User user2 = User.builder()
                .name(Name.builder()
                        .firstname("Ece")
                        .middlename("Beren")
                        .lastname("Erenoğlu")
                        .build())
                .username("Ece")
                .password("12345645454")
                .gender(EGender.WOMAN)
                .addresses(Map.of(
                        EAddressType.HOME, Address.builder().city("İzmir").country("Türkiye").build(),
                        EAddressType.WORK, Address.builder().city("Antalya").country("Türkiye").build()
                        )
                )    // böyle Map.of da olur
                .age(18)
                .interests(List.of("Sinema", "Tiyatro"))  // böyle List.of ile de olur.
                .build();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtility.getSessionFactory().openSession();   // veritabanı bağlantısı açmak için
            transaction = session.beginTransaction();   // veri transferinde veri kaybını önler (mesela para aktarmada hata olursa kayıp olmaz)
            session.save(user);
            session.save(user2);
            System.out.println(user2.getInterests().size());
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        } finally{
            session.close();
        }
    }
}