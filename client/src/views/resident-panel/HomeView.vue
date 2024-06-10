<script setup lang="ts">
import CardsContainer from "@/components/CardsContainer.vue";
import ResidentCard from "@/components/Cards/ResidentCard.vue";
import VueFeather from "vue-feather";
import PaginationItem from "@/components/PaginationItem.vue";
import SearchBar from "@/components/SearchBar.vue";

const residet1 = {
  name: "marcelo",
  secondName: "Rivera",
  identification: "00212121",
  entries: 21,
};
</script>

<template>
  <div class="mb-5 flex w-full flex-col items-center">
    <button
      class="flex w-80 flex-col items-center rounded-lg border border-pasadita-shade-2 bg-pasadita-blue-4 py-7 text-2xl text-pasadita-blue-2 transition-all hover:rounded-2xl hover:bg-pasadita-blue-2/20 active:scale-95"
    >
      <div class="flex shrink-0 items-center justify-center">
        <VueFeather type="edit" size="40" stroke-width="2"></VueFeather>
      </div>
      Solicitar un permiso
    </button>
  </div>
  <CardsContainer title="Solicitudes" icon="grid">
    <SearchBar />
    <div class="my-5 grid gap-4 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
      <ResidentCard :resident="residet1" />
      <ResidentCard :resident="residet1" />
      <ResidentCard :resident="residet1" />
      <ResidentCard :resident="residet1" />
      <ResidentCard :resident="residet1" />
      <ResidentCard :resident="residet1" />
    </div>
  </CardsContainer>
  <PaginationItem :totalPages="5" />
</template>
