<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--静态包含 base标签 css样式，jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		//页面加载完成之后
		$(function () {

			$("#username").blur(function () {
              //1、获取用户名
				var username = this.value;
				$.getJSON("http://localhost:8080/book/userServlet","action=ajaxExistsusername&username="+username,function (data) {
                    console.log(data)
					if(data.existsUsername){
						$("span.errorMsg").text("用户名已存在！");
					}else {
						$("span.errorMsg").text("用户名可用");
					}
				});
			});

			$("#code_img").click(function () {
				//在事件响应的function函数中有一个this对象，这个this对象，是当前正在响应的dom对象
				//src属性表示验证码img标签的图片路径，它可读可写
              this.src="${basePath}/Kaptcha.jpg?=" + new Date();
			});


			//给注册绑定单击事件
          $("#sub_btn").click(function () {
            var username = $("#username").val();
            var regStr = /^\w{5,12}/;

            if(!regStr.test(username)){
                $("span.errorMsg").text("用户名不合法");
            	return false;
			}

            var passwordTest = $("#password").val();
            var passwordPatt = /^\w{5,12}/;

			  if(!passwordPatt.test(passwordTest)){
				  $("span.errorMsg").text("密码不合法");
				  return false;
			  }

			  var repwdTest = $("#repwd").val();
			  if(repwdTest!=passwordTest){
				  $("span.errorMsg").text("确认密码和密码不一致");
				  return false;
			  }

			  var emailTest = $("#email").val();
			  var emailPatt = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
			  if(!emailPatt.test(emailTest)){
				  $("span.errorMsg").text("邮箱不合法");
				  return false;
			  }

			  var codeTest = $("#code").val();

			  if(codeTest==null||codeTest==" "){
				  $("span.errorMsg").text("验证码不能为空");
				  return false;
			  }




					$("span.errorMsg").text("");

		  });




		});
	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>

<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   value="${requestScope.username}"
										   autocomplete="off" tabindex="1" name="username" id="username" />

									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   value="${requestScope.email}"
										   autocomplete="off" tabindex="1" name="email" id="email"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 80px;" id="code"/>
									<img id="code_img" alt="" src="Kaptcha.jpg" style="float: right; margin-right: 40px;width: 110px;height: 30px;">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--静态包含页脚内容 --%>
		<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>