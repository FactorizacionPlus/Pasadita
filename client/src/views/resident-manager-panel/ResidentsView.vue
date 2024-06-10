<script setup lang="ts">
import { ref } from "vue";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import ResidentCard from "@/components/Cards/ResidentCard.vue";
import Pagination from "@/components/PaginationItem.vue";
import SearchBar from "@/components/SearchBar.vue";
import VueFeather from "vue-feather";
import ModalAdd from "@/components/Modal/ResidentManager/AddResident.vue";
const modalAdd = ref<typeof ModalAdd>();

const residentList = [
  {
    name: "Juan",
    secondName: "Perez",
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
      v-for="resident in residentList"
      :key="resident.identification"
    />
  </ul>
  <Pagination class="py-4" :total-pages="6" />
  <ModalAdd ref="modalAdd">Hola</ModalAdd>
</template>
