<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";
import PaginationItem from "@/components/PaginationItem.vue";
import SearchBar from "@/components/SearchBar.vue";
import ModalAdd from "@/components/Modal/Resident/AddResidentModal.vue";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import AccessRequestCard from "@/components/Cards/AccessRequestCard.vue";
import type Permission from "@/types/Permission";
import { matchSearch } from "@/utils/matchSearch";

const modalAdd = ref<typeof ModalAdd>();

const searchText = ref('')
const hideNoResults = ref(false)
const fieldsToSearch = ["invitedUser.firstName", "invitedUser.lastName", "invitedUser.identifier", "residence.description"]

const permissions: Permission[] = [
  {
    authorized: false,
    creationDate: new Date(),
    endDate: new Date(),
    invitedUser: {
      firstName: "Juan Daniel",
      identifier: "C137",
      identifierType: "PASSPORT",
      lastName: "Gaturrón Treminio",
      uuid: "00000000000000"
    },
    residence: {
      description: "Casa de los Pueblos",
      maxHabitants: 32,
      status: "ACTIVE"
    },
    resident: {
      firstName: "David",
      identifier: "C137",
      identifierType: "PASSPORT",
      lastName: "Quintanilla Ruiz",
      uuid: "00000000000000"
    },
    startDate: new Date(),
    uuid: "0000000000"
  },
  {
    authorized: undefined,
    creationDate: new Date(),
    endDate: new Date(),
    invitedUser: {
      firstName: "Marcelo",
      identifier: "C137",
      identifierType: "PASSPORT",
      lastName: "Rivera Soto",
      uuid: "00000000000000"
    },
    residence: {
      description: "Casa de los Pueblos",
      maxHabitants: 32,
      status: "ACTIVE"
    },
    resident: {
      firstName: "David",
      identifier: "C137",
      identifierType: "PASSPORT",
      lastName: "Quintanilla Ruiz",
      uuid: "00000000000000"
    },
    startDate: new Date(),
    uuid: "0000000000"
  },
  {
    authorized: true,
    creationDate: new Date(),
    endDate: new Date(),
    invitedUser: {
      firstName: "Juan Daniel",
      identifier: "C137",
      identifierType: "PASSPORT",
      lastName: "Gaturrón Treminio",
      uuid: "00000000000000"
    },
    residence: {
      description: "Casa de los Pueblos",
      maxHabitants: 32,
      status: "ACTIVE"
    },
    resident: {
      firstName: "David",
      identifier: "C137",
      identifierType: "PASSPORT",
      lastName: "Quintanilla Ruiz",
      uuid: "00000000000000"
    },
    startDate: new Date(),
    uuid: "0000000000"
  }
];
</script>

<template>
  <CurrentPageInfo title="Historial de solicitudes" icon="grid">
    <button
      class="inline-flex items-center rounded-lg bg-white p-2 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-shades-100 active:scale-95"
      @click="modalAdd?.show()">
      <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
      <span>Solicitar un permiso</span>
    </button>
  </CurrentPageInfo>

  <article class="flex flex-col gap-8 rounded-lg bg-white p-4">
    
    <SearchBar @search="searchText = $event" @toggle-no-results="hideNoResults = $event" />

    <ul class="grid w-full gap-4 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
      <AccessRequestCard 
      :class="{ 'animate-scale-up border-2 border-blue-400': matchSearch(item, searchText, fieldsToSearch) && searchText.length > 2, 'hidden': !matchSearch(item, searchText, fieldsToSearch) && hideNoResults && searchText.length > 2 }"
      :permission="item" :key="index" v-for="(item, index) in permissions" />
    </ul>
  </article>
  <ModalAdd ref="modalAdd">Hola</ModalAdd>
</template>
