<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
</head>
<body>

<script type="text/javascript">

    var goEasy = new GoEasy({
        appkey: "BC-1f3b41fa7a3647429e5657d9cfb2fd2b"
    });

    goEasy.subscribe({
        channel: "xsh",
        onMessage: function (message) {
            alert("Channel:" + message.channel + " content:" + message.content);
        }
    });

</script>
</body>
</html>