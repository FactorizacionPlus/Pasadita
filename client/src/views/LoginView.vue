<script setup lang="ts">
import { useLoginUrl } from "@/composables/useLoginRegister";

const loginUrl = useLoginUrl();
</script>

<template>
  <main class="flex size-full flex-col items-center justify-center gap-24">
    <div class="flex flex-col items-center gap-6">
      <img
        src="/logo-pasadita-invertido.svg"
        alt="Pasadita logo"
        class="h-[379px] w-[562px] object-contain"
      />
      <h1 class="text-8xl font-bold uppercase text-pasadita-blue-1">Pasadita</h1>
    </div>

    <a
      :href="loginUrl.toString()"
      class="flex flex-row items-center gap-2 rounded-lg border border-pasadita-shade-2 px-6 py-4"
    >
      <img src="/google-g.svg" alt="Google G Logo" class="size-9" />
      <span class="text-4xl font-normal text-pasadita-shade-1">Continuar con Google</span>
    </a>
  </main>
</template>
