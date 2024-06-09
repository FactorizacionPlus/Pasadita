<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import Residences from "@/components/Cards/ResidenceCard.vue";
import type Residence from "@/types/Residence";
import SearchBar from "@/components/SearchBar.vue";

const residenceList: Residence[] = [
  {
    maxHabitants: 1,
    description: "Lorem Ipsum",
  },
  {
    maxHabitants: 2,
    description: "Lorem Ipsum",
  },
  {
    maxHabitants: 3,
    description: "Lorem Ipsum",
  },
];
</script>

<template>
  <CurrentPageInfo title="Residencias" icon="home" />
  <SearchBar class="py-4" />
  <ul class="grid grid-cols-4 gap-4">
    <Residences
      :residence="residence"
      v-for="residence in residenceList"
      :key="residence.description"
    />
  </ul>
</template>
