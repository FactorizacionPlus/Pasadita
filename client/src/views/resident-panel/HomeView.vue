<script setup lang="ts">
import CardsContainer from "@/components/CardsContainer.vue"
import ResidentCard from "@/components/Cards/ResidentCard.vue"
import VueFeather from 'vue-feather';

const residet1 = {
  name: "marcelo",
  secondName: "Rivera",
  identification: "00212121",
  entries: 21,
}
</script>

<template>
  <div class="flex flex-col w-full items-center">
    <button
      class="bg-pasadita-blue-4 text-pasadita-blue-2 text-2xl rounded-lg border border-pasadita-shade-2 py-7 w-[20rem] flex flex-col items-center">
      <div class="flex justify-center items-center flex-shrink-0">
        <VueFeather type="edit" size="40" stroke-width="2"></VueFeather>
      </div>
      Solicitar un permiso
    </button>
  </div>
  <CardsContainer title="Solicitudes" icon="grid">
    <div class="grid grid-cols-4 gap-4">

      <ResidentCard :resident="residet1" />
      <ResidentCard :resident="residet1" />
      <ResidentCard :resident="residet1" />
      <ResidentCard :resident="residet1" />
      <ResidentCard :resident="residet1" />
      <ResidentCard :resident="residet1" />
    </div>
  </CardsContainer>
</template>
