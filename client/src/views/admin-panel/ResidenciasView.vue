<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import Residences from "@/components/Cards/ResidenceCard.vue";
import type Residence from "@/types/Residence";
import SearchBar from "@/components/SearchBar.vue";
import ModalAdd from "@/components/Modal/Residence/ManageResidence.vue"
const modalAdd = ref<typeof ModalAdd>();

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
  <div class="flex flex-row items-center justify-between">
    <CurrentPageInfo title="Residencias" icon="home" />
    <button
        class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-3 hover:text-white active:scale-95" @click="modalAdd?.show()">
        <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
        <span>Agregar Residencia</span>
      </button>
  </div>
  <SearchBar class="py-4" />
  <ul class="grid grid-cols-4 gap-4">
    <Residences :residence="residence" v-for="residence in residenceList" :key="residence.description" />
  </ul>
  <ModalAdd ref="modalAdd">Hola</ModalAdd>
</template>
