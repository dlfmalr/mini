package com.example.ms1.note.member;

import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Getter
    @Setter
    class MemberForm {
        @NotEmpty(message = "아이디는 필수항목입니다.")
        private String loginId;
        @NotEmpty(message = "비밀번호는 필수항목입니다.")
        private String password;
        @NotEmpty(message = "닉네임은 필수항목입니다.")
        private String nickname;
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        @NotEmpty(message = "이메일은 필수항목입니다.")
        private String email;
    }

    @GetMapping("/signup")
    public String signup(MemberForm memberForm) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberForm memberForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        memberService.save(memberForm.getLoginId(), memberForm.getPassword(), memberForm.getNickname(), memberForm.getEmail());

        return "redirect:/";
    }
}
