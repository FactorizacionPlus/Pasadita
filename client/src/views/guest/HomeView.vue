<script setup lang="ts">
import { ref } from "vue";
import ModalActive from "@/components/Modal/Guest/PermissionActive.vue";
import ModalNoActive from "@/components/Modal/Guest/NoActivePermission.vue";
import ModalAdd from "@/components/Modal/Guest/AddGuest.vue";
import SquareButton from "@/components/SquareButton.vue";
const modalActive = ref<typeof ModalActive>();
const modalNoActive = ref<typeof ModalNoActive>();
const modalAdd = ref<typeof ModalAdd>();
</script>

<template>
  <div class="flex h-screen items-center justify-center">
    <SquareButton
      type="button"
      icon="custom-days-clock"
      title="Ver Permiso Activo"
      @click="modalActive?.show()"
    />
  </div>
  <ModalActive ref="modalActive" />
  <ModalNoActive ref="modalNoActive" />
  <ModalAdd ref="modalAdd" />
</template>
