<script setup lang="ts">
import type Options from "@/types/Option";
import Pasadita from "@/assets/PasaditaLogoV2.svg?url";
import InputForm from "@/components/Forms/InputForm.vue";
import SelectForm from "@/components/Forms/SelectForm.vue";
import type { TerminalType } from "@/types/TerminalType";
import { ref } from "vue";
import { useBaseFetch } from "@/composables/useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";
import { useTerminal } from "@/stores/terminal";

interface TerminalOptions extends Options {
  value: TerminalType;
}

enum Message {
  EMPTY = "",
  LOADING_LOGIN = "Iniciando sesión...",
  LOADING_USER = "Cargando datos de usuario...",
  NO_USER = "No existe un usuario para esta cuenta",
  ERROR_AUTH = "Inicio de sesión incorrecto",
  ERROR_USER = "No se pudo obtener los datos de este usuario, por favor intente más tarde",
  ERROR = "Ocurrió un error en el sistema, por favor intente más tarde",
}
const message = ref<Message>(Message.EMPTY);

const formData = ref<{
  terminalType: TerminalType | null;
  password: string;
}>({
  terminalType: null,
  password: "",
});

const terminal = useTerminal();

const options: TerminalOptions[] = [
  {
    text: "Vigilante",
    value: "MANUAL",
  },
  {
    text: "Entrada Vehicular",
    value: "BARRIER",
  },
  {
    text: "Entrada Peatonal",
    value: "DOOR",
  },
];

async function doLogin() {
  message.value = Message.LOADING_LOGIN;
  const { data, statusCode } = await useBaseFetch("/auth/login/terminal")
    .json<GeneralResponse<string>>()
    .post(formData.value);

  if (!data.value) {
    message.value = Message.ERROR;
    return;
  }

  if (statusCode.value == 400) {
    message.value = Message.EMPTY;
    // const errorMap = data.value.data as unknown as ErrorMap;
    // setValidationErrorForm(inputMap, errorMap);
    return;
  }

  if (statusCode.value == 404) {
    message.value = Message.NO_USER;
    return;
  }
  if (statusCode.value == 401) {
    message.value = Message.ERROR_AUTH;
    return;
  }
  if (!data.value.ok) {
    message.value = Message.ERROR;
    return;
  }

  terminal.setTerminalLogin({
    type: formData.value.terminalType!,
    password: formData.value.password,
  });
}

async function handleSubmit() {
  await doLogin();
}
</script>

<template>
  <div class="flex h-screen flex-col items-center justify-center gap-5">
    <img :src="Pasadita" class="size-72" />
    <form @submit.prevent="handleSubmit" autocomplete="on" class="flex flex-col gap-5">
      <SelectForm
        name="terminal-type"
        defaultOption="Seleccione la Terminal"
        title="Terminales"
        :options="options"
        v-model="formData.terminalType"
      />
      <InputForm
        name="password"
        title="Contraseña"
        type="password"
        placeholder="Contraseña"
        v-model="formData.password"
      />

      <span v-if="message != Message.EMPTY">{{ message }}</span>

      <button type="submit">Ingresar</button>
    </form>
  </div>
</template>
