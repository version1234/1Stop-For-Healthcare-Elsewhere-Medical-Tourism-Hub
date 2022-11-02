const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const policiesForm = document.getElementById("policies-form")
cons policiesContainer = document.getElementById("via-policies-container")

cons policyStartDate= document.getElementById("policy-startdate")
cons policyEndDate = document.getElementById("policy-enddate")

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8082/api/v1/policy"

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
        startDate: policyStartDate.value,
        endDate: policyEndDate.value
    }

    const response = await fetch(`${baseUrl}/profile`, {
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




policiesForm.addEventListener("submit", handleSubmit)