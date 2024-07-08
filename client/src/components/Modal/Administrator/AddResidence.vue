<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import BodyModal from "@/components/Modal/BodyModal.vue";
import ControlsModal from "../ControlsModal.vue";
import { useToast } from "@/stores/toast";
import TextAreaForm from "@/components/Forms/TextAreaForm.vue";
import { AlertType } from "@/types/Alert";
import InputForm from "@/components/Forms/InputForm.vue";
import { ToastType } from "@/types/Toast";
import type SaveResidence from "@/types/Residence/SaveResidence";
import { saveResidence } from "@/composables/useResidence";
import type Alert from "@/types/Alert";

const modal = ref<typeof Modal>();
const { addToast } = useToast();

const data = ref<SaveResidence>({
  description: "",
  maxHabitants: 0,
})

const alertForMaxHabitants = ref<Alert | undefined>()
const alertForDescription = ref<Alert | undefined>()

enum Message {
  BUTTON_ACCEPT = "Aceptar",
  BUTTON_CANCEL = "Cancelar",
  RESIDENCE = "Residencia",
  RESIDENTS = "Residentes Encargados",
  NO_RESIDENTS = "No hay residentes asignados",
  SEARCH = "Buscar"
}

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});

async function handleSubmit(event: Event) {
  event.preventDefault();

  if(data.value.description.length < 5){
    alertForDescription.value = {
      message: "La descripción debe ser mayor a cinco carácteres.",
      type: AlertType.WARNING
    }
    return;
  }

  if(data.value.maxHabitants < 1){
    alertForMaxHabitants.value = {
      message: "El máximo de habitantes debe ser al menos uno.",
      type: AlertType.WARNING
    }
    return;
  }

  const response = await saveResidence(data?.value);

  if (response.statusCode.value !== 200 || !response.data.value?.ok) {
    addToast({
      message: "No se ha podido guardar la residencia, por favor, verifique sus datos.",
      type: ToastType.ERROR
    });
    return;
  }

  addToast({
      message: "La residencia ha sido guardada correctamente, se recargará la página en unos segundos para reflejar los cambios.",
      type: ToastType.SUCCESS
    });

    modal?.value?.close();
    
    setTimeout(()=> {
      window.location.reload();
    },5000)
}

</script>

<template>
  <Modal ref="modal">
    <form class="w-full max-w-2xl overflow-hidden rounded-md bg-white" @submit="handleSubmit">
      <HeaderModal :title="Message.RESIDENCE" icon="home" action="create" />
      <BodyModal>
        <InputForm :alert="alertForMaxHabitants" @update:value="data.maxHabitants = $event" title="Cantidad de Residentes" name="resident_quantity" type="number" />
        <TextAreaForm :alert="alertForDescription" @update:value="data.description = $event" title="Descripción" name="description" />
      </BodyModal>
      <ControlsModal>
        <button type="submit"
          class="inline-flex items-center gap-0.5 rounded-lg bg-green-100 p-2 text-center text-sm font-normal text-green-400 transition-all hover:rounded-xl hover:bg-green-200 active:scale-95">
          <VueFeather type="check" stroke-width="2.5" size="16"></VueFeather>
          <span>{{ Message.BUTTON_ACCEPT }}</span>
        </button>
        <button type="button" @click="modal?.close()"
          class="inline-flex items-center gap-0.5 rounded-lg bg-red-100 p-2 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-200 active:scale-95">
          <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
          <span>{{ Message.BUTTON_CANCEL }}</span>
        </button>
      </ControlsModal>
    </form>
  </Modal>
</template>
