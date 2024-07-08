<script setup lang="ts">
import { ref, onMounted, defineEmits, type Ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import BodyModal from "@/components/Modal/BodyModal.vue";
import ControlsModal from "../ControlsModal.vue";
import type Alert from "@/types/Alert";
import type { IdentifierType } from "@/types/User/IdentifierType";
import { useUser } from "@/stores/user";
import type RegisteredUser from "@/types/User/RegisteredUser";
import { useToast } from "@/stores/toast";
import TextAreaForm from "@/components/Forms/TextAreaForm.vue";
import SimpleAlert from "@/components/SimpleAlert.vue";
import { AlertType } from "@/types/Alert";
import InputForm from "@/components/Forms/InputForm.vue";
import { getUserByIdentifier } from "@/composables/useRegisteredUser";
import { ToastType } from "@/types/Toast";
import UserImage from "@/components/UserImage.vue";
import type SaveResidence from "@/types/Residence/SaveResidence";

const modal = ref<typeof Modal>();
const { addToast } = useToast();

const users = ref<RegisteredUser[]>([])
const foundUser = ref<RegisteredUser>()
const currentIdentifier = ref("")
const data = ref<SaveResidence>({
  description: "",
  maxHabitants: 0,
})


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

async function searchUser() {

  if (currentIdentifier.value.length < 8) return;

  if(users.value.some((u) => u.identifier == currentIdentifier.value)){
    addToast({message: "Este usuario ya ha sido agregado a la lista de Residentes Encargados.", type: ToastType.WARNING});
    return;
  }

  const { data, statusCode } = await getUserByIdentifier(currentIdentifier.value)

  if (!data.value?.ok) {
    addToast({
      message: "Ha ocurrido un error al tratar de encontrar el usuario, si el error persiste, contacte al soporte técnico.",
      type: ToastType.ERROR
    });
    return;
  }

  if (statusCode.value !== 200) {
    addToast({
      message: "No se ha encontrado un usuario con esta identificación, por favor, verifique sus datos.",
      type: ToastType.ERROR
    });
    return;
  }

  if (Array.isArray(data.value.data)) { 
    users.value.push((data.value.data as RegisteredUser[])[0]); 
  }
  else { 
    users.value.push(data.value.data); 
  }

  addToast({ message: "Se ha encontrado al usuario con la identificación, ha sido agregado como Residente Encargado de la residencia.", type: ToastType.SUCCESS })
}

</script>

<template>
  <Modal ref="modal">
    <form class="w-full max-w-2xl overflow-hidden rounded-md bg-white">
      <HeaderModal :title="Message.RESIDENCE" icon="home" action="create" />
      <BodyModal>
        <TextAreaForm @update:value="data.description = $event" title="Descripción" name="description" />
        <div class="flex flex-col gap-1">
          <span class="text-sm font-medium">{{ Message.RESIDENTS }}</span>
          <ul v-if="users.length > 0" class="flex flex-wrap gap-2 border border-blue-200 bg-shades-100 p-2">
            <li :key="index" class="flex items-center gap-1 rounded-lg bg-white p-2 text-blue-500" v-for="(user, index) in users">
              <UserImage :image="user.imageUrl" class="size-8" />
              <div class="flex flex-col">
                <span class="text-xs">{{ user.identifier }}</span>
                <p>{{ user.firstName }} {{ user.lastName }}</p>
              </div>
            </li>
          </ul>
          <SimpleAlert v-else :alert="{ message: Message.NO_RESIDENTS, type: AlertType.INFO }" />
        </div>
        <div class="flex flex-row gap-2">
          <InputForm @update:value="data.maxHabitants = $event" class="max-w-56" title="Cantidad de Residentes" name="resident_quantity" type="number" />
          <div class="flex flex-1 gap-2">
            <InputForm @update:value="currentIdentifier = $event" class="flex-1" title="Identificación"
              name="identity" />
            <button type="button" @click="searchUser"
              class="inline-flex items-center gap-0.5 rounded-lg bg-blue-100 p-2 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-200 active:scale-95">
              <VueFeather type="search" stroke-width="2.5" size="16"></VueFeather>
              <span>{{ Message.SEARCH }}</span>
            </button>
          </div>
        </div>
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
