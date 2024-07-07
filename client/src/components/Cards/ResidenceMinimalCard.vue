<script setup lang="ts">
import type Residence from "@/types/Residence";
import VueFeather from "vue-feather";

const props = defineProps<{
  residence: Residence;
}>();

enum Message {
  SINGULAR = "Residencia",
  ENTRIES_QUANTITY = "Cantidad de Entradas: ",
  DESCRIPTION = "Descripción",
  PEOPLE = "Habitantes",
}
</script>

<template>
  <div class="flex flex-col rounded-lg">
    <div class="flex flex-col gap-1 p-2">
      <h2 class="text-base font-medium text-blue-500">{{ Message.SINGULAR }}</h2>
      <div class="flex flex-col gap-2">
        <div class="flex items-center gap-2 text-blue-400">
          <VueFeather type="home" class="size-8 min-w-8" stroke-width="1.5"></VueFeather>
          <p class="text-base font-normal">
            {{ props.residence.habitants }} / {{ props.residence.maxHabitants }}
            {{ Message.PEOPLE }}
          </p>
        </div>
        <p>
          {{ props.residence.description }}
        </p>
      </div>
    </div>
  </div>
</template>
