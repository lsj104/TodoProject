<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>카멜레온 집사</title>
    <%@ include file="/WEB-INF/inc/asset.jsp" %>
    <script>
        function popup() {
            var url = "/todo/todolist/popup.do";
            var name = "popup test";
            var option = "width = 500, height = 600, top = 100, left = 200, location = no"
            window.open(url, name, option);
        }

        function confirm_reset() {
            if (confirm("정말 삭제하시겠습니까?") == true) {

            } else {
                return false;
            }
        }


        let temp = '';
        let tempcolor = '';

        //202, 203
        function colorChange(seq) {

            if (temp != seq) {

                var color = ["#ffdddd", "#ffe5dd", "#ffffdd", "#dde5ff", "#ddf6ff", "#eeffdd"];

                var num = Math.floor(Math.random() * color.length);

                temp = seq;

                tempcolor = color[num];
            }

            return tempcolor;
        }


    </script>
    <style>
        body {
            font-family: 'Montserrat', sans-serif;
            margin: 0;
        }
        

        .xcontainer {
            display: flex;
            align-items: center;
            align-content: center;
            flex-wrap: wrap;
            width: 10px;
            height: 10px;
            margin: 0 auto;
        }

        .card {
        	width: 500px;
			margin: 0 auto;
        }

        .btn {
            flex: 1 1 auto;
            margin: 10px;
            padding: 5px;
            text-align: center;
            text-transform: uppercase;
            transition: 0.5s;
            background-size: 200% auto;
            color: white;
            text-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
            box-shadow: 0 0 20px #eee;
            border-radius: 10px;
        }

        .btn:hover {
            background-position: right center;
        }

        .btn-2 {
            background-image: linear-gradient(to right, #fbc2eb 0%, #a6c1ee 51%, #fbc2eb 100%);
            margin-left: 350px;
        }


    </style>

</head>

<body>
<div class="container-scroller">
    <!-- navbar -->
    <%@include file="/WEB-INF/inc/navbar.jsp" %>
    <div class="container-fluid page-body-wrapper">
        <!-- sidebar -->
        <%@include file="/WEB-INF/inc/sidebar.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="page-header">
                            <h3 class="page-title">
                                Time Table
                            </h3>
                            <nav aria-label="breadcrumb">
                            </nav>
                        </div>
                
                         
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title" style="text-align: center">Time Table</h4>
                                        <div class="container">
                                            <a class="btn btn-2" onclick="popup()">Edit</a>
                                        </div>
                                        <table class="table" id="table">
                                            <thead>
                                            <tr>
                                                <th>Time</th>
                                                <th>Content</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="i" begin="0" end="23">
                                                <tr id="tr${i}">
                                                    <td>${i}:00</td>
                                                    <td>
                                                        <c:forEach var="dto" items="${list}">
                                                            <c:if test="${dto.starttime <= i and dto.endtime > i}">
                                                                ${dto.content}
                                                                <script>
                                                                    document.getElementById('tr${i}').style.backgroundColor = colorChange(${dto.seq});
                                                                </script>
                                                            </c:if>
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
              
                        <!-- content-wrapper ends -->
                    </div>
						<%@include file="/WEB-INF/inc/footer.jsp" %>
                    <!-- <!— main-panel ends —> -->
                </div>
              
            </div> 
             
        </div>

</body>

</html>