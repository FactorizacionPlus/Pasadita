<script setup lang="ts">
import { ref, computed } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import InputForm from "@/components/Forms/InputForm.vue";
import Passport from "@/Passport.svg?component";
import Identity from "@/Identity.svg?component";
import TextAreaForm from "@/components/Forms/TextAreaForm.vue";

const modal = ref<typeof Modal>();
const passportSelected = ref(false);
const identitySelected = ref(false);
// Computed property to determine if the input should be enabled
const isIdentificationEnabled = computed(() => passportSelected.value || identitySelected.value);

function passportSelection() {
  identitySelected.value = false;
  passportSelected.value = true;
}

function identitySelection() {
  passportSelected.value = false;
  identitySelected.value = true;
}

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form class="overflow-hidden rounded-md bg-white">
      <HeaderModal title="Perfil Anonimo" icon="user" action="add" />
      <div class="flex flex-col gap-4 p-4">
        <div class="flex items-center gap-5 text-xl text-pasadita-blue-1">
          <InputForm name="nombre" title="Nombre" type="text" placeholder="Nombre" />
          <InputForm name="apellido" title="Apellido" type="text" placeholder="Apellido" />
        </div>
        <div class="grid grid-cols-2 gap-2">
          <button
            type="button"
            :data-state="identitySelected"
            class="h-32 rounded-lg border-[1.5px] border-pasadita-shade-2 bg-pasadita-blue-4 text-pasadita-blue-2 data-[state=true]:bg-pasadita-blue-2 data-[state=true]:text-pasadita-blue-5"
            @click="identitySelection()"
          >
            <Identity class="inline" />
          </button>
          <button
            type="button"
            :data-state="passportSelected"
            class="h-32 rounded-lg border-[1.5px] border-pasadita-shade-2 bg-pasadita-blue-4 text-pasadita-blue-2 data-[state=true]:bg-pasadita-blue-2 data-[state=true]:text-pasadita-blue-5"
            @click="passportSelection()"
          >
            <Passport class="inline" />
          </button>
        </div>
        <InputForm
          :disabled="!isIdentificationEnabled"
          name="identificacion"
          title="Identificación"
          type="text"
          placeholder="Identificación"
        />
        <TextAreaForm name="description" title="Descripcion" placeholder="Descripcion" />
      </div>

      <!-- Botones -->
      <div class="flex justify-end gap-2 border-t border-t-pasadita-shade-2 p-2">
        <button
          class="inline-flex items-center rounded-lg bg-pasadita-green-2 p-2 text-center text-sm font-normal text-pasadita-green-1 transition-all hover:rounded-xl hover:bg-pasadita-green-1 hover:text-white active:scale-95"
        >
          <VueFeather type="check" stroke-width="2.5" size="16"></VueFeather>
          <span>Aceptar</span>
        </button>
        <button
          @click="modal?.close()"
          type="button"
          class="inline-flex items-center rounded-lg bg-pasadita-red-2 p-2 text-center text-sm font-normal text-pasadita-red-0 transition-all hover:rounded-xl hover:bg-pasadita-red-1 active:scale-95"
        >
          <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
          <span>Cancelar</span>
        </button>
      </div>
    </form>
  </Modal>
</template>
