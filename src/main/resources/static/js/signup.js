let f = document.getElementById("regForm");

async function checkEmail() {
    let response = await fetch("/api/mailcheck?email=" + f.email.value);
    if(await response.json()) {
        f.submit();
    } else {
        alert("Этот email уже занят!");
        f.email.focus();
    }
}