<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import Residences from "@/components/Cards/ResidenceCard.vue";
import type Residence from "@/types/Residence";
import SearchBar from "@/components/SearchBar.vue";
import ModalAdd from "@/components/Modal/Residence/ManageResidence.vue";
import PaginationItem from "@/components/PaginationItem.vue";
const modalAdd = ref<typeof ModalAdd>();

const residenceList: Residence[] = [
  {
    maxHabitants: 2,
    description: "Palacio de Miraflores, República de Venezuela",
    currentResidents: 1,
  },
  {
    maxHabitants: 2,
    description: "Casa Presidencial, Team Factorización, Mejicanos, San Salvador",
    currentResidents: 1,
  },
  {
    maxHabitants: 3,
    description: "Universidad Centroamericana, Managua, Nicaragua",
    currentResidents: 3,
  },
];
</script>

<template>
  <div class="flex flex-row items-center justify-between">
    <CurrentPageInfo title="Residencias" icon="home" />
    <button
      class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-3 hover:text-white active:scale-95"
      @click="modalAdd?.show()"
    >
      <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
      <span>Agregar Residencia</span>
    </button>
  </div>
  <SearchBar class="py-4" />
  <ul class="grid w-full gap-4 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 2xl:grid-cols-5">
    <Residences
      :residence="residence"
      v-for="residence in residenceList"
      :key="residence.description"
    />
  </ul>
  <PaginationItem class="pt-4" :total-pages="6" />
  <ModalAdd ref="modalAdd">Hola</ModalAdd>
</template>
