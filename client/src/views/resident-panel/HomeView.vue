<script setup lang="ts">
import SquareButton from "@/components/SquareButton.vue";
import ModalAdd from "@/components/Modal/Resident/AddResidentModal.vue";
import type { SquareButtonIconsType } from "@/types/SquareButtonIconsType";
import { ref } from "vue";

const RESIDENT_ROOT = "/residente/";

interface RouteProp {
  title: string;
  icon: SquareButtonIconsType;
  href: string;
}

const modalAdd = ref<typeof ModalAdd>();

const routes: RouteProp[] = [
  {
    title: "Mis Solicitudes",
    icon: "custom-eye-watch",
    href: "historial-solicitudes",
  },
  {
    title: "Generar llave qr",
    icon: "custom-phone-qr",
    href: "historial-solicitudes",
  },
];
</script>

<template>
  <section class="flex size-full items-center justify-center gap-4">
    <SquareButton
      type="button"
      icon="custom-tablet-touch"
      title="Solicitar un permiso"
      @click="modalAdd?.show()"
    />
    <SquareButton
      type="link"
      :icon="item.icon"
      :title="item.title"
      :href="RESIDENT_ROOT + item.href"
      :key="index"
      v-for="(item, index) in routes"
    />

    <ModalAdd ref="modalAdd" />
  </section>
</template>
