<script setup lang="ts">
import {
  makeLoginRequest,
  makeRegisterRequest,
  useRegisterUrl,
} from "@/composables/useLoginRegister";
import { useAuth } from "@/stores/auth";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";

enum Message {
  LOADING_LOGIN = "Iniciando sesión...",
  LOADING_REGISTER = "Registrando usuario...",
  NO_USER = "No existe un usuario para esta cuenta, ¿deseas crear una?",
  ERROR = "Ocurrió un error en el sistema, por favor intente más tarde",
}
const message = ref<Message>(Message.LOADING_LOGIN);

const auth = useAuth();
const router = useRouter();
const route = useRoute();

const isLogin = route.name == "login-google";
if (!isLogin) {
  message.value = Message.LOADING_REGISTER;
}

const registerUrl = useRegisterUrl("none");

async function doLogin(code: string) {
  const response = await makeLoginRequest(code);
  if (response == null) {
    message.value = Message.ERROR;
    return;
  }
  if (!response.ok) {
    if (response.message == "No user found") {
      message.value = Message.NO_USER;
    } else {
      message.value = Message.ERROR;
    }
    return;
  }
  auth.setToken(response.data);

  router.push("/");
}
async function doRegister(code: string) {
  const response = await makeRegisterRequest(code);
  if (response == null) {
    message.value = Message.ERROR;
    return;
  }
  if (!response.ok) {
    message.value = Message.ERROR;
    return;
  }
  auth.setToken(response.data);

  router.push("/");
}

onMounted(() => {
  const search = window.location.search;
  const params = new URLSearchParams(search);
  const code = params.get("code");

  if (code == null) {
    console.error("No code returned");
    message.value = Message.ERROR;
    return;
  }

  if (isLogin) {
    doLogin(code);
  } else {
    doRegister(code);
  }
});
</script>

<template>
  <main class="w-full h-full flex flex-col items-center justify-center gap-24">
    <h1 class="font-bold lg:text-6xl">{{ message }}</h1>
    <a
      :href="registerUrl.toString()"
      class="flex flex-row gap-2 items-center px-6 py-4 rounded-lg border border-pasadita-shade-2"
      v-if="message == Message.NO_USER"
    >
      <img src="/google-g.svg" alt="Google G Logo" class="size-9" />
      <span class="text-4xl font-normal text-pasadita-shade-1">Registrarse con Google</span>
    </a>
  </main>
</template>
