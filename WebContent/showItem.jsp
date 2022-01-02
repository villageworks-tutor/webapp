<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>Show All Item</title>
</head>
<body>

	<table border="1">
		<tr>
			<td>NO</td>
			<td>商品名</td>
			<td>価格</td>
		</tr>
		<!-- 商品の表示：taglibによる繰り返し処理 -->
		<c:forEach items="${items}" var="item">
		<tr>
			<td>${item.code}</td>
			<td>${item.name}</td>
			<td>${item.price}</td><!-- TODO：3桁区切り表示に変更する（補足題） -->
		</tr>
		</c:forEach>
	</table>

</body>
</html>