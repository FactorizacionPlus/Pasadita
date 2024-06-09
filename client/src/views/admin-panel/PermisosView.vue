<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import CardsContainer from "@/components/CardsContainer.vue";
import type AccessRequest from "@/types/AccessRequest";
import type Residence from "@/types/Residence";
import AccessRequestCard from "@/components/Cards/AccessRequestCard.vue";
import Pagination from "@/components/PaginationItem.vue";
import SearchBar from "@/components/SearchBar.vue";

interface PermissionHistoryProps {
  residence: Residence;
  requests: AccessRequest[];
}

const permissionHistory: PermissionHistoryProps[] = [
  {
    residence: {
      description: "Palacio de Miraflores",
      maxHabitants: 5,
    },
    requests: [
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
    ],
  },
  {
    residence: {
      description: "Casa Presidencial de Team Factorización   ",
      maxHabitants: 3,
    },
    requests: [
      {
        status: "ACCEPTED",
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
        status: "ACCEPTED",
        startDate: new Date("2004-10-04"),
        endDate: new Date("2024-10-04"),
        residentId: "0011904041016S",
        residentName: "Juan Daniel Gaturron",
        image: "",
      },
    ],
  },
];
</script>

<template>
  <CurrentPageInfo title="Historial de Permisos" icon="file" />
  <CardsContainer
    v-for="(history, index) in permissionHistory"
    :key="index"
    icon="home"
    :title="history.residence.description"
  >
    <SearchBar class="p-4 pb-0" />
    <ul class="grid w-full gap-4 p-4 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4">
      <AccessRequestCard
        :access-request="item"
        :key="index"
        v-for="(item, index) in history.requests"
      />
    </ul>
    <Pagination class="py-4" :total-pages="6" />
  </CardsContainer>
</template>
