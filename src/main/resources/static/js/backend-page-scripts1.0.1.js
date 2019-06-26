// 跳转详情页
function goDetail(userId, nickName) {
    window.location.href="/detail?userId=" + userId + "&nickName=" + nickName
};

// 显示和隐藏文章内容
function showOrDisplay(clockInId) {
    $("#content" + clockInId).toggle();
}