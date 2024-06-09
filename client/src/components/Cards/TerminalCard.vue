<script setup lang="ts">
import VueFeather from "vue-feather";
import type Terminal from "../../types/AdminTerminal";

const props = defineProps<{
  terminal: Terminal;
}>();
</script>

<template>
  <li class="flex flex-col bg-white">
    <!--Information-->
    <div
      class="flex items-center justify-center gap-2 rounded-t-lg border-x border-t border-pasadita-shade-2 p-2"
    >
      <div class="flex items-center">
        <VueFeather type="tablet" size="40" stroke="#01193F" stroke-width="1.5"></VueFeather>
      </div>
      <div class="flex-1">
        <p class="text-base font-medium text-pasadita-blue-1">Terminal</p>
        <p class="text-lg font-normal text-pasadita-blue-1">
          {{ props.terminal.type }}
        </p>
      </div>
    </div>
    <!--Description-->
    <div class="flex h-10 flex-row gap-1 border-x border-pasadita-shade-2 p-2">
      <p class="line-clamp-2 font-medium text-pasadita-blue-1">Cantidad de entradas:</p>
      <p class="line-clamp-2 font-normal text-pasadita-blue-1">
        {{ props.terminal.numentries }}
      </p>
    </div>
    <!--CRUD-->
    <div class="flex flex-row justify-end gap-2 rounded-b-lg border border-pasadita-shade-2 p-2">
      <button
        type="button"
        class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2.5 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-3 hover:text-white active:scale-95"
      >
        <VueFeather type="eye" stroke-width="3.5" size="16"></VueFeather>
      </button>
    </div>
  </li>
</template>
