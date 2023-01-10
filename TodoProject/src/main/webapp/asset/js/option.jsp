<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>

if ('${theme}' == '밤하늘의 별 테마') {
	$('head').append('<link rel="stylesheet" href="/todo/asset/css/dark.css">');
}

if ('${fonttype}' == '폰트 변경권 - 교보손글씨체') {
	$('head').append('<link rel="stylesheet" href="/todo/asset/css/kyoboHand.css">')
}

if ('${fonttype}' == '폰트 변경권 - 마이쮸체') {
	$('head').append('<link rel="stylesheet" href="/todo/asset/css/mychew.css">')
}

if ('${fonttype}' == '폰트 변경권 - 조선 궁서체') {
	$('head').append('<link rel="stylesheet" href="/todo/asset/css/chosunGs.css">')
}

</script>