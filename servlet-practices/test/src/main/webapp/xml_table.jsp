<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<body>

	<p></p>

	<p id="demo"></p>


	<script>
		var myObj = ${jsonObject};
		document.write(myObj);

		var obj, dbParam, xmlhttp, myObj, x, txt = "";

		txt += "<table border='1'>"
		var i = 0;
		for (x in myObj) {
			for (y in myObj[x]) {
				txt += "<tr><td>" + myObj[x][y] + "</td></tr>";
			}
		}
		txt += "</table>"
		document.getElementById("demo").innerHTML = txt;
	</script>
</body>
</html>
