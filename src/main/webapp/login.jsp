<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String errorParam = request.getParameter("error");
	boolean errorExists = (errorParam != null);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="refresh" content="300" />
<title>Ingreso al sistema</title>

<style type="text/css">
body {
	background-color: #bac1c8;
	font-family: Verdana, Arial, Sans-serif;
	font-size: 12px;
	margin: 0px;
	padding: 0px;
}

.inputLabel {
	color: #011b58;
}

.inputText {
	border: 1px solid #cccccc;
	padding: 3px;
	width: 275px;
}

#login-container {
	padding-top: 50px;
	width: 1000px;
	max-width: 1000px;
	min-width: 1000px;
	height: 600px;
	max-height: 600px;
	min-height: 600px;
	margin: auto;
	margin-top: 30px;
	background-repeat: no-repeat;
	width: 1000px;
}

#login-table {
	display: none;
	position: absolute;
	top: 0px;
	left: 0px;
	position: absolute;
}

#submit-cell {
	text-align: right;
}

.redText {
	display: block;
	color: #f79239;
}

.redText a {
	text-decoration: none;
	color: #f79239;
}
</style>
<title>Escolar</title>
<script type="text/javascript">
	function positionLoginTable() {
		var esIE = false;
		if (document.all) {
			esIE = true;
		}
		var loginContainer = document.getElementById("login-container");
		var loginTable = document.getElementById("login-table");

		var yPos = findPosY(loginContainer);
		var xPos = findPosX(loginContainer);

		yPos += 360;
		xPos += 540;

		loginTable.style.top = yPos + "px";
		loginTable.style.left = xPos + "px";
		loginTable.style.display = "block";
	}
	function findPosX(obj) {
		var curleft = 0;
		if (obj.offsetParent)
			while (1) {
				curleft += obj.offsetLeft;
				if (!obj.offsetParent)
					break;
				obj = obj.offsetParent;
			}
		else if (obj.x)
			curleft += obj.x;
		return curleft;
	}

	function findPosY(obj) {
		var curtop = 0;
		if (obj.offsetParent)
			while (1) {
				curtop += obj.offsetTop;
				if (!obj.offsetParent)
					break;
				obj = obj.offsetParent;
			}
		else if (obj.y)
			curtop += obj.y;
		return curtop;
	}
</script>
</head>
<body onload="positionLoginTable();" onresize="positionLoginTable();">
	<div id="main-container">
		<div id="login-container">
			<form id="login-form" action="j_security_check" method="post">
				<table id="login-table">
					<tr>
						<td colspan="2"><label for="j_username" class="inputLabel">Usuario</label>
						</td>
					</tr>
					<tr>
						<td colspan="2"><input id="j_username" type="text"
							autofocus="autofocus" name="j_username" class="inputText"
							maxlength="35" /></td>
					</tr>
					<tr>
						<td colspan="2"><label for="j_password" class="inputLabel">Contrase&ntilde;a</label>
						</td>
					</tr>
					<tr>
						<td colspan="2"><input id="j_password" type="password"
							name="j_password" class="inputText" maxlength="35" /></td>
					</tr>
					<tr>
						<td>
							<%
								if (errorExists) {
							%> <span class="redText" style="font-weight: bolder;">Login
								incorrecto</span> <%
 	}
 %><span class="redText"><input id="rememberLogin"
								name="rememberLogin" type="checkbox" />Recordarme</span> <span
							class="redText"><a href="" target="_blank"
								onclick="window.open('/sigse/reseteoClave.jsf', null, 'height=205,width=350,top=230,left=450'); return false;">&iquest;Olvid&oacute;
									su contrase&ntilde;a?</a> </span>
						</td>
						<td id="submit-cell"><input type="image"
							src="<%=request.getContextPath()%>/resources/images/login/btn-ingresar.png" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>