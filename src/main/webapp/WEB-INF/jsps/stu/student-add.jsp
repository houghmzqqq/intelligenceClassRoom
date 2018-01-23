<%--
  Created by IntelliJ IDEA.
  User: handsome-boy
  Date: 2017/12/6
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>Bootstrap Admin</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href='http://fonts.useso.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/lib/font-awesome/css/font-awesome.css">

    <%--<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"/>--%>
    <%--<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"/>--%>

    <script src="${pageContext.request.contextPath }/lib/jquery-1.11.1.min.js" type="text/javascript"></script>



    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/stylesheets/theme.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/stylesheets/premium.css">

</head>
<body class=" theme-blue">

<!-- Demo page code -->

<script type="text/javascript">
    $(function() {
        var match = document.cookie.match(new RegExp('color=([^;]+)'));
        if(match) var color = match[1];
        if(color) {
            $('body').removeClass(function (index, css) {
                return (css.match (/\btheme-\S+/g) || []).join(' ')
            })
            $('body').addClass('theme-' + color);
        }

        $('[data-popover="true"]').popover({html: true});

    });

</script>
<style type="text/css">
    #line-chart {
        height:300px;
        width:800px;
        margin: 0px auto;
        margin-top: 1em;
    }
    .navbar-default .navbar-brand, .navbar-default .navbar-brand:hover {
        color: #fff;
    }
</style>

<script type="text/javascript">
    $(function() {
        var uls = $('.sidebar-nav > ul > *').clone();
        uls.addClass('visible-xs');
        $('#main-menu').append(uls.clone());
    });
</script>


<div class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <a class="" href="../index.jsp"><span class="navbar-brand"><span class="fa fa-paper-plane"></span> 智慧教室管理系统</span></a></div>

    <div class="navbar-collapse collapse" style="height: 1px;">

        <ul class="nav navbar-nav navbar-left">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">系统管理</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-left">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">智能控制</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-left">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">课表管理</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-left">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">考勤数据</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-left">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">环境监测</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-left">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">资产管理</a>
            </li>
        </ul>

        <ul id="main-menu" class="nav navbar-nav navbar-right">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user padding-right-small" style="position:relative;top: 3px;"></span> 这里是用户名
                    <i class="fa fa-caret-down"></i>
                </a>

                <ul class="dropdown-menu">
                    <li><a href="../">更改密码</a></li>
                    <li class="divider"></li>
                    <li class="dropdown-header">Admin Panel</li>
                    <li><a href="../">用户信息</a></li>
                    <li><a href="../">Security</a></li>
                    <li><a tabindex="-1" href="../">Payments</a></li>
                    <li class="divider"></li>
                    <li><a tabindex="-1" href="sign-in.jsp">注销</a></li>
                </ul>
            </li>
        </ul>

    </div>
</div>
</div>
<!--  <div class="copyrights">Collect from <a href="http://www.cssmoban.com/"  title="网站模板">网站模板</a></div> -->

