const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const registerForm = document.getElementById('register-form')
const registerUsername = document.getElementById('register-username')
const registerPassword = document.getElementById('register-password')
const registerFirstName = document.getElementById('register-firstname')
const registerLastName = document.getElementById('register-lastname')
const registerDateOfBirth = document.getElementById('register-dateOfBirth')
const registerEmail = document.getElementById('register-email')
const registerPhoneNumber = document.getElementById('register-phoneNumber')
const registerAddress = document.getElementById('register-address')
const registerCity = document.getElementById('register-city')
const registerState = document.getElementById('register-state')
const registerZip = document.getElementById('register-zip')

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = 'http://localhost:8082/api/v1/profile'


function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        profileid: userId,
        username: registerUsername.value,
        password: registerPassword.value,
       firstname: registerFirstName.value,
        lastname: registerLastName.value,
     dateofbirth: registerDateOfBirth.value,
           email: registerEmail.value,
     phonenumber: registerPhoneNumber.value,
         address: registerAddress.value,
            city: registerCity.value,
           state: registerState.value,
             zip: registerZip.value
    }

    const response = await fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))

    const responseArr = await response.json()

    if (response.status === 200){
        window.location.replace(responseArr[0])
    }
}


async function getUserDetails(userId) {
    console.log(userId)
    await fetch(`${baseUrl}/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => displayUserDetails(data))
        .catch(err => console.error(err))
}


const displayUserDetails = (obj) => {
    if(obj.username!=null){
        document.getElementById('registerh1').innerHTML='<h1>Edit Profile</h1>'
        document.getElementById('loginLink').innerHTML='<a href="./1Stop4HEMTP.html">Main Page</a>  <a href="./login.html" onclick="handleLogout()">Logout</a>'
        document.getElementById('register-username').value = obj.username
        document.getElementById('register-password').value = ""
        document.getElementById('register-firstname').value = obj.firstname
        document.getElementById('register-lastname').value = obj.lastname
        document.getElementById('register-dateOfBirth').value = obj.dateofbirth
        document.getElementById('register-email').value = obj.email
        document.getElementById('register-phoneNumber').value = obj.phonenumber
        document.getElementById('register-address').value = obj.address
        document.getElementById('register-city').value = obj.city
        document.getElementById('register-state').value = obj.state
        document.getElementById('register-zip').value = obj.zip
    }

}

getUserDetails(userId);
registerForm.addEventListener("submit", handleSubmit)
