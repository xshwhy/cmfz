<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<script>
    $(function () {



        var tb = [{
            iconCls: 'icon-add',
            text: '添加',
            handler: function () {
               // alert('编辑按钮')
                $('#dd_master').dialog('open');

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
                $('#dg_master').edatagrid('destroyRow');
                //$('#dg_banner').edatagrid('load');
            }
        }, '-', {
            iconCls: 'icon-save',
            text: '保存',
            handler: function () {
                //alert('帮助按钮')
                $('#dg_master').edatagrid('saveRow');

            }
        }];

        $('#dg_master').edatagrid({
            url: '${pageContext.request.contextPath}/master/selectAllMaster',
            saveUrl: '${pageContext.request.contextPath}/master/updateMaster',
            updateUrl:'${pageContext.request.contextPath}/master/updateMaster',
            destroyUrl: '${pageContext.request.contextPath}/master/deleteMaster',
            fit: true,
            fitColumns: true,
            pagination: true,
            pageSize: 2,
            pageList: [2, 4, 6, 8, 10],
            columns: [[
                {field: 'dharma', title: '标题', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: 'text',
                        options: {required: true}
                    }
                },
            ]],
            toolbar: tb,
            view: detailview,
            //rowIndex:行的索引
            //rowData ：行数据
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/master/'+ rowData.headImg + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +

                    '</td>' +
                    '</tr></table>';
            }
        });
    })

    function addArticle() {
        $('#ff_article').form('submit', {
            url:'${pageContext.request.contextPath}/master/insertMaster',
            success: function (data) {
                //alert(data)
                data = JSON.parse(data);
                if(data.isInsert){
                    //关闭修改对话框
                    $("#dd_master").dialog("close");

                    //刷新datagrid
                    $("#dg_master").datagrid("reload");
                }else{
                    alert("添加失败，请确认")
                }
            }
        });
    }

</script>
<table id="dg_master"></table>
<div id="dd_master" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
            buttons:[{
				text:'保存',
				handler:function(){
				    addArticle();
				}
			},{
				text:'关闭',
				handler:function(){}
			}]">

    <form id="ff_article" method="post" enctype="multipart/form-data">
        <div>
            <label for="dharma">法名:</label>
            <input id="dharma" class="easyui-validatebox" type="text" name="dharma" data-options="required:true"/>
        </div>

        <div>
            <label >状态:</label>
            <input id="status1" class="easyui-validatebox" type="radio" name="status" value="0" data-options="required:true"/>上
            <input id="status2" class="easyui-validatebox" type="radio" name="status" value="1" data-options="required:true"/>下
        </div>
        <input class="easyui-filebox" name="file"  style="width:150px">
    </form>
</div>



