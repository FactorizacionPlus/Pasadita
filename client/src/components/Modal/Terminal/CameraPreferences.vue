<script setup lang="ts">
import { computed, ref } from "vue";
import Modal from "@/components/Modal/ModalComponent.vue";
import type { CameraDevice } from "html5-qrcode";
import HeaderModal from "../HeaderModal.vue";
import VueFeather from "vue-feather";
import { useCameraPreferences } from "@/stores/prefferedCamera";
import SelectForm from "@/components/Forms/SelectForm.vue";
import type Option from "@/types/Option";
import SwitchForm from "@/components/Forms/SwitchForm.vue";
import { storeToRefs } from "pinia";

const modal = ref<InstanceType<typeof Modal>>();
const props = defineProps<{
  cameras: CameraDevice[];
  canUseTorch: boolean;
}>();

const cameraStore = useCameraPreferences();
const { preferences } = storeToRefs(cameraStore);

const options = computed<Option[]>(() => {
  return props.cameras.map((v) => {
    return {
      value: v.id,
      text: v.label,
    };
  }, []);
});

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <div class="overflow-hidden rounded-md bg-white">
      <HeaderModal title="Preferencias de cámara" icon="tablet" action="edit" />

      <div class="flex flex-col gap-4 p-4">
        <SelectForm
          name="cameras"
          defaultOption="Selecciona la cámara"
          title="Cámaras"
          :options="options"
          v-model="preferences.cameraId"
        />

        <SwitchForm name="useTorch" :disabled="!props.canUseTorch" v-model="preferences.useTorch" />
      </div>

      <!-- Botones -->
      <div class="flex justify-end gap-2 border-t border-t-pasadita-shade-2 p-2">
        <button
          @click="modal?.close()"
          type="button"
          class="inline-flex items-center rounded-lg bg-pasadita-red-2 p-2 text-center text-sm font-normal text-pasadita-red-0 transition-all hover:rounded-xl hover:bg-pasadita-red-1 active:scale-95"
        >
          <VueFeather type="x" stroke-width="2.5" size="16" />
          <span>Cancelar</span>
        </button>
      </div>
    </div>
  </Modal>
</template>
