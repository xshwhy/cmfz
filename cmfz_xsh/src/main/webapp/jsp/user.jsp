<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>

<script type="text/javascript" src="../js/distpicker.data.js"></script>
<script type="text/javascript" src="../js/distpicker.js"></script>
<script type="text/javascript" src="../js/main.js"></script>


<script>
    $(function () {

        var tb = [{
            iconCls: 'icon-add',
            text: '添加',
            handler: function () {
                //alert('编辑按钮')
                $('#dd_user').dialog('open');

            }
        }, '-', {
            iconCls: 'icon-edit',
            text: '修改',
            handler: function () {
                alert('帮助按钮')

            }
        }, '-', {
            iconCls: 'icon-delete',
            text: '删除',
            handler: function () {
                //alert('帮助按钮')
                $('#dg_user').edatagrid('destroyRow');
                //$('#dg_banner').edatagrid('load');
            }
        }, '-', {
            iconCls: 'icon-save',
            text: '保存',
            handler: function () {
                //alert('帮助按钮')
                $('#dg_user').edatagrid('saveRow');

            }
        }];

        $('#dg_user').edatagrid({
            method: 'get',
            url: '${pageContext.request.contextPath}/user/queryAll',
            saveUrl: '${pageContext.request.contextPath}/user/updateUser',
            updateUrl:'${pageContext.request.contextPath}/user/updateUser',
            destroyUrl: '${pageContext.request.contextPath}/user/deleteUser',
            fit: true,
            fitColumns: true,
            pagination: true,
            pageSize: 2,
            pageList: [2, 4, 6, 8, 10],
            columns: [[
                {field: 'name', title: '姓名', width: 100},
                {field: 'dharma', title: '法名', width: 100},
                {
                    field: 'sex', title: '性别', width: 100, editor: {
                        type: 'text',
                        options: {required: true}
                    }
                },
                {field: 'province', title: '省', width: 100},
                {field: 'city', title: '市', width: 100},
                {field: 'sign', title: '签名', width: 100},
                {field: 'status', title: '状态', width: 100, editor: {
                        type: 'text',
                        options: {required: true}
                    }},
                {field: 'phone', title: '手机号', width: 100},
                {field: 'password', title: '密码', width: 100},
                {field: 'salt', title: '盐', width: 100},
                {field: 'headImg', title: '头像', width: 100},
                {field: 'masterId', title: '上师', width: 100},
                {field: 'createDate', title: '日期', width: 100}
            ]],
            toolbar: tb,
            view: detailview,
            //rowIndex:行的索引
            //rowData ：行数据
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/user/'+rowData.headImg+'" style="height:50px;"></td>' +
                    '</tr></table>';
            }
        });
    })

    function addBanner() {
        // call 'submit' method of form plugin to submit the form
        $('#ff').form('submit', {
            url: '${pageContext.request.contextPath}/user/insertUser',
            success: function (data) {
                //alert(data)
                data = JSON.parse(data);
                if(data.isInsert){
                    //关闭修改对话框
                    $("#dd_user").dialog("close");

                    //刷新datagrid
                    $("#dg_user").datagrid("reload");
                }else{
                    alert("添加失败，请确认")
                }
            }
        });

    }

</script>
<table id="dg_user"></table>
<div id="dd_user" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
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
            <label for="name">姓名:</label>
            <input id="name" class="easyui-validatebox" type="text" name="name" data-options="required:true"/>
        </div>
        <div>
            <label for="dharma">法名:</label>
            <input id="dharma" class="easyui-validatebox" type="text" name="dharma" data-options="required:true"/>
        </div>
        <div>
            <label>性别:</label>
            <input id="sex1" class="easyui-validatebox" type="radio" name="sex"  value="0" data-options="required:true"/>男
            <input id="sex2" class="easyui-validatebox" type="radio" name="sex"  value="1" data-options="required:true"/>女
        </div>
        <div data-toggle="distpicker">
        <div class="form-group">
            <label class="sr-only" for="province1">省份:</label>
            <%--<input id="province" class="easyui-validatebox" type="text" name="province" data-options="required:true"/>--%>
            <select class="form-control" id="province1"   name="province"></select>
        </div>
        <div class="form-group">
            <label class="sr-only" for="city1">城市:</label>
            <%--<input id="city" class="easyui-validatebox" type="text" name="city" data-options="required:true"/>--%>
            <select id="city1" class="form-control"  name="city"/>
        </div>
        </div>
        <div>
            <label for="sign">签名:</label>
            <input id="sign" class="easyui-validatebox" type="text" name="sign" data-options="required:true"/>
        </div>
        <div>
            <label for="phone">电话:</label>
            <input id="phone" class="easyui-validatebox" type="text" name="phone" data-options="required:true"/>
        </div>
        <div>
            <label for="password">密码:</label>
            <input id="password" class="easyui-validatebox" type="text" name="password" data-options="required:true"/>
        </div>
        <input class="easyui-filebox" name="file"  style="width:150px">
    </form>
</div>



