<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<script>
    $(function () {


        var tb = [{
            iconCls: 'icon-add',
            text: '专辑详情',
            handler: function () {
                var row = $('#tt_album').treegrid('getSelected');
                if (row!=null){
                        selectOne(row);
                }else{
                    alert("请选择专辑");
                }
            }
        }, '-', {
            iconCls: 'icon-edit',
            text: '添加专辑',
            handler: function () {
                alert('帮助按钮')
                $('#dd_banner').dialog('open');
            }
        }, '-', {
            iconCls: 'icon-delete',
            text: '添加章节',
            handler: function () {
                var row = $('#tt_album').treegrid('getSelected');
                $("#yy1").val(row.id);
                if (row!=null){

                    $('#dd_banner_zj').dialog('open');
                }else{
                    alert("请选择专辑");
                }
            }
        }, '-', {
            iconCls: 'icon-save',
            text: '下载音频',
            handler: function () {
                alert('帮助按钮')
                var row = $('#tt_album').treegrid('getSelected');
                location.href="${pageContext.request.contextPath}/chapter/download?musicPath="+row.musicPath+"&title="+row.title;
            }
        }, '-', {
            iconCls: 'icon-save',
            text: '播放音频',
            handler: function () {
                alert('帮助按钮')
                var row = $('#tt_album').treegrid('getSelected');
               if(row!=null){
                   if(row.size){
                   var str = '<audio id="player" controls="controls" autoplay="autoplay">' +
                       '<source id="musicPath" src="${pageContext.request.contextPath}/file2/'+ row.musicPath +'" />' +
                       '</audio>';
                   $('#dd_music').dialog('open');

                   $('#dd_music').html(str);
               }else {
                   alert("请选择专辑")
               }
               }else {
                   alert(("请选择章节"))
               }
            }
        }, '-', {
            iconCls: 'icon-edit',
            text: '导出表格',
            handler: function () {
                //var row = $('#tt_album').treegrid('getSelected');
                $.ajax({
                    url:'${pageContext.request.contextPath}/chapter/dao'
                })
            }
        }];

        $('#tt_album').treegrid({
            //后台Controller查询所有专辑以及对应的章节集合
            url:'${pageContext.request.contextPath}/album/selectAllChapter',
            idField:'id',//用来标识标识树节点   主干树与分支树节点  ID不能有相同  并且使用相同的字段  否则ID冲突
            treeField:'title',//用来定义树节点   树形表格上要展示的信息   注意使用相同的字段 用来展示对应节点名称
            toolbar:tb,
            fit:true,
            fitColumns:true,
            columns:[[
                {field:'title',title:'名字',width:180},
                {field:'size',title:'章节大小',width:60},
                {field:'duration',title:'章节的时长',width:80}
            ]]
        });

    })


    function addBanner() {
        // call 'submit' method of form plugin to submit the form
        $('#ff').form('submit', {
            url: '${pageContext.request.contextPath}/album/queryInsert',

            // onSubmit: function () {
            // do some check
            // return false to prevent submit;
            //},
            success: function (data) {
                //alert(data)
                data = JSON.parse(data);
                if(data.isInsert){
                    //关闭修改对话框
                    $("#dd_banner").dialog("close");

                    //刷新datagrid
                    //$("#dg_banner").datagrid("load");
                }else{
                    alert("添加失败，请确认")
                }
            }
        });

    }


    function selectOne(rowData) {
        //打开修改对话框
        alert(rowData.title)

        $("#dd_banner_up").dialog("open");
        //把原本的数据填入到修改对话框中
        $("#title1").val(rowData.title);
        $("#title2").val(rowData.amount);
        $("#title3").prop("src","${pageContext.request.contextPath}/url/"+ rowData.imgPath);


    }


    function addChapter() {
        $('#dd').form('submit',{
            url:'${pageContext.request.contextPath}/chapter/insertChapter',
            success: function (data) {
                $('#dd').dialog('closed')
            }
        });
    }


</script>
<table id="tt_album" style="width:600px;height:400px"></table>
<div id="dd_banner" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'保存',
				handler:function(){
				    addBanner();
				}
			},{
				text:'关闭',
				handler:function(){}
			}]">

    <form id="ff" method="post" enctype="multipart/form-data">
        <div>
            <label for="title">名字</label>
            <input id="title" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            <label for="amount">集数</label>
            <input id="amount" class="easyui-validatebox" type="text" name="amount" data-options="required:true"/>
        </div>
        <div>
            <label for="score">评级</label>
            <input id="score" class="easyui-validatebox" type="text" name="score" data-options="required:true"/>
        </div>
        <div>
            <label for="author">作者</label>
            <input id="author" class="easyui-validatebox" type="text" name="author" data-options="required:true"/>
        </div>
        <div>
            <label for="boardcast">播音人员</label>
            <input id="boardcast" class="easyui-validatebox" type="text" name="boardcast" data-options="required:true"/>
        </div>
        <div>
            <label for="brief">简介</label>
            <input id="brief" class="easyui-validatebox" type="text" name="brief" data-options="required:true"/>
        </div>
        <input  name="file" class="easyui-filebox" style="width:150px">
    </form>
</div>

<div id="dd_banner_up" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="up" method="post" enctype="multipart/form-data">
        <div>
            <label for="title">标题:</label>
            <input id="title1" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>

        <div>
            <label for="title">数量:</label>
            <input id="title2" class="easyui-validatebox" type="text" name="amount" data-options="required:true"/>
        </div>
        <div>
            <label for="title">图片:</label>
           <%-- <input id="title3" class="easyui-validatebox" type="text" name="imgPath" data-options="required:true"/>--%>
            <img  id="title3"  style="height:50px;">
        </div>


    </form>
</div>


<div id="dd_banner_zj" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'保存',
				handler:function(){
				    addChapter();
				}
			},{
				text:'重置',
				handler:function(){
				$('#dd').form('reset');
				}
			}]">

    <form id="dd" method="post" enctype="multipart/form-data">
        <input type="hidden" id="yy1" value="" name="albumId">
        <div>
            <label for="title">名字</label>
            <input id="title11" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            <label for="audio">专辑</label>
            <input id="audio" class="easyui-filebox" type="text" name="audio" data-options="required:true"/>
        </div>
    </form>
</div>


<div id="dd_music" class="easyui-dialog" title="音频播放" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">

</div>




