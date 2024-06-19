<template>
  <main class="flex size-full flex-col items-center justify-center gap-20">
    <div class="mx-auto min-h-[32rem]">
      <QRCodeStyling :data="token" />
    </div>
    <button
      class="mx-auto rounded-lg bg-pasadita-blue-4 p-3 text-pasadita-blue-2"
      @click="getQRToken"
    >
      Generate QR
    </button>
  </main>
</template>

<script setup lang="ts">
import type QRCodeStyling from "@/components/QRCodeStyling.vue";
import { ref } from "vue";

const userAuthToken =
  "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbXl1a2k0QGdtYWlsLmNvbSIsImlhdCI6MTcxNzQ2NTE0NSwiZXhwIjoxNzE4NzYxMTQ1fQ.JWd6j_YJg1dEP9sornZfkTnBOtAtRsZ2hMmODVQ-QDZZTQsp7zmD92uwTXhZjGlF82PxyuHspwYJFncgAZu8LA";

const token = ref("");

async function getQRToken() {
  const response = await fetch("http://localhost:8080/api/access/generate/", {
    headers: { Authorization: "Bearer " + userAuthToken },
  });

  if (response.ok) {
    const json = await response.json();
    token.value = json.data;
  }
}
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
