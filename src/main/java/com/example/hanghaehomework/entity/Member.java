package com.example.hanghaehomework.entity;

import com.example.hanghaehomework.dto.SignupRequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;



    public Member(SignupRequestDto signupRequestDto) {

        this.username = signupRequestDto.getUsername();
        this.password = signupRequestDto.getPassword();

    }
}



