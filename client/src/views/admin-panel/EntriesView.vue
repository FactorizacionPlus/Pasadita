<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import Entry from "@/components/Cards/EntryCard.vue";
import type EntryCard from "@/types/EntryCard";
import SearchBar from "@/components/SearchBar.vue";
import PaginationItem from "@/components/PaginationItem.vue";

const entryList: EntryCard[] = [
  {
    id: 0,
    user: {
      id: "0011904041016S",
      image: "https://www.github.com/davidquintr.png",
      name: "David",
      lastName: "Quintanilla",
      role: "Residente Encargado",
    },
    description: "Rumbo a la casas de los pueblos",
    entryDate: new Date("10-04-2005"),
  },
  {
    id: 1,
    user: {
      id: "0011904041016S",
      image: "https://www.github.com/poncka.png",
      name: "David",
      lastName: "Parrales Ponce",
      role: "Residente",
    },
    description: "Rumbo a la casas de los pueblos",
    entryDate: new Date("10-04-2015 12:00"),
  },
  {
    id: 2,
    user: {
      id: "0011904041016S",
      image: "https://www.github.com/JezerM.png",
      name: "Jezer",
      lastName: "Mejía Otero",
      role: "Residente",
    },
    description: "Rumbo a la casas de los pueblos",
    entryDate: new Date("10-04-2015 12:00"),
  },
  {
    id: 3,
    user: {
      id: "0011904041016S",
      image: "https://www.github.com/Mrsty21.png",
      name: "Marcelo",
      lastName: "Rivera Soto",
      role: "Residente",
    },
    description: "Rumbo a la casas de los pueblos",
    entryDate: new Date("10-04-2015 12:00"),
  },
  {
    id: 4,
    user: {
      id: "0011904041016S",
      image: "https://www.github.com/leocorea.png",
      name: "Leo",
      lastName: "Corea Navarrete",
      role: "Residente",
    },
    description: "Rumbo a la casas de los pueblos",
    entryDate: new Date("10-04-2015 12:00"),
  },
];
</script>

<template>
  <CurrentPageInfo title="Entradas" icon="folder" />
  <SearchBar class="py-4" />
  <ul class="grid w-full gap-4 pt-4 lg:grid-cols-2">
    <Entry :entryCard="entry" v-for="entry in entryList" :key="entry.id" />
  </ul>
  <PaginationItem class="pt-4" :total-pages="6" />
</template>
