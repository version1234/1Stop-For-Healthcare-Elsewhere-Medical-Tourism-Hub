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
        window.location.replace("http://localhost:8082/home.html")
}

const displayAvailableInsuranceDetails = (array) => {
        array.forEach(obj => {
        let insuranceCard = `
            <div class="selectedInsurance_card">
                    <table><tr><td><img src="../images/${obj.imageName}" width="350" height="100"></td></tr>
                    <tr><td>
                   <h3> ${obj.policyname}</h3><br>
                   ${obj.policydetail}<br>
                   Limit:  ${obj.policylimit}<br>
                   Deductable: ${obj.policyDeductable}<br>
                   Premium: $${obj.policydailyprice}

                  <div>
                      <button class="button3" onclick="handleInsert(${obj.policyid})">Select</button>
                  </div>
                  </td></tr></table>
            </div><br>
            `
        policiesContainer.insertAdjacentHTML("beforeend", insuranceCard)
    })
}

const handleSubmit = async (e) =>{
    e.preventDefault()
    policiesContainer.innerHTML=""

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
}





async function verifyAndRoute(userId) {
    if(userId==null){
     window.location.replace("http://localhost:8082/1Stop4HEMTP.html")
    }
}

verifyAndRoute(userId);


policiesForm.addEventListener("submit", handleSubmit);