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
      description: "Palacio de Miraflores. república de Venezuela",
      maxHabitants: 5,
      currentResidents: 4,
    },
    requests: [
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
    ],
  },
  {
    residence: {
      description: "Casa Presidencial de Team Factorización, Mejicanos, San Salvador",
      maxHabitants: 3,
      currentResidents: 2,
    },
    requests: [
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
    <SearchBar class="pb-0" />
    <ul class="grid w-full gap-4 pt-4 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4">
      <AccessRequestCard
        :access-request="item"
        :controls="false"
        :key="index"
        v-for="(item, index) in history.requests"
      />
    </ul>
    <Pagination class="pt-4" :total-pages="6" />
  </CardsContainer>
</template>
