let angleAcumulat = 0;

function validar(){
        this.event.preventDefault();
        netejar()
        document.getElementById('pregunta').innerText = "";
        if (!localStorage.getItem('usuari')) {
                window.location.href = 'Login.html';
        } else {
                let resultat= document.getElementById('resultat');
                let girar= document.getElementById('girar');
                resultat.textContent = "";
                $.ajax ({
                        url: "php/Validar.php",
                        type: "GET",
                        data: { "partida": document.getElementById("partida").value, "user": localStorage.getItem('usuari')},
                        dataType: 'json',
                        success: function (data) {
                                if (data.estat === 'OK') {
                                        girar.className = "btn btn-dark w-25 h-100 mx-auto fs-3";
                                        localStorage.setItem('partida', data.partida);
                                } else {
                                        resultat.textContent = data.error;
                                }
                        },
                        error: function (jqXHR){
                                resultat.innerHTML=jqXHR.responseText;
                        }
                });
        }

}

function tancarSessio(){
        if(!localStorage.getItem('partida')){
                localStorage.removeItem('partida');
        }
        netejar()
        document.getElementById('pregunta').innerText = "";
        localStorage.removeItem('usuari');
        window.location.href = 'index.html';
}
function clica(){
        document.getElementById("pieContainer").style.animation = "none";
        document.getElementById("resultat").innerText = "";
        document.getElementById('pregunta').innerText = "";
        netejar()
        let voltes = Math.floor(Math.random() * 5) + 15;
        let angleFinal = angleAcumulat + (voltes * 360 + Math.floor(Math.random() * 360));
        let angleRelatiu = (angleFinal % 360);

        // Determinar la secció seleccionada
        let seccio="";
        let seccio2="";
        if (angleRelatiu >= 0 && angleRelatiu < 60) {
                seccio = "Hotpink";
        } else if (angleRelatiu >= 60 && angleRelatiu < 120) {
                seccio = "Vermell";
        } else if (angleRelatiu >= 120 && angleRelatiu < 180) {
                seccio = "Groc";
        } else if (angleRelatiu >= 180 && angleRelatiu < 240){
                seccio = "Lime";
        } else if (angleRelatiu >= 240 && angleRelatiu < 300){
                seccio = "Cyan";
        } else{
                seccio = "Fuchsia";
        }

        if (angleRelatiu >= 0 && angleRelatiu < 90) {
                seccio2 = "Groc";
        } else if (angleRelatiu >= 90 && angleRelatiu < 180) {
                seccio2 = "Lime";
        } else if (angleRelatiu >= 180 && angleRelatiu < 270) {
                seccio2 = "Cyan";
        } else{
                seccio2 = "Fuchsia";
        }

        let nomAnimacio = "gira_" + Date.now();
        let estil = document.createElement("style");
        estil.innerHTML = "@keyframes "+ nomAnimacio + " {" +
            "from { transform: rotate(0deg); }" +
            " to { transform: rotate(" + angleFinal + "deg); }" + "}";

        document.head.appendChild(estil);
        document.getElementById("pieContainer").style.animation = nomAnimacio + " 5s ease-out forwards";
        document.getElementById("pieContainer2").style.animation = nomAnimacio + " 5s ease-out forwards";
        angleAcumulat = angleRelatiu;
        setTimeout(() => {
                document.getElementById("resultat").innerHTML = seccio + "<br/>";
                document.getElementById("resultat").innerHTML += seccio2 + "<br/>";
                setTimeout(() => {
                        if (seccio2 === "Groc") {
                                seccioPunts(50)
                        } else if (seccio2 === "Lime") {
                                seccioPunts(-50)
                        } else if (seccio2 === "Cyan") {
                                preguntar(seccio)
                                localStorage.setItem('formatge', seccio);
                        } else{
                                preguntar(seccio)
                        }
                }, 2000);
        }, 5000);



}
function seccioPunts(valor){
        let resultat= document.getElementById('resultat');
        resultat.textContent = "";
        $.ajax ({
                url: "php/canviaPunts.php",
                type: "GET",
                data: { "valor": valor, "user": localStorage.getItem('usuari') },
                dataType: 'json',
                success: function (data) {
                        if (data.estat === 'OK') {
                                resultat.textContent = "Puntuacio: " + data.punts;
                        } else {
                                resultat.textContent = data.error;
                        }
                },
                error: function (jqXHR){
                        resultat.innerHTML=jqXHR.responseText;
                }
        });
}

