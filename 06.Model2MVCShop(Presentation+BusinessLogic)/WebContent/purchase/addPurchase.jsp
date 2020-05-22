<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ page import="com.model2.mvc.service.domain.*" %>

<%-- <%
	Purchase purchase = (Purchase)request.getAttribute("purchase");
%> --%>

<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=${ purchase.tranNo }" method="post">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td><%-- <%= purchase.getPurchaseProd().getProdNo() %> --%>${ purchase.purchaseProd.prodNo }</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td><%-- <%= purchase.getBuyer().getUserId() %> --%>${ purchase.buyer.userId }</td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
		
			<%-- <%= purchase.getPaymentOption() %> --%>${ purchase.paymentOption }
		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td><%-- <%= purchase.getReceiverName() %> --%>${ purchase.receiverName }</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td><%-- <%= purchase.getReceiverPhone() %> --%>${ purchase.receiverPhone }</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td><%-- <%= purchase.getDivyAddr() %> --%>${ purchase.divyAddr }</td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td><%-- <%= purchase.getDivyRequest() %> --%>${ purchase.divyRequest }</td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td><%-- <%= purchase.getDivyDate() %> --%>${ purchase.divyDate }</td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>