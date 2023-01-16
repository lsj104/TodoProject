package com.test.todo.challenge;

import lombok.Data;

/**
 * 
 * ChallengeDTO
 * 
 * @author 4조
 *
 */
@Data
public class ChallengeDTO {

	private int seq;
	private String createDate;
	private String duedatenumber;
	private String membercnt;
	private int cseq;
	private String name;
	private String hseq;
	private String mission;
	
	
	/**
	 * ChallengeDTO 생성자
	 * 
	 * @param seq 챌린지 번호
	 * @param createDate 생성날짜
	 * @param duedatenumber 모집기간
	 * @param membercnt 모집인원
	 * @param cseq 카테고리 번호
	 * @param name 챌린지 이름
	 * @param hseq  회원번호
	 * @param mission 공동미션
	 */
	public ChallengeDTO(int seq, String createDate, String duedatenumber, String membercnt, int cseq, String name,
			String hseq, String mission) {
		
		super();
		this.seq = seq;
		this.createDate = createDate;
		this.duedatenumber = duedatenumber;
		this.membercnt = membercnt;
		this.cseq = cseq;
		this.name = name;
		this.hseq = hseq;
		this.mission = mission;
	}


	@Override
	public String toString() {
		return "ChallengeDTO [seq=" + seq + ", createDate=" + createDate + ", duedatenumber=" + duedatenumber
				+ ", membercnt=" + membercnt + ", cseq=" + cseq + ", name=" + name + ", hseq=" + hseq + ", mission="+ mission + "]";
	}
	
	
	
	
}
