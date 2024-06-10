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
        name: "Pedro",
        secondName: "Zelaya",
        identification: "C3049392",
        entries: 0,
        image: "https://github.com/davidquintr.png",
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
    ],
    nameContainer: "Santa Fe",
  },
  {
    resident: [
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
