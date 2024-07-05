<script setup lang="ts">
import { defineProps } from "vue";
import type Option from "@/types/Option";

interface Props {
  defaultOption: string;
  options: Option[];
  disabled?: boolean;
  name: string;
}

const props = defineProps<Props>();

const model = defineModel<string | null>({ default: null });
</script>

<template>
  <select
    :name="props.name"
    class="peer h-10 w-72 appearance-none rounded-[4px] bg-transparent px-2 text-pasadita-blue-0 accent-pasadita-blue-3 ring-1 ring-pasadita-shade-2 transition-all placeholder:text-transparent hover:bg-pasadita-blue-5 focus:bg-pasadita-blue-4 focus:outline-none focus:ring-pasadita-blue-3 disabled:opacity-40"
    v-model="model"
    :disabled="props.disabled"
  >
    <option disabled selected value="null">{{ props.defaultOption }}</option>
    <option v-for="option in props.options" :key="option.value" :value="option.value">
      {{ option.text }}
    </option>
  </select>
</template>
