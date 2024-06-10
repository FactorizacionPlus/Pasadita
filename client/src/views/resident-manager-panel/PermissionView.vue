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
    status: "ACCEPTED",
    startDate: new Date("2024-01-01"),
    endDate: new Date("2024-01-29"),
    residentId: "0011904041016S",
    residentName: "Marcelo Rivera Soto",
    image: "https://github.com/MrSty.png",
  },
  {
    status: "ACCEPTED",
    startDate: new Date("2024-01-01"),
    endDate: new Date("2024-01-29"),
    residentId: "0011904041016S",
    residentName: "Marcelo Rivera Soto",
    image: "https://github.com/MrSty.png",
  },
  {
    status: "REJECTED",
    startDate: new Date("2024-01-01"),
    endDate: new Date("2024-01-29"),
    residentId: "0011904041016S",
    residentName: "Marcelo Rivera Soto",
    image: "https://github.com/MrSty.png",
  },
  {
    status: "ACCEPTED",
    startDate: new Date("2024-01-01"),
    endDate: new Date("2024-01-29"),
    residentId: "0011904041016S",
    residentName: "Marcelo Rivera Soto",
    image: "https://github.com/MrSty.png",
  },
  {
    status: "ACCEPTED",
    startDate: new Date("2024-01-01"),
    endDate: new Date("2024-01-29"),
    residentId: "0011904041016S",
    residentName: "Marcelo Rivera Soto",
    image: "https://github.com/MrSty.png",
  },
  {
    status: "ACCEPTED",
    startDate: new Date("2004-10-04"),
    endDate: new Date("2024-10-04"),
    residentId: "0011904041016S",
    residentName: "Leo Corea Navarrete",
    image: "https://github.com/leocorea.png",
  },
  {
    status: "PENDING",
    startDate: new Date("2013-05-03"),
    endDate: new Date("2024-07-28"),
    residentId: "0011904041016S",
    residentName: "Nicolás Maduro Moros",
    image: "https://pasadita.vercel.app/madurito.png",
  },
  {
    status: "PENDING",
    startDate: new Date("2024-05-04"),
    endDate: new Date("2024-10-04"),
    residentId: "0011904041016S",
    residentName: "Juan Daniel Gaturron",
    image:
      "https://www.laprensagrafica.com/__export/1714704878780/sites/prensagrafica/img/2024/05/02/what_in_the_world_is_a_shoebill_6_new_stateman.jpg_423682103.jpg",
  },
  {
    status: "REJECTED",
    startDate: new Date("2004-10-04"),
    endDate: new Date("2024-10-04"),
    residentId: "0011904041016S",
    residentName: "Hugo Chávez Frías",
    image:
      "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Hugo_Rafael_Ch%C3%A1vez_Fr%C3%ADas.jpeg/640px-Hugo_Rafael_Ch%C3%A1vez_Fr%C3%ADas.jpeg",
  },
];
</script>

<template>
  <CurrentPageInfo title="Historial de solicitudes" icon="grid">
    <button
      class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-6 active:scale-95"
      @click="modalAdd?.show()"
    >
      <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
      <span>Agregar solicitud</span>
    </button>
  </CurrentPageInfo>
  <SearchBar class="py-4 pb-0" />
  <ul class="grid w-full gap-4 py-4 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
    <AccessRequestCard
      :access-request="item"
      :key="index"
      v-for="(item, index) in permissionHistory"
    />
  </ul>
  <Pagination class="py-4" :total-pages="6" />
  <ModalAdd ref="modalAdd">Hola</ModalAdd>
</template>
