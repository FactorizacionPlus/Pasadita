<script setup lang="ts">
import QRCodeReader from "@/components/QRCodeReader.vue";
import { ref } from "vue";

const message = ref("Muestra la llave QR");

async function validateToken(token: string) {
  message.value = "Validando token...";

  let response = null;
  try {
    response = await fetch("http://localhost:8080/api/access/validate/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        terminalType: "DOOR",
        password: "Usuario123.",
        tokenContent: token,
      }),
    });
  } catch (err) {
    console.error(err);
    message.value = "Hubo un error al validar el token";
    return;
  }

  if (response.ok) {
    message.value = "Llave válida, acceso permitido";
  } else {
    message.value = "Llave inválida, acceso denegado";
  }
}
</script>

<template>
  <main>
    <span>{{ message }}</span>
    <QRCodeReader @update="(v) => validateToken(v)" />
  </main>
</template>
