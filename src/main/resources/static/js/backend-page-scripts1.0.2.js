// 跳转详情页
function goDetail(userId, nickName) {
    window.location.href="/detail?userId=" + userId + "&nickName=" + nickName;
};

// 显示和隐藏文章内容
function showOrDisplay(clockInId) {
    $("#content" + clockInId).toggle();
};

// 用户登录
function login() {
    var username = $("#username").val();
    if(username == null || username == ""){
        alert("用户名不能为空");
        return false;
    }
    var password = $("#password").val();
    if(password == null || password == ""){
        alert("密码不能为空");
        return false;
    }
    window.location.href="/login?username=" + username + "&password=" + password;
}