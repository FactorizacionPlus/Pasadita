<template>
  <h1>QR Code generation</h1>
  <div v-if="token">
    <QRCodeStyling :data="token" />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import QRCodeStyling from "../components/QRCodeStyling.vue";

const userAuthToken =
  "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyaXZlcmFzb3RvLm1hcmNlbG8uYmFja3VwQGdtYWlsLmNvbSIsImlhdCI6MTcxNzM5MzE5NCwiZXhwIjoxNzE4Njg5MTk0fQ.IfwJL5pjKPfJ4MqzX4wcgxkqUYCwrWVoPZgW28pbYHdVTR1ylGQrxi0jVqTAtNien8AUPck9FdCH_AlV7E1eiw";

const token = ref("");

onMounted(async () => {
  const response = await fetch("http://localhost:8080/api/access/generate/", {
    headers: { Authorization: "Bearer " + userAuthToken },
  });

  if (response.ok) {
    const json = await response.json();
    token.value = json.data;
  }
});
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
