<script setup lang="ts">
import { defineProps } from "vue";
import VueFeather from "vue-feather";

interface Props {
  icon: "grid" | "user" | "tablet" | "file-text" | "home" | "folder" | "file";
  title: string;
}

const props = defineProps<Props>();
</script>

<template>
  <section class="flex flex-col justify-between gap-2 py-2 text-pasadita-shade-0 sm:flex-row">
    <div class="flex items-center gap-1">
      <VueFeather stroke-width="2" size="32" :type="props.icon" />
      <h1 class="text-3xl font-bold">{{ props.title }}</h1>
    </div>
    <div class="ml-auto">
      <slot />
    </div>
  </section>
</template>
