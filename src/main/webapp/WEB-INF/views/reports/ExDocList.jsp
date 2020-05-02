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
		modelAttribute="docList">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<div class="row mb--20">
			<div class="col-1">номер</div>
			<div class="col-1">количетво стр</div>
			<div class="col-1">общее кол </div>
			<div class="col-1">дата</div>
		</div>

		<c:forEach items="${docList}" varStatus="i">
			<div class="row mb--20">
			
				<div class="col-2">
					<form:label path="docList[${i.index }].numberString">
					</form:label>
				</div>

				<div class="col-1">
					<form:label path="docList[${i.index }].totalInstrum;">
					</form:label>
				</div>
				<div class="col-2">
						<form:label path="docList[${i.index }]. totalAmount;">
					</form:label>
				</div>
				<div class="col-1">
					<form:label path="docList[${i.index }].  date;">
					</form:label>
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