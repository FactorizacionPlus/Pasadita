<script setup lang="ts">
import { useLoginUrl } from "@/composables/useLoginRegister";

const loginUrl = useLoginUrl();
</script>

<template>
  <main class="w-full h-full flex flex-col items-center justify-center gap-24">
    <div class="flex flex-col gap-6 items-center">
      <img
        src="/logo-pasadita-invertido.svg"
        alt="Pasadita logo"
        class="w-[562px] h-[379px] object-contain"
      />
      <h1 class="uppercase font-bold text-8xl text-pasadita-blue-1">Pasadita</h1>
    </div>

    <a
      :href="loginUrl.toString()"
      class="flex flex-row gap-2 items-center px-6 py-4 rounded-lg border border-pasadita-shade-2"
    >
      <img src="/google-g.svg" alt="Google G Logo" class="size-9" />
      <span class="text-4xl font-normal text-pasadita-shade-1">Continuar con Google</span>
    </a>
  </main>
</template>
