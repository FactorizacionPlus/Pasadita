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

const TERMINAL_DETAIL = `/admin/terminal/${props.terminal.uuid}`;
</script>

<template>
  <li class="flex flex-col rounded-lg border border-blue-200 bg-blue-100">
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
            <span class="font-medium">{{ Message.ENTRIES_QUANTITY }}</span> {{ entriesQuantity }}
          </p>
        </div>
      </div>
    </div>
    <!--CRUD-->
    <div class="flex flex-row justify-end gap-2 rounded-b-lg bg-white p-2">
      <a
        class="inline-flex items-center rounded-lg bg-blue-100 p-2.5 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-200 active:scale-95"
        :href="TERMINAL_DETAIL"
      >
        <VueFeather type="file-text" stroke-width="2.5" size="16"></VueFeather>
      </a>
    </div>
  </li>
</template>
