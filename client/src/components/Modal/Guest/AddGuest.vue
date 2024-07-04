<script setup lang="ts">
import { ref, onMounted } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import UserImage from "@/components/UserImage.vue";
import InputForm from "@/components/Forms/InputForm.vue";

import BodyModal from "@/components/Modal/BodyModal.vue";
import ControlsModal from "../ControlsModal.vue";
import IdentityTypeSelection from "../IdentityTypeSelection.vue";
import SimpleAlert from "@/components/SimpleAlert.vue";
import type Alert from "@/types/Alert";
import { AlertType } from "@/types/Alert";
import checkIsValidIdentifier from "@/utils/checkIsValidIdentifier";
import TextAreaForm from "@/components/Forms/TextAreaForm.vue";
import type { IdentifierType } from "@/types/User/IdentifierType";

const modal = ref<typeof Modal>();

const alertForIdentifier = ref<Alert | undefined>();

const data = ref({
  identifierType: "",
  identifier: "",
});

enum Message {
  BUTTON_ACCEPT = "Aceptar",
  BUTTON_CANCEL = "Cancelar",
  ACCOUNT = "Cuenta",
}

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});

onMounted(() => {
  modal.value?.show();
});

const alertFillData: Alert = {
  message: "Para cambiar los demás datos, debes ir a la configuración.",
  type: AlertType.INFO,
};

function handleSubmit(event: Event) {
  event.preventDefault();

  if (!data.value.identifier) {
    const alert: Alert = {
      message: "Llena esta mierda hpta",
      type: AlertType.WARNING,
    };
    alertForIdentifier.value = alert;
    return;
  }

  if (!checkIsValidIdentifier(data.value.identifier, data.value.identifierType as IdentifierType)) {
    const alert: Alert = {
      message: "Esta mierda no calza con el formato de la turca que se usa hpta",
      type: AlertType.WARNING,
    };
    alertForIdentifier.value = alert;
    return;
  }
  resetValues();
  modal.value?.close();
}

function resetValues() {
  data.value.identifier = "";
  alertForIdentifier.value = undefined;
}
</script>
<template>
  <Modal ref="modal">
    <form class="w-full max-w-xl overflow-hidden rounded-md bg-white" @submit="handleSubmit">
      <HeaderModal :title="Message.ACCOUNT" icon="user" action="create" />
      <BodyModal>
        <UserImage image="" class="size-36 self-center" />

        <div class="flex w-full items-center gap-3 text-xl text-pasadita-blue-1">
          <InputForm name="firstName" title="Nombre" type="text" class="flex-1" />
          <InputForm name="lastName" title="Apellido" type="text" class="flex-1" />
        </div>

        <SimpleAlert :alert="alertFillData" />
        <IdentityTypeSelection @identity-type="data.identifierType = $event" />
        <InputForm
          :value="data.identifier"
          :alert="alertForIdentifier"
          name="identifier"
          @update:value="data.identifier = $event"
          title="Identificación"
          type="text"
        />
        <TextAreaForm class="mt-1" name="why-are-you-gay" title="Explícanos por qué eres gay" />
      </BodyModal>

      <ControlsModal>
        <button
          type="submit"
          class="inline-flex items-center gap-0.5 rounded-lg bg-green-100 p-2 text-center text-sm font-normal text-green-400 transition-all hover:rounded-xl hover:bg-green-200 active:scale-95"
        >
          <VueFeather type="check" stroke-width="2.5" size="16"></VueFeather>
          <span>{{ Message.BUTTON_ACCEPT }}</span>
        </button>
        <button
          type="button"
          @click="modal?.close()"
          class="inline-flex items-center gap-0.5 rounded-lg bg-red-100 p-2 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-200 active:scale-95"
        >
          <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
          <span>{{ Message.BUTTON_CANCEL }}</span>
        </button>
      </ControlsModal>
    </form>
  </Modal>
</template>
