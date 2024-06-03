<template>
  <h1>QR Code generation</h1>
  <div v-if="token">
    <QRCodeStyling :data="token" />
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import QRCodeStyling from "../components/QRCodeStyling.vue";

const userAuthToken =
  "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyaXZlcmFzb3RvLm1hcmNlbG8uYmFja3VwQGdtYWlsLmNvbSIsImlhdCI6MTcxNzMyMjY1NSwiZXhwIjoxNzE4NjE4NjU1fQ.M6ogKuKQ70Ri_KA52LWSWAX763ljaO-ErsryZwZCEG_s0YQxaTGyqRWCxbNQ4Y3JKHRD6KuUM03BdL7BIxZ8Tg";

const token = ref("");

const response = await fetch("http://localhost:8080/api/access/generate/", {
  headers: { Authorization: "Bearer " + userAuthToken },
});

if (response.ok) {
  const json = await response.json();
  token.value = json.data;
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
