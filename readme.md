# README.MD
Proyecto desarrollado como prueba de ingreso a mercadolibre. 

El objetivo del proyecto es poder seleccionar de una lista de productos la mayor cantidad de productos que no excedan el valor de un cupón


## Contentido

GIT: rama master con los desarrollos de la solución
Java: 1.8 - Spring boot 2.6.6


## Como clonar
Se debe seguir la siguiente lista de pasos:

* Abrir consola de comandos y ubicarse en la carpeta donde se quiere clonar el repositorio

* git clone git@github.com:feralip/CuponMercadolibre.git



## Instalación local
Se debe seguir la siguiente lista de pasos:

* mvn clean install

* cd target

* java -jar cupon-0.0.1-SNAPSHOT.jar

## Ejecutar proyecto local

Para ejecutar el proyecto local se puede ejecutar el comendo CURL

curl -X POST http://localhost:8080/coupon/
   -H 'Content-Type: application/json'
   -d '{ "item_ids": ["MCO839643993", "MCO850939562", "MCO630791535", "MLA4", "MCO630791535"], "amount": 50000}'
   
o desde postman de la siguiente forma

Metodo POST
URL http://localhost:8080/coupon/
Body 
{ 
"item_ids": ["MCO839643993", "MCO850939562", "MCO630791535", "MLA4", "MCO630791535"], 
"amount": 50000 
} 

## Ejecutar proyecto remoto

Para ejecutar el proyecto local se puede ejecutar el comendo CURL

curl -X POST http://balanceadorcoupon-19442187.us-east-1.elb.amazonaws.com/coupon/
   -H 'Content-Type: application/json'
   -d '{ "item_ids": ["MCO839643993", "MCO850939562", "MCO630791535", "MLA4", "MCO630791535"], "amount": 50000}'
   
o desde postman de la siguiente forma

Metodo POST
URL http://balanceadorcoupon-19442187.us-east-1.elb.amazonaws.com/coupon/
Body 
{ 
"item_ids": ["MCO839643993", "MCO850939562", "MCO630791535", "MLA4", "MCO630791535"], 
"amount": 50000 
} 


### Notes
Projec