<script setup lang="ts">
import { defineProps } from 'vue';

export type ErrorCodes = 404 | 500 | number;

const props = defineProps<{ code: ErrorCodes }>();

const errorResponse: { [key in ErrorCodes]: string } = {
  404: "No se han encontrado elementos, es probable que no exista.",
  500: "Ha ocurrido un error interno, si esto continúa sucediendo, por favor contacte a soporte técnico.",
};

</script>

<template>
  <article class="flex rounded-lg bg-white p-4 py-8">
    <div class="mx-auto flex max-w-[512px] flex-col items-center gap-4 text-center sm:flex-row sm:text-left">
      <img src="/identity/pasaditaLogoMin.svg" class="h-auto w-[200px]" />
      <div class="text-blue-400">
        <h2 class="text-5xl font-semibold">{{ props.code }}</h2>
        <p>{{ errorResponse[props.code] }}</p>
      </div>
    </div>
  </article>
</template>
