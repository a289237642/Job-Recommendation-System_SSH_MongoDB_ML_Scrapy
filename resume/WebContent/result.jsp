<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set  var="gw" value="${gangwei}"/>
<c:set  var="result" value="${data.ok}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${gw}</title>
<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>
<link href="https://cdn.bootcss.com/ng-table/1.0.0-beta.9/ng-table.min.css" rel="stylesheet">
<link rel="stylesheet" href="bootstrap.min.css">
<script src="https://cdn.bootcss.com/ng-table/1.0.0-beta.9/ng-table.js"></script>

</head>
<body>
<div ng-app="myApp" ng-controller="ExampleCtrl as vm ">
<table ng-table="vm.tableParams" class="table" show-filter="true">
    <tr ng-repeat="user in $data">
        <td title="'intern_data'" filter="{ intern_data: 'text'}" >
            {{user.intern_data}}</td>
        <td title="'intern_url'" filter="{ intern_url: 'text'}" >
            {{user.intern_url}}</td>
        <td title="'intern_info'" filter="{ intern_info: 'text'}">
            {{user.intern_info}}</td>
    </tr>
</table>
</div>

<script>
var app = angular.module('myApp',['ngTable']);
app.controller('ExampleCtrl',function($scope,NgTableParams){
var vm=this;
var data = ${result};
vm.tableParams = new NgTableParams({ count: 100},{dataset: data});
});
</script>

</body>
</html>