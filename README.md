# Parcial ARSW

## API para consultar el estado del clima de una ciudad

Esta implementacion se conecta a una api externa para poder consultar el estado del clima, devuelve un ResponseDTO
El cual tiene la informacion  name, country, lat, lon, temp_c, text que equivale a la condicion actual del clima y demas

Se configuro que se guardara en redis las peticiones que se hacen para que se guarden y no tener que hacer tantos llamados y demas



Link del despliegue: https://parcialarssw-fmdygqancdc3eqhf.canadacentral-01.azurewebsites.net/
# Endpoint
api/generate
Espera un String que hace refencia a la ciudad 