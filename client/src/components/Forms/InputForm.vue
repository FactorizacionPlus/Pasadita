<script setup lang="ts">
import { defineEmits, defineProps, onMounted } from "vue";
import toSlug from "@/utils/toSlug";
import type Alert from "@/types/Alert";
import SimpleAlert from "../SimpleAlert.vue";

type InputType =
  | "text"
  | "number"
  | "date"
  | "datetime-local"
  | "email"
  | "month"
  | "password"
  | "radio"
  | "range"
  | "search"
  | "tel"
  | "time"
  | "url"
  | "week";

interface Props {
  title?: string;
  placeholder?: string;
  type?: InputType;
  disabled?: boolean;
  name?: string;
  value?: string;
  alert?: Alert;
}

const props = defineProps<Props>();

const titleSlug = toSlug(props.title || "");
const emitValue = defineEmits(["update:value"]);

const handleChange = (event: Event) => {
  const inputElement = event.target as HTMLInputElement;
  console.log("change");
  emitValue("update:value", inputElement.value);
};

onMounted(() => {
  emitValue("update:value", props.value);
});
</script>

<template>
  <div class="rounded-lg bg-white">
    <div class="relative bg-inherit">
      <input
        :type="props.type"
        :id="titleSlug"
        :name="props.name"
        class="peer h-10 w-full rounded-md border-b-2 border-b-shades-300 bg-transparent px-2 text-base text-blue-500 ring-1 ring-shades-400 transition-all placeholder:text-transparent hover:bg-blue-100 focus:bg-blue-100 focus:outline-none focus:ring-blue-300 disabled:opacity-40"
        :placeholder="props.name"
        :disabled="props.disabled"
        :value="props.value"
        @input="handleChange"
      />
      <span
        :for="titleSlug"
        class="pointer-events-none absolute -top-3 left-0 mx-1 cursor-text bg-inherit px-1 text-xs font-medium text-blue-500 transition-all peer-placeholder-shown:top-2 peer-placeholder-shown:text-base peer-placeholder-shown:font-normal peer-placeholder-shown:text-shades-400 peer-focus:-top-3 peer-focus:text-xs peer-focus:text-blue-500 peer-disabled:opacity-40"
      >
        {{ props.title }}
      </span>
    </div>
    <SimpleAlert v-if="props.alert !== undefined" class="mt-2" :alert="props.alert" />
  </div>
</template>
