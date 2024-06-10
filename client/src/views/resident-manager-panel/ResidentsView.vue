<script setup lang="ts">
import { ref } from "vue";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import ResidentCard from "@/components/Cards/ResidentCard.vue";
import Pagination from "@/components/PaginationItem.vue";
import SearchBar from "@/components/SearchBar.vue";
import VueFeather from "vue-feather";
import ModalAdd from "@/components/Modal/ResidentManager/AddResident.vue";
import type Resident from "@/types/Resident";
const modalAdd = ref<typeof ModalAdd>();

const residentHistory: Resident[] = [
  {
    image: "https://www.github.com/davidquintr.png",
    name: "David",
    secondName: "Quintanilla",
    identification: "0011904041016S",
    entries: 12,
  },
  {
    name: "David",
    secondName: "Parrales Ponce",
    identification: "C3049392",
    entries: 0,
    image: "https://www.github.com/poncka.png",
  },
  {
    name: "Jezer",
    secondName: "Mejía Otero",
    identification: "C3049392",
    entries: 0,
    image: "https://www.github.com/JezerM.png",
  },
  {
    name: "Marcelo",
    secondName: "Rivera Soto",
    identification: "C3049392",
    entries: 0,
    image: "https://www.github.com/Mrsty21.png",
  },
  {
    name: "Leo",
    secondName: "Corea Navarrete",
    identification: "C3049392",
    entries: 0,
    image: "https://www.github.com/leocorea.png",
  },
];
</script>

<template>
  <CurrentPageInfo title="Lista de residentes" icon="user">
    <button
      class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-6 active:scale-95"
      @click="modalAdd?.show()"
    >
      <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
      <span>Solicitar un permiso</span>
    </button>
  </CurrentPageInfo>
  <SearchBar class="py-4 pb-0" />
  <ul class="grid w-full gap-4 py-4 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4">
    <ResidentCard
      :resident="resident"
      v-for="resident in residentHistory"
      :key="resident.identification"
    />
  </ul>
  <Pagination class="py-4" :total-pages="6" />
  <ModalAdd ref="modalAdd">Hola</ModalAdd>
</template>
