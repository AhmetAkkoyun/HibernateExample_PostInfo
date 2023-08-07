package com.ahmetakkoyun;

import com.ahmetakkoyun.controller.PostController;
import com.ahmetakkoyun.controller.UserController;
import com.ahmetakkoyun.repository.entity.Address;
import com.ahmetakkoyun.repository.entity.Name;
import com.ahmetakkoyun.repository.entity.Post;
import com.ahmetakkoyun.repository.entity.User;
import com.ahmetakkoyun.repository.enums.EAddressType;
import com.ahmetakkoyun.repository.enums.EGender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws InterruptedException {

        UserController userController = new UserController();

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

        System.out.println(userController.save(user));
        System.out.println(userController.save(user2));

        PostController postController = new PostController();
        Post post1 = Post.builder()
                .content("ilk post")
                .userId(user.getId())
                .build();
        System.out.println(postController.save(post1));

        Thread.sleep(2000);
        userController.findAll().forEach(System.out::println);
        Thread.sleep(2000);
        userController.findAll().forEach(x-> System.out.println(x.getUsername()));

    }
}
