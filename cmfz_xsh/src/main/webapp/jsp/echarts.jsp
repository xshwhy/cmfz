<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
<script  src="../js/china.js" type="text/javascript" charset="utf-8" ></script>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="statistics_main" style="width: 600px;height: 400px;;margin-top: 30px;margin-left: 30px"></div>

<script type="text/javascript">

    var goEasy = new GoEasy({
        appkey: "BC-1f3b41fa7a3647429e5657d9cfb2fd2b"
    });

    goEasy.subscribe({
        channel: "xsh",

        onMessage: function (message) {
            //alert("Channel:" + message.channel + " content:" + message.content);
            var y=message.content;
            alert(y)
            var content=JSON.parse(y);
            var x1=content.a;
            var x2=content.b;
            myChart.setOption({
                xAxis: {
                    data: x1
                },
                series: [{
                    // 根据名字对应到相应的系列
                    name: '活跃用户',
                    data: x2
                }]
            });
        }
    });




    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('statistics_main'));

    $.ajax({
        url:'${pageContext.request.contextPath }/user/selectByDate',
        dataType:'JSON',
        success:function (data){

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '持名法州App活跃用户'
                },
                tooltip: {},
                legend: {
                    data:['用户数量']
                },
                xAxis: {
                    data: data.a
                },
                yAxis: {},
                series: [{
                    name: '数量',
                    type: 'bar',
                    data: data.b
                }]
            };
            myChart.setOption(option);
        }

    })



</script>
