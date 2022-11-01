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


const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
//        profileid: 1,
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

registerForm.addEventListener("submit", handleSubmit)
