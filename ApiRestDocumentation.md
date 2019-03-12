# Description
All API queries begin with /api.
Any request that is not authorized to be made returns the error code 405 Method Not Allowed, 403 Forbidden or 401 Not Authorized.


## Add new tema (only Administrator)
#### Url
	< https://localhost:8443/api/temas>
	
* #### Method:

	`POST`

* #### Request:
		{
		    "id": 54,
		    "obras": [],
		    "contenido": "Videojuegos",
		    "numObras": 0
		}

## Show Tema
#### Url
	< https://localhost:8443/api/temas/Videojuegos >
	
* #### Method:

	`GET`

* #### Request:
		{
		    "obras": [],
		    "contenido": "Videojuegos"
		}



## Delete Tema (only Administrator)
#### Url
	< https://localhost:8443/api/temas/Videojuegos >
	
* #### Method:

	`DELETE`

* #### Request:
		{
		    "timestamp": "2019-03-12T00:54:25.554+0000",
		    "status": 500,
		    "error": "Internal Server Error",
		    "message": "Could not write JSON: failed to lazily initialize a collection, could not initialize proxy - no Session; nested exception is com.fasterxml.jackson.databind.JsonMappingException: failed to lazily initialize a collection, could not initialize proxy - no Session (through reference chain: es.daw.bibliografia.book.Tema[\"obras\"])",
		}


## Add new obra (only Administrator)
#### URL

	< https://localhost:8443/api/obras >

* #### Method:

	`POST`
	
* #### Parameters:

	* URL
        - id = [int]
	
* #### Request:
		{
		    "id": 55,
		    "title": "Pokemon esmeralda",
		    "URL": "https://i.pinimg.com/originals/3f/47/b2/3f47b2a3ac5d6f6b4f3790c4a2730a93.jpg",
		    "date": "No se sabe",
		    "editorial": "¿Pokemon?",
		    "url_editorial": "¿?",
		    "citas": [],
		    "autores": [
			{
			    "id": 1,
			    "nombre": "William Shakespeare",
			    "url_foto": "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Shakespeare.jpg/255px-Shakespeare.jpg",
			    "fecha_nac": "26 de abril de 1564",
			    "fecha_def": "23 de abril 1616",
			    "url_mapa": "https://media-cdn.tripadvisor.com/media/photo-s/0d/f4/e1/3e/england-in-one-day-stonehenge.jpg",
			    "lugar": "Stratford-upon-Avon, Inglaterra"
			}
		    ],
		    "url": "https://i.pinimg.com/originals/3f/47/b2/3f47b2a3ac5d6f6b4f3790c4a2730a93.jpg"
		}


## Show obra
#### URL

	< https://localhost:8443/api/obras/Pokemon esmeralda >

* #### Method:

	`GET`

   
* #### Request:

		{
		    "id": 55,
		    "title": "Pokemon esmeralda",
		    "URL": "https://i.pinimg.com/originals/3f/47/b2/3f47b2a3ac5d6f6b4f3790c4a2730a93.jpg",
		    "date": "No se sabe",
		    "editorial": "¿Pokemon?",
		    "url_editorial": "¿?",
		    "citas": [],
		    "autores": [
			{
			    "id": 1,
			    "nombre": "William Shakespeare",
			    "url_foto": "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Shakespeare.jpg/255px-Shakespeare.jpg",
			    "fecha_nac": "26 de abril de 1564",
			    "fecha_def": "23 de abril 1616",
			    "url_mapa": "https://media-cdn.tripadvisor.com/media/photo-s/0d/f4/e1/3e/england-in-one-day-stonehenge.jpg",
			    "lugar": "Stratford-upon-Avon, Inglaterra"
			}
		    ]
		}


## Edit obra (only Administrator)
#### URL

	< https://localhost:8443/api/obras >

* #### Method:

	`PUT`

   
* #### Request:

		{
		    "id": 55,
		    "title": "Pokemon esmeralda",
		    "URL": "https://i.pinimg.com/originals/3f/47/b2/3f47b2a3ac5d6f6b4f3790c4a2730a93.jpg",
		    "date": "October 21, 2005",
		    "editorial": "Pokemon company",
		    "url_editorial": "https://upload.wikimedia.org/wikipedia/commons/5/55/Logo_of_The_Pok%C3%A9mon_Company.jpg",
		    "citas": [],
		    "autores": [
			{
			    "id": 1,
			    "nombre": "William Shakespeare",
			    "url_foto": "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Shakespeare.jpg/255px-Shakespeare.jpg",
			    "fecha_nac": "26 de abril de 1564",
			    "fecha_def": "23 de abril 1616",
			    "url_mapa": "https://media-cdn.tripadvisor.com/media/photo-s/0d/f4/e1/3e/england-in-one-day-stonehenge.jpg",
			    "lugar": "Stratford-upon-Avon, Inglaterra"
			}
		    ],
		    "url": "https://i.pinimg.com/originals/3f/47/b2/3f47b2a3ac5d6f6b4f3790c4a2730a93.jpg"
		}
						
