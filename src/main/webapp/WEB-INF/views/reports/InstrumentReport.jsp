<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuReportView.jsp"%>
<div class="col-9">
	отчет по наличию инструмента
	<form:form action="./instrumentReport" method="post"
		modelAttribute="report">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<div class="row mb--20">
			<div class="col-3">виберете инструмент</div>
			<form:select path="id">
						<form:options items="${instrumentMap}" />
					</form:select>
		</div>

		<div class="row mb--20">
		<div class="col-3">
		место хран
		</div>
		<div class="col-2">
		ячейка
		</div>
		<div class="col-2">
		количество
		</div>
		<div class="col-2">
		ед измирения
		</div>
		</div>
		<c:forEach items="${report.locReport}" varStatus="k">
		<div class="row mb--20">
		<div class="col-3ф">
					<form:label path="locReport[${k.index}].locationName">
					${report.locReport[k.index].locationName}
					</form:label>
				</div>
				<div class="col-2">
					<form:label path="locReport[${k.index}].name">
					${report.locReport[k.index].name}
					</form:label>
				</div>

				<div class="col-2">
					<form:label path="locReport[${k.index}].amount">
					${report.locReport[k.index].amount}
					</form:label>
				</div>
					<div class="col-2">
					<form:label path="locReport[${k.index}].measure">
					${report.locReport[k.index].measure}
					</form:label>
				</div>
			</div>
</c:forEach>


		<input type="submit" value="создать отчет" class="btn btn-size-md" />
		
	</form:form>

	

</div>
<!--  close div of SideMenuView -->
</div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/include/FooterView.jsp"%>