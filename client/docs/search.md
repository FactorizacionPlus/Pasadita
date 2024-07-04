# Búsqueda para las Cards

Este es un método genérico que lo trae implementado el componente `SearchBar.vue` en conjunto con la función `matchSearch`, para ver una implementación real de este, puedes ir a `src/views/EntriesView.vue`.

```vue
<script setup lang="ts">
import { matchSearch } from "@/utils/matchSearch";
const searchText = ref("");
const hideNoResults = ref(false);
const fieldsToSearch = ["description", "user.firstName", "user.lastName", "user.identifier"];
</script>
<template>
<SearchBar @search="searchText = $event" @toggle-no-results="hideNoResults = $event" />
<ul class="grid gap-4 lg:grid-cols-2">
    <EntryCard
    :class="{
        'animate-scale-up border-2 border-blue-400':
        matchSearch(entry, searchText, fieldsToSearch) && searchText.length > 2,
        hidden:
        !matchSearch(entry, searchText, fieldsToSearch) &&
        hideNoResults &&
        searchText.length > 2,
    }"
    :entry="entry"
    v-for="(entry, index) in entryList"
    :key="index"
    />
</ul>
</template>
```

para funcionar nuestra búsqueda, necesitamos ciertas variables para funcionar la barra de búsqueda y el filtrado.

| Variable | Funcionalidad |
|----------|-------------|
| `searchText` | Esta variable recolecta desde el componente `<SearchBar />` por medio de la propiedad |`@Search="searchText = $event"`, cada vez que se pulse enter, este realizará la tarea de búsqueda y lo enviará a esta variable. |
| `hideNoResults` | Esta variable únicamente es un booleando que define si se está ocultando o no las cards que no cumplan con los criterios de búsqueda |
| `fieldsToSearch` | Imagina como si en esta variable, descompones por strings la estructura de un objeto y le indicas en dónde buscar. |
