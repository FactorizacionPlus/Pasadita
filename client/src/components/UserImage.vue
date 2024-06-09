<script setup lang="ts">
import VueFeather from "vue-feather";
const props = defineProps<{ image: string; size: string; padding: string }>();
</script>

<template>
  <img
    v-if="props.image"
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
</template>
