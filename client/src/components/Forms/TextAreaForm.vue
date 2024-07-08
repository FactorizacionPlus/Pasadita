<script setup lang="ts">
import { defineProps, ref } from "vue";
import toSlug from "@/utils/toSlug";
import type Alert from "@/types/Alert";
import SimpleAlert from "../SimpleAlert.vue";
import type InputProps from "@/types/utils/InputProps";

interface Props extends InputProps {
  alert?: Alert;
}

const props = defineProps<Props>();
const model = defineModel<string>();

const titleSlug = toSlug(props.title || "");

const alertRef = ref<Alert | undefined>(); // Modified here

function setAlert(alert: Alert) {
  alertRef.value = alert;
}

const emitValue = defineEmits(["update:value"]);

function handleInput(event: Event) {
  alertRef.value = undefined;
  const inputElement = event.target as HTMLInputElement;
  emitValue("update:value", inputElement.value);
}

defineExpose({ setAlert, props });
</script>

<template>
  <div class="rounded-lg bg-white">
    <div class="relative bg-inherit">
      <textarea
        :id="titleSlug"
        :name="props.name"
        class="peer h-10 min-h-24 w-full rounded-md border-b-2 border-b-shades-300 bg-transparent px-2 pt-2 text-base text-blue-500 ring-1 ring-shades-400 transition-all placeholder:text-transparent hover:bg-blue-100 focus:bg-blue-100 focus:outline-none focus:ring-blue-300 disabled:opacity-40"
        :placeholder="props.name"
        :disabled="props.disabled"
        @input="handleInput"
        v-model="model"
      />
      <span
        :for="titleSlug"
        class="pointer-events-none absolute -top-3 left-0 mx-1 cursor-text bg-inherit px-1 text-xs font-medium text-blue-500 transition-all peer-placeholder-shown:top-2 peer-placeholder-shown:text-base peer-placeholder-shown:font-normal peer-placeholder-shown:text-shades-400 peer-focus:-top-3 peer-focus:text-xs peer-focus:text-blue-500 peer-disabled:opacity-40"
      >
        {{ props.title }}
      </span>
    </div>
    <SimpleAlert v-if="props.alert" class="mt-2" :alert="props.alert" />
    <SimpleAlert v-if="alertRef" class="mt-2" :alert="alertRef" />
  </div>
</template>
