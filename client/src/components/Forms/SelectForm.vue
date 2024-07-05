<script setup lang="ts">
import { defineProps, ref } from "vue";
import type Option from "@/types/Option";
import type Alert from "@/types/Alert";
import type InputProps from "@/types/utils/InputProps";
import { AlertType } from "@/types/Alert";
import SimpleAlert from "../SimpleAlert.vue";

interface Props extends InputProps {
  defaultOption: string;
  options: Option[];
}

const props = defineProps<Props>();

const model = defineModel<string>({ default: "" });

const alertRef = ref<Alert>();

function setAlert(alert: Alert) {
  alertRef.value = alert;
}
function handleInput() {
  requestAnimationFrame(() => {
    alertRef.value = undefined;
  });
}

defineExpose({ setAlert, props });
</script>

<template>
  <div class="rounded-lg bg-white">
    <select
      :name="props.name"
      class="peer h-10 w-full appearance-none rounded-[4px] bg-transparent px-2 text-pasadita-blue-0 accent-pasadita-blue-3 ring-1 ring-pasadita-shade-2 transition-all placeholder:text-transparent hover:bg-pasadita-blue-5 focus:bg-pasadita-blue-4 focus:outline-none focus:ring-pasadita-blue-3 disabled:opacity-40"
      @input="handleInput"
      v-model="model"
      :disabled="props.disabled"
    >
      <option disabled value="">{{ props.defaultOption }}</option>
      <option v-for="option in props.options" :key="option.value" :value="option.value">
        {{ option.text }}
      </option>
    </select>

    <SimpleAlert
      v-if="props.label"
      class="mt-2"
      :alert="{ type: AlertType.INFO, message: props.label }"
    />
    <SimpleAlert v-if="alertRef" class="mt-2" :alert="alertRef" />
  </div>
</template>
