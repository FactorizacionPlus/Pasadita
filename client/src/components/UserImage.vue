<script setup lang="ts">
import isValidURL from "@/utils/isValidUrl";
import VueFeather from "vue-feather";
const props = defineProps<{ image?: string; size: string; padding: string }>();

</script>

<template>
  <picture class="grid aspect-square place-items-center rounded-full border-2 border-pasadita-blue-2 bg-pasadita-blue-4">
    <img
      v-if="isValidURL(props.image)"
      :src="props.image"
      alt="User Image"
      class="size-full rounded-full object-cover"
    />
    <VueFeather
      v-else
      type="user"
      :size="props.size"
      stroke="#004f82"
      stroke-width="1.5"
      :style="{ padding: props.padding }"
      class="!flex"
    ></VueFeather>
  </picture>
</template>
