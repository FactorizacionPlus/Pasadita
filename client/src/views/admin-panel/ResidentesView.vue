<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import ResidentCard from "@/components/Cards/ResidentCard.vue";
import type Resident from "@/types/InvitedResident";
import CardsContainer from "@/components/CardsContainer.vue";
import SearchBar from "@/components/SearchBar.vue";

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
    ],
    nameContainer: "Santa Fe"
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
    nameContainer: "Palacio de Miraflores, sede de la Presidencia de la República"
  },
];

/*const residentList: Resident[] = [
  {
    name: "Pedro",
    secondName: "Zelaya",
    identification: "C3049392",
    entries: 0,
    image: "",
  },
  {
    name: "Sanchez",
    secondName: "Zelaya",
    identification: "C3049392",
    entries: 0,
    image: "",
  },
];*/
</script>

<template>
  <CurrentPageInfo title="Residentes" icon="user" />
  <CardsContainer v-for="(residentGroup, index) in residentHistory" :key="index" icon="home" :title="residentGroup.nameContainer">
    <SearchBar class="p-4 pb-0"/>
    <div class="grid grid-cols-3 gap-3 p-4">  
      <ResidentCard :resident="resident" :key="index" v-for="(resident, index) in residentGroup.resident" />
    </div>
  </CardsContainer>
</template>
