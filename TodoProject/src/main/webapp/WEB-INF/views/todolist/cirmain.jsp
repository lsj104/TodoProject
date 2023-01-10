<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>원형 TodoList</title>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <%@ include file="/WEB-INF/inc/asset.jsp" %>
</head>
<style>
    .btn-2 {
        background-image: linear-gradient(to right, #fbc2eb 0%, #a6c1ee 51%, #fbc2eb 100%);
        margin-left: 800px;
        width: 60px;
        height: 30px;
        padding: 0;
        padding-top: 5px;
        /*vertical-align: center;*/
        margin-bottom: 20px;
    }

    h3 {
        margin-bottom: 10px;
    }

    .btn:hover {
        background-position: right center;
    }

    .card .card-body {
        width: 1120px;
    }
    
</style>

<body>
<div class="container-scroller">
    <!-- navbar -->
    <%@include file="/WEB-INF/inc/navbar.jsp" %>

    <div class="container-fluid page-body-wrapper">
        <!-- sidebar -->
        <%@include file="/WEB-INF/inc/sidebar.jsp" %>

        <div class="main-panel">
            <div class="content-wrapper">
                <div class="container">
                    <div class="page-header">
                        <h3 class="page-title">
                            ${nickname}님의 TodoList
                        </h3>
                        <nav aria-label="breadcrumb">
                        </nav>
                    </div>
                    <div class="row">

                            <div class="card">
                                <div class="card-body">
                                    <div class="container">
                                        <div class="container">
                                            <a class="btn btn-2" onclick="popup()">Edit</a>
                                        </div>
                                        <div id="piechart1" style="width: 1000px; height: 600px; fontsize: 10px;"></div>
                                        <ul style="margin-left: 430px; list-style: none;" >
                                            <c:forEach items="${clist1}" var="cdto">
                                                <li >
                                                         🍎 ${cdto.starttime} ~ ${cdto.endtime}시: ${cdto.content}
                                                </li>
                                            </c:forEach>
                                        </ul>

                                    </div>
                                </div>
                           
                        </div>
                    </div>
                </div>
            </div>

            <!-- content-wrapper ends -->
            <%@include file="/WEB-INF/inc/footer.jsp" %>

        </div>
        <!-- main-panel ends -->
    </div>

</div>


<script>

    function popup() {
        var url = "/todo/todolist/circlepopup.do";
        var name = "popup test";
        var option = "width = 500, height = 600, top = 100, left = 200, location = no"
        window.open(url, name, option);
    }


    google.charts.load('current', {
        'packages': ['corechart']
    });
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {

        //차트 1
        var data = google.visualization.arrayToDataTable([
            ['content', 'nickname'],

            <%--            <c:forEach items="${clist1}" var="cdto">--%>
            <%--            ['${cdto.content} ${cdto.starttime} ~ ${cdto.endtime}시',--%>
            <%--                (100 * (${cdto.endtime} - ${cdto.starttime})) / 24],--%>
            <%--            </c:forEach>--%>

            <c:forEach items="${clist1}" var="cdto">
            ['${cdto.content}',
                (100 * (${cdto.endtime} - ${cdto.starttime})) / 24],
            </c:forEach>

        ]);

        var options = {
            // pieSliceText: 'label',
            pieSliceText: 'label',
            legend: {position: 'none'},
            slices: [{color: '#ffdddd'}, {color: '#ffe5dd'}, {color: '#ffffdd'}, {color: '#dde5ff'},
                {color: '#ddf6ff'}, {color: '#eeffdd'}, {color: '#ddffe5'}],
            pieSliceTextStyle: {
                color: 'black',
                fontsize: 10
            },

        };


        var chart = new google.visualization.PieChart(document.getElementById('piechart1'));

        chart.draw(data, options);


    }
</script>

</body>

</html>

