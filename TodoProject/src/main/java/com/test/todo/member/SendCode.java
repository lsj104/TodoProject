package com.test.todo.member;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/sendcode.do")
public class SendCode extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String code = req.getParameter("code");

//		Random rnd = new Random();
//
//		int num = 0;
//		String temp = "";
//		String code = "";
//
//		for (int i = 0; i < 6; i++) {
//
//			num = rnd.nextInt(9);
//			temp = Integer.toString(num);
//			code += temp;
//		}
		
//		System.out.println(code);


		
		System.out.println("begin");
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("tjwls771@gmail.com", "hgzrhxtvjpysdrad");
			}
		});

		String receiver = email;
		String title = "안녕하세요. 카멜레온입니다. 인증코드 안내 메일입니다.";
		String content = "<h1>카멜레온 이메일 인증 안내메일입니다.<h1>\n<h2>인증코드는 <span style='color: red;'>" + code + " </span>입니다.</h2>\n안내드린 인증코드를 입력하시고 회원가입을 완료해주세요.";
		Message message = new MimeMessage(session);
		System.out.println(receiver);
		
		try {
			message.setFrom(new InternetAddress("tjwls771@gmail.com", "카멜레온", "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html; charset=utf-8");

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("end");
			
	}

}
