<%@ page language="java" contentType="text/html;" pageEncoding="utf-8" %>
<html>

<head>

    <link rel="stylesheet" href="/static/css//bootstrap.min.css">
    <link rel="stylesheet" href="/static/js/bootstrap-table/dist/bootstrap-table.css">
    <link href="/static/js/bootstrap-table/dist/extensions/page-jump-to/bootstrap-table-page-jump-to.css"
          rel="stylesheet"/>

    <script src="/static/js/jquery.min.js" type="text/javascript"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/bootstrap-table/src/bootstrap-table.js"></script>
    <script src="/static/js/bootstrap-table/dist/extensions/page-jump-to/bootstrap-table-page-jump-to.js"></script>
    <script src="/static/js/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
    <script src="/static/js/bootstrap/dist/js/bootstrap-datetimepicker.zh-CN.min.js"></script>

    <title>Spring MVC Form Handling</title>
    <script type="text/javascript">
        $(function () {
            $('#table').bootstrapTable({
                url: 'http://localhost:8090/getUsers',
                method: "get",
                columns: [
                    {
                        field: 'userId',
                        title: '用户Id',
                        align: 'center',
                        width: '200px'
                    },
                    {
                        field: 'userName',
                        title: '用户名',
                        align: 'center',
                        width: '200px'
                    },
                    {
                        field: 'firstName',
                        title: '姓氏',
                        align: 'center',
                        width: '150px'
                    },
                    {
                        field: 'lastName',
                        title: '名字',
                        align: 'center',
                        width: '200px'
                    },
                    {
                        field: 'gender',
                        title: '性别',
                        align: 'center',
                        width: '200px'
                    },
                    {
                        field: 'email',
                        title: '邮箱',
                        align: 'center',
                        width: '200px'
                    },
                    {
                        field: 'country',
                        title: '国家',
                        align: 'center'
                    },
                    {
                        field: 'favorites',
                        title: '爱好',
                        align: 'center',
                        width: '550px'
                    },
                    {
                        field: 'userId',
                        title: '操作',
                        align: 'center',
                        width: '550px',
                        formatter: function (value, row, index) {
                            var html = '';
                            html += '<button id="edit"   data-id="' + row.userId + '" class="btn btn-info btn-sm rightSize" type="button"><span class="glyphicon glyphicon-pencil"></span> 编辑</button>&nbsp;&nbsp;';
                            html += '<button id="delete" data-id="' + row.userId + '" class="btn btn-danger btn-sm rightSize" type="button"><span class="glyphicon glyphicon-trash"></span> 删除</button>';
                            return html;
                        }
                    }
                ],
                uniqueId: "userId",
                sortOrder: 'desc',
                idField: 'userId',
                selectItemName: 'table',
                pagination: true,
                sidePagination: 'server',
                pageNumber: 1,
                pageSize: 10,
                pageList: [10, 30, 50]
            });

            $(document).on('click', '#delete', function (e) {
                var id = $(this).data("id");
                $.post('http://localhost:8090/delete/' + id, function (rsp) {
                    $('#table').bootstrapTable('refresh');
                });
            });
        });
    </script>
</head>

<body>
<div style="min-height:500px;">
    <table id="table"></table>
</div>
</body>
</html>