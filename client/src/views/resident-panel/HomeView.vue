<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";
import PaginationItem from "@/components/PaginationItem.vue";
import SearchBar from "@/components/SearchBar.vue";
import ModalAdd from "@/components/Modal/Resident/AddResidentModal.vue";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import type AccessRequest from "@/types/AccessRequest";
import AccessRequestCard from "@/components/Cards/AccessRequestCard.vue";

const modalAdd = ref<typeof ModalAdd>();

const accessRequests: AccessRequest[] = [
  {
    status: "PENDING",
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
  {
    status: "REJECTED",
    startDate: new Date("2004-10-04"),
    endDate: new Date("2024-10-04"),
    residentId: "0011904041016S",
    residentName: "Juan Daniel Gaturron",
    image: "",
  },
]

</script>

<template>
  <CurrentPageInfo title="Historial de solicitudes" icon="grid">
    <button
      class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-6 active:scale-95"
      @click="modalAdd?.show()">
      <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
      <span>Solicitar un permiso</span>
    </button>
  </CurrentPageInfo>
  <SearchBar class="py-4 pb-0" />
  <ul class="grid w-full gap-4 py-4 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
    <AccessRequestCard
      :access-request="item"
      :key="index"
      v-for="(item, index) in accessRequests"
    />
  </ul>
  <PaginationItem :totalPages="5" />
  <ModalAdd ref="modalAdd">Hola</ModalAdd>
</template>
