<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import Guest from "@/components/Cards/InvitedResidentCard.vue";
import type InvitedResident from "@/types/InvitedResident";

const invitedResidentList: InvitedResident[] = [
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
  <ul class="grid gap-4 pt-4 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4">
    <Guest
      :invitedResident="invitedResident"
      v-for="invitedResident in invitedResidentList"
      :key="invitedResident.identification"
    />
  </ul>
</template>
