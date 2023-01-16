package com.test.todo.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * LoginOK 클래스
 * 로그인 했을 때 사용자 이메일, pw를 받아옵니다.
 * 로그인 했을 때 사용자 카테고리, 번호, 비밀번호, 프로필이미지를 세션에 저장합니다.
 * @author 4조
 */
@WebServlet("/member/loginok.do")
public class LoginOk extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String pw = req.getParameter("pw");

        MemberDTO dto = new MemberDTO();

        dto.setEmail(email);
        dto.setPw(pw);

        MemberDAO dao = new MemberDAO();

        MemberDTO result = dao.login(dto);
        
        MemberDTO option = dao.option(dto, email);
        
        MemberDTO subToDo = dao.isSubToDo(email);
        MemberDTO timeTable = dao.isTimeTable(email);
        MemberDTO timeCal = dao.isTimeCal(email);
        MemberDTO timeCir = dao.isTimeCir(email);

        if (result != null) {

            HttpSession session = req.getSession();

            session.setAttribute("auth", email);
            session.setAttribute("nickname", result.getNickname());
            session.setAttribute("message", result.getMessage());
            
            session.setAttribute("category", result.getCategory()); // 사용자 카테고리
            session.setAttribute("seq", result.getSeq());			// 사용자 번호
            session.setAttribute("pw", pw);							// 사용자 비밀번호
            session.setAttribute("image", result.getImage());		// 프로필 이미지
            
            session.setAttribute("theme", option.getTheme());
            session.setAttribute("fonttype", option.getFonttype());
            
            session.setAttribute("subToDo",subToDo.getSubToDo());
            session.setAttribute("timeTable",timeTable.getTimeTable());
            session.setAttribute("timeCal",timeCal.getTimeCal());
            session.setAttribute("timeCir",timeCir.getTimeCir());
            
            
            
            PlusRewardDAO pdao = new PlusRewardDAO();
            
            int isLoginPoint = pdao.isLoginPoint((String)session.getAttribute("auth"), 2);
            
            System.out.println(isLoginPoint);
            
            if (isLoginPoint == 0) { 
            	int prResult = pdao.getPoint((String)session.getAttribute("auth"), 2);
            	pdao.updatePoint((String)session.getAttribute("auth"));
            } 
            
            resp.sendRedirect("/todo/splash/loading.do");
            
            
        } else {

            PrintWriter writer = resp.getWriter();
            writer.print("<script>");
            writer.print("alert('failed');");
            writer.print("history.back();");
            writer.print("</script>");
            writer.close();
            System.out.println(dto);

        }

    }
}

































