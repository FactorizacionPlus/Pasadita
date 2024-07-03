<script setup lang="ts">
import isValidURL from "@/utils/isValidUrl";
import VueFeather from "vue-feather";
const props = defineProps<{ image?: string; size?: string; padding?: string }>();
</script>

<template>
  <picture
    class="relative flex aspect-square w-auto items-center justify-center rounded-full border-2 border-blue-400 bg-blue-100"
  >
    <img
      v-if="isValidURL(props.image)"
      :src="props.image"
      alt="User Image"
      class="size-full rounded-full object-cover"
    />
    <VueFeather
      v-else
      type="user"
      stroke="#004f82"
      stroke-width="1.5"
      class="relative aspect-square h-auto w-3/4 *:absolute *:left-1/2 *:top-1/2 *:m-auto *:-translate-x-1/2 *:-translate-y-1/2"
    ></VueFeather>
  </picture>
</template>
