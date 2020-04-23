<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<div class="main-content-wrapper">
	<div class="page-content-inner ptb--80 ptb-md--60 pb-sm--55">
		<div class="container">
			<div class="row">
				<div class="col-3">
					<div class="user-dashboard-tab flex-column flex-md-row">
						<div class="user-dashboard-tab__head nav flex-md-column"
							role="tablist" aria-orientation="vertical">
							<a class="nav-link 
							${page!=null?page.equals("doc")?"active": "":""}"
							href="./doc">Документи</a>
							<a class="nav-link 
							${page!=null?page.equals("exodoc")?"active": "":""}"
							 href="./createExDoc">создать док перемещения</a>
							  <a class="nav-link 
							 ${page!=null?page.equals("indoc")?"active": "":""}"
							 href="./createInDoc"
								aria-selected="true">создать док приема</a> 
								<a class="nav-link 
								${page!=null?page.equals("outdoc")?"active": "":""}"
							href="./createOutDoc" aria-selected="true">
							создать док списания</a>
							<a class="nav-link 
							${page!=null?page.equals("location")?"active": "":""}"
							 href="./exdoclist">список док перем</a>
							  <a class="nav-link 
							 ${page!=null?page.equals("box")?"active": "":""}"
							 href="./indoclist"
								aria-selected="true">список док приема</a> 
								<a class="nav-link 
								${page!=null?page.equals("instrument")?"active": "":""}"
							href="./outdoclist" aria-selected="true">
							список док списания</a>

						</div>
					</div>
				</div>