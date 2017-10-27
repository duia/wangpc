/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */

(function(){

    $("#toggle-advanced-search").click(function(){
        $("i",this).toggleClass("fa-angle-double-down fa-angle-double-up");
        $("#div-advanced-search").slideToggle("fast");
    });

    var table = $('#department_table').DataTable({
        processing: true,
        serverSide: true,
        ajax:{
            url:'/sys/department/searchPage',
            type:'post',
            data:function (data) {
                data.condition = {
                    /* 条件1:'123',//添加额外参数
                     条件2:'123456' */
                }
                return JSON.stringify(data);
            },
            dataType: "json",
            processData: true,
            contentType: 'application/json;charset=UTF-8'
        },
        language: {
            url:'/static/plugins/DataTables-1.10.12/media/Chinese.json'
        },
        pagingType: "full_numbers",
        columns: [{
            title: '<input type="checkbox" name="checklist" id="checkall" /><label style="margin-left: 10px;">ID</label>',
            data: null,
            render: function(data, type, row, meta) {
                return '<input type="checkbox" name="checklist" value="' + row.id + '" /><label style="margin-left: 10px;">' + row.id + '</label>';
            }
        },{
            title: '字段2',
            data: ""
        },{
            title: '字段3',
            data: ""
        },{
            title: '字段4',
            data: ""
        },{
            title: '操作',
            data: null,
            render: function (data, type, row, metad) {
                var html = [];
                html.push('<button type="button" data-id="'+data.id+'" class="btn btn-primary btn-sm updateDepartmentBtn">修改</button>');
                html.push('<button type="button" class="btn btn-danger btn-sm deleteDepartmentBtn">删除</button>');
                return html.join(' ');
            }
        }],
        dom: "<'row'<'#mytool.col-xs-3'><'col-xs-9'>><'row'<'.col-xs-12'tr>><'row'<'col-xs-3'i><'col-xs-2'l><'col-xs-7'p>>",
        initComplete: function () {
            //$("#mytool").append('<button id="datainit" type="button" class="btn btn-primary btn-sm">增加基础数据</button>&nbsp');
            //$("#mytool").append('<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">添加</button>');
            //$("#datainit").on("click", initData);
        },
        fnDrawCallback: function(table) {
            var tableId = $(this).attr('id');
            $('#'+tableId+'_paginate').append("<div>到第 <input type='text' id='changePage' class='input-text' style='width:50px;height:27px'> 页 " +
                "<a class='btn btn-default shiny' href='javascript:void(0);' id='dataTable-btn' style='text-align:center'>确认</a></div>");
            var oTable = $('#'+tableId).dataTable();
            $('#dataTable-btn').click(function(e) {
                if($("#changePage").val() && $("#changePage").val() > 0) {
                    var redirectpage = $("#changePage").val() - 1;
                } else {
                    var redirectpage = 0;
                }
                oTable.fnPageChange(redirectpage);
            });
        }
    });

    $('#saveDepartment').on('click', function(){
        $('#departmentform').submit();
    });

    $('#departmentform').validate({
        submitHandler: function(form) {
            console.log('a');
            $.ajax({
                url:'/sys/department/addOrUpdate',
                data:$('#departmentform').serialize(),
                type:'post',
                success:function(result){
                    if(result.code == '200'){
                        console.log('ok');
                        $('#myModal').modal('hide');
                        //table.draw(false);
                        table.ajax.reload(null, false);
                    }else{
                        alert(result.msg);
                    }
                }
            });
        }
    });

    $('#department_table').delegate('.updateDepartmentBtn', 'click', function(e){
        $.post('/sys/department/findById', {
            id: $(e.target).attr('data-id')
        }, function (data) {
            BootstrapDialog.alert('修改用户'+data.result.account);
        });
    });

    $('#department_table').delegate('.deleteDepartmentBtn', 'click', function(e){
        BootstrapDialog.show({
            message: '删除用户',
            buttons: [{
                label: '删除',
                cssClass: 'btn-primary',
                action: function(dialogItself){
                    $.post('/sys/department/delete', {
                        id: 0
                    }, function(data){
                        if(verIfy(data)){
                            console.log(data);
                            dialogItself.close();
                        }
                    });
                }
            }, {
                icon: 'glyphicon glyphicon-ban-circle',
                label: '取消',
                cssClass: 'btn-warning',
                action: function(dialogItself){
                    dialogItself.close();
                }
            }]
        });
    });

})();