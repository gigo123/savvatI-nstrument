<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuDocView.jsp"%>
<div class="col-9">
	список документов перемещения
	<form:form action="./createExDoc" method="post"
		modelAttribute="exDocWEBList">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<div class="row mb--20">
			<div class="col-1">номер</div>
			<div class="col-2">место видачи</div>
			<div class="col-1">ячека видачи</div>
			<div class="col-2">место приема</div>
			<div class="col-1">ячейка приема</div>
			<div class="col-2">инструмент</div>
			<div class="col-2">количество</div>
			<div class="col-1">удалить</div>
		</div>

		<c:forEach items="${exDocWEBList.docList}" varStatus="i">
			<div class="row mb--20">
				<div class="col-1">${i.index+1}</div>
				<div class="col-2">
					<form:select path="docList[${i.index }].outLocation"
						onchange="searchOutBoxNE(${i.index })">
						<form:options items="${locationList}" />
					</form:select>
				</div>

				<div class="col-1">
					<form:select path="docList[${i.index }].outBox"
						onchange="searchInstrum(${i.index })">
					</form:select>
				</div>
				<div class="col-2">
					<form:select path="docList[${i.index }].inLocation"
						onchange="searchOutBox(${i.index })">
						<form:options items="${locationList}" />
					</form:select>
				</div>
				<div class="col-1">
					<form:select path="docList[${i.index }].inBox">
					</form:select>
				</div>
				<div class="col-2">
					<form:select path="docList[${i.index }].instrument">
						<form:options items="${instrumentMap}" />
					</form:select>
				</div>
				<div class="col-2">
					<form:input path="docList[${i.index }].amount"
						id="amount-${i.index}" class="form__input" required="true" />
				</div>
				<div class="col-1">
					<input type="button" value="remove"
						onclick="removeRow(${i.index })">
				</div>
			</div>
		</c:forEach>



		<input type="submit" value="создать документ" class="btn btn-size-md" />
		<input class="btn btn-size-md" id="searchInstrument" value="add row"
			onclick="addRow()" />
	</form:form>
	<div id="feedback"></div>

	

</div>
<!--  close div of SideMenuView -->
</div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/include/FooterView.jsp"%>