package com.ahmetakkoyun.repository.entity;

import com.ahmetakkoyun.repository.enums.EAddressType;
import com.ahmetakkoyun.repository.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded   // gömülü sınıfın gömüldüğü yer
    private Name name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @Size(min=4, max=32, message="Sifre 4 ile 32 arasinda olmalidir")
    private String password;

    @Transient // bu özelliğin database de görünmemesi için
    private int age;

    @Enumerated(EnumType.STRING)
    private EGender gender;

    @ElementCollection  // hücrede liste barındırmak için
    @Builder.Default        // hiçbir şey vermezsek bu default değerler gelir
    List<String> interests = new ArrayList<>();

    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    Map<EAddressType,Address> addresses;

    int postCount;

}
