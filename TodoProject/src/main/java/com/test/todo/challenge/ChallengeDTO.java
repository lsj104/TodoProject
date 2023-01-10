package com.test.todo.challenge;

import lombok.Data;

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
