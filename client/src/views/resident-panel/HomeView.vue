<script setup lang="ts">
import SquareButton from "@/components/SquareButton.vue";
import ModalAdd from "@/components/Modal/Resident/AddResidentModal.vue";
import ModalQR from "@/components/Modal/Resident/LlaveQR.vue";
import { ref, onMounted } from "vue";
import type Residence from "@/types/Residence";
import type { SquareButtonIconsType } from "@/types/SquareButtonIconsType";
import { getOwnResidence } from "@/composables/useResidence";
import NoResidence from "@/components/Modal/Resident/NoResidence.vue";

const residence = ref<Residence>();
const RESIDENT_ROOT = "/residente/";
const modalQR = ref<InstanceType<typeof ModalQR>>();

interface RouteProp {
  title: string;
  icon: SquareButtonIconsType;
  href: string;
}

const modalAdd = ref<typeof ModalAdd>();
async function fetchResidence() {
  const { data } = await getOwnResidence();
  const record = data.value;
  residence.value = record?.data;
}

const routes: RouteProp[] = [
  {
    title: "Mis Solicitudes",
    icon: "custom-eye-watch",
    href: "historial-solicitudes",
  },
];

async function handleClick() {
  if (!residence.value) {
    await fetchResidence();
  }
  modalQR.value?.show();
}

onMounted(async () => {
  await fetchResidence();
});
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
    <SquareButton
      type="button"
      icon="custom-phone-qr"
      title="Generar llave QR"
      @click="handleClick"
    />
  </section>
  <ModalQR v-if="residence" :residence="residence" ref="modalQR" />
  <NoResidence v-else ref="modalQR" />
</template>
