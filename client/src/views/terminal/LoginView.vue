<script setup lang="ts">
import type Options from "@/types/Option";
import { onMounted, ref, type Ref } from "vue";
import { useBaseFetch } from "@/composables/useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";
import { useTerminal } from "@/stores/terminal";
import { AlertType } from "@/types/Alert";
import type { ErrorMap } from "@/types/utils/ErrorMap";
import {
  setValidationErrorForm,
  type AnyInputFormType,
  type InputFormType,
  type SelectFormType,
} from "@/utils/formValidation";
import SimpleAlert from "@/components/SimpleAlert.vue";
import { useRouter } from "vue-router";
import type { TerminalType } from "@/types/TerminalType";
import InputForm from "@/components/Forms/InputForm.vue";

interface TerminalOptions extends Options {
  value: TerminalType;
}
const APPLICATION = "PASADITA";
enum Message {
  EMPTY = "",
  LOADING_LOGIN = "Iniciando sesión...",
  ERROR_AUTH = "Inicio de sesión incorrecto",
  ERROR = "Ocurrió un error en el sistema, por favor intente más tarde",
}
const message = ref<Message>(Message.EMPTY);

const alertType: { [key in Message]: AlertType } = {
  [Message.EMPTY]: AlertType.INFO,
  [Message.LOADING_LOGIN]: AlertType.INFO,
  [Message.ERROR]: AlertType.ERROR,
  [Message.ERROR_AUTH]: AlertType.ERROR,
};

const formData = ref<{
  terminalType: TerminalType | undefined;
  password: string;
}>({
  terminalType: undefined,
  password: "",
});

const terminal = useTerminal();
const router = useRouter();

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

const terminalSelect = ref<SelectFormType>();
const passwordInput = ref<InputFormType>();
const inputMap = new Map<string, Ref<AnyInputFormType | undefined>>();

onMounted(() => {
  inputMap.set(terminalSelect.value!.props.name, terminalSelect);
  inputMap.set(passwordInput.value!.props.name, passwordInput);
});

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
    const errorMap = data.value.data as unknown as ErrorMap;
    setValidationErrorForm(inputMap, errorMap);
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

  if (terminal.terminalLogin != null) {
    router.push("/terminal/");
  }
}
</script>

<template>
  <div class="flex h-full flex-col items-center justify-center">
    <form
      @submit.prevent="handleSubmit"
      @input="message = Message.EMPTY"
      autocomplete="on"
      class="flex w-full flex-col items-stretch gap-5 bg-white p-6 md:w-[548px]"
    >
      <div class="flex flex-col items-center">
        <img
          src="/identity/pasaditaLogoMin.svg"
          alt="Pasadita logo"
          class="w-[360px] object-contain text-blue-400"
        />
        <h1 class="text-[70px] font-bold uppercase text-blue-400">{{ APPLICATION }}</h1>
      </div>
      <SelectForm
        ref="terminalSelect"
        name="terminalType"
        defaultOption="Seleccione la Terminal"
        title="Terminales"
        :options="options"
        v-model="formData.terminalType"
      />
      <InputForm
        ref="passwordInput"
        name="password"
        :alert="{ type: AlertType.INFO, message: 'Juan' }"
        title="Contraseña"
        type="password"
        placeholder="Contraseña"
        v-model="formData.password"
      />

      <SimpleAlert
        v-if="message != Message.EMPTY"
        class="mt-2"
        :alert="{ type: alertType[message], message }"
      />

      <button type="submit" class="self-end rounded-lg bg-blue-100 px-4 py-2 text-blue-400">
        Iniciar sesión
      </button>
    </form>
  </div>
</template>
