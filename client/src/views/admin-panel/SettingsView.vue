<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";

enum Message {
  TITLE = "Configuraciones",
}
</script>
<template>
  <CurrentPageInfo :title="Message.TITLE" icon="settings" />
</template>