## Edit obra: add autor (only Administrator)
#### URL

	< https://localhost:8443/api/obras/autor >

* #### Method:

	`PUT`
	
* #### Parameters:

	* URL
        - idAutor = [int]
        - idObra = [int]


   
* #### Request:
		{
		    "id": 26,
		    "title": "Hamlet",
		    "URL": "https://www.catedra.com/jpg_g/catedra/CA00014412.jpg",
		    "date": "1605",
		    "editorial": "Ed. Oceano",
		    "url_editorial": "https://pbs.twimg.com/profile_images/3243051312/6a054036cdb81091d4e57f68fe756462_400x400.jpeg",
		    "citas": [
			{
			    "id": 12,
			    "contenido": "Ser o no ser, esa es la cuestión. ¿Cuál es más digna acción del ánimo, sufrir los tiros penetrantes de la fortuna injusta, u oponer los brazos a este torrente de calamidades, y darlas fin con atrevida resistencia? Morir es dormir. ¿No más? "
			}
		    ],
		    "autores": [
			{
			    "id": 1,
			    "nombre": "William Shakespeare",
			    "url_foto": "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Shakespeare.jpg/255px-Shakespeare.jpg",
			    "fecha_nac": "26 de abril de 1564",
			    "fecha_def": "23 de abril 1616",
			    "url_mapa": "https://media-cdn.tripadvisor.com/media/photo-s/0d/f4/e1/3e/england-in-one-day-stonehenge.jpg",
			    "lugar": "Stratford-upon-Avon, Inglaterra"
			},
			{
			    "id": 5,
			    "nombre": "Pedro Calderón de la Barca",
			    "url_foto": "https://es.wikipedia.org/wiki/Pedro_Calder%C3%B3n_de_la_Barca#/media/File:Retrato_de_Pedro_Calder%C3%B3n_de_la_Barca.jpg",
			    "fecha_nac": "​17 enero 1600",
			    "fecha_def": "25 mayo 1681",
			    "url_mapa": "https://es.wikipedia.org/wiki/Corona_de_Castilla#/media/File:Corona_de_Castilla_1400_es.svg",
			    "lugar": "Madrid"
			}
		    ],
		    "url": "https://www.catedra.com/jpg_g/catedra/CA00014412.jpg"
		}


## Edit obra: add cita (only Administrator)
#### URL

	< https://localhost:8443/api/obras/cita >

* #### Method:

	`PUT`
	
* #### Parameters:

	* URL
        - contenido = [string]
        - idObra = [int]


   
* #### Request:
		{
		    "id": 26,
		    "title": "Hamlet",
		    "URL": "https://www.catedra.com/jpg_g/catedra/CA00014412.jpg",
		    "date": "1605",
		    "editorial": "Ed. Oceano",
		    "url_editorial": "https://pbs.twimg.com/profile_images/3243051312/6a054036cdb81091d4e57f68fe756462_400x400.jpeg",
		    "citas": [
			{
			    "id": 12,
			    "contenido": "Ser o no ser, esa es la cuestión. ¿Cuál es más digna acción del ánimo, sufrir los tiros penetrantes de la fortuna injusta, u oponer los brazos a este torrente de calamidades, y darlas fin con atrevida resistencia? Morir es dormir. ¿No más? "
			},
			{
			    "id": 56,
			    "contenido": "Esta obra no mola, hazme caso"
			}
		    ],
		    "autores": [
			{
			    "id": 1,
			    "nombre": "William Shakespeare",
			    "url_foto": "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Shakespeare.jpg/255px-Shakespeare.jpg",
			    "fecha_nac": "26 de abril de 1564",
			    "fecha_def": "23 de abril 1616",
			    "url_mapa": "https://media-cdn.tripadvisor.com/media/photo-s/0d/f4/e1/3e/england-in-one-day-stonehenge.jpg",
			    "lugar": "Stratford-upon-Avon, Inglaterra"
			},
			{
			    "id": 5,
			    "nombre": "Pedro Calderón de la Barca",
			    "url_foto": "https://es.wikipedia.org/wiki/Pedro_Calder%C3%B3n_de_la_Barca#/media/File:Retrato_de_Pedro_Calder%C3%B3n_de_la_Barca.jpg",
			    "fecha_nac": "17 enero 1600",
			    "fecha_def": "25 mayo 1681",
			    "url_mapa": "https://es.wikipedia.org/wiki/Corona_de_Castilla#/media/File:Corona_de_Castilla_1400_es.svg",
			    "lugar": "Madrid"
			}
		    ],
		    "url": "https://www.catedra.com/jpg_g/catedra/CA00014412.jpg"
		}


