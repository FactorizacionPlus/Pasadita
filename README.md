# Proyecto Pasadita

## Environment Variables

Estas variables de entorno deben de definirse en la configuración del servidor de Spring Boot. Son sólo de ejemplo, pueden preguntar al Project Manager sobre qué datos ingresar aquí.

```env
DATABASE_URL=
GOOGLE_CLIENT_ID=470401541816-35t7onpn3ger6dbe5b6q8dph6o78t4rf.apps.googleusercontent.com
GOOGLE_CLIENT_SECRET=
POSTGRES_PASSWORD=Usuario123.
POSTGRES_USER=postgres
PUBLIC_DOMAIN=http://localhost:8080
```

## Microcontrolador

Hecho para el microcontrolador RP2040 en un Raspberry Pi Pico W, con MicroPython. Una vez instalado el MicroPython para el Pico W, con `rshell` se copia el archivo de `pasadita.py` al Pico W.

```sh
rshell -p /dev/ttyACM0 --buffer-size 512 cp pasadita.py /pyboard/main.py
```

> [!NOTE]
> El dispositivo puede variar en nombre según sistema operativo, puede ser `/dev/tty.usbmodem11101`

## Contribuciones

Cuando se esté avanzando en un feature, debe colocarse el feature que está en la tabla del proyecto como In Progress. Una vez completado, debe pasarse a Review y crearse el Pull Request.

### Ramas
Con el issue asignado, se debe crear una rama con el siguiente formato:

```
feature/${ISSUE_NUMBER}-${TITLE}
```

Siendo el `ISSUE_NUMBER` el número del issue, y `TITLE` una descripción corta o el título del issue.


### Pull Requests

Se debe crear un PR para poder implementar un feature.
