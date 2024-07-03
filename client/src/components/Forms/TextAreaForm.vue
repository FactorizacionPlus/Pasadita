<script setup lang="ts">
import { defineEmits, withDefaults, defineProps, onMounted } from "vue";
import toSlug from "@/utils/toSlug";

const props = withDefaults(
  defineProps<{
    title: string;
    placeholder?: string;
    disabled?: boolean;
    name: string;
    value?: string;
  }>(),
  {
    placeholder: "",
    disabled: false,
    value: "",
  }
);

const titleSlug = toSlug(props.title || "");
const emitValue = defineEmits(["update:value"]);

const handleChange = (event: Event) => {
  const textareaElement = event.target as HTMLTextAreaElement;
  emitValue("update:value", textareaElement.value);
};

onMounted(() => {
  emitValue("update:value", props.value);
});
</script>

<template>
  <div class="flex-1 rounded-lg bg-white">
    <div class="relative bg-inherit">
      <textarea
        :id="titleSlug"
        :name="props.name"
        class="peer h-24 min-h-24 w-full rounded-md border-b-2 border-b-shades-300 bg-transparent px-2 pt-2 text-base text-blue-500 ring-1 ring-shades-400 transition-all placeholder:text-transparent hover:bg-blue-100 focus:bg-blue-100 focus:outline-none focus:ring-blue-300 disabled:opacity-40"
        :placeholder="props.placeholder"
        :disabled="props.disabled"
        :value="props.value"
        @input="handleChange"
      ></textarea>
      <label
        :for="titleSlug"
        class="pointer-events-none absolute -top-3 left-0 mx-1 cursor-text bg-inherit px-1 text-xs font-medium text-blue-500 transition-all peer-placeholder-shown:top-1 peer-placeholder-shown:text-base peer-placeholder-shown:font-normal peer-placeholder-shown:text-shades-400 peer-focus:-top-3 peer-focus:text-xs peer-focus:text-blue-500 peer-disabled:opacity-40"
      >
        {{ props.title }}
      </label>
    </div>
  </div>
</template>