function preguntar(seccio){
        let resposta= document.getElementById("resposta");
        let constestar= document.getElementById("contestar");
        let pregunta= document.getElementById("pregunta");
        let tema = 0;
        if (seccio === "Hotpink") {
                tema = 6;
        } else if (seccio === "Vermell") {
                tema = 5;
        } else if (seccio === "Groc") {
                tema = 4;
        } else if (seccio === "Lime") {
                tema = 3;
        } else if (seccio === "Cyan") {
                tema = 2;
        } else{
                tema = 1;
        }
        $.ajax({
                url: "php/obtenerPregunta.php",
                type: "GET",
                data: { "tema": tema },
                dataType: 'json',
                success: function(data) {
                        console.log(data.solucio)
                        if(data.estat === 'OK') {
                                pregunta.textContent = data.pregunta;
                                let respostes = '';
                                respostes += '<option value="1">'+data.resposta1+'</option>';
                                respostes += '<option value="2">'+data.resposta2+'</option>';
                                respostes += '<option value="3">'+data.resposta3+'</option>';
                                respostes += '<option value="4">'+data.resposta4+'</option>';
                                resposta.innerHTML = respostes;
                                localStorage.setItem('solucio', data.solucio);
                                resposta.className = "form-select fs-3 w-auto";
                                constestar.className = "btn btn-dark fs-3 px-4";
                        } else {
                                pregunta.textContent = data.error;
                        }
                },
                error: function(jqXHR) {
                        pregunta.innerHTML=jqXHR.responseText;
                }
        });
}
function comprovarResposta() {
        let resultat = document.getElementById("resultat");
        let pregunta = document.getElementById("pregunta");
        let resposta = document.getElementById("resposta").value;
        pregunta.textContent = "";
        if (resposta === localStorage.getItem('solucio')) {
                resultat.textContent = "Correcte! +100 punts";
                if (localStorage.getItem('formatge')) {
                        $.ajax ({
                                url: "php/modificaFormatges.php",
                                type: "GET",
                                data: { "formatge": localStorage.getItem('formatge'), "partida": localStorage.getItem('partida') },
                                dataType: 'json',
                                success: function (data) {
                                        if (data.estat === 'OK') {
                                                setTimeout(() => {
                                                        pregunta.textContent = "Nou formatge: " + localStorage.getItem('formatge');
                                                        setTimeout(() => {
                                                                comprovaFormatges()
                                                        }, 2000);
                                                }, 2000);

                                        } else {
                                                setTimeout(() => {pregunta.textContent =  data.error;}, 2000);
                                        }
                                },
                                error: function (jqXHR){
                                        setTimeout(() => {pregunta.innerHTML=jqXHR.responseText;}, 2000);
                                }
                        });
                }
                setTimeout(() => {seccioPunts(100)}, 3000);
        } else {
                resultat.textContent = "Incorrecte! -50 punts :(";
                setTimeout(() => {seccioPunts(-50)}, 3000);
        }
        netejar();
}

function comprovaFormatges(){
        let resultat= document.getElementById('resultat');
        let girar= document.getElementById('girar');
        resultat.textContent = "";
        $.ajax ({
                url: "php/comprovaFormatges.php",
                type: "GET",
                data: { "partida": localStorage.getItem('partida')},
                dataType: 'json',
                success: function (data) {
                        if (data.estat === 'OK') {
                                if(data.complet){
                                        resultat.textContent = 'Tots els formatges conseguits! :)';
                                        girar.className = "btn btn-dark w-25 h-100 mx-auto fs-3 d-none";
                                }
                        } else {
                                resultat.textContent = data.error;
                        }
                },
                error: function (jqXHR){
                        resultat.innerHTML=jqXHR.responseText;
                }
        });
}

function netejar(){
        let resposta = document.getElementById("resposta");
        let constestar= document.getElementById("contestar");
        resposta.className = "form-select fs-3 w-auto d-none";
        constestar.className = "btn btn-dark fs-3 px-4 d-none";
        if (!localStorage.getItem('formatge')) {
                localStorage.removeItem('formatge');
        }
        if (!localStorage.getItem('solucio')) {
                localStorage.removeItem('solucio');
        }
}