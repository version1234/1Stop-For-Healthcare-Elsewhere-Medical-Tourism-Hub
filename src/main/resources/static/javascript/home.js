
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const submitForm = document.getElementById("vis-form")
const userDetailsContainer = document.getElementById("via-userdetails-container")
const selectedInsurancesContainer = document.getElementById("via-selectedInsurance-container")
const policiesContainer = document.getElementById("via-policies-container")


const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8082/api/v1/"

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

async function handleDelete(confirmid){
    await fetch(`${baseUrl}confirm/${confirmid}`, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getConfirmedInsuranceDetailsByUser(userId);
}


async function getUserDetails(userId) {
    await fetch(`${baseUrl}profile/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => displayUserDetails(data))
        .catch(err => console.error(err))
}


const displayUserDetails = (obj) => {
    userDetailsContainer.innerHTML = ''
    let displayCard = document.createElement("div")
    displayCard.classList.add("m-2")
    displayCard.innerHTML = `
        <div class="card d-flex" style="width: 36rem; height: 15rem;">
            <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                <p class="card-text"> <h3>Welcome back ${obj.firstname}, ${obj.lastname}</h3></p>
                Address : ${obj.address}, ${obj.city}, ${obj.state}, ${obj.zip}
                <br>Phone : ${obj.phonenumber}
                <br>email: ${obj.email}
            </div>
        </div>
        `
    userDetailsContainer.append(displayCard)
}

async function getConfirmedInsuranceDetailsByUser(userId) {
    await fetch(`${baseUrl}confirm/profile/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => displayConfirmedInsuranceDetails(data))
        .catch(err => console.error(err))
}

const displayConfirmedInsuranceDetails = (array) => {
    selectedInsurancesContainer.innerHTML = '<table><tr>'
        array.forEach(obj => {
    let displayCard = document.createElement("div")

    displayCard.classList.add("m-2")
    displayCard.innerHTML = `<td>
        <div class="card d-flex" style="width: 18rem; height: 18rem;">
            <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                <p>
                    <p class="card-text"> <h4> ${obj.policy.policyname}</h4></p><br>
                    ${obj.policy.policydetail}<br>
                    Limit:  ${obj.policy.policylimit}<br>
                    Premium: ${obj.price}<br>
                    Valid From  ${obj.startDate},  To  ${obj.endDate}
               </p>
               <div class="d-flex justify-content-between">
                   <button class="btn btn-danger" onclick="handleDelete(${obj.confirmid})">Delete</button>
               </div>
            </div>
        </div></td>
        `
    selectedInsurancesContainer.append(displayCard)

    })
    selectedInsurancesContainer.innerHTML = selectedInsurancesContainer.innerHTML + '</tr></table>'
}


getUserDetails(userId);
getConfirmedInsuranceDetailsByUser(userId);

submitForm.addEventListener("submit", handleSubmit)

