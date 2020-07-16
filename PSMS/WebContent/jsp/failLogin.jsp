<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:p="http://primefaces.org/ui">
<h:head>
	<meta charset="ISO-8859-1">
	<title>Ops.. something when wrong</title>
</h:head>
<h:body>
	<h1>Wrong username or password</h1>
	<a href="login.jsp">Come back to login</a>
	<p:outputLabel>label</p:outputLabel>
	<h2>PrimeFaces Autocomplete TextArea</h2>
	<h:outputText value="Enter JavaTpoint for sugessions" />
	<h:inputTextarea>text area</h:inputTextarea>
</h:body>
</html>