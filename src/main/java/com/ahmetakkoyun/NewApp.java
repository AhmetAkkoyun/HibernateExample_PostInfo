package com.ahmetakkoyun;

import com.ahmetakkoyun.controller.PostController;
import com.ahmetakkoyun.controller.UserController;
import com.ahmetakkoyun.repository.PostRepository;
import com.ahmetakkoyun.repository.UserRepository;
import com.ahmetakkoyun.repository.entity.Address;
import com.ahmetakkoyun.repository.entity.Name;
import com.ahmetakkoyun.repository.entity.Post;
import com.ahmetakkoyun.repository.entity.User;
import com.ahmetakkoyun.repository.enums.EAddressType;
import com.ahmetakkoyun.repository.enums.EGender;

import java.util.*;

public class NewApp {
    public static void main(String[] args) {

//        UserController userController = new UserController();
        UserRepository userRepository = new UserRepository();
//        PostController postController = new PostController();
        PostRepository postRepository = new PostRepository();

//        createUsers(userController);
//        createPosts(postController);

//        System.out.println("user ==> "+userController.findByUsername("ahmet"));
//        System.out.println("user ==> "+userController.findByUsername("ali"));

//        System.out.println(userRepository.findById2(25L));

//        userRepository.findAllNames().forEach(x-> System.out.println(x));

//        userRepository.findAllFirstNames().forEach(x-> System.out.println(x));

//        userRepository.findAllFirstnameStartWith("M").forEach(x-> System.out.println(x));

//        userRepository.findAllFirstnameStartWithAndGtPostCount("M",9).forEach(x-> System.out.println(x));

//        System.out.println("sumPostCount => "+userRepository.sumPostCount());

//        System.out.println("avgPostCount => "+userRepository.avgPostCount());

//        userRepository.groupByPostCount()
//                .forEach(objectArray -> {
//                    for (Object o : objectArray) {
//                        System.out.print(o+"-");
//                    }
//                    System.out.println();
//                });
//
//        userRepository.groupByPostCount()
//                .forEach(objectArray -> {
//                    System.out.println(Arrays.toString(objectArray));    // böyle de olur
//                });


//        userRepository.mostPostingUser().forEach(x-> System.out.println(x));

//        userRepository.getUsernameGenderPostCount().forEach(x-> System.out.println(Arrays.toString(x)));

//        userRepository.getUserGendersWithTotalPost().forEach(x-> System.out.println(Arrays.toString(x)));

        System.out.println(postRepository.findByUserWithPostId(10L));



    }

    public static  void createUsers(UserController userController){
        List<String> list1 = List.of("Astroloji", "Sinema");
        List<String> list2 = List.of("Dans", "Müzik");
        List<String> list3 = List.of("Seyahat", "Tiyatro");
        List<String> list4 = List.of("Kitap", "Konsol Oyunları");

        Map<EAddressType, Address> map1 = new HashMap<>();
        map1.put(EAddressType.HOME, Address.builder().city("Ankara").country("Türkiye").build());
        map1.put(EAddressType.WORK, Address.builder().city("İstanbul").country("Türkiye").build());

        Map<EAddressType, Address> map2 = new HashMap<>();
        map2.put(EAddressType.HOME, Address.builder().city("İzmir").country("Türkiye").build());
        map2.put(EAddressType.WORK, Address.builder().city("Antalya").country("Türkiye").build());

        Map<EAddressType, Address> map3 = new HashMap<>();
        map3.put(EAddressType.HOME, Address.builder().city("İstanbul").country("Türkiye").build());
        map3.put(EAddressType.WORK, new Address("İstanbul", "Türkiye"));   // böyle de olur

        User user1 = User.builder()
                .name(Name.builder()
                        .firstname("Zeliha")
                        .lastname("Ünlü")
                        .build())
                .username("zlh")
                .gender(EGender.WOMAN)
                .interests(list1)
                .addresses(map2)
                .postCount(20)
                .password("1234")
                .build();

        User user2 = User.builder()
                .name(Name.builder()
                        .firstname("Ahmet")
                        .lastname("Akkoyun")
                        .build())
                .username("ahmet")
                .gender(EGender.MAN)
                .interests(list2)
                .addresses(map1)
                .postCount(5)
                .password("123")
                .build();

        User user3 = User.builder()
                .name(Name.builder()
                        .firstname("Merve")
                        .lastname("Keskin")
                        .build())
                .username("merve")
                .gender(EGender.WOMAN)
                .interests(list2)
                .addresses(map3)
                .postCount(9)
                .password("12345")
                .build();

        User user4 = User.builder()
                .name(Name.builder()
                        .firstname("Mert")
                        .lastname("Gürel")
                        .build())
                .username("mert")
                .gender(EGender.MAN)
                .interests(list4)
                .addresses(map3)
                .postCount(12)
                .password("123456")
                .build();

        User user5 = User.builder()
                .name(Name.builder()
                        .firstname("Gökhan")
                        .lastname("Kaya")
                        .build())
                .username("gökhan")
                .gender(EGender.MAN)
                .interests(list3)
                .addresses(map1)
                .postCount(4)
                .password("123456")
                .build();

        userController.save(user1);
        userController.save(user2);
        userController.save(user3);
        userController.save(user4);
        userController.save(user5);

    }

    public static void createPosts(PostController postController){
        Post post1 = Post.builder()
                .content("içerik1")
                .userId(1L)
                .build();

        Post post2 = Post.builder()
                .content("içerik2")
                .userId(1L)
                .build();

        Post post3 = Post.builder()
                .content("içerik3")
                .userId(2L)
                .build();

        Post post4 = Post.builder()
                .content("içerik4")
                .userId(3L)
                .build();

        Post post5 = Post.builder()
                .content("içerik5")
                .userId(2L)
                .build();

        Post post6 = Post.builder()
                .content("içerik6")
                .userId(5L)
                .build();

        Post post7 = Post.builder()
                .content("içerik7")
                .userId(4L)
                .build();

        Post post8 = Post.builder()
                .content("içerik8")
                .userId(4L)
                .build();

        Post post9 = Post.builder()
                .content("içerik9")
                .userId(1L)
                .build();

        Post post10 = Post.builder()
                .content("içerik10")
                .userId(2L)
                .build();

        Post post11 = Post.builder()
                .content("içerik11")
                .userId(3L)
                .build();

        Post post12 = Post.builder()
                .content("içerik12")
                .userId(5L)
                .build();

        Post post13 = Post.builder()
                .content("içerik13")
                .userId(1L)
                .build();

        Post post14 = Post.builder()
                .content("içerik14")
                .userId(2L)
                .build();

        Post post15 = Post.builder()
                .content("içerik15")
                .userId(3L)
                .build();

        Post post16 = Post.builder()
                .content("içerik16")
                .userId(4L)
                .build();

        postController.save(post1);
        postController.save(post2);
        postController.save(post3);
        postController.save(post4);
        postController.save(post5);
        postController.save(post6);
        postController.save(post7);
        postController.save(post8);
        postController.save(post9);
        postController.save(post10);
        postController.save(post11);
        postController.save(post12);
        postController.save(post13);
        postController.save(post14);
        postController.save(post15);
        postController.save(post16);

    }

}
