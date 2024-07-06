<script setup lang="ts">
import { useLoginUrl, useRegisterUrl } from "@/composables/useLoginRegister";

const loginUrl = useLoginUrl();
const registerUrl = useRegisterUrl();

enum Message {
  APPLICATION = "PASADITA",
  LOGIN = "Continuar con Google",
  REGISTER = "Registrarse con Google",
  TERMINAL = "Iniciar una terminal"
}

</script>

<template>
  <main class="flex size-full flex-col items-center justify-center">

    <div class="flex flex-col gap-10 bg-white py-5">
      <div class="flex flex-col items-center">
        <img src="/identity/pasaditaLogoMin.svg" alt="Pasadita logo" class="w-[360px] object-contain text-blue-400" />
        <h1 class="text-[70px] font-bold uppercase text-blue-400"> {{ Message.APPLICATION }}</h1>
        < /div>
          < div class="flex flex-col gap-4">

            <div class="flex gap-3 mx-4">

              <a :href="loginUrl.toString()"
                class="flex flex-row items-center gap-2 bg-blue-100 rounded-lg px-6 py-4 hover:rounded-xl hover:bg-blue-200 active:scale-95 transition-all">
                <img src="/google-g.svg" alt="Google G Logo" class="size-6" />
                <span class="text-2xl font-normal text-blue-400"> {{ Message.LOGIN }}</span>
              </a>
              <a :href="registerUrl.toString()"
                class="flex flex-row items-center gap-2 rounded-lg bg-blue-100 px-6 py-4 hover:rounded-xl hover:bg-blue-200 active:scale-95 transition-all">
                <img src="/google-g.svg" alt="Google G Logo" class="size-6" />
                <span class="text-2xl font-normal text-blue-400"> {{ Message.REGISTER }}</span>
              </a>
            </div>
            <a href="/auth/terminal"
              class="flex flex-row items-center gap-2 rounded-lg bg-blue-100 px-6 py-4 mx-4 hover:rounded-xl hover:bg-blue-200 active:scale-95 transition-all">
              <p class="w-full flex items-center justify-center text-blue-400 text-2xl">
                {{ Message.TERMINAL }}
              </p>
            </a>
      </div>
    </div>
  </main>
</template>
