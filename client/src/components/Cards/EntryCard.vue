<script setup lang="ts">
import VueFeather from "vue-feather";
import UserEntryCard from "@/components/Cards/UserEntryCard.vue";
import type EntryCard from "@/types/EntryCard";
import getFormattedDateTime from "@/utils/getFormattedDateTime";

const props = defineProps<{
  entryCard: EntryCard;
}>();
</script>

<template>
  <li class="flex flex-col gap-4 rounded-lg border border-pasadita-shade-2 bg-pasadita-shade-3 p-4">
    <div class="flex flex-col justify-between gap-4 sm:flex-row sm:items-center">
      <UserEntryCard :user="entryCard.user" />
      <div class="flex flex-col text-pasadita-blue-1 sm:text-right">
        <p class="font-semibold">Fecha y hora de entrada</p>
        <p>{{ getFormattedDateTime(props.entryCard.entryDate) }}</p>
      </div>
    </div>
    <div class="flex flex-row items-center gap-1">
      <VueFeather type="home" size="24" stroke="#01193F" stroke-width="1.5"></VueFeather>
      <p>{{ props.entryCard.description }}</p>
    </div>
  </li>
</template>
