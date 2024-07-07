<script setup lang="ts">
import { usePreferences } from "@/stores/preferences";
import CardsContainer from "@/components/CardsContainer.vue";
import VueFeather from "vue-feather";
import SwitchForm from "@/components/Forms/SwitchForm.vue";
import { watch } from "vue";
import type Option from "@/types/Option";
import SelectForm from "@/components/Forms/SelectForm.vue";

const preferences = usePreferences();

watch(preferences.preferences, (v) => {
  preferences.setPreferences(v);
});

const optionsSelect: Option[] = [
  {
    value: "BIG",
    text: "Grande",
  },
  {
    value: "NORMAL",
    text: "Normal",
  },
  {
    value: "SMALL",
    text: "Pequeño",
  },
];
</script>
<template>
  <CardsContainer icon="settings" title="Configuraciones">
    <div class="flex flex-col rounded-[4px] border border-blue-200">
      <div class="flex w-full items-center gap-1 rounded-t-[4px] bg-blue-100 px-1">
        <VueFeather type="tool" stroke-width="2" class="size-[18px] text-blue-400"></VueFeather>
        <p class="text-xl text-blue-400">Accesibilidad</p>
      </div>
      <div class="grid grid-cols-2 gap-4 p-4">
        <div class="flex w-full justify-between">
          <p>Alto contraste</p>
          <SwitchForm
            name="highContrast"
            :disabled="false"
            v-model="preferences.preferences.highContrast"
          />
        </div>
        <div class="flex w-full justify-between">
          <p>Fuente para dislexia</p>
          <SwitchForm
            name="dyslexicFont"
            :disabled="false"
            v-model="preferences.preferences.dyslexicFont"
          />
        </div>
        <div class="flex w-full justify-between">
          <p>Tamaño de fuente</p>
          <SelectForm
            name="fontSize"
            default-option="Selecciona una opcion"
            :options="optionsSelect"
            v-model="preferences.preferences.fontSize"
          />
        </div>
      </div>
    </div>
  </CardsContainer>
</template>
