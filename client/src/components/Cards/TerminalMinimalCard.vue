<script setup lang="ts">
import VueFeather from "vue-feather";
import type Terminal from "../../types/AdminTerminal";

const props = defineProps<{
  terminal: Terminal;
}>();

const entriesQuantity = 0;

enum dic {
  SINGULAR = "Terminal",
  ENTRIES_QUANTITY = "Cantidad de Entradas: ",
  DESCRIPTION = "Descripción",
}
</script>

<template>
  <div class="flex flex-col rounded-lg">
    <div class="flex flex-col gap-1 p-2">
      <h2 class="text-base font-medium text-blue-500">{{ dic.SINGULAR }}</h2>
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
            <span class="font-medium">{{ dic.ENTRIES_QUANTITY }}</span> {{ entriesQuantity }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>
