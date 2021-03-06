<%--
  Created by IntelliJ IDEA.
  User: happy
  Date: 2019-04-07
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加帐户</title>
    <meta charset="UTF-8">
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        #wrap{
            width: 330px;
            margin: 20px auto;
            text-align: left;
        }
        .btn{
            margin-top: 10px;
        }
        input{
            margin-bottom: 10px;
        }
        #accountHint,#empNoHint{
            font-size: 13px;
            color: red;
        }
    </style>
    <script>
      window.onload = function (ev) {
        var back = document.getElementById("back");
        back.onclick = function (ev1) {
            window.location.href = "/web/manager/listAccount.do?page=1";
        };
        // 判断帐户是否存在
        var accounts = document.getElementById("accounts");
        accounts.onblur = function (ev1) {
            var values = this.value;
            var request = getRequest();
            var method = "GET";
            var url = "checkAccount.do?account=" + values;
            request.open(method, url);
            request.send();
            request.onreadystatechange = function () {
                if (request.readyState == 4){
                    if (request.status == 200 || request.status == 302){
                        document.getElementById("accountHint").innerHTML = request.responseText;
                    }
                }
            }
        };
        // 判断员工编号是否存在
        var empNoes = document.getElementById("empNoes");
        empNoes.onblur = function (ev1) {
            var values = this.value;
            var request = getRequest();
            var method = "GET";
            var url = "checkEmpNo.do?empNo=" + values;
            request.open(method, url);
            request.send();
            request.onreadystatechange = function () {
                if (request.readyState == 4){
                    if (request.status == 200 || request.status == 302){
                        document.getElementById("empNoHint").innerHTML = request.responseText;
                    }
                }
            }
        };
        function getRequest() {
            var request;
            if (window.XMLHttpRequest){
                request = new XMLHttpRequest();
            } else {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            }
            return request;
        }

      };
    </script>

</head>
<body>
<div id="wrap">
    <form action="saveAccount.do" method="post" accept-charset="UTF-8">
        <span>帐号：</span>
        <input type="text" name="account" id="accounts">
        <span id="accountHint"></span><br>
        <span>密码：</span>
        <input type="password" name="pwd"><br>
        <span>确认密码：</span>
        <input type="password" name="newPwd"><br>
        <span>员工编号：</span>
        <input type="text" name="empNo" id="empNoes" placeholder="账号以E开头">
        <span id="empNoHint"></span><br>
        <span>员工姓名：</span>
        <input type="text" name="empName"><br>
        <span>状态：</span>
        <select name="accountStatus">
            <c:forEach items="${listAccountStatus}" var="status">
                        <option value="${status.configPageValue}">${status.configPageValue}</option>
            </c:forEach>
        </select><br>
        <span>角色：</span>
        <select name="roleName">
            <c:forEach items="${listRoleName}" var="roleNames">
                        <option value="${roleNames.configPageValue}">${roleNames.configPageValue}</option>
            </c:forEach>
        </select><br>
        <div class="btn">
            <input type="submit" value="添加">
            <input type="reset" value="重置">
            <input type="button" value="返回" id="back">
        </div>
    </form>
</div>
</body>
</html>
