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

	`GET`
 
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


					
## Show autor
#### URL

	< /themes/{id} >

* #### Method:

	`GET`
	
* #### Parameters:

	* URL
        - id = [int]

   
* #### Request:				{
					"name": "Show Autor",
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": ""
						},
						"url": {
							"raw": "https://localhost:8443/api/autores/William Shakespeare",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"autores",
								"William Shakespeare"
							]
						}
					},
					"response": []
				},
				
### Add new autor (only Administrator)
#### URL

	< /themes/{id} >

* #### Method:

	`GET`
	
* #### Parameters:

	* URL
        - id = [int]

   
* #### Request:
				{
					"name": "Add new autor",
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"name": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"nombre\": \"Pikachu\",\n    \"url_foto\": \"https://as.com/epik/imagenes/2018/11/16/portada/1542384053_864693_1542384302_noticia_normal.jpg\",\n    \"fecha_nac\": \"2 de septiembre de 1998\",\n    \"fecha_def\": \"\",\n    \"url_mapa\": \"https://cdn.wikimg.net/en/strategywiki/images/thumb/b/b1/PRS%26E-SafariZone.png/1200px-PRS%26E-SafariZone.png\",\n    \"lugar\": \"Kanto\"\n}"
						},
						"url": {
							"raw": "https://localhost:8443/api/autores?idObra=30",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"autores"
							],
							"query": [
								{
									"key": "idObra",
									"value": "30"
								}
							]
						}
					},
					"response": []
				},
### Delete autor (only Administrator)
#### URL

	< /themes/{id} >

* #### Method:

	`GET`
	
* #### Parameters:

	* URL
        - id = [int]

   
* #### Request:
				{
					"name": "Delete autor",
					"request": {
						"method": "DELETE",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": ""
						},
						"url": {
							"raw": "https://localhost:8443/api/autores/Pikachu",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"autores",
								"Pikachu"
							]
						}
					},
					"response": []
				},
### Edit autor (only Administrator)
#### URL

	< /themes/{id} >

* #### Method:

	`GET`
	
* #### Parameters:

	* URL
        - id = [int]

   
* #### Request:
				{
					"name": "Edit autor",
					"request": {
						"method": "PUT",
						"header": [
							{
								"key": "Content-Type",
								"name": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"nombre\": \"William Shakespeare\",\n    \"url_foto\": \"https://as.com/epik/imagenes/2018/11/16/portada/1542384053_864693_1542384302_noticia_normal.jpg\",\n    \"fecha_nac\": \"Ayer\",\n    \"fecha_def\": \"Mañana\",\n    \"url_mapa\": \"https://as.com/epik/imagenes/2018/11/16/portada/1542384053_864693_1542384302_noticia_normal.jpg\",\n    \"lugar\": \"Aqui\"\n}"
						},
						"url": {
							"raw": "https://localhost:8443/api/autores",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"autores"
							]
						}
					},
					"response": []
				}
			]
		},
		
		
## Themes
		{
			"name": "Tema",
			"item": [
				{
					"name": "Show Tema",
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": ""
						},
						"url": {
							"raw": "https://localhost:8443/api/temas/Tragedia",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"temas",
								"Tragedia"
							]
						}
					},
					"response": []
				},
				{
					"name": "Add new tema",
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"name": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"contenido\": \"Gaseoso\"\n}"
						},
						"url": {
							"raw": "https://localhost:8443/api/temas",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"temas"
							]
						}
					},
					"response": []
				},
				{
					"name": "Delete Tema",
					"request": {
						"method": "DELETE",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": ""
						},
						"url": {
							"raw": "https://localhost:8443/api/temas/Tragedia",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"temas",
								"Tragedia"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Obra",
			"item": [
				{
					"name": "Show obra",
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": ""
						},
						"url": {
							"raw": "https://localhost:8443/api/obras/Hamlet",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"obras",
								"Hamlet"
							]
						}
					},
					"response": []
				},
				{
					"name": "Add new obra",
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"title\": \"Obra de prueba\",\n    \"URL\": \"ss\",\n    \"date\": \"ss\",\n    \"editorial\": \"asfas\",\n    \"url_editorial\": \"asfas\"\n}\n"
						},
						"url": {
							"raw": "https://localhost:8443/api/obras?idAutor=1&idTema=39",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"obras"
							],
							"query": [
								{
									"key": "idAutor",
									"value": "1"
								},
								{
									"key": "idTema",
									"value": "39"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Delete obra",
					"request": {
						"method": "DELETE",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": ""
						},
						"url": {
							"raw": "https://localhost:8443/api/obras/Obra de prueba",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"obras",
								"Obra de prueba"
							]
						}
					},
					"response": []
				},
				{
					"name": "Edit obra",
					"request": {
						"method": "PUT",
						"header": [
							{
								"key": "Content-Type",
								"name": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"title\": \"Hamlet\",\n    \"URL\": \"https://as.com/epik/imagenes/2018/11/16/portada/1542384053_864693_1542384302_noticia_normal.jpg\",\n    \"date\": \"ayer\",\n    \"editorial\": \"URJC\",\n    \"url_editorial\": \"https://as.com/epik/imagenes/2018/11/16/portada/1542384053_864693_1542384302_noticia_normal.jpg\"\n}"
						},
						"url": {
							"raw": "https://localhost:8443/api/obras",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"obras"
							]
						}
					},
					"response": []
				},
				{
					"name": "Edit obra: add autor",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": ""
						},
						"url": {
							"raw": "https://localhost:8443/api/obras/autor?idObra=26&idAutor=5",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"obras",
								"autor"
							],
							"query": [
								{
									"key": "idObra",
									"value": "26"
								},
								{
									"key": "idAutor",
									"value": "5"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Edit Obra: create cita",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": ""
						},
						"url": {
							"raw": "https://localhost:8443/api/obras/cita?idObra=26&contenido=hola",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"obras",
								"cita"
							],
							"query": [
								{
									"key": "idObra",
									"value": "26"
								},
								{
									"key": "contenido",
									"value": "hola"
								}
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Index pageable",
			"item": [
				{
					"name": "Pageable Autor",
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": ""
						},
						"url": {
							"raw": "https://localhost:8443/api/autores?autorPage=0",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"autores"
							],
							"query": [
								{
									"key": "autorPage",
									"value": "0"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Pageable Obra",
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": ""
						},
						"url": {
							"raw": "https://localhost:8443/api/obras?obraPage=0",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"obras"
							],
							"query": [
								{
									"key": "obraPage",
									"value": "0"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "Pageable Tema",
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": ""
						},
						"url": {
							"raw": "https://localhost:8443/api/temas?temaPage=0",
							"protocol": "https",
							"host": [
								"localhost"
							],
							"port": "8443",
							"path": [
								"api",
								"temas"
							],
							"query": [
								{
									"key": "temaPage",
									"value": "0"
								}
							]
						}
					},
					"response": []
				}
			],
			"event": [
				{
					"listen": "prerequest",
					"script": {
						"id": "a21d1bad-5055-442f-b44f-e6dc60c363f5",
						"type": "text/javascript",
						"exec": [
							""
						]
					}
				},
				{
					"listen": "test",
					"script": {
						"id": "538b64d1-b0e8-4af4-9789-88c253c9223b",
						"type": "text/javascript",
						"exec": [
							""
						]
					}
				}
			]
		}
	]
}
