<script setup lang="ts">
import { ref } from "vue";
import ModalEntry from "@/components/Modal/Terminal/CreateEntry.vue";
import SquareButton from "@/components/SquareButton.vue";
import { getRootPath } from "@/utils/viewRole";

const modalEntry = ref<InstanceType<typeof ModalEntry>>();

const rootPath = getRootPath();
</script>

<template>
  <div class="flex size-full items-center justify-center gap-4">
    <SquareButton
      type="button"
      icon="custom-entry"
      title="Añadir Entrada Anónima"
      @click="modalEntry?.show()"
    />

    <SquareButton
      type="link"
      icon="custom-phone-qr"
      title="Leer Llaves QR"
      :href="rootPath + '/camera'"
    />
  </div>

  <ModalEntry ref="modalEntry" />
</template>
