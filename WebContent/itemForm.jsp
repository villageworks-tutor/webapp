<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

ソート：<a href="/webapp/ItemServlet2?action=sort&key=price_asc">価格の低い順</a>、<a href="/webapp/ItemServlet2?action=sort&key=price_desc">価格の高い順</a><br />

<form action="/webapp/ItemServlet2" method="post">
	追　加：商品名 <input type="text" name="name" />　価格 <input type="text" name="price" size="5" /> を
	<input type="submit" value="追加" />
	<input type="hidden" name="action" value="add" />
</form>

<form action="/webapp/ItemServlet2" method="post">
	検　索：<input type="text" name="price" size="5" /> 円以下の商品を
	<input type="submit" value="検索" />
	<input type="hidden" name="action" value="search" />
</form>


