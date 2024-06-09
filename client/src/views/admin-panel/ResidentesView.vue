<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import ResidentCard from "@/components/Cards/ResidentCard.vue";
import type Resident from "@/types/InvitedResident";

const residentList: Resident[] = [
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
  <div class="flex flex-col gap-10">
    <div>
      <CurrentPageInfo title="Residentes" icon="user" />
      <ResidentCard
        :resident="resident"
        v-for="resident in residentList"
        :key="resident.identification"
      />
    </div>
    <div>
      <CurrentPageInfo title="Residentes" icon="user" />
      <ResidentCard
        :resident="resident"
        v-for="resident in residentList"
        :key="resident.identification"
      />
    </div>
  </div>
</template>
