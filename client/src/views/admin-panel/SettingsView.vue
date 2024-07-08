<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import { usePreferences } from "@/stores/preferences";
import VueFeather from "vue-feather";
import SwitchForm from "@/components/Forms/SwitchForm.vue";
import { computed, onMounted, ref, watch } from "vue";
import type Option from "@/types/Option";
import type Configuration from "@/types/Configuration";
import SelectForm from "@/components/Forms/SelectForm.vue";
import InputForm from "@/components/Forms/InputForm.vue";
import {
  getConfigurations,
  resetConfigurations,
  updateConfigurations,
} from "@/composables/useConfigurations";

enum Message {
  TITLE = "Configuraciones",
}
const preferences = usePreferences();

watch(preferences.preferences, (v) => {
  preferences.setPreferences(v);
});

const config = ref<Configuration>();

onMounted(async () => {
  const { data } = await getConfigurations();
  const response = data.value;
  if (response?.data != null) {
    config.value = response.data;
  }
});
const rest = ref<string>("");
async function saveConfigurations() {
  if (config.value != undefined) {
    const { data } = await updateConfigurations(config.value);
    const response = data.value;
    if (response?.message != undefined) {
      rest.value = response.message;
    }
  }
  console.log(rest.value);
}

async function resetConfigurationsF() {
  await resetConfigurations();
}

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
  <CurrentPageInfo :title="Message.TITLE" icon="settings" />
  <div class="flex flex-col gap-7">
    <div class="flex flex-col rounded-[4px] border border-blue-200 bg-white">
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
    <div class="flex flex-col rounded-[4px] border border-blue-200 bg-white" v-if="config">
      <div class="flex w-full items-center justify-between rounded-t-[4px] bg-blue-100 px-1">
        <div class="flex items-center gap-1">
          <VueFeather type="clock" stroke-width="2" class="size-[18px] text-blue-400"></VueFeather>
          <p class="text-xl text-blue-400">Tiempo</p>
        </div>
        <div class="my-2 flex items-center gap-1">
          <button
            @click="saveConfigurations"
            class="rounded-lg bg-green-300 p-1 text-sm text-green-400 transition-all active:scale-95"
          >
            <span>Guardar cambios</span>
          </button>
          <button
            @click="resetConfigurationsF"
            class="flex items-center gap-1 rounded-lg bg-yellow-300 px-4 py-1 text-sm text-yellow-400 transition-all active:scale-95"
          >
            <VueFeather type="repeat" stroke-width="3" class="size-3 text-yellow-400" />
            <span>Reset</span>
          </button>
        </div>
      </div>
      <div class="grid grid-cols-2 gap-4 p-4">
        <div class="flex w-full justify-between">
          <p>Tiempo de gracia (en segundos)</p>
          <InputForm name="timeFrame" type="number" v-model="config.timeframe" />
        </div>
        <div class="flex w-full justify-between">
          <p>Expiración de qr (en segundos)</p>
          <InputForm name="qrExpiration" type="number" v-model="config.qrExpiration" />
        </div>
      </div>
    </div>
  </div>
</template>
