<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@include file="/templates/tags.jsp" %>

<html xmlns='http://www.w3.org/1999/xhtml'>
	<head>
		<meta http-equiv="Content-Type" content="text/html; iso-8859-1">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/shared/css/bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/shared/css/bootstrap-responsive.css"/>
		<title><bean:message key="page.title"/></title>
	</head>
	<body>
						
		<tiles:insert attribute="body" />

	</body>
</html>
