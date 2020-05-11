<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuReportView.jsp"%>
<div class="col-9">
	общий отчет об хранении
	
	<form:form action="./locationReport" method="post"
		modelAttribute="report">
		<div class="row mb--20">
		<div class="col-4">
		название
		</div>
		<div class="col-2">
		количество
		</div>
		<div class="col-2">
		ед измирения
		</div>
		</div>
		<c:forEach items="${report.locReportList}" varStatus="k">
		<div class="row mb--20">
			<div class="col-4">
				<form:label path="locReportList[${k.index}].name">
					${report.locReportList[k.index].name}
					</form:label>
			</div>
			<div class="col-2">
				<form:label path="locReportList[${k.index}].totalAmount">
					${report.locReportList[k.index].totalAmount}
					</form:label>
			</div>
		</div>
			<c:forEach items="${report.locReportList[k.index].reportBox}" varStatus="i">
			<div class="row mb--20">
			
				<div class="col-4">
					<form:label path="locReportList[${k.index}].reportBox[${i.index }].name">
					${report.locReportList[k.index].reportBox[i.index].name}
					</form:label>
				</div>

				<div class="col-2">
					<form:label path="locReportList[${k.index}].reportBox[${i.index }].totalAmount">
					${report.locReportList[k.index].reportBox[i.index].totalAmount}
					</form:label>
				</div>

			</div>
			<c:forEach items="${report.locReportList[k.index].reportBox[i.index].reportItems}" varStatus="j">
			<div class="row mb--20">
			
				<div class="col-4">
					<form:label path="locReportList[${k.index}].reportBox[${i.index}].reportItems[${j.index}].name">
					${report.locReportList[k.index].reportBox[i.index].reportItems[j.index].name}
					</form:label>
				</div>

				<div class="col-2">
					<form:label path="locReportList[${k.index}].reportBox[${i.index}].reportItems[${j.index}].amount">
					${report.locReportList[k.index].reportBox[i.index].reportItems[j.index].amount}
					</form:label>
				</div>
					<div class="col-2">
					<form:label path="locReportList[${k.index}].reportBox[${i.index}].reportItems[${j.index}].measure">
					${report.locReportList[k.index].reportBox[i.index].reportItems[j.index].measure}
					</form:label>
				</div>
			</div>
			</c:forEach>
		</c:forEach>
</c:forEach>
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