<script setup lang="ts">
import CardsContainer from "@/components/CardsContainer.vue";
import AccessRequestCard from "@/components/Cards/AccessRequestCard.vue";
import Pagination from "@/components/PaginationItem.vue";
import SearchBar from "@/components/SearchBar.vue";

const permissionHistory: PermissionHistoryProps[] = [
  {
    residence: {
      description: "Historial de solicitudes",
      maxHabitants: 5,
    },
    requests: [
      {
        status: "REJECTED",
        startDate: new Date("2004-10-04"),
        endDate: new Date("2024-10-04"),
        residentId: "0011904041016S",
        residentName: "Vladimir Putin",
      },
      {
        status: "PENDING",
        startDate: new Date("2004-10-04"),
        endDate: new Date("2024-10-04"),
        residentId: "0011904041016S",
        residentName: "Ronald Reagan",
      },
      {
        status: "ACCEPTED",
        startDate: new Date("2004-10-04"),
        endDate: new Date("2024-10-04"),
        residentId: "0011904041016S",
        residentName: "George Bush",
      },
      {
        status: "REJECTED",
        startDate: new Date("2004-10-04"),
        endDate: new Date("2024-10-04"),
        residentId: "0011904041016S",
        residentName: "Barack Obama",
      },
      {
        status: "ACCEPTED",
        startDate: new Date("2004-10-04"),
        endDate: new Date("2024-10-04"),
        residentId: "0011904041016S",
        residentName: "Donald Trump",
      },
      {
        status: "REJECTED",
        startDate: new Date("2004-10-04"),
        endDate: new Date("2024-10-04"),
        residentId: "0011904041016S",
        residentName: "Joe Biden",
      },
      {
        status: "REJECTED",
        startDate: new Date("2004-10-04"),
        endDate: new Date("2024-10-04"),
        residentId: "0011904041016S",
        residentName: "Kamala Harris",
      },
      {
        status: "REJECTED",
        startDate: new Date("2004-10-04"),
        endDate: new Date("2024-10-04"),
        residentId: "0011904041016S",
        residentName: "Jonh F. Kennedy",
      },
    ],
  },
];
</script>

<template>
  <CardsContainer
    v-for="(history, index) in permissionHistory"
    :key="index"
    icon="grid"
    :title="history.residence.description"
  >
    <SearchBar class="p-4 pb-0" />
    <ul class="grid lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4 gap-4 p-4 w-full">
      <AccessRequestCard
        :access-request="item"
        :key="index"
        v-for="(item, index) in history.requests"
      />
    </ul>
    <Pagination class="py-4" :total-pages="6" />
  </CardsContainer>
</template>
