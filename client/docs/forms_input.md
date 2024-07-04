> Para ver un ejemplo de implementación de este formulario, puedes ver esto en `src/components/Modal/AddGuest.vue`

## Uso básico
Para hacer un uso de este componente, se requieren de ciertas variables para hacer que muestre lo que queremos que muestre.

```vue
<template>
    <InputForm
        :value="data.identifier"
        :alert="alertForIdentifier"
        name="identifier"
        @update:value="data.identifier = $event"
        title="Identificación"
        type="text"
    />
</template>
<script>
const alertForIdentifier = ref<Alert>(undefined)
const data = ref({
    identifierType: "",
    identifier: "",
});

const alert: Alert = {
    message: "Esta mierda no calza con el formato de la turca que se usa hpta",
    type: AlertType.WARNING,
};
    
</script>
```

cuando usamos `@update:value="data.identifier = $event"` en InputForm, este es uno de los parámetros que nos interesa, porque estamos recibiendo en la variable `data`, en su propiedad `identifier` todo lo que capte el input.

Por otra parte, cuando nosotros le mandamos una alerta al Input en la propiedad `:alert=""`, debemos mandarla como undefined por defecto, en dado caso deseemos mostar alguna cosa, procederemos a asignarlo, como en el siguiente ejemplo:

```js
if (!data.value.identifier) {
const alert: Alert = {
    message: "Llena esta mierda hpta",
    type: AlertType.WARNING,
};
alertForIdentifier.value = alert;
return;
}
```