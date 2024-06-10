<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import AccessRequestCard from "@/components/Cards/AccessRequestCard.vue";
import SearchBar from "@/components/SearchBar.vue";
import Pagination from "@/components/PaginationItem.vue";
import type AccessRequest from "@/types/AccessRequest";

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
];
</script>

<template>
  <CurrentPageInfo title="Historial de solicitudes" icon="grid" />
  <SearchBar class="p-4 pb-0" />
  <ul class="grid w-full gap-4 p-4 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
    <AccessRequestCard
      :access-request="item"
      :key="index"
      v-for="(item, index) in permissionHistory"
    />
  </ul>
  <Pagination class="py-4" :total-pages="6" />
</template>
