package com.example.demo.domain;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberRequestDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;


    @Builder
    public MemberRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }


    public Member toEntity(){
        return Member.builder()
                .name(name)
                .email(email)
                .build();
    }

}
