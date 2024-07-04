<script setup lang="ts">
import getFormattedDateTime from "@/utils/getFormattedDateTime";
import type Entry from "@/types/Entry";
import UserInfoCard from "./UserInfoCard.vue";

enum Message {
  TITLE = "Entradas",
  SINGULAR = "Entrada",
  ACCESS_DATE = "Fecha y Hora de Entrada",
  DESCRIPTION = "Descripción",
}

const props = defineProps<{
  entry: Entry;
}>();
</script>

<template>
  <li class="flex flex-col overflow-hidden rounded-lg border border-blue-200 bg-blue-100">
    <div class="flex flex-1 flex-col gap-1 p-2">
      <h2 class="font-medium leading-5">{{ Message.SINGULAR }}</h2>
      <div class="flex flex-col justify-between gap-2 sm:flex-row sm:items-center">
        <UserInfoCard :user="entry.user" />
        <div class="flex flex-col leading-none sm:text-right">
          <h3 class="text-xs font-semibold text-blue-500">{{ Message.ACCESS_DATE }}</h3>
          <p class="text-blue-400">{{ getFormattedDateTime(props.entry.accessDate) }}</p>
        </div>
      </div>
    </div>
    <div class="flex flex-col gap-1 bg-white p-2 text-blue-500">
      <h3 class="text-xs font-medium">{{ Message.DESCRIPTION }}</h3>
      <p class="leading-tight">{{ props.entry.description }}</p>
    </div>
  </li>
</template>
