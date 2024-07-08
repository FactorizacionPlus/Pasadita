<script setup lang="ts">
import SquareButton from "@/components/SquareButton.vue";
import ModalQR from "@/components/Modal/Resident/LlaveQR.vue";
import { ref, onMounted } from "vue";
import type Residence from "@/types/Residence";
import type { SquareButtonIconsType } from "@/types/SquareButtonIconsType";
import { getOwnResidence } from "@/composables/useResidence";
const residence = ref<Residence>();
const RESIDENT_ROOT = "/residente/";
const modalQR = ref<InstanceType<typeof ModalQR>>();

onMounted(async () => {
  await fetchResidence();
});

interface RouteProp {
  title: string;
  icon: SquareButtonIconsType;
  href: string;
}


async function fetchResidence() {
  const { data } = await getOwnResidence();
  const record = data.value;
  residence.value = record?.data;
  console.log(record);
}

const routes: RouteProp[] = [
  {
    title: "Mis Solicitudes",
    icon: "custom-eye-watch",
    href: "historial-solicitudes",
  },
];
</script>

<template>
  <section class="flex size-full items-center justify-center gap-4">
    <SquareButton type="link" :icon="item.icon" :title="item.title" :href="RESIDENT_ROOT + item.href" :key="index"
      v-for="(item, index) in routes" />
    <SquareButton type="button" icon="custom-phone-qr" title="Generar llave QR" @click="modalQR?.show()" />
  </section>
  <ModalQR v-if="residence" :residence="residence" ref="modalQR" />
</template>
