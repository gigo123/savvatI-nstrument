<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuView.jsp"%>
<div class="col-9">
	создание нового документа премещения
	<div class="table-content table-responsive">
	<form:form action="./createExDoc" method="post" modelAttribute = "exDocWEBList">
		<table class="table text-center">
			<thead>
				<tr>
					<th class="text-left">номер</th>
					<th>место видачи</th>
					<th>ячека видачи</th>
					<th>место приема</th>
					<th>ячейка приема</th>
					<th>инструмент</th>
					<th>количество</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${exDocWEBList.docList}"  varStatus="i">
					<tr id="docRow${i.index}">
						<td class="doc-id">
								${i.index}
							</td>
							<td class="doc-out-loc text-left">
								<form:select path="docList[${i.index }].outLocationString">
								<form:option value="NONE" label="Select" />
								<form:options items="${locationList}" />
								</form:select>
							</td>
						<td class="doc-out-box text-left wide-column">
							
						</td>
						<td class="doc-in-loc">
						<form:select path="docList[${i.index }].outLocationString">
								<form:option value="NONE" label="Select" />
								<form:options items="${locationList}" />
								</form:select>
						</td>
						<td class="doc-in-box">
						ячейка вход
						</td>
						<td class="doc-instrum">
							<form:select path="docList[${i.index }].inBoxString">
							<form:option value="NONE" label="Select" />
							<form:options items="${instrumentMap}" />
							</form:select>
							</td>
						<td class="doc-amount text-left">
						<form:input path ="docList[${i.index }].amount" id="amount"
						 class="form__input" required ="true"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="btn btn-size-md btn-shape-square btn-fullwidth"
		id="checkOut" onclick="confirmCreate()">Proceed To Checkout
		<input type="submit" value="создать ячейку"
						class="btn btn-size-md" />
						</div>
						</form:form>
</div>

<script>
function confirmCreate() {
	$.ajax({
				url : "./createExDoc",
				type : "POST",
				dataType : "html",
				data : {docMap: docMap},
				success : function(responseJson) {
					var returnedData = JSON.parse(responseJson);
			message("sucses");
				},
				error : function(response) { // Данные не отправлены
					message("error");
				}
			});
}
</script>
	
</div>
<!--  close div of SideMenuView -->
</div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/include/FooterView.jsp"%>