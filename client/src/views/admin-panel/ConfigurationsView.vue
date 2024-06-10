<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import ConfigContainer from "@/components/ConfigContainer.vue";
</script>

<template>
  <CurrentPageInfo title="Configuraciones" icon="settings" />
  <div class="grid grid-cols-2 gap-2">
    <ConfigContainer icon="sliders" title="Preferencias"> </ConfigContainer>
    <ConfigContainer icon="tool" title="Accesibilidad"> </ConfigContainer>
  </div>
</template>
