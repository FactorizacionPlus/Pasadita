<script setup lang="ts">
import getFormattedDateTime from "@/utils/getFormattedDateTime";
import isResident from "@/utils/isResident";
import UserEntryCard from "./UserEntryCard.vue";
import type Entry from "@/types/Entry";

const props = defineProps<{
  entryCard: Entry;
}>();

</script>

<template>
  <li class="flex flex-col overflow-hidden rounded-lg border border-blue-200 bg-blue-100">
    <div class="flex flex-1 flex-col gap-1 p-2">
      <h2 class="font-medium leading-5">{{ (isResident(entryCard.user)) ?  "Residente" : "Invitado" }}</h2>
      <div class="flex flex-col justify-between gap-2 sm:flex-row sm:items-center">
        <UserEntryCard :user="entryCard.user" />
        <div class="flex flex-col leading-none sm:text-right">
          <p class="text-xs font-semibold text-blue-500">Fecha y hora de entrada</p>
          <p class="text-blue-400">{{ getFormattedDateTime(props.entryCard.accessDate) }}</p>
        </div>
      </div>
    </div>
  <div class="flex flex-col gap-1 bg-white p-2 text-blue-500">
      <h3 class="text-xs font-medium">Descripción</h3>
      <p class="leading-tight">{{ props.entryCard.description }}</p>
    </div>
  </li>
</template>
