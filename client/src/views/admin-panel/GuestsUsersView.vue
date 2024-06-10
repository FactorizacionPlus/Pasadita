<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import Guest from "@/components/Cards/InvitedResidentCard.vue";
import type InvitedResident from "@/types/InvitedResident";
import PaginationItem from "@/components/PaginationItem.vue";
import SearchBar from "@/components/SearchBar.vue";

const invitedResidentList: InvitedResident[] = [
  {
    name: "Pedro",
    secondName: "Zelaya",
    identification: "C3049392",
    entries: 0,
    image: "",
  },
  {
    name: "Pedro",
    secondName: "Zelaya",
    identification: "C3049392",
    entries: 0,
    image: "",
  },
  {
    name: "Pedro",
    secondName: "Zelaya",
    identification: "C3049392",
    entries: 0,
    image: "",
  },
  {
    name: "Pedro",
    secondName: "Zelaya",
    identification: "C3049392",
    entries: 0,
    image: "",
  },
  {
    name: "Pedro",
    secondName: "Zelaya",
    identification: "C3049392",
    entries: 0,
    image: "",
  },
];
</script>

<template>
  <CurrentPageInfo title="Invitados" icon="user" />
  <SearchBar class="py-3" />
  <ul class="grid gap-4 pt-4 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4 min-h-[43rem]">
    <Guest :invitedResident="invitedResident" v-for="invitedResident in invitedResidentList"
      :key="invitedResident.identification" />
  </ul>
  <PaginationItem :totalPages=5 />
</template>
