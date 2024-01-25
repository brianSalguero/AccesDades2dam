/**
 * Al cargar la pàgina, cargaran les dades automàticament. Si estem en la pàgina de recuperació de dades no mostraran eixos dades
 */
document.addEventListener('DOMContentLoaded', function () {
    if (!document.body.classList.contains('pagina-recuperacio')) {
        getDades();
    } else {
        getRecuperacioDades();
    }
});

/**
 * Funció GET per a mostrar totes les dades.
 */
function getDades() {
    axios
        .get("https://api.disneyapi.dev/character/")
        .then(response => {
            const cardContainer = document.querySelector('.card-container');
            cardContainer.innerHTML = "";

            response.data.data.forEach(personatge => {
                // Crear elemento de tarjeta de Bootstrap
                const card = document.createElement('div');
                card.className = 'card';
                card.style = 'width: 18rem; margin: 20px; margin-bottom: 0px '; // Ajusta el estilo según tus necesidades

                let id = personatge._id;
                let nomPersonatge = personatge.name;
                let imatgeUrl = personatge.imageUrl;
                let pelicules = personatge.films;
                let webUrl = personatge.sourceUrl;
                let series = personatge.tvShows;
                let videoJoc = personatge.videoGames;

                // Si n'hi ha mes de 3 pelicules o series o videojocs, mostraràn a soles 3 d'elles

                if (personatge.films.length > 3) {
                    pelicules = personatge.films ? personatge.films.slice(0, 3) : [];
                }
                if (pelicules == "undefined" || pelicules === null || pelicules.length === 0) {
                    pelicules = "Sense películes"
                }

                if (personatge.tvShows.length > 3) {
                    series = personatge.tvShows ? personatge.tvShows.slice(0, 3) : [];
                }
                if (series == "undefined" || series === null || series.length === 0) {
                    series = "Sense series"
                }

                if (personatge.videoGames.length > 3) {
                    videoJoc = personatge.videoGames ? personatge.videoGames.slice(0, 3) : [];
                }
                if (videoJoc == "undefined" || videoJoc === null || videoJoc.length === 0) {
                    videoJoc = "Sense video jocs"
                }

                // Crea el contingut de la tarjeta de Bootstrap
                card.innerHTML = `
                    <div class="card-body text-center">
                        <h5 class="card-title">${nomPersonatge}</h5>
                        <p class="card-text">
                            <strong>Id:</strong>  ${id}
                        </p>
                        <p class="card-text">
                            <strong>Films:</strong>  ${generarParagrafs(pelicules)}
                        </p>
                        <p class="card-text">
                            <strong>Series:</strong> ${generarParagrafs(series)}
                        </p>
                        <p class="card-text">
                            <strong>Video Joc:</strong> ${generarParagrafs(videoJoc)}
                        </p>
                        <a href="${webUrl}" class="btn btn-primary">Ir a la página web</a>
                        <button data-id="${id}" data-films="${pelicules}" data-series="${series}" data-name="${nomPersonatge}" data-videoJoc="${videoJoc}" data-imatgeUrl="${imatgeUrl}" data-sourceUrl=${webUrl}" style="margin-top: 5px" type="button" class="btn btn-warning" onclick="guardarInfo(this)">Guardar Informació</button>
                    </div>
                `;
                const imgElement = document.createElement('img');
                imgElement.className = 'card-img-top';

                if (imatgeUrl) {
                    imgElement.src = imatgeUrl;
                    imgElement.alt = nomPersonatge;
                    card.insertBefore(imgElement, card.firstChild);  // Inserta l'imatge abans del contenedor div
                } else {
                    imgElement.style.display = 'none';  // Oculta l'imatge si no n'hi ha url
                }

                // Afegeix la tarjeta al contenidor
                cardContainer.appendChild(card);
            })

            // Organitza tarjetes en tres columnes per fila (utilitzant Bootstrap)
            const cardColumns = document.querySelectorAll('.card');
            cardColumns.forEach(function (card, index) {
                if (index % 3 === 0) {
                    const cardRow = document.createElement('div');
                    cardRow.className = 'row';
                    cardContainer.appendChild(cardRow);
                }

                const cardRow = cardContainer.lastElementChild;
                cardRow.appendChild(card);
            });
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

/**
 * Funció GET per a mostrar totes les dades segon el nom del personatge que volguem.
 */
function getDadesPersonatges() {
    const strBusquedaPersonatges = document.getElementById('charactersDisney').value;
    if (strBusquedaPersonatges === '') {
        getDades();
    } else {
        axios
            .get("https://api.disneyapi.dev/character/?name=" + strBusquedaPersonatges)
            .then(response => {
                const cardContainer = document.querySelector('.card-container');
                cardContainer.innerHTML = "";
                const personatges = response.data.data;

                // En cas que siga un array recorrerà tots els personatges donats amb eixe nom. Si es un objecte ho mostrarà.
                if (Array.isArray(personatges)) {
                    if (personatges.length === 0) {
                        cardContainer.innerHTML = "<p>No s'han encontrat coincidencies</p>";
                    } else {
                        response.data.data.forEach(personatge => {
                            // Crear elemento de tarjeta de Bootstrap
                            const card = document.createElement('div');
                            card.className = 'card';
                            card.style = 'width: 18rem; margin: 20px; margin-bottom: 0px '; // Ajusta el estilo según tus necesidades

                            let id = personatge._id;
                            let nomPersonatge = personatge.name;
                            let imatgeUrl = personatge.imageUrl;
                            let pelicules = personatge.films;
                            let series = personatge.tvShows;
                            let videoJoc = personatge.videoGames;
                            let webUrl = personatge.sourceUrl;

                            if (personatge.films.length > 3) {
                                pelicules = personatge.films ? personatge.films.slice(0, 3) : [];
                            }
                            if (pelicules == "undefined" || pelicules === null || pelicules.length === 0) {
                                pelicules = "Sense películes"
                            }

                            if (personatge.tvShows.length > 3) {
                                series = personatge.tvShows ? personatge.tvShows.slice(0, 3) : [];
                            }
                            if (series == "undefined" || series === null || series.length === 0) {
                                series = "Sense series"
                            }

                            if (personatge.videoGames.length > 3) {
                                videoJoc = personatge.videoGames ? personatge.videoGames.slice(0, 3) : [];
                            }
                            if (videoJoc == "undefined" || videoJoc === null || videoJoc.length === 0) {
                                videoJoc = "Sense video jocs"
                            }

                            // Crear el contenido de la tarjeta de Bootstrap
                            card.innerHTML = `
                        <img src="${imatgeUrl}" class="card-img-top" alt="${nomPersonatge}">
                        <div class="card-body text-center">
                            <h5 class="card-title">${nomPersonatge}</h5>
                            <p class="card-text">
                                <strong>Id:</strong>  ${id}
                            </p>
                            <p class="card-text">
                                <strong>Films:</strong>  ${generarParagrafs(pelicules)}
                            </p>
                            <p class="card-text">
                                <strong>Series:</strong> ${generarParagrafs(series)}
                            </p>
                            <p class="card-text">
                                <strong>Video Joc:</strong> ${generarParagrafs(videoJoc)}
                            </p>
                            <a href="${webUrl}" class="btn btn-primary">Ir a la página web</a>
                            <button data-id="${id}" data-films="${pelicules}" data-series="${series}" data-name="${nomPersonatge}" data-videoJoc="${videoJoc}" data-imatgeUrl="${imatgeUrl}" data-sourceUrl=${webUrl}" style="margin-top: 5px" type="button" class="btn btn-warning" onclick="guardarInfo(this)">Guardar Informació</button>
                        </div>
                    `;
                            // Agregar la tarjeta al contenedor
                            cardContainer.appendChild(card);
                        });
                        // Organizar tarjetas en tres columnas por fila (usando Bootstrap)
                        const cardColumns = document.querySelectorAll('.card');
                        cardColumns.forEach(function (card, index) {
                            if (index % 3 === 0) {
                                const cardRow = document.createElement('div');
                                cardRow.className = 'row';
                                cardContainer.appendChild(cardRow);
                            }

                            const cardRow = cardContainer.lastElementChild;
                            cardRow.appendChild(card);
                        });
                    }
                } else if (personatges && typeof personatges === 'object') {
                    // Manetjar a soles un personatge
                    const character = personatges;
                    const card = document.createElement('div');
                    card.className = 'card';
                    card.style = 'width: 18rem; margin: 20px; margin-bottom: 0px ';

                    let id = character._id;
                    let nomPersonatge = character.name;
                    let imatgeUrl = character.imageUrl;
                    let pelicules = character.films;
                    let series = character.tvShows;
                    let videoJoc = character.videoGames;
                    let webUrl = character.sourceUrl;

                    if (character.films.length > 3) {
                        pelicules = character.films ? character.films.slice(0, 3) : [];
                    }
                    if (pelicules == "undefined" || pelicules === null || pelicules.length === 0) {
                        pelicules = "Sense películes"
                    }

                    if (character.tvShows.length > 3) {
                        series = character.tvShows ? character.tvShows.slice(0, 3) : [];
                    }
                    if (series == "undefined" || series === null || series.length === 0) {
                        series = "Sense series"
                    }

                    if (character.videoGames.length > 3) {
                        videoJoc = character.videoGames ? character.videoGames.slice(0, 3) : [];
                    }
                    if (videoJoc == "undefined" || videoJoc === null || videoJoc.length === 0) {
                        videoJoc = "Sense video jocs"
                    }

                    card.innerHTML = `
                        <img src="${imatgeUrl}" class="card-img-top" alt="${nomPersonatge}">
                        <div class="card-body text-center">
                            <h5 class="card-title">${nomPersonatge}</h5>
                            <p class="card-text">
                                <strong>Id:</strong>  ${id}
                            </p>
                            <p class="card-text">
                                <strong>Films:</strong>  ${generarParagrafs(pelicules)}
                            </p>
                            <p class="card-text">
                                <strong>Series:</strong> ${generarParagrafs(series)}
                            </p>
                            <p class="card-text">
                                <strong>Video Joc:</strong> ${generarParagrafs(videoJoc)}
                            </p>
                            <a href="${webUrl}" class="btn btn-primary">Ir a la página web</a>
                            <button data-id="${id}" data-films="${pelicules}" data-series="${series}" data-name="${nomPersonatge}" data-videoJoc="${videoJoc}" data-imatgeUrl="${imatgeUrl}" data-sourceUrl=${webUrl}" style="margin-top: 5px" type="button" class="btn btn-warning" onclick="guardarInfo(this)">Guardar Informació</button>
                        </div>
                    `;
                    cardContainer.appendChild(card);
                }
            })
            .catch(function (error) {
                // Manejar errores
                console.error('Error:', error);
            });
    }
}

/**
 * Funció GET per a mostrar totes les dades segons el nom de la película que volguem. Fem la mateixa lógica que getDadesPersonatges().
 */
function getDadesPelicules() {
    const strBusquedaPelicules = document.getElementById('charactersDisney').value;
    if (strBusquedaPelicules === '') {
        getDades();
    } else {
        axios
            .get("https://api.disneyapi.dev/character/?films=" + strBusquedaPelicules)
            .then(response => {
                const cardContainer = document.querySelector('.card-container');
                cardContainer.innerHTML = "";
                const personatges = response.data.data;
                if (Array.isArray(personatges)) {
                    if (personatges.length === 0) {
                        cardContainer.innerHTML = "<p>No s'han encontrat coincidencias</p>";
                    } else {
                        response.data.data.forEach(personatge => {
                            const card = document.createElement('div');
                            card.className = 'card';
                            card.style = 'width: 18rem; margin: 20px; margin-bottom: 0px ';

                            let id = personatge._id;
                            let nomPersonatge = personatge.name;
                            let imatgeUrl = personatge.imageUrl;
                            let pelicules = personatge.films;
                            let series = personatge.tvShows;
                            let videoJoc = personatge.videoGames;
                            let webUrl = personatge.sourceUrl;

                            if (personatge.films.length > 3) {
                                pelicules = personatge.films ? personatge.films.slice(0, 3) : [];
                            }
                            if (pelicules == "undefined" || pelicules === null || pelicules.length === 0) {
                                pelicules = "Sense películes"
                            }

                            if (personatge.tvShows.length > 3) {
                                series = personatge.tvShows ? personatge.tvShows.slice(0, 3) : [];
                            }
                            if (series == "undefined" || series === null || series.length === 0) {
                                series = "Sense series"
                            }

                            if (personatge.videoGames.length > 3) {
                                videoJoc = personatge.videoGames ? personatge.videoGames.slice(0, 3) : [];
                            }
                            if (videoJoc == "undefined" || videoJoc === null || videoJoc.length === 0) {
                                videoJoc = "Sense video jocs"
                            }

                            card.innerHTML = `
                        <img src="${imatgeUrl}" class="card-img-top" alt="${nomPersonatge}">
                        <div class="card-body text-center">
                            <h5 class="card-title">${nomPersonatge}</h5>
                            <p class="card-text">
                                <strong>Id:</strong>  ${id}
                            </p>
                            <p class="card-text">
                                <strong>Films:</strong>  ${generarParagrafs(pelicules)}
                            </p>
                            <p class="card-text">
                                <strong>Series:</strong> ${generarParagrafs(series)}
                            </p>
                            <p class="card-text">
                                <strong>Video Joc:</strong> ${generarParagrafs(videoJoc)}
                            </p>
                            <a href="${webUrl}" class="btn btn-primary">Ir a la página web</a>
                            <button data-id="${id}" data-films="${pelicules}" data-series="${series}" data-name="${nomPersonatge}" data-videoJoc="${videoJoc}" data-imatgeUrl="${imatgeUrl}" data-sourceUrl=${webUrl}" style="margin-top: 5px" type="button" class="btn btn-warning" onclick="guardarInfo(this)">Guardar Informació</button>
                        </div>
                    `;
                            cardContainer.appendChild(card);
                        });

                        const cardColumns = document.querySelectorAll('.card');
                        cardColumns.forEach(function (card, index) {
                            if (index % 3 === 0) {
                                const cardRow = document.createElement('div');
                                cardRow.className = 'row';
                                cardContainer.appendChild(cardRow);
                            }

                            const cardRow = cardContainer.lastElementChild;
                            cardRow.appendChild(card);
                        });
                    }
                } else if (personatges && typeof personatges === 'object') {
                    const personatge = pelicules;
                    const card = document.createElement('div');
                    card.className = 'card';
                    card.style = 'width: 18rem; margin: 20px; margin-bottom: 0px ';

                    let id = personatge._id;
                    let nomPersonatge = personatge.name;
                    let imatgeUrl = personatge.imageUrl;
                    let pelicules = personatge.films;
                    let series = personatge.tvShows;
                    let videoJoc = personatge.videoGames;
                    let webUrl = personatge.sourceUrl;

                    if (personatge.films.length > 3) {
                        pelicules = personatge.films ? personatge.films.slice(0, 3) : [];
                    }
                    if (pelicules == "undefined" || pelicules === null || pelicules.length === 0) {
                        pelicules = "Sense películes"
                    }

                    if (personatge.tvShows.length > 3) {
                        series = personatge.tvShows ? personatge.tvShows.slice(0, 3) : [];
                    }
                    if (series == "undefined" || series === null || series.length === 0) {
                        series = "Sense series"
                    }

                    if (personatge.videoGames.length > 3) {
                        videoJoc = personatge.videoGames ? personatge.videoGames.slice(0, 3) : [];
                    }
                    if (videoJoc == "undefined" || videoJoc === null || videoJoc.length === 0) {
                        videoJoc = "Sense video jocs"
                    }

                    card.innerHTML = `
                        <img src="${imatgeUrl}" class="card-img-top" alt="${nomPersonatge}">
                        <div class="card-body text-center">
                            <h5 class="card-title">${nomPersonatge}</h5>
                            <p class="card-text">
                                <strong>Id:</strong>  ${id}
                            </p>
                            <p class="card-text">
                                <strong>Films:</strong>  ${generarParagrafs(pelicules)}
                            </p>
                            <p class="card-text">
                                <strong>Series:</strong> ${generarParagrafs(series)}
                            </p>
                            <p class="card-text">
                                <strong>Video Joc:</strong> ${generarParagrafs(videoJoc)}
                            </p>
                            <a href="${webUrl}" class="btn btn-primary">Ir a la página web</a>
                            <button data-id="${id}" data-films="${pelicules}" data-series="${series}" data-name="${nomPersonatge}" data-videoJoc="${videoJoc}" data-imatgeUrl="${imatgeUrl}" data-sourceUrl=${webUrl}" style="margin-top: 5px" type="button" class="btn btn-warning" onclick="guardarInfo(this)">Guardar Informació</button>
                        </div>
                    `;
                    cardContainer.appendChild(card);
                }
            })
            .catch(function (error) {
                console.error('Error:', error);
            });
    }
}

/**
 * Funció GET per a mostrar totes les dades segon el nom de la serie que volguem. Fem la mateixa lógica que getDadesPersonatges().
 */
function getDadesSeries() {
    const strBusquedaSeries = document.getElementById('charactersDisney').value;
    if (strBusquedaSeries === '') {
        getDades();
    } else {
        axios
            .get("https://api.disneyapi.dev/character/?tvShows=" + strBusquedaSeries)
            .then(response => {
                const cardContainer = document.querySelector('.card-container');
                cardContainer.innerHTML = "";
                const personatges = response.data.data;
                if (Array.isArray(personatges)) {
                    if (personatges.length === 0) {
                        cardContainer.innerHTML = "<p>No s'han encontrat coincidencias</p>";
                    } else {
                        response.data.data.forEach(personatge => {
                            const card = document.createElement('div');
                            card.className = 'card';
                            card.style = 'width: 18rem; margin: 20px; margin-bottom: 0px ';

                            let id = personatge._id;
                            let nomPersonatge = personatge.name;
                            let imatgeUrl = personatge.imageUrl;
                            let pelicules = personatge.films;
                            let series = personatge.tvShows;
                            let videoJoc = personatge.videoGames;
                            let webUrl = personatge.sourceUrl;

                            if (personatge.films.length > 3) {
                                pelicules = personatge.films ? personatge.films.slice(0, 3) : [];
                            }
                            if (pelicules == "undefined" || pelicules === null || pelicules.length === 0) {
                                pelicules = "Sense películes"
                            }

                            if (personatge.tvShows.length > 3) {
                                series = personatge.tvShows ? personatge.tvShows.slice(0, 3) : [];
                            }
                            if (series == "undefined" || series === null || series.length === 0) {
                                series = "Sense series"
                            }

                            if (personatge.videoGames.length > 3) {
                                videoJoc = personatge.videoGames ? personatge.videoGames.slice(0, 3) : [];
                            }
                            if (videoJoc == "undefined" || videoJoc === null || videoJoc.length === 0) {
                                videoJoc = "Sense video jocs"
                            }

                            card.innerHTML = `
                        <img src="${imatgeUrl}" class="card-img-top" alt="${nomPersonatge}">
                        <div class="card-body text-center">
                            <h5 class="card-title">${nomPersonatge}</h5>
                            <p class="card-text">
                                <strong>Id:</strong>  ${id}
                            </p>
                            <p class="card-text">
                                <strong>Films:</strong>  ${generarParagrafs(pelicules)}
                            </p>
                            <p class="card-text">
                                <strong>Series:</strong> ${generarParagrafs(series)}
                            </p>
                            <p class="card-text">
                                <strong>Video Joc:</strong> ${generarParagrafs(videoJoc)}
                            </p>
                            <a href="${webUrl}" class="btn btn-primary">Ir a la página web</a>
                            <button data-id="${id}" data-films="${pelicules}" data-series="${series}" data-name="${nomPersonatge}" data-videoJoc="${videoJoc}" data-imatgeUrl="${imatgeUrl}" data-sourceUrl=${webUrl}" style="margin-top: 5px" type="button" class="btn btn-warning" onclick="guardarInfo(this)">Guardar Informació</button>
                        </div>
                    `;
                            cardContainer.appendChild(card);
                        });
                        const cardColumns = document.querySelectorAll('.card');
                        cardColumns.forEach(function (card, index) {
                            if (index % 3 === 0) {
                                const cardRow = document.createElement('div');
                                cardRow.className = 'row';
                                cardContainer.appendChild(cardRow);
                            }

                            const cardRow = cardContainer.lastElementChild;
                            cardRow.appendChild(card);
                        });
                    }
                } else if (personatges && typeof personatges === 'object') {
                    const personatge = pelicules;
                    const card = document.createElement('div');
                    card.className = 'card';
                    card.style = 'width: 18rem; margin: 20px; margin-bottom: 0px ';

                    let id = personatge._id;
                    let nomPersonatge = personatge.name;
                    let imatgeUrl = personatge.imageUrl;
                    let pelicules = personatge.films;
                    let series = personatge.tvShows;
                    let videoJoc = personatge.videoGames;
                    let webUrl = personatge.sourceUrl;

                    if (personatge.films.length > 3) {
                        pelicules = personatge.films ? personatge.films.slice(0, 3) : [];
                    }
                    if (pelicules == "undefined" || pelicules === null || pelicules.length === 0) {
                        pelicules = "Sense películes"
                    }

                    if (personatge.tvShows.length > 3) {
                        series = personatge.tvShows ? personatge.tvShows.slice(0, 3) : [];
                    }
                    if (series == "undefined" || series === null || series.length === 0) {
                        series = "Sense series"
                    }

                    if (personatge.videoGames.length > 3) {
                        videoJoc = personatge.videoGames ? personatge.videoGames.slice(0, 3) : [];
                    }
                    if (videoJoc == "undefined" || videoJoc === null || videoJoc.length === 0) {
                        videoJoc = "Sense video jocs"
                    }

                    card.innerHTML = `
                        <img src="${imatgeUrl}" class="card-img-top" alt="${nomPersonatge}">
                        <div class="card-body text-center">
                            <h5 class="card-title">${nomPersonatge}</h5>
                            <p class="card-text">
                                <strong>Id:</strong>  ${id}
                            </p>
                            <p class="card-text">
                                <strong>Films:</strong>  ${generarParagrafs(pelicules)}
                            </p>
                            <p class="card-text">
                                <strong>Series:</strong> ${generarParagrafs(series)}
                            </p>
                            <p class="card-text">
                                <strong>Video Joc:</strong> ${generarParagrafs(videoJoc)}
                            </p>
                            <a href="${webUrl}" class="btn btn-primary">Ir a la página web</a>
                            <button data-id="${id}" data-films="${pelicules}" data-series="${series}" data-name="${nomPersonatge}" data-videoJoc="${videoJoc}" data-imatgeUrl="${imatgeUrl}" data-sourceUrl=${webUrl}" style="margin-top: 5px" type="button" class="btn btn-warning" onclick="guardarInfo(this)">Guardar Informació</button>
                        </div>
                    `;
                    cardContainer.appendChild(card);
                }
            })
            .catch(function (error) {
                console.error('Error:', error);
            });
    }
}

/**
 * Funció GET per a mostrar totes les dades segon el nom del videoJoc que volguem. Fem la mateixa lógica que getDadesPersonatges().
 */
function getDadesVideoJocs() {
    const strBusquedaVideoJocs = document.getElementById('charactersDisney').value;
    if (strBusquedaVideoJocs === '') {
        getDades();
    } else {
        axios
            .get("https://api.disneyapi.dev/character/?videoGames=" + strBusquedaVideoJocs)
            .then(response => {
                const cardContainer = document.querySelector('.card-container');
                cardContainer.innerHTML = "";
                const personatges = response.data.data;
                if (Array.isArray(personatges)) {
                    if (personatges.length === 0) {
                        cardContainer.innerHTML = "<p>No s'han encontrat coincidencias</p>";
                    } else {
                        response.data.data.forEach(personatge => {
                            const card = document.createElement('div');
                            card.className = 'card';
                            card.style = 'width: 18rem; margin: 20px; margin-bottom: 0px ';

                            let id = personatge._id;
                            let nomPersonatge = personatge.name;
                            let imatgeUrl = personatge.imageUrl;
                            let pelicules = personatge.films;
                            let series = personatge.tvShows;
                            let videoJoc = personatge.videoGames;
                            let webUrl = personatge.sourceUrl;

                            if (personatge.films.length > 3) {
                                pelicules = personatge.films ? personatge.films.slice(0, 3) : [];
                            }
                            if (pelicules == "undefined" || pelicules === null || pelicules.length === 0) {
                                pelicules = "Sense películes"
                            }

                            if (personatge.tvShows.length > 3) {
                                series = personatge.tvShows ? personatge.tvShows.slice(0, 3) : [];
                            }
                            if (series == "undefined" || series === null || series.length === 0) {
                                series = "Sense series"
                            }

                            if (personatge.videoGames.length > 3) {
                                videoJoc = personatge.videoGames ? personatge.videoGames.slice(0, 3) : [];
                            }
                            if (videoJoc == "undefined" || videoJoc === null || videoJoc.length === 0) {
                                videoJoc = "Sense video jocs"
                            }

                            card.innerHTML = `
                        <img src="${imatgeUrl}" class="card-img-top" alt="${nomPersonatge}">
                        <div class="card-body text-center">
                            <h5 class="card-title">${nomPersonatge}</h5>
                            <p class="card-text">
                                <strong>Id:</strong>  ${id}
                            </p>
                            <p class="card-text">
                                <strong>Films:</strong>  ${generarParagrafs(pelicules)}
                            </p>
                            <p class="card-text">
                                <strong>Series:</strong> ${generarParagrafs(series)}
                            </p>
                            <p class="card-text">
                                <strong>Video Joc:</strong> ${generarParagrafs(videoJoc)}
                            </p>
                            <a href="${webUrl}" class="btn btn-primary">Ir a la página web</a>
                            <button data-id="${id}" data-films="${pelicules}" data-series="${series}" data-name="${nomPersonatge}" data-videoJoc="${videoJoc}" data-imatgeUrl="${imatgeUrl}" data-sourceUrl=${webUrl}" style="margin-top: 5px" type="button" class="btn btn-warning" onclick="guardarInfo(this)">Guardar Informació</button>
                        </div>
                    `;
                            cardContainer.appendChild(card);
                        });
                        const cardColumns = document.querySelectorAll('.card');
                        cardColumns.forEach(function (card, index) {
                            if (index % 3 === 0) {
                                const cardRow = document.createElement('div');
                                cardRow.className = 'row';
                                cardContainer.appendChild(cardRow);
                            }

                            const cardRow = cardContainer.lastElementChild;
                            cardRow.appendChild(card);
                        });
                    }
                } else if (personatges && typeof personatges === 'object') {
                    const personatge = pelicules;
                    const card = document.createElement('div');
                    card.className = 'card';
                    card.style = 'width: 18rem; margin: 20px; margin-bottom: 0px ';

                    let id = personatge._id;
                    let nomPersonatge = personatge.name;
                    let imatgeUrl = personatge.imageUrl;
                    let pelicules = personatge.films;
                    let series = personatge.tvShows;
                    let videoJoc = personatge.videoGames;
                    let webUrl = personatge.sourceUrl;

                    if (personatge.films.length > 3) {
                        pelicules = personatge.films ? personatge.films.slice(0, 3) : [];
                    }
                    if (pelicules == "undefined" || pelicules === null || pelicules.length === 0) {
                        pelicules = "Sense películes"
                    }

                    if (personatge.tvShows.length > 3) {
                        series = personatge.tvShows ? personatge.tvShows.slice(0, 3) : [];
                    }
                    if (series == "undefined" || series === null || series.length === 0) {
                        series = "Sense series"
                    }

                    if (personatge.videoGames.length > 3) {
                        videoJoc = personatge.videoGames ? personatge.videoGames.slice(0, 3) : [];
                    }
                    if (videoJoc == "undefined" || videoJoc === null || videoJoc.length === 0) {
                        videoJoc = "Sense video jocs"
                    }

                    card.innerHTML = `
                        <img src="${imatgeUrl}" class="card-img-top" alt="${nomPersonatge}">
                        <div class="card-body text-center">
                            <h5 class="card-title">${nomPersonatge}</h5>
                            <p class="card-text">
                                <strong>Id:</strong>  ${id}
                            </p>
                            <p class="card-text">
                                <strong>Films:</strong>  ${generarParagrafs(pelicules)}
                            </p>
                            <p class="card-text">
                                <strong>Series:</strong> ${generarParagrafs(series)}
                            </p>
                            <p class="card-text">
                                <strong>Video Joc:</strong> ${generarParagrafs(videoJoc)}
                            </p>
                            <a href="${webUrl}" class="btn btn-primary">Ir a la página web</a>
                            <button data-id="${id}" data-films="${pelicules}" data-series="${series}" data-name="${nomPersonatge}" data-videoJoc="${videoJoc}" data-imatgeUrl="${imatgeUrl}" data-sourceUrl=${webUrl}" style="margin-top: 5px" type="button" class="btn btn-warning" onclick="guardarInfo(this)">Guardar Informació</button>
                        </div>
                    `;
                    cardContainer.appendChild(card);
                }
            })
            .catch(function (error) {
                console.error('Error:', error);
            });
    }
}

/**
 * Funció per a cridar a la base de dades y guardar la indormació.
 * @param {*} button Botó amb les dades dels personatges
 */
function guardarInfo(button) {

    const id = button.getAttribute('data-id');
    const nomPersonatge = button.getAttribute('data-name');
    const pelicules = button.getAttribute('data-films');
    const series = button.getAttribute('data-series');
    const videoJocs = button.getAttribute('data-videoJoc');
    const imatgeUrl = button.getAttribute('data-imatgeUrl');
    const webUrl = button.getAttribute('data-sourceUrl');

    const dades = {
        id: id,
        nomPersonatge: nomPersonatge,
        imatgeUrl: imatgeUrl,
        pelicules: pelicules,
        series: series,
        videoJocs: videoJocs,
        webUrl: webUrl
    };

    $.ajax({
        type: "POST", //metode POST per a enviar dades al servidor
        url: "baseDades.php", // ruta del fitxer PHP del servidor
        data: dades, // dades a enviar (p.ex. {valor:’1234’, nom:’pepe’})
        success: function (response) { //resultat del PHP del servidor
            alert(response);
        },
        error: function () {
            alert("Error");
        }
    });
}

/**
 * Genera paragrafs segons el numero d'elements de l'array.
 * @param {*} array Array segons siga pelicules, series o videojocs.
 * @returns Torna els paragrafs
 */
function generarParagrafs(array) {
    if (Array.isArray(array)) {
        return array.map(element => `<p>${element}</p>`).join('');
    } else {
        return `<p>${array}</p>`;
    }
}

/**
 * Recupera les dades de la Base de Dades i la mostra en html
 */
function getRecuperacioDades() {
    axios
        .get("consultaDades.php")
        .then(response => {
            const cardContainer = document.querySelector('.card-container');
            cardContainer.innerHTML = "";
            if (response.data.length === 0) {
                cardContainer.innerHTML = "<p>No s'han encontrat coincidencies</p>";
            } else {
                response.data.forEach(personatge => {
                    const card = document.createElement('div');
                    card.className = 'card';
                    card.style = 'width: 18rem; margin: 20px; margin-bottom: 0px ';

                    let id = personatge.id;
                    let nomPersonatge = personatge.nomPersonatge;
                    let imatgeUrl = personatge.imatgeUrl;
                    let pelicules = personatge.pelicules;
                    let series = personatge.series;
                    let videoJocs = personatge.videoJocs;
                    let webUrl = personatge.webUrl;
                    card.innerHTML = `
                <img src="${imatgeUrl}" class="card-img-top" alt="${nomPersonatge}">
                <div class="card-body text-center">
                    <h5 class="card-title">${nomPersonatge}</h5>
                    <p class="card-text">
                        <strong>Id:</strong>  ${id}
                    </p>
                    <p class="card-text">
                        <strong>Films:</strong>  
                        <p>${pelicules}</p>
                    </p>
                    <p class="card-text">
                        <strong>Series:</strong> 
                        <p>${series}</p>
                    </p>
                    <p class="card-text">
                        <strong>Video Joc:</strong> 
                        <p>${videoJocs}</p>
                    </p>
                    <a href="${webUrl}" class="btn btn-primary">Ir a la página web</a>
                </div>
        `;
                    // Agregar la tarjeta al contenedor
                    cardContainer.appendChild(card);
                });
            }
            const cardColumns = document.querySelectorAll('.card');
            cardColumns.forEach(function (card, index) {
                if (index % 3 === 0) {
                    const cardRow = document.createElement('div');
                    cardRow.className = 'row';
                    cardContainer.appendChild(cardRow);
                }

                const cardRow = cardContainer.lastElementChild;
                cardRow.appendChild(card);
            });
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

/**
 * Recupera les dades dels personatges segons el nom del personatge de la Base de Dades i la mostra en html
 */
function getDadesPersonatgeBBDD() {
    const strBusquedaPersonatges = document.getElementById('charactersDisney').value;
    if (strBusquedaPersonatges === '') {
        getRecuperacioDades();
    } else {
        axios
            .get("consultaDadesPersonatge.php?nomPersonatge=" + strBusquedaPersonatges)
            .then(response => {
                const cardContainer = document.querySelector('.card-container');
                cardContainer.innerHTML = "";
                if (Array.isArray(response.data)) {
                    if (response.data.length === 0) {
                        cardContainer.innerHTML = "<p>No s'han encontrat coincidencies</p>";
                    } else {
                        response.data.forEach(personatge => {
                            const card = document.createElement('div');
                            card.className = 'card';
                            card.style = 'width: 18rem; margin: 20px; margin-bottom: 0px ';

                            let id = personatge.id;
                            let nomPersonatge = personatge.nomPersonatge;
                            let imatgeUrl = personatge.imatgeUrl;
                            let pelicules = personatge.pelicules;
                            let series = personatge.series;
                            let videoJocs = personatge.videoJocs;
                            let webUrl = personatge.webUrl;
                            card.innerHTML = `
                <img src="${imatgeUrl}" class="card-img-top" alt="${nomPersonatge}">
                <div class="card-body text-center">
                    <h5 class="card-title">${nomPersonatge}</h5>
                    <p class="card-text">
                        <strong>Id:</strong>  ${id}
                    </p>
                    <p class="card-text">
                        <strong>Films:</strong>  
                        <p>${pelicules}</p>
                    </p>
                    <p class="card-text">
                        <strong>Series:</strong> 
                        <p>${series}</p>
                    </p>
                    <p class="card-text">
                        <strong>Video Joc:</strong> 
                        <p>${videoJocs}</p>
                    </p>
                    <a href="${webUrl}" class="btn btn-primary">Ir a la página web</a>
                </div>
        `;
                            // Agregar la tarjeta al contenedor
                            cardContainer.appendChild(card);
                        });
                    }

                    const cardColumns = document.querySelectorAll('.card');
                    cardColumns.forEach(function (card, index) {
                        if (index % 3 === 0) {
                            const cardRow = document.createElement('div');
                            cardRow.className = 'row';
                            cardContainer.appendChild(cardRow);
                        }

                        const cardRow = cardContainer.lastElementChild;
                        cardRow.appendChild(card);
                    });
                } else if (response.data && typeof response.data === 'object') {
                    // Manetjar a soles un personatge
                    const personatge = response.data;
                    const card = document.createElement('div');
                    card.className = 'card';
                    card.style = 'width: 18rem; margin: 20px; margin-bottom: 0px ';

                    let id = personatge._id;
                    let nomPersonatge = personatge.name;
                    let imatgeUrl = personatge.imageUrl;
                    let pelicules = personatge.films;
                    let series = personatge.tvShows;
                    let videoJoc = personatge.videoGames;
                    let webUrl = personatge.sourceUrl;

                    if (personatge.films.length > 3) {
                        pelicules = personatge.films ? personatge.films.slice(0, 3) : [];
                    }
                    if (pelicules == "undefined" || pelicules === null || pelicules.length === 0) {
                        pelicules = "Sense películes"
                    }

                    if (personatge.tvShows.length > 3) {
                        series = personatge.tvShows ? personatge.tvShows.slice(0, 3) : [];
                    }
                    if (series == "undefined" || series === null || series.length === 0) {
                        series = "Sense series"
                    }

                    if (personatge.videoGames.length > 3) {
                        videoJoc = personatge.videoGames ? personatge.videoGames.slice(0, 3) : [];
                    }
                    if (videoJoc == "undefined" || videoJoc === null || videoJoc.length === 0) {
                        videoJoc = "Sense video jocs"
                    }

                    card.innerHTML = `
                <img src="${imatgeUrl}" class="card-img-top" alt="${nomPersonatge}">
                <div class="card-body text-center">
                    <h5 class="card-title">${nomPersonatge}</h5>
                    <p class="card-text">
                        <strong>Id:</strong>  ${id}
                    </p>
                    <p class="card-text">
                        <strong>Films:</strong>  ${generarParagrafs(pelicules)}
                    </p>
                    <p class="card-text">
                        <strong>Series:</strong> ${generarParagrafs(series)}
                    </p>
                    <p class="card-text">
                        <strong>Video Joc:</strong> ${generarParagrafs(videoJoc)}
                    </p>
                    <a href="${webUrl}" class="btn btn-primary">Ir a la página web</a>
                    <button data-id="${id}" data-films="${pelicules}" data-series="${series}" data-name="${nomPersonatge}" data-videoJoc="${videoJoc}" data-imatgeUrl="${imatgeUrl}" data-sourceUrl=${webUrl}" style="margin-top: 5px" type="button" class="btn btn-warning" onclick="guardarInfo(this)">Guardar Informació</button>
                </div>
            `;
                    cardContainer.appendChild(card);
                }
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }
}
