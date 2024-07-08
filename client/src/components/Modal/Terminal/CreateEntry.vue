<script setup lang="ts">
import { ref, computed, onMounted, watch } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import InputForm from "@/components/Forms/InputForm.vue";
import Passport from "@/Passport.svg?component";
import Identity from "@/Identity.svg?component";
import TextAreaForm from "@/components/Forms/TextAreaForm.vue";
import type User from "@/types/User/User";
import { createAnonymousEntry, createAnonymousUser } from "@/composables/useEntry";
import { useToast } from "@/stores/toast";
import { ToastType } from "@/types/Toast";

const toast = useToast();

const modal = ref<InstanceType<typeof Modal>>();

const props = defineProps<{ user?: User }>();

const foundUser = computed(() => props.user != undefined);

const formData = ref({
  firstName: props.user?.firstName ?? "",
  lastName: props.user?.lastName ?? "",
  identifier: props.user?.identifier ?? "",
  identifierType: props.user?.identifierType,
  description: "",
});

watch(props, () => {
  formData.value.firstName = props.user?.firstName ?? "";
  formData.value.lastName = props.user?.lastName ?? "";
  formData.value.identifier = props.user?.identifier ?? "";
  formData.value.identifierType = props.user?.identifierType;
  formData.value.description = "";
});

const isIdentificationEnabled = computed(() => formData.value.identifierType != undefined);

async function createUser(): Promise<boolean> {
  const { firstName, lastName, identifier, identifierType } = formData.value;
  const { error } = await createAnonymousUser({
    firstName,
    lastName,
    identifier,
    identifierType: identifierType!,
  });

  if (error.value) {
    toast.addToast({ type: ToastType.ERROR, message: "No se pudo crear el usuario" });
    return false;
  }
  return true;
}

async function createEntry() {
  const { description, identifier } = formData.value;
  const { error } = await createAnonymousEntry({ description, identifier });

  if (error.value) {
    toast.addToast({ type: ToastType.ERROR, message: "No se pudo crear la entrada" });
  } else {
    toast.addToast({ type: ToastType.SUCCESS, message: "Entrada creada" });
  }
}

async function handleSubmit() {
  if (!foundUser.value) {
    // Create user
    const created = await createUser();
    if (!created) {
      return;
    }
  }
  await createEntry();

  modal.value?.close();
}

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form class="overflow-hidden rounded-md bg-white" @submit.prevent="handleSubmit">
      <HeaderModal title="Añadir Entrada" icon="user" action="add" />
      <div class="flex flex-col gap-4 p-4">
        <div class="flex items-center gap-5 text-xl text-pasadita-blue-1">
          <InputForm
            :disabled="foundUser"
            name="nombre"
            title="Nombre"
            type="text"
            placeholder="Nombre"
            v-model="formData.firstName"
          />
          <InputForm
            :disabled="foundUser"
            name="apellido"
            title="Apellido"
            type="text"
            placeholder="Apellido"
            v-model="formData.lastName"
          />
        </div>
        <div class="flex flex-col gap-1">
          <p class="text-sm leading-none text-pasadita-blue-3">
            Seleccione el tipo de identificacion
          </p>
          <div class="grid w-full grid-cols-2 gap-2">
            <button
              :disabled="foundUser"
              type="button"
              :data-state="formData.identifierType == 'DUI'"
              class="h-32 rounded-lg border-[1.5px] border-pasadita-shade-2 bg-pasadita-blue-4 text-pasadita-blue-2 transition-all hover:bg-pasadita-blue-6 data-[state=true]:bg-pasadita-blue-2 data-[state=true]:text-pasadita-blue-5"
              @click="formData.identifierType = 'DUI'"
            >
              <Identity class="inline" />
            </button>
            <button
              :disabled="foundUser"
              type="button"
              :data-state="formData.identifierType == 'PASSPORT'"
              class="h-32 rounded-lg border-[1.5px] border-pasadita-shade-2 bg-pasadita-blue-4 text-pasadita-blue-2 transition-all hover:bg-pasadita-blue-6 data-[state=true]:bg-pasadita-blue-2 data-[state=true]:text-pasadita-blue-5"
              @click="formData.identifierType = 'PASSPORT'"
            >
              <Passport class="inline" />
            </button>
          </div>
        </div>
        <InputForm
          :disabled="!isIdentificationEnabled || foundUser"
          name="identificacion"
          title="Identificación"
          type="text"
          placeholder="Identificación"
          v-model="formData.identifier"
        />
        <TextAreaForm
          name="description"
          title="Descripcion"
          placeholder="Descripcion"
          v-model="formData.description"
        />
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
