<script setup lang="ts">
import { ref, onMounted, type Ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import BodyModal from "@/components/Modal/BodyModal.vue";
import ControlsModal from "../ControlsModal.vue";
import type Option from "@/types/Option";
import InputForm from "@/components/Forms/InputForm.vue";
import SelectForm from "@/components/Forms/SelectForm.vue";
import VueFeather from "vue-feather";
import TextAreaForm from "@/components/Forms/TextAreaForm.vue";
import BoxContainerForm from "@/components/Forms/BoxContainerForm.vue";
import Modal from "@/components/Modal/ModalComponent.vue";
import type BoxContainerItem from "@/types/BoxContainerItem";
import type Alert from "@/types/Alert";
import { setValidationErrorForm, type InputFormType } from "@/utils/formValidation";

enum Message {
  EMPTY = "",
  LOADING_REGISTER = "Registrando usuario...",
  LOADING_USER = "Cargando datos de usuario...",
  USERNAME_USED = "Este nombre de usuario ya está en uso",
  EMAIL_USED = "Este correo electrónico ya está en uso",
  ERROR_AUTH = "Autenticación incorrecta",
  ERROR_USER = "No se pudo obtener los datos de este usuario, por favor intente más tarde",
  ERROR = "Ocurrió un error en el sistema, por favor intente más tarde",
}
const message = ref<Message>(Message.EMPTY);

const modal = ref<typeof Modal>();
const alertForIdentifier = ref<Alert | undefined>();

const formData = ref({
  description: "",
  identification: "",
});

//const descriptionInput = ref<TextAreaForm>();
const identificationInput = ref<InputFormType>();
const inputMap = new Map<string, Ref<InputFormType | undefined>>();

onMounted(async () => {
  inputMap.set(identificationInput.value!.props.name, identificationInput);
});

/*const people: Option[] = [
  {
    text: "Juan Daniel",
    value: "cat",
  },
  {
    text: "Juana Daniela",
    value: "cat2",
  },
  {
    text: "Juan Pablo",
    value: "cat3",
  },
];
*/
const boxItems: BoxContainerItem[] = [
  {
    text: "Rita",
    icon: true,
    value: "hello",
  },
  {
    text: "Wilson",
    icon: true,
    value: "hello",
  },
];

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form class="overflow-hidden rounded-md bg-white">
      <HeaderModal title="Residencia" icon="home" action="edit" />
      <BodyModal>
        <TextAreaForm
          ref="description"
          v-model="formData.description"
          name="description"
          title="Descripcion de la residencia"
          placeholder="Inserte la cantidad de habitantes"
        />
        <BoxContainerForm :items="boxItems" title="Residentes Encargados" />
        <div class="flex gap-2">
          <InputForm
            name="quantity"
            title="Cantidad de habitantes"
            type="number"
            placeholder="Inserte la cantidad de habitantes"
          />
          <InputForm
            ref="identificacion"
            v-model="formData.identification"
            name="quantity"
            title="Identificacion"
            type="text"
            placeholder="Inserte la cantidad de habitantes"
          />
          <button
            class="inline-flex items-center gap-1 rounded-lg bg-blue-100 p-2 text-center text-base font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-200 active:scale-95"
          >
            <VueFeather type="search" stroke-width="2.5" size="15"></VueFeather>
            <span>Agregar</span>
          </button>
        </div>
      </BodyModal>
      <ControlsModal>
        <button
          class="inline-flex items-center rounded-lg bg-green-100 p-2 text-center text-sm font-normal text-green-400 transition-all hover:rounded-xl hover:bg-green-200 active:scale-95"
        >
          <VueFeather type="check" stroke-width="2.5" size="16"></VueFeather>
          <span>Aceptar</span>
        </button>
        <button
          @click="modal?.close()"
          type="button"
          class="inline-flex items-center rounded-lg bg-red-100 p-2 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-200 active:scale-95"
        >
          <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
          <span>Cancelar</span>
        </button>
      </ControlsModal>
    </form>
  </Modal>
</template>
