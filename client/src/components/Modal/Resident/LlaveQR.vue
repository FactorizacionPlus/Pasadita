<script setup lang="ts">
import { ref, onMounted } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import QRCodeStyling from "@/components/QRCodeStyling.vue";
import { getQrToken } from "@/composables/useQRToken";
import type Residence from "@/types/Residence";
import { useToast } from "@/stores/toast";
import { ToastType } from "@/types/Toast";

const props = defineProps<{ residence: Residence }>();
const modal = ref<typeof Modal>();
const qrToken = ref<string>();
const { addToast } = useToast();

onMounted(async () => {
  await fetchToken();

  //await fetchPermission();
});

async function fetchToken() {
  const { data } = await getQrToken();
  const record = data.value;
  console.log(record);
  if (!record || !record.ok) return;
  qrToken.value = record.data;
}

function generateQr() {
  fetchToken();
  addToast({ message: "QR Generado", type: ToastType.SUCCESS });
}

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form class="w-full max-w-[480px] overflow-hidden rounded-md bg-white">
      <HeaderModal title="Llave QR" icon="grid" action="view" />
      <div class="flex max-h-[80vh] flex-col gap-4 overflow-y-auto px-4 py-6">
        <div class="flex flex-col gap-1 rounded-t-lg text-blue-400">
          <p class="text-base font-medium text-blue-500">Información de la Residencia</p>
          <div class="flex items-center gap-1">
            <VueFeather type="home" size="35" stroke-width="1.5" />
            <p class="text-base font-normal">
              {{ props.residence.habitants }} / {{ props.residence.maxHabitants }} Habitantes
            </p>
          </div>
          <p class="text-base text-blue-500">{{ props.residence.description }}</p>
        </div>
        <!-- QR Code Section -->
        <div class="flex items-center justify-center">
          <div class="flex flex-col items-center justify-center">
            <QRCodeStyling v-if="qrToken" :data="qrToken" />
            <div class="mt-4 flex justify-center">
              <button
                @click.prevent="generateQr"
                class="inline-flex gap-1 rounded-lg bg-blue-100 p-2 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-200 hover:text-blue-400 active:scale-95"
              >
                <VueFeather type="loader" stroke-width="2.5" size="16"></VueFeather>
                <span>Generar QR</span>
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- Botones -->
      <div class="flex justify-end gap-2 border-t border-t-pasadita-shade-2 p-2">
        <button
          @click="modal?.close()"
          type="button"
          class="inline-flex items-center rounded-lg bg-red-100 p-2 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-200 active:scale-95"
        >
          <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
          <span>Cancelar</span>
        </button>
      </div>
    </form>
  </Modal>
</template>
