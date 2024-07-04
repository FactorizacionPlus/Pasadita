> Para ver un ejemplo de implementación de este formulario, puedes ver esto en `src/components/Modal/AddGuest.vue`

Este funciona de una forma sencilla, únicamente mandamos a llamar el componente, por otra parte debemos tener una variable donde almacenar el valor que sea asignado a través del controlador.

```vue
<template>
        <IdentityTypeSelection @identity-type="data.identifierType = $event" />
</template>
```
En dado caso tengamos un valor por defecto, el componente `IdentityTypeSelection` integra la propiedad `identityType` que deberá ser "DUI" ó "PASSPORT".

```vue
<template>
        <IdentityTypeSelection identity-type="PASSPORT" @identity-type="data.identifierType = $event" />
</template>
```
