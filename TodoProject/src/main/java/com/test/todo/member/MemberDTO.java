package com.test.todo.member;

import lombok.Data;

/**
 * MemberDTO클래스
 * 이메일, 비밀번호, 닉네임, 활동유무, 응원메시지, 회원 번호, 카테고리, 이미지, 테마, 폰트타입, sub투두리스트 유무, 타임테이블 유무, 캘린더 유무, 24시간 계획표 유무  
 * @author 4조
 */
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

    private String subToDo;
    private String timeTable;
    private String timeCal;
    private String timeCir;
    
   
}
































