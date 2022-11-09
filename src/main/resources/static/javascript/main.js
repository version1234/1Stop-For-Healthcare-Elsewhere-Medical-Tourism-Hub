
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}



async function verifyAndRoute(userId) {
    if(userId!=null){
        document.getElementById('loginLogutLink').innerHTML='<a href="./home.html">Home</a>  <a href="./register.html">Profile</a>  <a href="./login.html" onclick="handleLogout()">Logout</a>'
    }
}

verifyAndRoute(userId);
