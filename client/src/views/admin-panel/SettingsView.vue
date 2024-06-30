<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";

enum dic {
  TITLE = "Configuraciones",
}

</script>
<template>
    <CurrentPageInfo :title="dic.TITLE" icon="settings" />
</template>
  