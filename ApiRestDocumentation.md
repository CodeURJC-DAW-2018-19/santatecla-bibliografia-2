#Description
All API queries begin with /api.

## Author
#### Url
	< /autores/ >
	
* #### Method:

	`GET`
{
	"info": {
		"_postman_id": "4d06952d-3799-4788-ab2b-db20206da27e",
		"name": "Bibliografia",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "Autor",
			"item": [
				{
					"name": "Extras",
					"item": [
						{
							"name": "Show tema in autor",
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": ""
								},
								"url": {
									"raw": "https://localhost:8443/api/autores/temas/Tragedia",
									"protocol": "https",
									"host": [
										"localhost"
									],
									"port": "8443",
									"path": [
										"api",
										"autores",
										"temas",
										"Tragedia"
									]
								}
							},
							"response": []
						},
						{
							"name": "Delete tema in autor",
							"request": {
								"method": "DELETE",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": ""
								},
								"url": {
									"raw": "https://localhost:8443/api/autores/temas/Tragedia",
									"protocol": "https",
									"host": [
										"localhost"
									],
									"port": "8443",
									"path": [
										"api",
										"autores",
										"temas",
										"Tragedia"
									]
								}
							},
							"response": []
						},
						{
							"name": "Show obra in autor",
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": ""
								},
								"url": {
									"raw": "https://localhost:8443/api/autores/obras/Hamlet",
									"protocol": "https",
									"host": [
										"localhost"
									],
									"port": "8443",
									"path": [
										"api",
										"autores",
										"obras",
										"Hamlet"
									]
								}
							},
							"response": []
						},
						{
							"name": "Delete obra in autor",
							"request": {
								"method": "DELETE",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": ""
								},
								"url": {
									"raw": "https://localhost:8443/api/autores/obras/Hamlet",
									"protocol": "https",
									"host": [
										"localhost"
									],
									"port": "8443",
									"path": [
										"api",
										"autores",
										"obras",
										"Hamlet"
									]
								}
							},
							"response": []
						},
						{
							"name": "Show cita in autor",
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": ""
								},
								"url": {
									"raw": "https://localhost:8443/api/autores/citas/12",
									"protocol": "https",
									"host": [
										"localhost"
									],
									"port": "8443",
									"path": [
										"api",
										"autores",
										"citas",
										"12"
									]
								}
							},
							"response": []
						},
						{
							"name": "Delete cita in autor",
							"request": {
								"method": "DELETE",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": ""
								},
								"url": {
									"raw": "https://localhost:8443/api/autores/citas/12",
									"protocol": "https",
									"host": [
										"localhost"
									],
									"port": "8443",
									"path": [
										"api",
										"autores",
										"citas",
										"12"
									]
								}
							},
							"response": []
						}
					],
					"_postman_isSubFolder": true
				},
				{
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
