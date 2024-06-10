<script setup lang="ts">
import { ref } from "vue";
import type Resident from "@/types/Resident";
import ResidentCard from "@/components/Cards/ResidentCard.vue";
import VueFeather from "vue-feather";
import PaginationItem from "@/components/PaginationItem.vue";
import SearchBar from "@/components/SearchBar.vue";
import ModalAdd from "@/components/Modal/Resident/AddResidentModal.vue";
const modalAdd = ref<typeof ModalAdd>();

const resident1: Resident = {
  name: "marcelo",
  secondName: "Rivera",
  identification: "00212121",
  entries: 21,
};
</script>

<template>
  <div class="flex flex-row items-center justify-between">
    <CurrentPageInfo title="Historial de solicitudes" icon="grid" />
    <button
      class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-3 hover:text-white active:scale-95"
      @click="modalAdd?.show()"
    >
      <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
      <span>Solicitar un permiso</span>
    </button>
  </div>
  <SearchBar class="p-4 pb-0" />
  <div class="my-5 grid gap-4 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
    <ResidentCard :resident="resident1" />
    <ResidentCard :resident="resident1" />
    <ResidentCard :resident="resident1" />
    <ResidentCard :resident="resident1" />
    <ResidentCard :resident="resident1" />
    <ResidentCard :resident="resident1" />
  </div>
  <PaginationItem :totalPages="5" />
  <ModalAdd ref="modalAdd">Hola</ModalAdd>
</template>
