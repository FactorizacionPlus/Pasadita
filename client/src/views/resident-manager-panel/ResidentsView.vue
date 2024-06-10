<script setup lang="ts">
import CardsContainer from "@/components/CardsContainer.vue";
import ResidentCard from "@/components/Cards/ResidentCard.vue";
import Pagination from "@/components/PaginationItem.vue";
import SearchBar from "@/components/SearchBar.vue";

const residentList = [
  {
    name: "Juan",
    secondName: "Peres",
    identification: "C3049392",
    entries: 10,
    image: "",
  },
  {
    name: "Maria",
    secondName: "Delgado",
    identification: "C3049392",
    entries: 6,
    image: "",
  },
  {
    name: "Pedro",
    secondName: "Sanchez",
    identification: "C4270432",
    entries: 33,
    image: "",
  },
  {
    name: "Carlos",
    secondName: "Santos",
    identification: "C3643642",
    entries: 4,
    image: "",
  },
];
</script>

<template>
  <CardsContainer icon="grid" title="Historial de solicitudes">
    <SearchBar class="p-4 pb-0" />
    <ul class="grid w-full gap-4 p-4 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4">
      <ResidentCard
        :resident="resident"
        v-for="resident in residentList"
        :key="resident.identification"
      />
    </ul>
    <Pagination class="py-4" :total-pages="6" />
  </CardsContainer>
</template>
