package com.example.demo.api;


import com.example.demo.domain.Member;
import com.example.demo.domain.MemberDto;
import com.example.demo.domain.MemberRequestDto;
import com.example.demo.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {


    private final MemberService memberService;

    @GetMapping("/api/members")
    public Result members() {
        List<Member> members = memberService.findMembers();
        List<Object> collect = members.stream()
                .map(member -> new MemberDto(member.getName(), member.getEmail()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }


    @PostMapping("/api/member")
    public Long create(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        return memberService.join(memberRequestDto.toEntity());
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }


}
