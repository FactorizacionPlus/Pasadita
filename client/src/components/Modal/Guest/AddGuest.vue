<script setup lang="ts">
import { ref, onMounted, defineEmits, type Ref } from "vue";
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
import type { IdentifierType } from "@/types/User/IdentifierType";
import { useUser } from "@/stores/user";
import type RegisteredUser from "@/types/User/RegisteredUser";
import type SaveIdentification from "@/types/Guest/SaveIdentification";
import { requestIdentification } from "@/composables/requestIdentification";
import type { ErrorMap } from "@/types/ErrorMap";
import { setValidationErrorForm, type InputFormType } from "@/utils/formValidation";

const modal = ref<typeof Modal>();
const alertForIdentifier = ref<Alert | undefined>();
const user = useUser();

const data = ref<{
  registeredUser: RegisteredUser,
  identifierType: IdentifierType,
  identifier: string,
}>({registeredUser: user.user as RegisteredUser , identifierType: "DUI", identifier: "" });

const emit = defineEmits(['toggle-menu']);

const formData = ref<SaveIdentification>({
  indentifier:"",
  email:data.value.registeredUser.email,
  indentifierType: data.value.identifierType,
});


console.log(data.value.registeredUser)
console.log(data.value.identifierType)

enum Message {
  BUTTON_ACCEPT = "Aceptar",
  BUTTON_CANCEL = "Cancelar",
  ACCOUNT = "Cuenta",
}

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});

const identificationInput = ref<InputFormType>();
const inputMap = new Map<string, Ref<InputFormType | undefined>>();

onMounted(() => {
  modal.value?.show();
  inputMap.set(identificationInput.value!.props.name, identificationInput);
});

const alertFillData: Alert = {
  message: "Para cambiar los demás datos, debes ir a la configuración.",
  type: AlertType.INFO,
};

async function request(): Promise<boolean> {
  console.log(formData.value)
  const { data, statusCode } = await requestIdentification(formData.value);

  if (!data.value) {
    return false;
  }

  if (statusCode.value == 400) {
    const errorMap = data.value?.data as unknown as ErrorMap;
    setValidationErrorForm(inputMap, errorMap);
  }

  return data.value?.ok;
}

async function handleSubmit(event: Event) {
  event.preventDefault();

  const valid = await request();

  if (valid) {
    formData.value.indentifier = "";
  }

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
  emit('toggle-menu');
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
        <UserImage :image="data.registeredUser.imageUrl" class="size-36 self-center" />

        <div class="flex w-full items-center gap-3 text-xl text-pasadita-blue-1">
          <InputForm :disabled="true" :model-value="data.registeredUser.firstName" name="firstName" title="Nombre" type="text" class="flex-1" />
          <InputForm :disabled="true" :model-value="data.registeredUser.lastName" name="lastName" title="Apellido" type="text" class="flex-1" />
        </div>

        <SimpleAlert :alert="alertFillData" />
        <IdentityTypeSelection :identity-type="data.identifierType" @identity-type="data.identifierType = $event" />
        <InputForm ref="identificationInput" :modelValue="data.identifier" :alert="alertForIdentifier" name="identifier"
          @update:value="data.identifier = $event" v-model="formData.indentifier" title="Identificación" type="text" />
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
