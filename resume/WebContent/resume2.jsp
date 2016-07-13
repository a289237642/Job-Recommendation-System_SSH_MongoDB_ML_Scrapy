<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Resume</title>
	<script type = "text/javascript" src = "js/Resume.js"></script>
	<link rel = "stylesheet" type = "text/css" href = "css/Resume.css">
</head>
<body id = "user">
<form action="tijiaole.action" method="post">
<div id = "wrap">
	<div class = "title">
		<h1>
			填写简历 让更多机会找到您
			<i class = "tips">必填</i>
		</h1>
		<p class = "subtitle">
			Update your resume to have more opportunities
		</p>
	</div>
	<div class = "part1">
		<div class = "tag1">
			<h2>
				基本信息
			</h2>
		</div>
		<div class = "content1">
			<div class = "first-line">
				<div class = "first-column">
					<input type = "text" class = "name" placeholder="姓名" id = "name" onblur = "chkname()">
				</div>
				<div class = "second-column">
					<select type = "hidden" class = "birth-year" id = "year"  onfocus = "selyear()" >
					<option>出生年份</option>
					</select>               
				</div>
				<div class = "third-column">
					<select type = "hidden" class = "gradu-year" id = "year1"  onfocus = "selyear1()" >
					<option>毕业日期/年</option>
					</select>
				</div>
				<div class = "forth-column">
					<select type = "hidden" class = "gradu-month" id = "month" onfocus = "selmonth()">
					<option>月</option>
					</select>
				</div>
			</div>
			<div class = "firsthidden-line">
				<div class = "nameerror">
					<span id = "error1"></span>
				</div>
			</div>
			<div class = "second-line">
				<div class = "first-column">
					<!-- <button class = "male" id = "male" onclick = "mstyle()">男</button>
					<button class = "female" id = "female" onclick = "fstyle()">女</button> -->
					<select type = "hidden" id = "sex" name="sex" >
					<option>性别</option>
					<option>男</option>
					<option>女</option>
					</select>
				</div>
				<div class = "second-column">
					<input type = "text" class = "tel-num" id = "num" placeholder="电话号码" onblur = "chknum()">
				</div>
				<div class = "third-column">
					<input type = "text" class = "Email" id = "email" placeholder="邮箱" onblur = "chkemail()" >
				</div>
			</div>
			<div class = "secondhidden-line">
				<div class = "numbererror">
					<span id = "error2"></span>
				</div>
				<div class = "emailerror">
					<span id = "error3"></span>
				</div>
			</div>
		</div>
	</div>
	<div class = "part2">
		<div class = "tag2">
			<h2>
				教育情况
			</h2>
		</div>
		<div class = "content2">
			<div class = "first-line">
					<div class = "first-column">
						<label> 成绩排名百分比（%）</label>
					</div>
					<div class = "second-column">
						<input type = "range" name="pm" min = "5" max = "90" step = "5" value = "5" onchange = "showResult.value = value">
						<output id = "showResult"></output>
					</div>
			</div>
			<div class = "second-line">
					<div class = "first-column">
						<label> 论文发表等级</label>
					</div>
					<div class = "second-column">
						<input type = "checkbox" name="level" value="0">无</input>
						<input type = "checkbox" name="level" value="1">被SCI检索</input>
						<input type = "checkbox" name="level" value="2">被EI检索</input>
						<input type = "checkbox" name="level" value="3">CCF-A类</input>
						<input type = "checkbox" name="level" value="4">CCF-B类</input>
						<input type = "checkbox" name="level" value="5">CCF-C类</input>
						<input type = "checkbox" name="level" value="6">其他</input>
					</div>				
			</div>
			<div class = "third-line">
				<div class = "first-column">
					<label> 荣誉与奖励</label>
					</div>
				<div class = "second-column">
					<textarea id  = "award" class= "award">	
					</textarea>
				</div>
			</div>
		</div>
	</div>
	<div class = "part3">
		<div class = "tag3">
			<h2>
				专业技能
			</h2>
		</div>
			<div class = "content3">
				<div class = "first-line">
					<div class = "first-column" id = "C++">
						<select name="skill">
						<option value="0">C++</option>
						<option value="1">了解</option>
						<option value="2">熟悉</option>
						<option value="3">精通</option>
						</select>
					</div>
					<div class = "second-column">
						<select name="skill" type = "hidden" id = "JAVA" >
						<option value="0">JAVA</option>
						<option value="1">了解</option>
						<option value="2">熟悉</option>
						<option value="3">精通</option>
						</select>               
					</div>
					<div class = "third-column">
						<select name="skill" type = "hidden" id = "Python">
						<option value="0">Python</option>
						<option value="1">了解</option>
						<option value="2">熟悉</option>
						<option value="3">精通</option>
						</select>
					</div>
					<div class = "forth-column">
						<select name="skill" type = "hidden" id = "Linux"  >
						<option value="0">Linux</option>
						<option value="1">了解</option>
						<option value="2">熟悉</option>
						<option value="3">精通</option>						
						</select>
					</div>
					<div class = "fifth-column">
						<select name="skill" type = "hidden" id = "Windows" >
						<option value="0">Windows</option>
						<option value="1">了解</option>
						<option value="2">熟悉</option>
						<option value="3">精通</option>
						</select>
					</div>
				</div>
				<div class = "firsthidden-line">
				</div>
				<div class = "second-line">
					<div class = "first-column">
						<select name="skill" type = "hidden" id = "TCP/IP"  >
						<option value="0">TCP/IP</option>
						<option value="1">了解</option>
						<option value="2">熟悉</option>
						<option value="3">精通</option>
						</select>
					</div>
					<div class = "second-column">
						<select name="skill" type = "hidden" id = "算法"  >
						<option value="0">算法</option>
						<option value="1">了解</option>
						<option value="2">熟悉</option>
						<option value="3">精通</option>
						</select>               
					</div>
					<div class = "third-column">
						<select name="skill" type = "hidden" id = "3/4G"  >
						<option value="0">3/4G</option>
						<option value="1">了解</option>
						<option value="2">熟悉</option>
						<option value="3">精通</option>
						</select>
					</div>
					<div class = "forth-column">
						<select name="skill" type = "hidden" id = "JavaWeb"  >
						<option value="0">JavaWeb</option>
						<option value="1">了解</option>
						<option value="2">熟悉</option>
						<option value="3">精通</option>
						</select>
					</div>
					<div class = "fifth-column">
						<select name="skill" type = "hidden" id = "Android/IOS" >
						<option value="0">Android/IOS</option>
						<option value="1">了解</option>
						<option value="2">熟悉</option>
						<option value="3">精通</option>
						</select>
					</div>
				</div>
				<div class = "secondhidden-line">
				</div>
				<div class = "third-line">
					<div class = "first-column">
						<select name="skill" type = "hidden" id = "数据结构">
						<option value="0">数据结构</option>
						<option value="1">了解</option>
						<option value="2">熟悉</option>
						<option value="3">精通</option>
						</select>
					</div>
					<div class = "second-column">
						<select name="skill" type = "hidden" id = "数据库">
						<option value="0">数据库</option>
						<option value="1">了解</option>
						<option value="2">熟悉</option>
						<option value="3">精通</option>
						</select>               
					</div>
					<div class = "third-column" style = "display:none">
						<select type = "hidden" id = ""  onfocus = "selskill()" >
						<option></option>
						</select>
					</div>
					<div class = "forth-column" style = "display:none">
						<select type = "hidden" id = ""  onfocus = "selskill()" >
						<option></option>
						</select>
					</div>
					<div class = "fifth-column" style = "display:none">
						<select type = "hidden" id = ""  onfocus = "selskill()" >
						<option></option>
						</select>
					</div>
				</div>
				<div class = "thirdhidden-line">
				</div>
				<div class = "forth-line">
					<div class = "first-column">
						<label> 技能证书</label>
						</div>
					<div class = "second-column">
						<textarea id  = "certificate" class= "certificate">	
						</textarea>
					</div>
				</div>
			</div>
		</div>
	<div class = "part4">
		<div class = "tag4">
			<h2>
				实习经历
			</h2>
		</div>
		<div class = "content4">
			<div class = "first-line">
				<div class = "first-column">
					<select type = "hidden"  id = "intern"  name = "intern" class = "intern" onchange = "chkintern()" >
					<option selected = "selected" >有无实习经历</option>
					<option value = "0">无</option>
					<option value = "1">有</option>
					</select>
				</div>
				<div class = "second-column" name = "intdetail" style = "display:none">
					<label> 实习次数</label>
				</div>
				<div class = "third-column" name = "intdetail" style = "display:none">					
					<input type = "checkbox" name="sx" value="1">1次</input>
					<input type = "checkbox" name="sx" value="2">2次</input>					
					<input type = "checkbox" name="sx" value="3">3次</input>
					<input type = "checkbox" name="sx" value="4">3次以上</input>				
					</div>
				</div>
			<div class = "firsthidden-line" name = "intdetail" style = "display:none">
			</div>
			<div class = "second-line" name = "intdetail" style = "display:none">
				<div class = "first-column">
					<select type = "hidden"  class="intern" >
					<option selected = "selected">有无研发实习经历</option>
					<option value = "0">无</option>
					<option value = "1">有</option>
				</select>
			</div>
				<div class = "second-column">
					<select type = "hidden" class="intern" >
					<option selected = "selected">有无测试实习经历</option>
					<option value = "0">无</option>
					<option value = "1">有</option>
					</select>
				</div>
				<div class = "third-column">
					<select type = "hidden" class="intern" >
					<option selected = "selected">有无技术支持实习经历</option>
					<option value = "0">无</option>
					<option value = "1">有</option>						</select>
				</div>
			</div>
			<div class = "secondhidden-line" name = "intdetail" style = "display:none">
			</div>
			<div class = "third-line" name = "intdetail" style = "display:none">
				<div class = "first-column">
					<label>实习详情</label>
				</div>
				<div class = "second-column">
					<textarea >	
					</textarea>
				</div>
			</div>
			<div class = "thirdhidden-line" name = "intdetail" style = "display:none">
			</div>
			<div class = "thirdhidden-line" name = "intdetail" style = "display:none">
			</div>
		</div>
	</div>
	<div class = "part5">
		<div class = "tag5">
			<h2>
				实践活动
			</h2>
		</div>
		<div class = "content5">
			<div class = "first-line">
				<div class = "first-column">
					<select type = "hidden"  class = "project" id = "project" onchange = "chkpro()" >
					<option selected = "selected" >有无项目经历</option>
					<option value = "0">无</option>
					<option value = "1">有</option>
					</select>
				</div>
				<div class = "second-column" name = "prodetail" style = "display:none">
					<label> 项目个数</label>
				</div>
				<div class = "third-column"  name = "prodetail" style = "display:none">			
					<input type = "checkbox" name="xg" value="1">1次</input>
					<input type = "checkbox" name="xg" value="2">2次</input>			
					<input type = "checkbox" name="xg" value="3">3次</input>
					<input type = "checkbox" name="xg" value="4">3次以上</input>				
				</div>
			</div>
			<div class = "firsthidden-line" name = "prodetail" style = "display:none">
			</div>
			<div class = "second-line" name = "prodetail" style = "display:none">
				<div class = "first-column">
					<label>相关经验描述</label>
				</div>
				<div class = "second-column">
					<textarea >	
					</textarea>
				</div>
			</div>
			<div class = "thirdhidden-line" name = "prodetail" style = "display:none">
			</div>
			<div class = "firsthidden-line" name = "prodetail" style = "display:none">
			</div>
			<div class = "firsthidden-line" >
			</div>
			<div class = "third-line">
				<div class = "first-column">
					<select type = "hidden"  class = "corporation" id = "corporation" onchange = "chkcor()">
					<option selected = "selected" >有无社团经历</option>
					<option value = "0">无</option>
					<option value = "1">有</option>
					</select>
				</div>
				<div class = "second-column" name = "cordetail" style = "display:none">
					<label> 社团个数</label>
				</div>
				<div class = "third-column"  name = "cordetail" style = "display:none">			
					<input type = "checkbox" name="st" value="1">1个</input>
					<input type = "checkbox" name="st" value="2">2个</input>			
					<input type = "checkbox" name="st" value="3">3个</input>
					<input type = "checkbox" name="st" value="4">3个以上</input>				
				</div>
			</div>
			<div class = "thirdhidden-line">
			</div>
			<div class = "forth-line" name = "cordetail" style = "display:none">
				<div class = "first-column">
					<label>相关经验描述</label>
				</div>
				<div class = "second-column">
					<textarea >	
					</textarea>
				</div>
			</div>
			<div class = "forthhidden-line" name = "cordetail" style = "display:none">
			</div>
			<div class = "fifth-line">
				<div class = "first-column">
					<select type = "hidden" name="flag" id = "game" class = "game" onchange = "chkgame()" >
					<option selected = "selected" value = "0">有无大赛经历</option>
					<option  value = "0">无</option>
					<option   value = "1">有</option>
					</select>
				</div>
			</div>
			<div class = "thirdhidden-line"  name = "gamedetail" style = "display:none">
			</div>
			<div class = "sixth-line" name = "gamedetail" style = "display:none">
				<div class = "first-column">
					<label>大赛经历详情</label>
				</div>
				<div class = "second-column">
					<textarea >	
					</textarea>
				</div>
			</div>
			<div class = "forthhidden-line" name = "gamedetail" style = "display:none">
			</div>
		</div>
	</div>
	<div class = "save">
		<input type = "submit" value = "提交简历" class = "savecard">
	</div>
</div>
</form>
</body>
</html>