<div class="sidebar-nav">
    <ul>
        <li><a href="#" data-target=".dashboard-menu" class="nav-header collapsed" data-toggle="collapse"> 系统管理<i class="fa fa-collapse"></i></a></li>
        <li><ul class="dashboard-menu nav nav-list collapse">
            <li ><a href="nav01-users.jsp"><span class="fa fa-caret-right"></span> 用户管理</a></li>
            <li ><a href="${pageContext.request.contextPath}/sysMana/devControlView"><span class="fa fa-caret-right"></span> 设备控制</a></li>
            <li ><a href="nav01-devices-search.jsp"><span class="fa fa-caret-right"></span> 设备搜索</a></li>
            <li ><a href="nav01-ccu.jsp"><span class="fa fa-caret-right"></span> 中控设置</a></li>
            <li ><a href="nav01-attendance-machines.jsp"><span class="fa fa-caret-right"></span> 考勤端设置</a></li>
        </ul></li>

        <!-- --------------------------------------------------------------------------------------------------------- -->

        <li data-popover="true" rel="popover" data-placement="right"><a href="#" data-target=".premium-menu" class="nav-header collapsed" data-toggle="collapse">智能控制<i class="fa fa-collapse"></i></a> </li>
        <li> <ul class="premium-menu nav nav-list collapse">
            <li ><a href="nav02-lighting.jsp"><span class="fa fa-caret-right"></span> 照明控制</a></li>
            <li ><a href="nav02-curtains.jsp"><span class="fa fa-caret-right"></span> 窗帘控制</a></li>
            <li ><a href="nav02-infra-red.jsp"><span class="fa fa-caret-right"></span> 红外控制</a></li>
            <li ><a href="nav02-gate-lock.jsp"><span class="fa fa-caret-right"></span> 门锁控制</a></li>
        </ul>
        </li>

        <li><a href="#" data-target=".legal-menu" class="nav-header collapsed" data-toggle="collapse"> 班级管理<i class="fa fa-collapse"></i></a></li>
        <li><ul class="legal-menu nav nav-list collapse">
            <li ><a href="nav03-class-add.jsp"><span class="fa fa-caret-right"></span>添加班级</a></li>
            <li ><a href="nav03-class-view.jsp"><span class="fa fa-caret-right"></span>班级视图</a></li>
        </ul></li>

        <li><a href="#" data-target=".accounts-menu" class="nav-header collapsed" data-toggle="collapse"> 教师管理<i class="fa fa-collapse"></i></a></li>
        <li><ul class="accounts-menu nav nav-list collapse">
            <li ><a href="nav04-teacher-add.jsp"><span class="fa fa-caret-right"></span>添加教师</a></li>
            <li ><a href="nav04-teacher-view.jsp"><span class="fa fa-caret-right"></span>教师视图</a></li>
        </ul></li>

        <li><a href="#" data-target=".accounts01-menu" class="nav-header" data-toggle="collapse"> 学生管理<i class="fa fa-collapse"></i></a></li>
        <li><ul class="accounts01-menu nav nav-list collapse in">
            <li class="active"><a href="${pageContext.request.contextPath}/stu/toAdd"><span class="fa fa-caret-right"></span>添加学生</a></li>
            <li ><a href="${pageContext.request.contextPath}/stu/toAddList"><span class="fa fa-caret-right"></span>学生批量导入</a></li>
            <li ><a href="${pageContext.request.contextPath}/stu/toView"><span class="fa fa-caret-right"></span>学生视图</a></li>
        </ul></li>

        <li><a href="#" data-target=".accounts02-menu" class="nav-header collapsed" data-toggle="collapse"> 课表管理<i class="fa fa-collapse"></i></a></li>
        <li><ul class="accounts02-menu nav nav-list collapse">
            <li ><a href="${pageContext.request.contextPath}/couSch/toAddView"><span class="fa fa-caret-right"></span>录入课表</a></li>
            <li ><a href="nav06-course-schedule-view.jsp"><span class="fa fa-caret-right"></span>课表视图</a></li>
            <li ><a href="nav06-course-schedule-edit.jsp"><span class="fa fa-caret-right"></span>课表编辑</a></li>
        </ul></li>

        <li><a href="#" data-target=".accounts03-menu" class="nav-header collapsed" data-toggle="collapse"> 考勤机同步<i class="fa fa-collapse"></i></a></li>
        <li><ul class="accounts03-menu nav nav-list collapse">
            <li ><a href="${pageContext.request.contextPath}/attendance/toAttenView"><span class="fa fa-caret-right"></span>考勤机列表</a></li>
            <li ><a href="${pageContext.request.contextPath}/couSch/toView"><span class="fa fa-caret-right"></span>下发课程安排</a></li>
            <li ><a href="${pageContext.request.contextPath}/couSch/toSetTermView"><span class="fa fa-caret-right"></span>设置开学周星期一</a></li>
        </ul></li>

        <li><a href="#" data-target=".accounts04-menu" class="nav-header collapsed" data-toggle="collapse"> 考勤数据<i class="fa fa-collapse"></i></a></li>
        <li><ul class="accounts04-menu nav nav-list collapse">
            <li ><a href="${pageContext.request.contextPath}/record/toStuRecodeView"><span class="fa fa-caret-right"></span>学生记录查询</a></li>
            <li ><a href="nav08-attendance-record-update.jsp"><span class="fa fa-caret-right"></span>班级记录查询</a></li>
        </ul></li>

        <li><a href="#" data-target=".accounts05-menu" class="nav-header collapsed" data-toggle="collapse"> 环境监测<i class="fa fa-collapse"></i></a></li>
        <li><ul class="accounts05-menu nav nav-list collapse">
            <li ><a href="nav09-daily-report.jsp"><span class="fa fa-caret-right"></span>日报表</a></li>
            <li ><a href="nav09-weekly-report.jsp"><span class="fa fa-caret-right"></span>周报表</a></li>
            <li ><a href="nav09-monthly-report.jsp"><span class="fa fa-caret-right"></span>月报表</a></li>
        </ul></li>

        <li><a href="#" data-target=".accounts06-menu" class="nav-header collapsed" data-toggle="collapse"> 资产管理<i class="fa fa-collapse"></i></a></li>
        <li><ul class="accounts06-menu nav nav-list collapse">
            <li ><a href="nav10-privacy-policy.jsp"><span class="fa fa-caret-right"></span>待定</a></li>
            <li ><a href="nav10-privacy-policy.jsp"><span class="fa fa-caret-right"></span>待定</a></li>
        </ul></li>


    </ul>
