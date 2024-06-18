<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import ResidentCard from "@/components/Cards/ResidentCard.vue";
import type Resident from "@/types/InvitedResident";
import CardsContainer from "@/components/CardsContainer.vue";
import SearchBar from "@/components/SearchBar.vue";
import PaginationItem from "@/components/PaginationItem.vue";

interface ResidentHistory {
  resident: Resident[];
  nameContainer: string;
}

const residentHistory: ResidentHistory[] = [
  {
    resident: [
      {
        image: "https://www.github.com/davidquintr.png",
        name: "David",
        secondName: "Quintanilla",
        identification: "0011904041016S",
        entries: 12,
      },
      {
        name: "David",
        secondName: "Parrales Ponce",
        identification: "C3049392",
        entries: 0,
        image: "https://www.github.com/poncka.png",
      },
      {
        name: "Jezer",
        secondName: "Mejía Otero",
        identification: "C3049392",
        entries: 0,
        image: "https://www.github.com/JezerM.png",
      },
    ],
    nameContainer: "Casa Presidencial de Team Factorización, Mejicanos, San Salvador",
  },
  {
    resident: [
      {
        name: "Marcelo",
        secondName: "Rivera Soto",
        identification: "C3049392",
        entries: 0,
        image: "https://www.github.com/Mrsty21.png",
      },
      {
        name: "Leo",
        secondName: "Corea Navarrete",
        identification: "C3049392",
        entries: 0,
        image: "https://www.github.com/leocorea.png",
      },
    ],
    nameContainer: "Palacio de Miraflores, sede de la Presidencia de la República",
  },
];
</script>

<template>
  <CurrentPageInfo title="Residentes" icon="user" />
  <CardsContainer
    v-for="(residentGroup, index) in residentHistory"
    :key="index"
    icon="home"
    :title="residentGroup.nameContainer"
  >
    <SearchBar class="pb-0" />
    <ul class="grid gap-4 pt-4 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4">
      <ResidentCard
        :resident="resident"
        :key="index"
        v-for="(resident, index) in residentGroup.resident"
      />
    </ul>
    <PaginationItem class="pt-4" :total-pages="6" />
  </CardsContainer>
</template>
