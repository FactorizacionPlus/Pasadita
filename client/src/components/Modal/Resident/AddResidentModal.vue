<script setup lang="ts">
import { ref } from "vue";
import { usePermission } from "@/composables/usePermission";
import { useUser } from "@/stores/user";
import type { IdentifierType } from "@/types/User/IdentifierType";
import type { SavePermissionDto } from "@/types/Permission";
import type RegisteredUser from "@/types/User/RegisteredUser";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import DateTimeForm from "@/components/Forms/DateTimeForm.vue";
import InputForm from "@/components/Forms/InputForm.vue";
import VueFeather from "vue-feather";
import IdentityTypeSelection from "../IdentityTypeSelection.vue";
import Modal from "@/components/Modal/ModalComponent.vue";
import ControlsModal from "../ControlsModal.vue";
import SimpleAlert from "@/components/SimpleAlert.vue";
import type Alert from "@/types/Alert";
import { AlertType } from "@/types/Alert";
import checkIsValidIdentifier from "@/utils/checkIsValidIdentifier";
import { useToast } from "@/stores/toast";
import { ToastType } from "@/types/Toast";

const user = useUser();
const data = ref<{
  registeredUser: RegisteredUser;
  identifierType: IdentifierType;
  identifier: string;
}>({
  registeredUser: user.user as RegisteredUser,
  identifierType: "DUI",
  identifier: "",
});

enum Message {
  BUTTON_ACCEPT = "Aceptar",
  BUTTON_CANCEL = "Cancelar",
  ACCOUNT = "Cuenta",
}

const modal = ref<typeof Modal>();
const { createPermission, error, loading } = usePermission();
const { addToast } = useToast();

const startDate = ref("");
const endDate = ref("");
const identificacion = ref("");
const alertForDatetime = ref<Alert | undefined>();
const alertForIdentifier = ref<Alert | undefined>();
const alertFillData = ref<Alert | undefined>();


function validateForm() {
  if (!startDate.value || !endDate.value || !identificacion.value) {
    alertFillData.value = {
      message: "No se pueden dejar campos vacíos.",
      type: AlertType.WARNING,
    };
    return false;
  }
  if (new Date(startDate.value) >= new Date(endDate.value)) {
    alertForDatetime.value = {
      message: "La fecha de inicio debe ser anterior a la fecha de fin.",
      type: AlertType.WARNING,
    };
    return false;
  }
  if (!checkIsValidIdentifier(identificacion.value, data.value.identifierType)) {
    alertForIdentifier.value = {
      message: "Formato de identificación inválido.",
      type: AlertType.WARNING,
    };
    return false;
  }
  
  alertFillData.value = undefined;
  alertForDatetime.value = undefined;
  alertForIdentifier.value = undefined;
  return true;
}

async function submitForm() {
  if (!validateForm()) {
    return;
  }

  const dto: SavePermissionDto = {
    startDate: new Date(startDate.value),
    endDate: new Date(endDate.value),
    invitedUser: identificacion.value,
  };

  await createPermission(dto);

  if (!error.value) {
    addToast({ message: "Permiso creado correctamente", type: ToastType.SUCCESS });
    modal.value?.close();
  } else {
    alertForIdentifier.value = {
      message: error.value,
      type: AlertType.ERROR,
    };
  }
}

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form
      class="w-full max-w-[480px] overflow-hidden rounded-md bg-white"
      @submit.prevent="submitForm"
    >
      <HeaderModal title="Solicitud" icon="file-text" action="add" />
      <div class="flex flex-col gap-4 px-4 py-6">
        <SimpleAlert v-if="alertFillData" :alert="alertFillData" /> 
        <SimpleAlert v-if="alertForDatetime" :alert="alertForDatetime" /> 
        <div class="flex max-h-[80vh] flex-row gap-2">
          <DateTimeForm
            class="flex-1"
            v-model="startDate"
            name="fechaInicio"
            title="Fecha de inicio"
            type="datetime-local"
            placeholder="Fecha de inicio"
          />
          <DateTimeForm
            class="flex-1"
            v-model="endDate"
            name="fechaFin"
            title="Fecha de fin"
            type="datetime-local"
            placeholder="Fecha de fin"
          />
        </div>

        <IdentityTypeSelection
          :identity-type="data.identifierType"
          @identity-type="data.identifierType = $event"
        />

        <SimpleAlert v-if="alertForIdentifier" :alert="alertForIdentifier" />
        <InputForm
          v-model="identificacion"
          name="identificacion"
          title="Identificación"
          type="text"
          placeholder="Identificación"
        />
      </div>

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
