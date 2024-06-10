<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/Guest/PermissionActive.vue";
import IconComponent from "@/assets/Passaport.svg?component"
const modal = ref<typeof Modal>();
</script>

<template>
  <div class="flex h-screen items-center justify-center">
    <button
      type="button"
      class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2.5 text-center text-sm font-semibold text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-2 hover:text-white active:scale-95"
      @click="modal?.show()"
    >
      <div class="flex flex-col items-center justify-center gap-2 p-5">
        <VueFeather type="clock" size="125" stroke-width="1.5" />
        <span class="text-xl">Ver Permiso Activo</span>
      </div>
    </button>
    <!--<IconComponent class="text-pasadita-blue-2 hover:text-red-950"/>-->
  </div>
  <Modal ref="modal"> Hola </Modal>
</template>
