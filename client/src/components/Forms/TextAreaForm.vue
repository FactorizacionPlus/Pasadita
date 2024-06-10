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
        class="peer h-24 w-full rounded-[4px] bg-transparent px-2 pt-2 text-pasadita-blue-0 ring-1 ring-pasadita-shade-2 transition-all placeholder:text-transparent hover:bg-pasadita-blue-5 focus:bg-pasadita-blue-4 focus:outline-none focus:ring-pasadita-blue-3 disabled:opacity-40"
        :placeholder="props.placeholder"
        :disabled="props.disabled"
        :value="props.value"
        @input="handleChange"
      ></textarea>
      <label
        :for="titleSlug"
        class="absolute -top-3 left-0 mx-1 cursor-text bg-inherit px-1 text-sm text-pasadita-shade-1 transition-all peer-placeholder-shown:top-2 peer-placeholder-shown:text-base peer-placeholder-shown:text-pasadita-shade-0 peer-focus:-top-3 peer-focus:text-sm peer-focus:text-pasadita-blue-3 peer-disabled:opacity-40"
      >
        {{ props.title }}
      </label>
    </div>
  </div>
</template>
