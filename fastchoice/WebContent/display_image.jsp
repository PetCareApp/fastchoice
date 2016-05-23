<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%-- <a href="${pageContext.request.contextPath}/displayImage/amizades${param.idAmigo}">aqui porra</a> --%>

<input class="${param.classe}" type="image" src="${pageContext.request.contextPath}/displayImage/${param.entidade}${param.idNumber}" alt="Submit" width="${param.largura}" height="${param.altura}">
</body>
</html>