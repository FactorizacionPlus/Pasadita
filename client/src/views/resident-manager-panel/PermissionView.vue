<script setup lang="ts">
import { ref } from "vue";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import AccessRequestCard from "@/components/Cards/AccessRequestCard.vue";
import SearchBar from "@/components/SearchBar.vue";
import Pagination from "@/components/PaginationItem.vue";
import type AccessRequest from "@/types/AccessRequest";
import ModalAdd from "@/components/Modal/ResidentManager/AddResidentManager.vue";
const modalAdd = ref<typeof ModalAdd>();

const permissionHistory: AccessRequest[] = [
  {
    status: "PENDING",
    startDate: new Date("2004-10-04"),
    endDate: new Date("2024-10-04"),
    residentId: "0011904041016S",
    residentName: "Juan Daniel Gaturron",
    image: "",
  },
  {
    status: "ACCEPTED",
    startDate: new Date("2004-10-04"),
    endDate: new Date("2024-10-04"),
    residentId: "0011904041016S",
    residentName: "Juan Daniel Gaturron",
    image: "",
  },
  {
    status: "PENDING",
    startDate: new Date("2004-10-04"),
    endDate: new Date("2024-10-04"),
    residentId: "0011904041016S",
    residentName: "Juan Daniel Gaturron",
    image: "",
  },
];
</script>

<template>
  <div class="flex flex-row items-center justify-between">
    <CurrentPageInfo title="Historial de solicitudes" icon="grid" />
    <button
      class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-3 hover:text-white active:scale-95"
      @click="modalAdd?.show()">
      <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
      <span>Agregar solicitud</span>
    </button>
  </div>
  <SearchBar class="p-4 pb-0" />
  <ul class="grid w-full gap-4 p-4 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
    <AccessRequestCard :access-request="item" :key="index" :controls="true"
      v-for="(item, index) in permissionHistory" />
  </ul>
  <Pagination class="py-4" :total-pages="6" />
  <ModalAdd ref="modalAdd">Hola</ModalAdd>
</template>
