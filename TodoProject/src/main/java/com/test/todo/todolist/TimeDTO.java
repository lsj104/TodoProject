package com.test.todo.todolist;

import lombok.Data;

/**
 * TimeDTO 클래스
 * 시간표 데이터의 시작시간, 종료시간, 회원seq, 내용, 시간표 seq
 * @author 4조
 */
@Data
public class TimeDTO {
    private String starttime;
    private String endtime;
    private String mseq;
    private String content;
    private String seq;

}
