package com.ahmetakkoyun.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable    // gömülü sınıf
public class Name {

    private String firstname;
    private String middlename;
    private String lastname;

}
