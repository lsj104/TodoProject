<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>

        body {
            background-color: #f1edf3;
            display: flex;
        }

        .form-popup {
            background-color: white;
            box-sizing: border-box;
            color: black;
            display: inline-block;
            height: 500px;
            font-size: 14px;
            position: absolute;
            text-align: center;
            top: 5%;
            width: 400px;
            position: relative;
            margin-left: 40px;
            border-radius: 5px;
        }

        .form-popup-header {
            font-size: 28px;
            padding: 25px 5px 5px 5px;
        }


        input[type="text"], input[type="submit"] {
            background-color: white;
            border: 1px solid #f1edf3;
            border-radius: 3px;
            color: grey;
            display: block;
            margin: auto;
            margin-bottom: 5px;
            margin-top: 5px;
            padding: 5px;
            width: 30%;
        }

        label {
            font-size: 16px;
        }

        .form-popup-submit {
            background-color: #f1edf3;
            color: black;
            border: 0px;
            border-radius: 3px;
            font-weight: 900;
            margin: 20px;
            padding: 10px;
            width: 30%;
        }

        .form-popup-submit:hover {
            background-color: #f1edf3;
            color: gray;
        }

        input:focus {
            outline: none;
        }

        select:focus {
            outline: none;
            box-shadow: white;
        }


    </style>

    <script src="https://code.jquery.com/jquery-3.6.1.js"></script>
    <script>

        $(function () {
            $('#endtime').change(function () {
                var endtime = Number($(this).val());
                var starttime = Number($('#starttime').val());

                if (endtime <= starttime) {
                    alert('종료 시간을 다시 선택해 주세요.');
                    $('#login-form-popup-submit').attr("disabled", true);
                } else {
                    $('#login-form-popup-submit').attr("disabled", false);
                }
            });
        });

        $(function () {
            $('#login-form-popup-submit').on('click', function () {
                if ($("#content").val() == "") {
                    alert("내용을 입력해 주세요.");
                    $("#content").focus();
                    return false;
                }
            })

        });

        function confirm_reset() {
            if (confirm("정말 삭제하시겠습니까?") == true) {
                location.href = '/todo/timedel.do';
            } else {
                return false;
            }
        }

    </script>
</head>
<body>

<div class="form-popup-container" id="login-form-popup-container">
    <form name="form-popup" class="form-popup" id="login-form-popup" action="/todo/todolist/popupok.do">
        <p class="form-popup-header" id="login-form-popup-header">
            Time Table
        </p>
        <div class="list_option" style="text-align: center;">
            <h3>시작 시간</h3>
            <select id="starttime" name="starttime" class="starttime" onchange="selectBoxChange(this.value);"
                    style="display: inline; width: 100px; height:30px; font-size:10px;">
                <option>시작 시간</option>
                <option value="0">00:00</option>
                <option value="1">01:00</option>
                <option value="2">02:00</option>
                <option value="3">03:00</option>
                <option value="4">04:00</option>
                <option value="5">05:00</option>
                <option value="6">06:00</option>
                <option value="7">07:00</option>
                <option value="8">08:00</option>
                <option value="9">09:00</option>
                <option value="10">10:00</option>
                <option value="11">11:00</option>
                <option value="12">12:00</option>
                <option value="13">13:00</option>
                <option value="14">14:00</option>
                <option value="15">15:00</option>
                <option value="16">16:00</option>
                <option value="17">17:00</option>
                <option value="18">18:00</option>
                <option value="19">19:00</option>
                <option value="20">20:00</option>
                <option value="21">21:00</option>
                <option value="22">22:00</option>
                <option value="23">23:00</option>
            </select>
            <h3>종료 시간</h3>
            </script>
            <select id="endtime" name="endtime" class="starttime" onchange="selectBoxChange(this.value);"
                    style="display: inline; width: 100px; height:30px; font-size:10px;">
                <option>종료 시간</option>
                <option value="0">00:00</option>
                <option value="1">01:00</option>
                <option value="2">02:00</option>
                <option value="3">03:00</option>
                <option value="4">04:00</option>
                <option value="5">05:00</option>
                <option value="6">06:00</option>
                <option value="7">07:00</option>
                <option value="8">08:00</option>
                <option value="9">09:00</option>
                <option value="10">10:00</option>
                <option value="11">11:00</option>
                <option value="12">12:00</option>
                <option value="13">13:00</option>
                <option value="14">14:00</option>
                <option value="15">15:00</option>
                <option value="16">16:00</option>
                <option value="17">17:00</option>
                <option value="18">18:00</option>
                <option value="19">19:00</option>
                <option value="20">20:00</option>
                <option value="21">21:00</option>
                <option value="22">22:00</option>
                <option value="23">23:00</option>
            </select>
        </div>

        <input id="content" name="content" style="margin-top: 30px" type="text" placeholder="Content">

        <div>
            <button type="submit" class="form-popup-submit" id="login-form-popup-submit"
                    name="login-form-popup-submit-button">
                저장하기
            </button>
        </div>

        <form action="/todo/timedel.do">
            <div>
                <button type="submit" class="form-popup-submit" id="reset"
                        name="login-form-popup-submit-button" onclick="confirm_reset()">
                    초기화 하기
                </button>
            </div>
        </form>
    </form>

</div>

</body>
</html>
