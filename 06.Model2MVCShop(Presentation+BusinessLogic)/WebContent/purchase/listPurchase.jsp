<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<%-- <%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.domain.*" %>
<%@ page import="com.model2.mvc.common.*" %> --%>

<%-- <%
	List<Purchase> list = (List<Purchase>)request.getAttribute("list");
	Page resultPage = (Page)request.getAttribute("resultPage");
	
	Search search = (Search)request.getAttribute("search");

	//HashMap<String,Object> map=(HashMap<String,Object>)request.getAttribute("map");
	/*SearchVO searchVO=(SearchVO)request.getAttribute("searchVO");
	
	int total=0;
	ArrayList<Purchase> list=null;
	if(map != null){
		total=((Integer)map.get("count")).intValue();
		list=(ArrayList<Purchase>)map.get("list");
	}
	
	int currentPage=searchVO.getPage();
	
	int totalPage=0;
	if(total > 0) {
		totalPage= total / searchVO.getPageUnit() ;
		if(total%searchVO.getPageUnit() >0)
	totalPage += 1;
	}*/
%> --%>

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetUserList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 <%-- <%= resultPage.getTotalCount() %> --%>${ resultPage.totalCount } 건수, 현재  <%-- <%= resultPage.getCurrentPage() %> --%>${ resultPage.currentPage } 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%-- <%
		for(int i=0; i<list.size(); i++) {
		Purchase purchase = list.get(i);
	%> --%>
	
	<c:set var="i" value="0" />
	<c:forEach var="purchase" items="${list}">
		<c:set var="i" value="${ i+1 }" />
		<tr class="ct_list_pop">
			<td align="center"><a href="/getPurchase.do?tranNo=<%-- <%= purchase.getTranNo() %> --%>${ purchase.tranNo }">${ i }</a></td>
			<td></td>
			<td align="left">
				<a href="/getUser.do?userId=<%-- <%= purchase.getBuyer().getUserId() %> --%>${ purchase.buyer.userId }"><%-- <%= purchase.getBuyer().getUserId() %> --%>${ purchase.buyer.userId }</a>
			</td>
			<td></td>
			<td align="left"><%-- <%= purchase.getReceiverName() %> --%>${ purchase.receiverName }</td>
			<td></td>
			<td align="left"><%-- <%= purchase.getReceiverPhone() %> --%>${ purchase.receiverPhone }</td>
			<td></td>
			<td align="left">현재
						<%-- <% if(purchase.getTranCode().trim().equals("1")) { %>
							구매완료
						<% } else if(purchase.getTranCode().trim().equals("2")) { %>
							배송중
						<% } else if(purchase.getTranCode().trim().equals("3")) { %>
							배송완료
						<% } else { %>
							Error!!!!!!!!!!!!!!!!
						<% } %> --%>
				<c:if test="${ ! empty purchase.tranCode && fn:trim(purchase.tranCode)=='1' }">
					구매완료
				</c:if>
				<c:if test="${ ! empty purchase.tranCode && fn:trim(purchase.tranCode)=='2' }">
					배송중
				</c:if>
				<c:if test="${ ! empty purchase.tranCode && fn:trim(purchase.tranCode)=='3' }">
					배송완료
				</c:if>
				<c:if test="${ empty purchase.tranCode }">
					Error!!!!!!!!!!!!!!!!
				</c:if>
				상태 입니다.
				<%-- <% if(purchase.getTranCode().trim().equals("2")) { %> --%>
				<c:if test="${ ! empty purchase.tranCode && fn:trim(purchase.tranCode)=='2' }">
					<a href="/updateTranCode.do?tranNo=<%-- <%= purchase.getTranNo() %> --%>${ purchase.tranNo }&tranCode=3">물건도착</a>
				</c:if>
				<%-- <% } %> --%>
			</td>
		</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<%-- <% } %> --%>
	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
			<input type="hidden" id="currentPage" name="currentPage" value=""/>
		 	<%-- <% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					◀ 이전
			<% }else{ %>
					<a href="javascript:fncGetProductList('<%=resultPage.getCurrentPage()-1%>')">◀ 이전</a>
			<% } %>

			<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetProductList('<%=i %>');"><%=i %></a>
			<% 	}  %>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					이후 ▶
			<% }else{ %>
					<a href="javascript:fncGetProductList('<%=resultPage.getEndUnitPage()+1%>')">이후 ▶</a>
			<% } %> --%>
		 	<jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>