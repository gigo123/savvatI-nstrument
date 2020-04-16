<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuView.jsp"%>
<div class="col-9">
	создание нового документа приема
	<div class="table-content table-responsive">
	<form:form action="./createInDoc" method="post" modelAttribute = "exDocWEBList">
	 <form:errors path = "*" cssClass = "errorblock" element = "div" />
		<table class="table text-center">
			<thead>
				<tr>
					<th class="text-left">номер</th>
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
								${i.index+1}
							</td>
							<td class="doc-out-loc text-left" id = "doc-out-loc-${i.index}">
								<form:select path="docList[${i.index }].outLocation">
								<form:option value="NONE" label="Select" />
								<form:options items="${locationList}" />
								</form:select>
							</td>
						<td class="doc-out-box text-left wide-column">
							<form:input path ="docList[${i.index }].outBox" id="outBox-${i.index}"
						 class="form__input" required ="true"/>       
						  <form:errors path = "docList[${i.index }].outBox" />
						</td>
						<td class="doc-in-loc" id = "doc-in-loc-${i.index}">
						<form:select path="docList[${i.index }].inLocation">
								<form:option value="NONE" label="Select" />
								<form:options items="${locationList}" />
								</form:select>
						</td>
						<td class="doc-amount text-left">
						<form:input path ="docList[${i.index }].amount" id="amount-${i.index}"
						class="form__input" required ="true"/>
						<form:errors path = "docList[${i.index }].amount" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
		<input type="submit" value="создать документ"
						class="btn btn-size-md" />
						
							</form:form>
							<input type="submit" class="btn btn-size-md" 
							id="searchBox" value="searchBox" onclick="searchBox()"/>
							<div id="feedback"></div>
							<input type="submit" class="btn btn-size-md" 
							id="searchInstrument" value="searchInstrument" onclick="searchInstrum()"/>
							<div id="feedback"></div>
							
</div>

<script>
function searchBox() {
	
    var search = {}
	var list = document.getElementById("docList0.outLocation");
	var id = list.options[list.selectedIndex].value;
	alert("you select "+id);
    search["boxId"]= id;
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/istrumnet1/createInDoc/getBoxFilter",
        data : JSON.stringify(search),
        dataType : 'json',
        timeout : 100000,
        success : function(data) {
            console.log("SUCCESS: ", data);
            display(data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
            display(e);
        },
        done : function(e) {
            console.log("DONE");
            enableSearchButton(true);
        }
    });

}

function searchInstrum(){
	var search = {}
	var id = document.getElementById("outBox_0").value
	alert("you select "+id);
    search["boxId"]= id;
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/istrumnet1/createInDoc/getInstrumentFilter",
        data : JSON.stringify(search),
        dataType : 'json',
        timeout : 100000,
        success : function(data) {
            console.log("SUCCESS: ", data);
            display(data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
            display(e);
        },
        done : function(e) {
            console.log("DONE");
            enableSearchButton(true);
        }
    });
	
}

function enableSearchButton(flag) {
    $("#btn-search").prop("disabled", flag);
}

function display(data) {
    var json = "<h4>Ajax Response</h4><pre>"
            + JSON.stringify(data, null, 4) + "</pre>";
    $('#feedback').html(json);
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