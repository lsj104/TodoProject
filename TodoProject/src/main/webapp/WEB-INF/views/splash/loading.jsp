<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="shortcut icon" href="/todo/asset/images/favicon.png">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>카멜레온 집사</title>
    <link rel="stylesheet" href="/todo/asset/css/loading.css">

    <style>
        .logo {
            width: 300px;
            height: 150px;
        }
    </style>
</head>
<body>
<!--
https://rikschennink.github.io/fitty
-->
<div class="splash">

    <div class="counter">
        <div><h1>${message}</h1></div>
    </div>

    <img class="logo" src="/todo/asset/images/logo.png">

</div>
<script src="/todo/asset/js/loading.js"></script>
<meta http-equiv="refresh" content="4; url='/todo/todolist/todolistmain.do'">
</body>
</html>
