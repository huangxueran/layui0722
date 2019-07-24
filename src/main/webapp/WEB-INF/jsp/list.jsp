<%--
  Created by IntelliJ IDEA.
  User: teacher
  Date: 2019/7/19 0019
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<c:set var="mypath" value="${pageContext.request.contextPath}"/>
<script src="${mypath}/static/jquery/jquery-1.10.2.js"></script>
<link id="layui" rel="stylesheet" type="text/css"
      href="${mypath}/static/layui/css/layui.css"/>
<script src="${mypath}/static/layui/layui.js"></script>
<html>
<head>
    <title>显示所有用户</title>
</head>
<body>
<table id="demo" lay-filter="test"></table>
<script>
    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 312
            ,url: '/findAllEmp.do' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'empno', title: '用户编号', width:80, sort: true, fixed: 'left'}
                ,{field: 'ename', title: '用户名', width:180}
                ,{field: 'mgrname', title: '用户密码', width:180, sort: true}
                ,{field: 'job', title: '用户密码', width:180, sort: true}
                ,{field: 'total', title: '用户密码', width:180, sort: true}
                ,{field: 'dname', title: '用户密码', width:180, sort: true}
                ,{field: 'hiredate', title: '创建时间', width:180,templet : "<div>{{layui.util.toDateString(d.hiredate, 'yyyy-MM-dd')}}</div>"}
            ]]
        });
    });
</script>
</body>
</html>
