const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];


const policiesForm = document.getElementById("policies-form")
const policiesContainer = document.getElementById("via-policies-container")

const policyStartDate = document.getElementById("policy-startdate")
const policyEndDate = document.getElementById("policy-enddate")

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

async function handleInsert(policyIdValue){
    let bodyObj = {
        policyid: policyIdValue,
       profileid: userId,
       startDate: policyStartDate.value,
         endDate: policyEndDate.value,
           price: 0
     }
    await fetch(`${baseUrl}/save`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))
//    const responseArr = await response.json()

//    if (response.status === 200){
        window.location.replace("http://localhost:8082/home.html")
//    }
}


const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        profileid: userId,
        startDate: policyStartDate.value,
          endDate: policyEndDate.value,
         policyid: 0,
            price: 0

    }

    const response = await fetch(`${baseUrl}/profile`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .then(response => response.json())
        .then(data => displayAvailableInsuranceDetails(data))
        .catch(err => console.error(err.message))

//    const responseArr = await response.json()
//
//    if (response.status === 200){
//        window.location.replace(responseArr[0])
//    }
}

const displayAvailableInsuranceDetails = (array) => {
    policiesContainer.innerHTML = '<table><tr>'
        array.forEach(obj => {
    let displayCard = document.createElement("div")

    displayCard.classList.add("m-2")
    displayCard.innerHTML = `<td>
        <div class="card d-flex" style="width: 18rem; height: 18rem;">
            <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                <p>
                    <p class="card-text"> <h4> ${obj.policyname}</h4></p><br>
                    ${obj.policydetail}<br>
                    Limit:  ${obj.policylimit}<br>
                    Premium: ${obj.policydailyprice}

               </p>
               <div class="d-flex justify-content-between">
                   <button class="btn btn-secondary" onclick="handleInsert(${obj.policyid})">Select</button>
               </div>
            </div>
        </div></td>
        `
    policiesContainer.append(displayCard)

    })
    policiesContainer.innerHTML = policiesContainer.innerHTML + '</tr></table>'
}




policiesForm.addEventListener("submit", handleSubmit);