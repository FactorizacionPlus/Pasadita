<script setup lang="ts">
import Pasadita from "@/assets/PasaditaLogoV2.svg?url";
import Google from "@/assets/Google.svg?url";
</script>

<template>
  <div class="flex h-screen flex-col items-center justify-center gap-5">
    <img :src="Pasadita" class="size-72" />
    <div>
      <button
        class="inline-flex items-center gap-2 rounded-md border border-pasadita-shade-2 bg-white px-4 py-2 text-pasadita-shade-1 transition-all hover:rounded-xl hover:bg-pasadita-blue-4 active:scale-95"
      >
        <img :src="Google" class="size-6" />
        <span>Continuar con Google</span>
      </button>
    </div>
  </div>
</template>
