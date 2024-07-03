<template>
  <div class="flex flex-col gap-1">
    <p class="text-sm font-medium leading-none text-blue-500">{{ dic.SELECT_TYPE }}</p>
    <div class="grid w-full grid-cols-2 gap-2">
      <button
        v-for="(item, index) in identityTypes"
        :key="index"
        type="button"
        class="group grid h-32 place-items-center rounded-lg border border-blue-200 transition-all active:scale-95"
        :class="
          selection != item.type
            ? 'bg-white hover:bg-shades-100 text-blue-400'
            : 'bg-blue-400 text-white'
        "
        @click="handleSelection(item.type)"
      >
        <component
          class="transition-transform duration-500 group-hover:scale-105"
          :is="item.icon"
        />
      </button>
    </div>
  </div>
</template>
<script setup lang="ts">
enum dic {
  SELECT_TYPE = "Seleccione el tipo de identificación",
}

interface identityTypeProps {
  type: identifierType;
  icon: Component;
}

const identityTypes: identityTypeProps[] = [
  {
    type: "DUI",
    icon: Identity,
  },
  {
    type: "PASSPORT",
    icon: Passport,
  },
];
const emit = defineEmits(["identityType"]);
const props = defineProps<{ identityType?: identifierType }>();
const selection = ref<identifierType>(
  props.identityType != undefined ? props.identityType : identityTypes[0].type
);
import Passport from "@/Passport.svg?component";
import Identity from "@/Identity.svg?component";
import { onMounted, ref, type Component } from "vue";
import type { identifierType } from "@/types/IdentifierType";

function handleSelection(type: identifierType) {
  selection.value = type;
  emit("identityType", selection.value);
}

onMounted(() => {
  emit("identityType", selection.value);
});
</script>
