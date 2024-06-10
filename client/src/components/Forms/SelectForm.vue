<script setup lang="ts">
import { defineEmits, defineProps, onMounted, ref } from "vue";
import type Option from "@/types/Option";

interface Props {
  defaultOption: string;
  options: Option[];
  currentIndex?: number;
  disabled?: boolean;
  name: string;
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
  }
});
</script>

<template>
  <select
    :name="props.name"
    class="peer h-10 w-72 rounded-[4px] bg-transparent px-2 text-pasadita-blue-0 accent-pasadita-blue-3 ring-1 ring-pasadita-shade-2 transition-all placeholder:text-transparent hover:bg-pasadita-blue-5 focus:bg-pasadita-blue-4 focus:outline-none focus:ring-pasadita-blue-3 disabled:opacity-40"
    @change="handleChange"
    :disabled="props.disabled"
  >
    <option disabled selected>{{ props.defaultOption }}</option>
    <option
      v-for="(option, index) in props.options"
      :key="index"
      :value="option.value"
      :selected="index === props.currentIndex"
    >
      {{ option.text }}
    </option>
  </select>
</template>
