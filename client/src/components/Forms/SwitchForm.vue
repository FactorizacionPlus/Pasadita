<script setup lang="ts">
import { defineProps } from "vue";

interface Props {
  disabled?: boolean;
  name: string;
}

const props = defineProps<Props>();

const model = defineModel<boolean>();
</script>

<template>
  <label class="inline-flex cursor-pointer items-center">
    <input
      :name="props.name"
      :disabled="props.disabled"
      type="checkbox"
      class="peer sr-only"
      v-model="model"
    />
    <div
      class="peer relative h-6 w-11 rounded-full bg-gray-200 transition-colors after:absolute after:start-[2px] after:top-[2px] after:size-5 after:rounded-full after:border after:border-gray-300 after:bg-white after:transition-all after:content-[''] peer-checked:bg-pasadita-blue-3 peer-checked:after:translate-x-full peer-checked:after:border-white peer-focus:outline-none peer-disabled:opacity-40 rtl:peer-checked:after:-translate-x-full"
    ></div>
  </label>
</template>