</div>

<div class="content">
    <div class="header">

        <h1 class="page-title">学生管理</h1>
        <ul class="breadcrumb">
            <li><a href="../index.jsp">Home</a> </li>
            <li class="active">添加学生</li>
        </ul>

    </div>
    <div class="main-content">
        <!-- <ul class="nav nav-tabs">
          <li class="active"><a href="#home" data-toggle="tab">学生信息</a></li>
          <li><a href="#profile" data-toggle="tab">Password</a></li>
        </ul> -->


        <div class="row">
                <%--<input type="file" class="btn btn-default">--%>
            <form method="post" id="tab" action="${pageContext.request.contextPath}/stu/addOne" enctype="multipart/form-data">
            <div class="col-md-4">
                <br>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">


                            <div class="form-group">
                                <label>学号</label>
                                <input name="studentId" value="${studentVO.studentId}" type="text" required="required" maxlength="12" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>姓名</label>
                                <input name="studentName" value="${studentVO.studentName}" type="text" required="required" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>班级</label>
                                <select name="classId" class="form-control">
                                    <option value="0">选择班级</option>
                                    <c:forEach var="classesPO" items="${classesPOS}">
                                        <option <c:if test="${studentVO.classId==classesPO.classId}">selected="selected"</c:if>
                                                value="${classesPO.classId}">${classesPO.className}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>性别</label>
                                <select name="gender" class="form-control">
                                    <option <c:if test="${studentVO.gender=='男'}">selected="selected"</c:if>
                                            value="男">男</option>
                                    <option <c:if test="${studentVO.gender=='女'}">selected="selected"</c:if>
                                            value="女">女</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>RFID</label>
                                <input name="RFID" value="${studentVO.RFID}" type="text" class="form-control">
                            </div>
                            <div class="btn-toolbar list-toolbar">
                                <button class="btn btn-primary" ><i class="fa fa-save"></i> 保存</button>
                                <a href="#myModal" data-toggle="modal" class="btn btn-danger">删除</a>
                            </div>

                    </div>
                </div>
            </div>
                <div class="form-group">
                 <c:if test="${photo==true}">
                    <img src="${pageContext.request.contextPath}/stu/displayImg?stuId=${studentVO.studentId}">
                </c:if>
                <input type="file" name="photo" id="eleId" />
                <%--<form:errors path="photo"></form:errors>--%>
            </div>
            </form>
        </div>
        <div class="modal small fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">提示：</h3>
                    </div>
                    <div class="modal-body">
                        <p class="error-text"><i class="fa fa-warning modal-icon"></i>确定要删除该学生吗?<br></p>
                    </div>
                    <form action="${pageContext.request.contextPath}/stu/toDelete?studentId=${studentVO.studentId}">
                        <div class="modal-footer">
                            <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
                            <button class="btn btn-danger" data-dismiss="modal">删除</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>


<script src="${pageContext.request.contextPath }/lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
    $("[rel=tooltip]").tooltip();
    $(function() {
        $('.demo-cancel-click').click(function(){return false;});
    });
</script>


</body></html>
