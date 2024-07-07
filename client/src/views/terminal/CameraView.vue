<script setup lang="ts">
import VueFeather from "vue-feather";
import QRCodeReader from "@/components/QRCodeReader.vue";
import { ref, watch } from "vue";
import { useBaseFetch } from "@/composables/useBaseFetch";
import type TerminalLogin from "@/types/TerminalLogin";
import { useTerminal } from "@/stores/terminal";
import CameraPreferences from "@/components/Modal/Terminal/CameraPreferences.vue";

enum Message {
  NOT_ALLOWED = "No se han habilitado los permisos de acceso a cámara, por favor habilite los permisos.",

  WAITING = "Esperando por llave QR",
  VALIDATING = "Validando llave QR...",

  ERROR = "Hubo un error al validar el token",
  ERROR_INTERNAL = "Ocurrió un error con el lector QR",

  CORRECT = "Lláve válida, acceso permitido",
  INCORRECT = "Lláve inválida, acceso denegado",
}

const message = ref<Message>(Message.WAITING);
const token = ref("");

const terminal = useTerminal();

const qrCodeReader = ref<InstanceType<typeof QRCodeReader>>();
const preferencesModal = ref<InstanceType<typeof CameraPreferences>>();

const messageIcon: { [key in Message | "DEFAULT"]?: string } = {
  [Message.NOT_ALLOWED]: "alert-triangle",
  [Message.CORRECT]: "check",
  [Message.INCORRECT]: "x-circle",
  [Message.ERROR]: "x-circle",
  DEFAULT: "info",
};
const messageColor: { [key in Message | "DEFAULT"]?: string } = {
  [Message.NOT_ALLOWED]: "bg-yellow-100 border-yellow-200 text-yellow-400",
  [Message.CORRECT]: "bg-green-100 border-green-200 text-green-400",
  [Message.INCORRECT]: "bg-red-100 border-red-200 text-red-400",
  [Message.ERROR]: "bg-red-100 border-red-200 text-red-400",
  DEFAULT: "bg-white border-blue-300 text-blue-400",
};

interface ValidateToken extends TerminalLogin {
  tokenContent: string;
}

async function validateToken(tokenContent: string) {
  if (!terminal.terminalLogin) return;
  const login = terminal.terminalLogin;

  message.value = Message.VALIDATING;

  const validateToken: ValidateToken = {
    terminalType: login.terminalType,
    password: login.password,
    tokenContent,
  };
  const { response, error } = await useBaseFetch("/api/access/validate").post(validateToken);

  if (error.value) {
    message.value = Message.ERROR;
    return;
  }

  if (response.value) {
    if (response.value.ok) {
      message.value = Message.CORRECT;
    } else {
      message.value = Message.INCORRECT;
    }
  }

  console.log(error);

  setTimeout(() => {
    message.value = Message.WAITING;
    token.value = "";
  }, 5000);
}

function handleQrException(exception: DOMException) {
  if (exception.name == "NotAllowedError") {
    message.value = Message.NOT_ALLOWED;
  } else {
    console.error(exception);
    message.value = Message.ERROR_INTERNAL;
  }
}

watch(token, (value) => {
  if (message.value == Message.WAITING && value != "") {
    validateToken(value);
  }
});
</script>

<template>
  <main class="flex size-full flex-col items-center justify-center gap-4">
    <div
      class="relative aspect-square w-full overflow-hidden rounded-lg bg-blue-200 md:size-[512px]"
    >
      <QRCodeReader
        ref="qrCodeReader"
        @update="(v) => (token = v)"
        @exception="handleQrException"
        @init="message = Message.WAITING"
      />

      <button
        class="group absolute bottom-0 right-0 m-2 size-16 rounded-md bg-blue-300 p-2 text-blue-400 opacity-70 transition-opacity hover:opacity-100"
        @click="preferencesModal?.show()"
        v-if="message == Message.WAITING"
      >
        <VueFeather
          type="settings"
          class="size-full transition-transform duration-300 group-hover:rotate-90 group-hover:scale-105"
          stroke-width="1.5"
        />
      </button>

      <div
        class="absolute left-0 top-0 size-full bg-gray-600/50"
        v-if="message == Message.VALIDATING"
      ></div>

      <div class="absolute left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2 text-blue-300">
        <button
          class="p-8 transition-transform hover:scale-105"
          @click="qrCodeReader?.requestAccess()"
          v-if="message == Message.NOT_ALLOWED"
        >
          <VueFeather type="video-off" class="size-32" stroke-width="2" />
        </button>
        <VueFeather
          type="loader"
          animation="spin"
          class="size-32"
          stroke-width="2"
          v-if="message == Message.VALIDATING"
        />
      </div>
    </div>

    <h1
      class="flex w-[512px] flex-row items-center gap-1.5 rounded border p-2 font-rubik text-xl"
      :class="messageColor[message] ?? messageColor['DEFAULT']"
    >
      <VueFeather
        :type="messageIcon[message] ?? messageIcon['DEFAULT']"
        class="size-5 !overflow-visible"
        stroke-width="2"
      />
      <span>
        {{ message }}
      </span>
    </h1>

    <CameraPreferences
      ref="preferencesModal"
      :cameras="qrCodeReader?.cameras ?? []"
      :can-use-torch="qrCodeReader?.canUseTorch ?? false"
    />
  </main>
</template>
