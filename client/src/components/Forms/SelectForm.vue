<script setup lang="ts">
import { defineEmits, defineProps, onMounted, ref } from "vue";
import type Option from "@/types/Option";

interface Props {
  defaultOption: string;
  options: Option[];
  currentIndex?: number;
  disabled?: boolean;
}

const props = defineProps<Props>();

const currentOption = ref<Option | null>(null);

const emitValue = defineEmits(["update:value"]);


const handleChange = (event: Event) => {
  const selectElement = event.target as HTMLSelectElement;
  const selectedOptionIndex = selectElement.selectedIndex;
  currentOption.value = props.options[selectedOptionIndex - 1];
  emitValue("update:value", currentOption.value);
};

onMounted(() => {
  if (props.currentIndex !== undefined && props.options) {
    emitValue("update:value", props.options[props.currentIndex]);
  }});
</script>

<template>
  <select
    class="peer accent-pasadita-blue-3 bg-wh h-10 rounded-[4px] hover:bg-pasadita-blue-5 focus:bg-pasadita-blue-4 transition-all text-pasadita-blue-0 placeholder-transparent ring-1 px-2 ring-pasadita-shade-1 focus:ring-pasadita-blue-3 focus:outline-none disabled:opacity-40"
    @change="handleChange"
    :disabled="props.disabled"
  >
    <option disabled selected>{{ props.defaultOption }}</option>
    <option v-for="(option, index) in props.options" :key="index" :value="option.value" :selected="index === props.currentIndex">
      {{ option.text }}
    </option>
  </select>
</template>
