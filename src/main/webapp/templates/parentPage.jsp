
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring POC</title>

<link href="myStyle.css?v=4" rel="stylesheet" type="text/css"/>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js?v=1"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0rc1/angular-route.min.js?v=1"></script>

<script type="text/javascript" src="myApp.js?v=32"></script>

</head>
<body>

<div data-ng-app="loginApp" data-ng-controller ="loginController">

<%@include file="header.jsp"%>

<div data-ng-view></div>


<%@include file="footer.jsp"%>
</div>

</body>
</html>