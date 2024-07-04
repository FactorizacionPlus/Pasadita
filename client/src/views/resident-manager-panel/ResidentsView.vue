<script setup lang="ts">
import { ref } from "vue";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import ResidentCard from "@/components/Cards/ResidentCard.vue";
import SearchBar from "@/components/SearchBar.vue";
import VueFeather from "vue-feather";
import ModalAdd from "@/components/Modal/ResidentManager/AddResident.vue";
import { matchSearch } from "@/utils/matchSearch";
import type Resident from "@/types/User/Resident";

const modalAdd = ref<typeof ModalAdd>();

enum Message {
  TITLE = "Lista de Residentes",
}

const searchText = ref("");
const hideNoResults = ref(false);
const fieldsToSearch = ["firstName", "lastName", "identifier", "residence.description"];

const residents: Resident[] = [
  {
    imageUrl: "https://www.github.com/davidquintr.png",
    firstName: "David",
    lastName: "Quintanilla",
    identifier: "0011904041016S",
    email: "",
    identifierType: "PASSPORT",
    residence: {
      description: "Casa de los olivos",
      maxHabitants: 32,
      status: "ACTIVE",
    },
    role: "ROLE_ADMIN",
    uuid: "",
    entryCount: 0,
    status: "ACTIVE",
  },
];
</script>

<template>
  <article class="flex flex-col gap-8 rounded-lg bg-white p-4">
    <CurrentPageInfo class="p-0" :title="Message.TITLE" icon="user">
      <button
        class="inline-flex items-center rounded-lg bg-blue-100 p-2 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-200 active:scale-95"
        @click="modalAdd?.show()"
      >
        <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
        <span>Emitir un permiso</span>
      </button>
    </CurrentPageInfo>

    <SearchBar @search="searchText = $event" @toggle-no-results="hideNoResults = $event" />

    <ul class="grid w-full gap-4 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4">
      <ResidentCard
        :resident="resident"
        :class="{
          'animate-scale-up border-2 border-blue-400':
            matchSearch(resident, searchText, fieldsToSearch) && searchText.length > 2,
          hidden:
            !matchSearch(resident, searchText, fieldsToSearch) &&
            hideNoResults &&
            searchText.length > 2,
        }"
        v-for="(resident, index) in residents"
        :key="index"
      />
    </ul>
  </article>
  <ModalAdd ref="modalAdd">Hola</ModalAdd>
</template>
