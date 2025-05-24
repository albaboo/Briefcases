function novaPartida() {
    this.event.preventDefault();
    let resultat= document.getElementById('resultat');
    resultat.textContent = "";
    $.ajax ({
        url: "php/creaPartida.php",
        type: "GET",
        data: { "desc": document.getElementById("desc").value, "user": localStorage.getItem('usuari') },
        dataType: 'json',
        success: function (data) {
            if (data.estat === 'OK') {
                resultat.textContent = "Partida creada correctament";
            } else {
                resultat.textContent = data.error;
            }
        },
        error: function (jqXHR){
            resultat.innerHTML=jqXHR.responseText;
        }
    });
}
function novaPregunta() {
    this.event.preventDefault();
    let form = document.getElementById('formulario');
    if(form.checkValidity()) {
        this.event.preventDefault();
        let resultat= document.getElementById('resultat');
        resultat.textContent = "";
        $.ajax ({
            url: "php/creaPregunta.php",
            type: "GET",
            data: { "desc": document.getElementById("desc").value,
                "tema": document.getElementById('tema').value,
                "r1": document.getElementById('r1').value,
                "r2": document.getElementById('r2').value,
                "r3": document.getElementById('r3').value,
                "r4": document.getElementById('r4').value,
                "solu": document.getElementById('solu').value},
            dataType: 'json',
            success: function (data) {
                resultat.textContent = data;
            },
            error: function (jqXHR){
                resultat.innerHTML=jqXHR.responseText;
            }
        });
    } else {
        form.reportValidity();
    }
}
function modificaPregunta() {
    let form = document.getElementById('formulario');
    if(form.checkValidity()) {
        this.event.preventDefault();
        let resultat= document.getElementById('resultat');
        resultat.textContent = "";
        $.ajax ({
            url: "php/modificaPregunta.php",
            type: "GET",
            data: { "id": document.getElementById("id").value,
                "desc": document.getElementById("desc").value,
                "tema": document.getElementById('tema').value,
                "r1": document.getElementById('r1').value,
                "r2": document.getElementById('r2').value,
                "r3": document.getElementById('r3').value,
                "r4": document.getElementById('r4').value,
                "solu": document.getElementById('solu').value},
            dataType: 'json',
            success: function (data) {
                resultat.textContent = data;
            },
            error: function (jqXHR){
                resultat.innerHTML=jqXHR.responseText;
            }
        });
    } else {
        form.reportValidity();
    }
}
function login() {
    this.event.preventDefault();
    let resultat= document.getElementById('resultat');
    resultat.textContent = "";
    $.ajax ({
        url: "php/Login.php",
        type: "GET",
        data: { "user": document.getElementById("user").value, "pwd": document.getElementById('pwd').value},
        dataType: 'json',
        success: function (data) {
            if (data.estat === 'OK') {
                localStorage.setItem('usuari', data.usuari_app);
                window.location.href = 'Ruleta22.html';
            } else {
                resultat.textContent = data.error;
            }
        },
        error: function (jqXHR){
            resultat.innerHTML=jqXHR.responseText;
        }
    });
}
function register() {
    this.event.preventDefault();
    let resultat= document.getElementById('resultat');
    resultat.textContent = "";
    $.ajax ({
        url: "php/Register.php",
        type: "GET",
        data: { "user": document.getElementById("user").value, "pwd": document.getElementById('pwd').value},
        dataType: 'json',
        success: function (data) {
            if (data.estat === 'OK') {
                window.location.href = 'Login.html';
            } else {
                resultat.textContent = data.error;
            }
        },
        error: function (jqXHR){
            resultat.innerHTML=jqXHR.responseText;
        }
    });
}
function cercarPreguntes() {
    let tema = document.getElementById('tema').value;
    let resultat= document.getElementById('resultat');
    if (tema == 0) {
        document.getElementById("caixa").className = "d-flex align-content-start h-75 w-75";
    } else {
        document.getElementById("caixa").className = "d-flex align-content-start h-75 w-50"
    }
    $.ajax({
        url: 'php/preguntes.php',
        type: 'GET',
        data: { tema: tema },
        dataType: 'json',
        success: function(preguntes) {
            if (preguntes.length === 0) {
                $('#preguntes').textContent = "No hi ha preguntes per aquest tema";
                return;
            }
            let text = "";
            let num = 0;
            preguntes.forEach(pregunta => {
                if (tema == 0) {
                    if(num != pregunta["tema"]) {
                        num = pregunta["tema"];
                        if (num == 1) {
                            text += "<div class='col'>"+"<h4 class='w-100'>Tema "+pregunta["tema"]+"</h4>";
                        } else {
                            text += "</div>" +
                                "<div class='col'>"+"<h4 class='w-100'>Tema "+pregunta["tema"]+"</h4>";
                        }

                    }
                }
                text += "<p class='w-100'>"+pregunta["descripcio"]+"</p>";
            });
            if (tema == 0) {
                text += "</div>";
            }
            resultat.innerHTML = text;
        },
        error: function() {
            resultat.textContent = "Error al cercar preguntes";
        }
    });
}