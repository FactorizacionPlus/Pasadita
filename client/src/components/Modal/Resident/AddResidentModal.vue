<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import DateTimeForm from "@/components/Forms/DateTimeForm.vue";
import VueFeather from "vue-feather";
import InputForm from "@/components/Forms/InputForm.vue";
import Modal from "@/components/Modal/ModalComponent.vue";
import Passport from "@/Passport.svg?component";
import Identity from "@/Identity.svg?component";
const passportSelected = ref(false);
const identitySelected = ref(false);

function passportSelection() {
  identitySelected.value = false;
  passportSelected.value = true;
}
function identitySelection() {
  passportSelected.value = false;
  identitySelected.value = true;
}

const modal = ref<typeof Modal>();

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form class="w-full max-w-[480px] overflow-hidden rounded-md bg-white">
      <HeaderModal title="Solicitud" icon="file-text" action="add" />
      <div class="flex flex-col gap-4 px-4 py-6">
        <div class="flex max-h-[80vh] flex-row gap-2">
          <DateTimeForm class="flex-1" name="fechaInicio" title="Fecha de inicio" type="datetime-local"
            placeholder="Fecha de inicio" />
          <DateTimeForm class="flex-1" name="fechaFin" title="Fecha de fin" type="datetime-local" placeholder="Fecha de fin" />
        </div>
        <div class="flex flex-col gap-1">
          <p class="text-sm leading-none text-pasadita-blue-3">Seleccione el tipo de identificacion</p>
          <div class="grid w-full grid-cols-2 gap-2">
          <button type="button" :data-state="identitySelected"
            class="h-32 rounded-lg border-[1.5px] border-pasadita-shade-2 bg-pasadita-blue-4 text-pasadita-blue-2 transition-all hover:bg-pasadita-blue-6 data-[state=true]:bg-pasadita-blue-2 data-[state=true]:text-pasadita-blue-5"
            @click="identitySelection()">
            <Identity class="inline" />
          </button>
          <button type="button" :data-state="passportSelected"
            class="h-32 rounded-lg border-[1.5px] border-pasadita-shade-2 bg-pasadita-blue-4 text-pasadita-blue-2 transition-all hover:bg-pasadita-blue-6 data-[state=true]:bg-pasadita-blue-2 data-[state=true]:text-pasadita-blue-5"
            @click="passportSelection()">
            <Passport class="inline" />
          </button>
        </div>
          
        </div>
        
        <InputForm name="identificacion" title="Identificación" type="text" placeholder="Identificación" />
      </div>


      <div class="flex justify-end gap-2 border-t border-t-pasadita-shade-2 p-2">
        <button
          class="inline-flex items-center rounded-lg bg-pasadita-green-2 p-2 text-center text-sm font-normal text-pasadita-green-1 transition-all hover:rounded-xl hover:bg-pasadita-green-1 hover:text-white active:scale-95">
          <VueFeather type="check" stroke-width="2.5" size="16"></VueFeather>
          <span>Aceptar</span>
        </button>
        <button @click="modal?.close()" type="button"
          class="inline-flex items-center rounded-lg bg-pasadita-red-2 p-2 text-center text-sm font-normal text-pasadita-red-0 transition-all hover:rounded-xl hover:bg-pasadita-red-1 active:scale-95">
          <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
          <span>Cancelar</span>
        </button>
      </div>
    </form>
  </Modal>
</template>
