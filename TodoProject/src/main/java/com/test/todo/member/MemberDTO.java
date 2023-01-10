package com.test.todo.member;

import lombok.Data;

@Data
public class MemberDTO {
    private String email;
    private String pw;
    private String nickname;
    private String active;
    private String message;
    
    //memberInfo
    private String category;
    private String seq;
    private String image;
    
    private String theme;
    private String fonttype;
   
}
































