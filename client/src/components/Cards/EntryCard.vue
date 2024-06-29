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
  <li class="flex flex-col overflow-hidden rounded-lg border border-blue-200 bg-blue-100">
    <div class="flex flex-col gap-1 p-2">
      <h3 class="font-medium leading-5">{{ entryCard.user.role }}</h3>
      <div class="flex flex-col justify-between gap-4 sm:flex-row sm:items-center">
        <UserEntryCard :user="entryCard.user" />
        <div class="flex flex-col leading-none sm:text-right">
          <p class="text-xs font-semibold text-blue-500">Fecha y hora de entrada</p>
          <p class="text-blue-400">{{ getFormattedDateTime(props.entryCard.entryDate) }}</p>
        </div>
      </div>
    </div>
    <div class="flex flex-row items-center gap-1 bg-white p-2 text-blue-500">
      <VueFeather type="home" size="16" stroke-width="2"></VueFeather>
      <p>{{ props.entryCard.description }}</p>
    </div>
  </li>
</template>
