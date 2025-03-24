function crear(){
    opcio = document.getElementById("opcions").value;
    let jugar = confirm("Nivell "+opcio+": "+nivells[opcio]+" punts");
    if(jugar) {
        borrar();
        document.getElementById("win").innerText = "";
        document.getElementById("win").className = "";
        let divs = [];
        for (let i = 1; i <= nivells[opcio]; i++) {
            divs[i-1] = cartes[i-1];
            divs[i-1+nivells[opcio]] = cartes[i+16];
        }
        divs.sort(() => Math.random() - 0.5);
        for (let carta of divs) {
            let nova = document.createElement("div");
            nova.classList.add("items");
            nova.classList.add("red");
            nova.id = carta.id;
            nova.onclick = clica;
            document.getElementById("contenidor").appendChild(nova);
        }
        document.getElementById("punts").innerText="Punts: "+puntuacio;
        comptador();
    }
}
function comptador() {
    clearInterval(interval);
    segons= 0;
    document.getElementById("temps").innerText = "Temps: "+segons+"s";
    interval = setInterval(function () {
        segons++;
        document.getElementById("temps").innerText = "Temps: "+segons+"s";
    }, 1000);
}
function borrar() {
    let llista = document.querySelectorAll("#contenidor div");
    for (let item of llista) {
        document.getElementById("contenidor").removeChild(item);
    }
    puntuacio = 0;
    segons= 0;
    clearInterval(interval);
    girades = [];
    trobades = [];
    document.getElementById("temps").innerText = "Temps: "+segons+"s";
    document.getElementById("punts").innerText="Punts: "+puntuacio;

}
function girar() {
    clearInterval(temporizador);
    let llista = document.querySelectorAll("#contenidor div");
    for (let item of llista) {
        if (trobades.indexOf(cartes[item.id].nom) === -1){
            item.style.removeProperty("background");
            item.classList.add("red");
        }
    }
    girades = [];
}
function clica() {
    // S’ha d’evitar que es faci cas a clics quan ja hi ha 2 elements seleccionats.
    // Para quitar los 2s de espera si seleccionas 2 borrar && girades.length < 2
    if(this.classList.contains("red") && girades.length < 2) {
        if (girades.length === 2){
                girar();
        }
        this.classList.remove("red");
        this.style.background = cartes[this.id].imatge+" center/cover no-repeat";
        girades[girades.length] = cartes[this.id].nom;
        if (girades.length === 2){
            if (girades[0] === girades[1]) {
                trobades[trobades.length] = girades[0];
                trobades[trobades.length] = girades[1];
                puntuacio ++;
                document.getElementById("punts").innerText="Punts: "+puntuacio;
                if (puntuacio === nivells[opcio]) {
                    document.getElementById("win").innerText="Has guanyat! has trobat "+puntuacio+ " parelles d'ODS en "+segons+" segons";
                    document.getElementById("win").classList.add("p-3", "mb-2", "bg-success", "text-white");
                    borrar()
                }
                girades = [];
            } else {
                segons2 = 0;
                temporizador = setInterval(function () {
                    segons2++;
                    if (segons2 === 2) {
                        girar();
                    }
                }, 1000);
            }
        }
    }
    // En el cas que s’hagi clicat abans, s’ha de descartar el clic.
    // No me queda claro si se refiere a que no haga nada o que la gire, para que gire solo habria que descomentar lo siguiente.
    // else if (trobades.indexOf(cartes[this.id].nom) === -1 && girades.length === 1) {
    //    girar();
    // }
}
function Ods(id, nom, imatge) {
    this.id = parseInt(id);
    this.nom = nom;
    this.imatge = imatge;
}
let opcio;
let segons2 = 0;
let temporizador;
let segons = 0;
let interval;
let cartes = [];
let girades = [];
let trobades = [];
let puntuacio = 0;
let nivells = {facil: 5, mig: 10, dificil: 15};
for (let i = 1; i <= 17; i++) {
    cartes[i-1] = new Ods(""+i-1,"ods"+i,"url(imatges/ods"+i+".png)" );
    let num = i+16;
    cartes[num] = new Ods(""+num,"ods"+i,"url(imatges/ods"+i+".png)" );

}
/**FALTA IDK
 * Records: Un cop guanyada la partida, es mira si és el temps minim del nivell.
 * En tal cas, es guarda el valor tardat al localstorage. Mostrar en un lateral
 * 3 tres valors del localstorage. Si és nou record, mostrar el missatge pertient,
 * evitant un alert o altres coses estranyes.
 */