 #
 # Build stage
 # 

FROM maven:3.6.0-jdk-11-slim AS build
COPY ./Ejemplo/src /usr/src/app/src
COPY ./Ejemplo/pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

 #
 # Package stage
 #

 # Start with a base image containing Java runtime 
FROM openjdk:8-jdk-alpine

 # Add a volume pointing to /tmp (we created a mount point with path 
	# /tmp because this is where the spring boot application 
	# creates working directories for Tomcat by default 
VOLUME /tmp 

 # Make port available to the world outside this container
EXPOSE 8443

 # The application's jar file. JAR_FILE defines a variable with a
	#default value
ARG JAR_FILE=./Ejemplo/target/practica_fase4-0.1.0.jar

 # Add the application's jar to the container
ADD ${JAR_FILE} practica_fase4-0.1.0.jar

 # Run the jar file
ENTRYPOINT ["java","-jar","/practica_fase4-0.1.0.jar"]

 #
 #ANGULAR
 #
FROM node:8.10

# Directorio de trabajo donde se encuentra la aplicación dentro de nuestro contenedor: 
WORKDIR /app
#Copiamos los ficheros de nuestros proyecto al contenedor:
COPY ./ng /app
    # Install dependencies:
RUN npm install
RUN npm install -g @angular/cli 
RUN npm install --save echarts
RUN npm install --save @covalent/echarts
RUN npm install --save https://github.com/Teradata/covalent-echarts-nightly.git
RUN npm rebuild node-sass
RUN $(npm bin)/ng build --base-href /new/ --prod
 # Make port available to the world outside this container:
EXPOSE 4200
 # Punto de entrada de nuestro contenedor y el que hace correr nuestra aplicación una vez ejecutado el contenedor: 
ENTRYPOINT npm start




