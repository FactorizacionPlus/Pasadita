<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";
import ModalActive from "@/components/Modal/Guest/PermissionActive.vue";
import ModalNoActive from "@/components/Modal/Guest/NoActivePermission.vue"
import ModalAdd from "@/components/Modal/Guest/AddGuest.vue"
const modalActive = ref<typeof ModalActive>();
const modalNoActive = ref<typeof ModalNoActive>();
const modalAdd = ref<typeof ModalAdd>();
</script>

<template>
  <div class="flex h-screen items-center justify-center">
    <button type="button"
      class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2.5 text-center text-sm font-semibold text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-2 hover:text-white active:scale-95"
      @click="modalActive?.show()">
      <div class="flex flex-col items-center justify-center gap-2 p-5">
        <VueFeather type="clock" size="125" stroke-width="1.5" />
        <span class="text-xl">Ver Permiso Activo</span>
      </div>
    </button>
  </div>
  <ModalActive ref="modalActive">Hola</ModalActive>
  <ModalNoActive ref="modalNoActive">Hola</ModalNoActive>
  <ModalAdd ref="modalAdd">Hola</ModalAdd>
</template>