## Delete OBRA (only Administrator)
#### URL

	< https://localhost:8443/api/obras/Pokemon esmeralda >

* #### Method:

	`DELETE`
 
* #### Request:

		{
		    "id": 55,
		    "title": "Pokemon esmeralda",
		    "URL": "https://i.pinimg.com/originals/3f/47/b2/3f47b2a3ac5d6f6b4f3790c4a2730a93.jpg",
		    "date": "October 21, 2005",
		    "editorial": "Pokemon company",
		    "url_editorial": "https://upload.wikimedia.org/wikipedia/commons/5/55/Logo_of_The_Pok%C3%A9mon_Company.jpg",
		    "citas": [],
		    "autores": [],
		    "url": "https://i.pinimg.com/originals/3f/47/b2/3f47b2a3ac5d6f6b4f3790c4a2730a93.jpg"
		}

		
### Add new autor (only Administrator)
#### URL

	< /themes/{id} >

* #### Method:

	`POST`
	
* #### Parameters:

	* URL
        - idObra = [int]

   
* #### Request:

		{
		    "id": 57,
		    "nombre": "Pikachu",
		    "url_foto": "https://as.com/epik/imagenes/2018/11/16/portada/1542384053_864693_1542384302_noticia_normal.jpg",
		    "fecha_nac": "2 de septiembre de 1998",
		    "fecha_def": "",
		    "url_mapa": "https://cdn.wikimg.net/en/strategywiki/images/thumb/b/b1/PRS%26E-SafariZone.png/1200px-PRS%26E-SafariZone.png",
		    "lugar": "Kanto"
		}


### Show autor 
#### URL

	< https://localhost:8443/api/autores/Pikachu >

* #### Method:

	`GET`

   
* #### Request:

		{
		    "id": 57,
		    "nombre": "Pikachu",
		    "url_foto": "https://as.com/epik/imagenes/2018/11/16/portada/1542384053_864693_1542384302_noticia_normal.jpg",
		    "fecha_nac": "2 de septiembre de 1998",
		    "fecha_def": "",
		    "url_mapa": "https://cdn.wikimg.net/en/strategywiki/images/thumb/b/b1/PRS%26E-SafariZone.png/1200px-PRS%26E-SafariZone.png",
		    "lugar": "Kanto"
		}
				
### Edit autor (only Administrator)
#### URL

	< https://localhost:8443/api/autores >

* #### Method:

	`PUT`

   
* #### Request:
			
			
		{
		    "id": 1,
		    "nombre": "William Shakespeare",
		    "url_foto": "https://as.com/epik/imagenes/2018/11/16/portada/1542384053_864693_1542384302_noticia_normal.jpg",
		    "fecha_nac": "Ayer",
		    "fecha_def": "Mañana",
		    "url_mapa": "https://as.com/epik/imagenes/2018/11/16/portada/1542384053_864693_1542384302_noticia_normal.jpg",
		    "lugar": "Aqui"
		}
		

### Delete autor (only Administrator)
#### URL

	< https://localhost:8443/api/autores/Pikachu >

* #### Method:

	`DELETE`

   
* #### Request:
			

		{
		    "id": 57,
		    "nombre": "Pikachu",
		    "url_foto": "https://as.com/epik/imagenes/2018/11/16/portada/1542384053_864693_1542384302_noticia_normal.jpg",
		    "fecha_nac": "2 de septiembre de 1998",
		    "fecha_def": "",
		    "url_mapa": "https://cdn.wikimg.net/en/strategywiki/images/thumb/b/b1/PRS%26E-SafariZone.png/1200px-PRS%26E-SafariZone.png",
		    "lugar": "Kanto"
		}
