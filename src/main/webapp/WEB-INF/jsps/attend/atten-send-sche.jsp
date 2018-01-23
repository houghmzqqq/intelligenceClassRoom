<%--
  Created by IntelliJ IDEA.
  User: handsome-boy
  Date: 2017/12/6
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <a class="" href="index.jsp"><span class="navbar-brand"><span class="fa fa-paper-plane"></span> 智慧教室管理系统</span></a></div>

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
                    <li><a href="./">更改密码</a></li>
                    <li class="divider"></li>
                    <li class="dropdown-header">Admin Panel</li>
                    <li><a href="./">用户信息</a></li>
                    <li><a href="./">Security</a></li>
                    <li><a tabindex="-1" href="./">Payments</a></li>
                    <li class="divider"></li>
                    <li><a tabindex="-1" href="sign-in.jsp">注销</a></li>
                </ul>
            </li>
        </ul>

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

        <li><a href="#" data-target=".accounts01-menu" class="nav-header collapsed" data-toggle="collapse"> 学生管理<i class="fa fa-collapse"></i></a></li>
        <li><ul class="accounts01-menu nav nav-list collapse">
            <li ><a href="${pageContext.request.contextPath}/stu/toAdd"><span class="fa fa-caret-right"></span>添加学生</a></li>
            <li ><a href="${pageContext.request.contextPath}/stu/toAddList"><span class="fa fa-caret-right"></span>学生批量导入</a></li>
            <li ><a href="${pageContext.request.contextPath}/stu/toView"><span class="fa fa-caret-right"></span>学生视图</a></li>
        </ul></li>

        <li><a href="#" data-target=".accounts02-menu" class="nav-header collapsed" data-toggle="collapse"> 课表管理<i class="fa fa-collapse"></i></a></li>
        <li><ul class="accounts02-menu nav nav-list collapse">
            <li ><a href="${pageContext.request.contextPath}/couSch/toAddView"><span class="fa fa-caret-right"></span>录入课表</a></li>
            <li ><a href="nav06-course-schedule-view.jsp"><span class="fa fa-caret-right"></span>课表视图</a></li>
            <li ><a href="nav06-course-schedule-edit.jsp"><span class="fa fa-caret-right"></span>课表编辑</a></li>
        </ul></li>

        <li><a href="#" data-target=".accounts03-menu" class="nav-header" data-toggle="collapse"> 考勤机同步<i class="fa fa-collapse"></i></a></li>
        <li><ul class="accounts03-menu nav nav-list collapse in">
            <li ><a href="${pageContext.request.contextPath}/attendance/toAttenView"><span class="fa fa-caret-right"></span>考勤机列表</a></li>
            <li class="active"><a href="${pageContext.request.contextPath}/couSch/toView"><span class="fa fa-caret-right"></span>下发课程安排</a></li>
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

        <h1 class="page-title">考勤机同步</h1>
        <ul class="breadcrumb">
            <li><a href="index.jsp">Home</a> </li>
            <li class="active">下发课程安排</li>
        </ul>

    </div>
    <div class="main-content">

        <!-- <ul class="nav nav-tabs">
          <li class="active"><a href="#home" data-toggle="tab">学生信息</a></li>
          <li><a href="#profile" data-toggle="tab">Password</a></li>
        </ul> -->

        <div class="btn-toolbar list-toolbar">
            <!-- <button class="btn btn-primary">
                <i class="fa fa-plus"></i> New User
            </button> -->
            <form action="${pageContext.request.contextPath}/couSch/toFind" method="get" class="form-inline">
                <div class="form-group">
                    <label>教室：</label>
                    <select name="roomId" class="form-control">
                        <option value="0">选择课室</option>
                        <c:forEach var="classroomPO" items="${classroomPOS}">
                            <option value="${classroomPO.roomId}">${classroomPO.roomName}</option>
                        </c:forEach>
                    </select>
                    <label>学年：</label>
                    <select name="range" class="form-control">
                        <option value="${year-1}-${year}">${year-1}-${year}</option>
                        <option value="${year}-${year+1}">${year}-${year+1}</option>
                        <option value="${year+1}-${year+2}">${year+1}-${year+2}</option>
                    </select>
                    <label>学期：</label>
                    <select name="term" class="form-control">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                    <button type="submit" class="btn btn-default">查找</button>
                </div>
            </form>
        </div>

        <table class="table">
            <thead>
            <tr>
                <th>课程任务id</th>
                <th>开始周</th>
                <th>结束周</th>
                <th>班级id</th>
                <th>时间</th>
                <th>周天</th>
                <th style="width: 3.5em;"></th>
            </tr>
            <c:forEach var="courseScheduleVO" items="${courseScheduleVOS}">
                <tr>
                    <th>${courseScheduleVO.planId}</th>
                    <th>${courseScheduleVO.startWeek}</th>
                    <th>${courseScheduleVO.endWeek}</th>
                    <th>${courseScheduleVO.squadId}</th>
                    <th>${courseScheduleVO.time}</th>
                    <th>${courseScheduleVO.dayOfWeek}</th>
                    <th style="width: 3.5em;"></th>
                </tr>
            </c:forEach>

            </thead>
        </table>

        <form action="${pageContext.request.contextPath}/couSch/sendToAtten" method="get" class="form-inline">
            <input type="hidden" name="roomId" value="${roomId}">
            <input type="hidden" name="range" value="${range}">
            <input type="hidden" name="term" value="${term}">
            <button type="submit" class="btn btn-default">下发到考勤机</button>
        </form>

        <%--<ul class="pagination">
            <li><a href="${pageContext.request.contextPath }/stu/toView?thisPage=${dividePage.prePage}&classId=${classId}">&laquo;</a></li>

            <c:if test="${dividePage.pageCount < 5}">
                <c:set var="start" value="1" scope="page"></c:set>
                <c:set var="end" value="${dividePage.pageCount }" scope="page"></c:set>
            </c:if>
            <c:if test="${dividePage.pageCount > 5}">
                <c:choose>
                    <c:when test="${dividePage.thisPage <= 3}">
                        <c:set var="start" value="1" scope="page"></c:set>
                        <c:if test="${dividePage.lastPage <=5}">
                            <c:set var="end" value="${dividePage.lastPage}" scope="page"></c:set>
                        </c:if>
                        <c:if test="${dividePage.lastPage >5}">
                            <c:set var="end" value="5" scope="page"></c:set>
                        </c:if>
                    </c:when>
                    <c:when test="${dividePage.thisPage >= dividePage.lastPage-2}">
                        <c:set var="start" value="${dividePage.lastPage-4}" scope="page"></c:set>
                        <c:set var="end" value="${dividePage.lastPage}" scope="page"></c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="start" value="${dividePage.thisPage-2}" scope="page"></c:set>
                        <c:set var="end" value="${dividePage.thisPage+2}" scope="page"></c:set>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <c:forEach begin="${start}" end="${end}" step="1" var="i">
                <c:if test="${i == dividePage.thisPage}">
                    <li><a href="#" style="background-color: silver">${i}</a></li>
                </c:if>
                <c:if test="${i != dividePage.thisPage}">
                    <li><a href="${pageContext.request.contextPath}/stu/toView?thisPage=${i}&classId=${classId}">${i}</a></li>
                </c:if>
            </c:forEach>
            <li><a href="${pageContext.request.contextPath }/stu/toView?thisPage=${dividePage.nextPage}&classId=${classId}">&raquo;</a></li>
        </ul>--%>

     <%--   <div class="modal small fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">Delete Confirmation</h3>
                    </div>
                    <div class="modal-body">
                        <p class="error-text"><i class="fa fa-warning modal-icon"></i>Are you sure you want to delete the user?<br>This cannot be undone.</p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cancel</button>
                        <button class="btn btn-danger" data-dismiss="modal">Delete</button>
                    </div>
                </div>
            </div>
        </div>
--%>
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
