<script setup lang="ts">
import type Terminal from "@/types/Terminal";
import VueFeather from "vue-feather";

const props = defineProps<{
  terminal: Terminal;
}>();
</script>

<template>
  <li class="flex items-center gap-1 rounded-lg border border-blue-200 bg-blue-100 p-4">
    <VueFeather type="tablet" class="size-5" stroke="#01193F" stroke-width="2"></VueFeather>
    <p class="text-lg font-medium lowercase leading-none text-blue-400">
      {{ props.terminal.type }}
    </p>
  </li>
</template>
