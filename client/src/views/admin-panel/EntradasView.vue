<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue"
import Entry from "@/components/Cards/EntryCard.vue"
import type EntryCard from "@/types/EntryCard"
import SearchBar from "@/components/SearchBar.vue"
import Pagination from "@/components/PaginationItem.vue";

const entryList: EntryCard[] = [
  {
    id: 1,
    userType: "",
    username: "",
    dateTime: "5 de Mayo de 2024",
    direction: "Rumbo a Palacio de Miraflores para realizar tareas de mantenimiento",
    isRegister: true
  },
]

</script>

<template>
  <CurrentPageInfo title="Entradas" icon="folder" />
  <SearchBar class="py-4" />
  <ul class="grid lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4 gap-4 p-4 w-full">
    <Entry :entryCard="entry" v-for="entry in entryList" :key="entry.id" />
  </ul>
  <Pagination class="py-4" :total-pages="6" />
</template>