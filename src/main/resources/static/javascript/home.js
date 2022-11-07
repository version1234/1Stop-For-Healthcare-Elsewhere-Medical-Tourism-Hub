
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const submitForm = document.getElementById("vis-form")
const userDetailsContainer = document.getElementById("via-userdetails-container")
const selectedInsurancesContainer = document.getElementById("via-selectedInsurance-div")
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

    displayCard.innerHTML = `
            <div><table>
                <tr><td></td><td><p class="welcomeCard"> <h3>Welcome</h3></p></td></tr>
                <tr><td></td><td><p class="welcomeCard"> <h3>${obj.firstname}, ${obj.lastname}</h3></p></td></tr>
                <tr><td>Phone: </td><td> ${obj.phonenumber}</td></tr>
                 <tr><td>email:</td><td>${obj.email}</td></tr>
              </table>
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
        array.forEach(obj => {
        let insuranceCard = `
            <div class="selectedInsurance_card">
                <h3> ${obj.policy.policyname}</h3><br>
                ${obj.policy.policydetail}<br>
                Limit:  ${obj.policy.policylimit}<br>
                Premium: ${obj.price}<br>
                Valid From:  ${obj.startDateDisplay},  To:  ${obj.endDateDisplay}<br>
               <div >
                   <button class="button3" onclick="handleDelete(${obj.confirmid})">Delete</button>
               </div>
            <div><br>
        `
        selectedInsurancesContainer.insertAdjacentHTML("beforeend", insuranceCard)
    })
}


getUserDetails(userId);
getConfirmedInsuranceDetailsByUser(userId);

submitForm.addEventListener("submit", handleSubmit)

