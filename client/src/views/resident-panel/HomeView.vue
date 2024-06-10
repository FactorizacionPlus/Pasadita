<script setup lang="ts">
import CardsContainer from "@/components/CardsContainer.vue";
import ResidentCard from "@/components/Cards/ResidentCard.vue";
import VueFeather from "vue-feather";
import type Resident from "@/types/Resident";

const residet1: Resident = {
  name: "marcelo",
  secondName: "Rivera",
  identification: "00212121",
  entries: 21,
  image: "",
};
</script>

<template>
  <div class="flex w-full flex-col items-center">
    <button
      class="flex w-80 flex-col items-center rounded-lg border border-pasadita-shade-2 bg-pasadita-blue-4 py-7 text-2xl text-pasadita-blue-2"
    >
      <div class="flex shrink-0 items-center justify-center">
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
