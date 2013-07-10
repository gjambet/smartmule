<jsp:directive.page import="java.util.*" />
<%@ page import="guillaume.smartmule.Activity" %>
<jsp:directive.page contentType="text/html" isErrorPage="false" isThreadSafe="true" />
<html>
<head><title>Smartmule</title></head>
<body>
<h1>Smartmule</h1>

<div>
    Running since <% out.println(Activity.get().started()); %>
</div>

<br/>


</body>
</html>