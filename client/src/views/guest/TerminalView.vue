<script setup lang="ts">
import QRCodeReader from "@/components/QRCodeReader.vue";
import { ref, watch } from "vue";

enum Message {
  WAITING = "Esperando QR...",
  VALIDATING = "Validando QR...",
  ERROR = "Hubo un error al validar el token",
  CORRECT = "Lláve válida, acceso permitido",
  INCORRECT = "Lláve inválida, acceso denegado",
}

const message = ref<Message>(Message.WAITING);
const token = ref("");

async function validateToken(tokenContent: string) {
  message.value = Message.VALIDATING;

  let response = null;
  try {
    response = await fetch(import.meta.env.VITE_SERVER_ENDPOINT + "/api/access/validate", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        terminalType: "DOOR",
        password: "Usuario123.",
        tokenContent: tokenContent,
      }),
    });
  } catch (err) {
    console.error(err);
    message.value = Message.ERROR;
  }

  if (response) {
    if (response.ok) {
      message.value = Message.CORRECT;
    } else {
      message.value = Message.INCORRECT;
    }
  }

  setTimeout(() => {
    message.value = Message.WAITING;
    token.value = "";
  }, 5000);
}

watch(token, (value) => {
  if (message.value == Message.WAITING) {
    validateToken(value);
  }
});
</script>

<template>
  <main class="flex size-full flex-col items-center justify-center gap-28">
    <QRCodeReader @update="(v) => (token = v)" />
    <h1 class="font-medium lg:text-6xl">{{ message }}</h1>
  </main>
</template>
