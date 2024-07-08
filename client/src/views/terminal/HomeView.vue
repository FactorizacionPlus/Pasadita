<script setup lang="ts">
import { ref } from "vue";
import ModalEntry from "@/components/Modal/Terminal/CreateEntry.vue";
import SquareButton from "@/components/SquareButton.vue";
import { getRootPath } from "@/utils/viewRole";
import FindUser from "@/components/Modal/Terminal/FindUser.vue";
import type User from "@/types/User/User";
import { getAnonymousUser } from "@/composables/useRegisteredUser";
import { useToast } from "@/stores/toast";
import { ToastType } from "@/types/Toast";

const rootPath = getRootPath();

const toast = useToast();

const modalEntry = ref<InstanceType<typeof ModalEntry>>();
const findUser = ref<InstanceType<typeof FindUser>>();

const anonymousUser = ref<User>();

async function handleNext(identifier: string) {
  const { data } = await getAnonymousUser(identifier);

  if (data.value?.data) {
    anonymousUser.value = data.value.data;
    toast.addToast({ type: ToastType.SUCCESS, message: "Usuario encontrado" });
  } else {
    anonymousUser.value = undefined;
    toast.addToast({
      type: ToastType.ERROR,
      message: "No se encontró un Usuario con ese identificador",
    });
  }

  findUser.value?.close();
  modalEntry.value?.show();
}
</script>

<template>
  <div class="flex size-full items-center justify-center gap-4">
    <SquareButton
      type="button"
      icon="custom-entry"
      title="Añadir Entrada Anónima"
      @click="findUser?.show()"
    />

    <SquareButton
      type="link"
      icon="custom-phone-qr"
      title="Leer Llaves QR"
      :href="rootPath + '/camera'"
    />
  </div>

  <ModalEntry ref="modalEntry" :user="anonymousUser" />
  <FindUser ref="findUser" @next="handleNext" />
</template>
