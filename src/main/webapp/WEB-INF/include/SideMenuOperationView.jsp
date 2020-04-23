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
							<a class="nav-link ${page!=null?page.equals("operation")?"active": "":""}"
							href="./operation">Операции</a>
							<a class="nav-link ${page!=null?page.equals("location")?"active": "":""}"
							 href="./addlocation">Добавить
								место хран</a> <a class="nav-link ${page!=null?page.equals("box")?"active": "":""}"
							 href="./addbox"
								aria-selected="true">Добавить Ячейку</a> <a
								class="nav-link ${page!=null?page.equals("instrument")?"active": "":""}"
							href="./addinstrument"
								aria-selected="true">Добавить Интсрумент</a>

						</div>
					</div>
				</div>