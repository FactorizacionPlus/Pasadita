<script setup lang="ts">
import type Terminal from "@/types/Terminal";
import VueFeather from "vue-feather";

const props = defineProps<{
  terminal: Terminal;
}>();

const entriesQuantity = 0;

enum Message {
  SINGULAR = "Terminal",
  ENTRIES_QUANTITY = "Cantidad de Entradas: ",
  DESCRIPTION = "Descripción",
}
</script>

<template>
  <div class="flex flex-col rounded-lg">
    <div class="flex flex-col gap-1 p-2">
      <h2 class="text-base font-medium text-blue-500">{{ Message.SINGULAR }}</h2>
      <div class="flex items-center gap-2">
        <VueFeather
          type="tablet"
          class="size-8 min-w-8"
          stroke="#01193F"
          stroke-width="1.5"
        ></VueFeather>
        <div>
          <p class="text-lg font-normal text-blue-400">
            {{ props.terminal.type }}
          </p>
          <p>
            <span class="font-medium">{{ Message.ENTRIES_QUANTITY }}</span>
            {{ props.terminal.entryCount }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>
