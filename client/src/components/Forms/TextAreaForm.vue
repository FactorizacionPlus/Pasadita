<script setup lang="ts">
import { defineEmits, withDefaults, defineProps, onMounted } from "vue";
import toSlug from "@/utils/toSlug";

const props = withDefaults(
  defineProps<{
    title: string;
    placeholder?: string;
    disabled?: boolean;
    value?: string;
  }>(),
  {
    placeholder: "",
    disabled: false,
    value: ""
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
  <div class="bg-white rounded-lg flex-1">
    <div class="relative bg-inherit">
      <textarea
        :id="titleSlug"
        :name="titleSlug"
        class="peer pt-2 bg-transparent h-24 w-full rounded-[4px] hover:bg-pasadita-blue-5 focus:bg-pasadita-blue-4 transition-all text-pasadita-blue-0 placeholder-transparent ring-1 px-2 ring-pasadita-shade-1 focus:ring-pasadita-blue-3 focus:outline-none disabled:opacity-40"
        :placeholder="props.placeholder"
        :disabled="props.disabled"
        :value="props.value"
        @input="handleChange"
      ></textarea>
      <label
        :for="titleSlug"
        class="absolute cursor-text left-0 -top-3 text-sm text-pasadita-shade-1 bg-inherit mx-1 px-1 peer-placeholder-shown:text-base peer-placeholder-shown:text-pasadita-shade-0 peer-disabled:opacity-40 peer-placeholder-shown:top-2 peer-focus:-top-3 peer-focus:text-pasadita-blue-3 peer-focus:text-sm transition-all"
      >
        {{ props.title }}
      </label>
    </div>
  </div>
</template>
