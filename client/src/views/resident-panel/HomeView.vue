<script setup lang="ts">
import CardsContainer from "@/components/CardsContainer.vue"
import ResidentCard from "@/components/Cards/ResidentCard.vue"
import VueFeather from 'vue-feather';
import PaginationItem from "@/components/PaginationItem.vue"
import SearchBar from "@/components/SearchBar.vue"

const residet1 = {
  name: "marcelo",
  secondName: "Rivera",
  identification: "00212121",
  entries: 21,
}
</script>

<template>
  <div class="flex flex-col w-full items-center mb-5">
    <button
      class="bg-pasadita-blue-4 text-pasadita-blue-2 text-2xl rounded-lg border border-pasadita-shade-2 hover:bg-pasadita-blue-2/20 hover:rounded-2xl active:scale-95 transition-all py-7 w-[20rem] flex flex-col items-center">
      <div class="flex justify-center items-center flex-shrink-0">
        <VueFeather type="edit" size="40" stroke-width="2"></VueFeather>
      </div>
      Solicitar un permiso
    </button>
  </div>
  <CardsContainer title="Solicitudes" icon="grid">
    <SearchBar />
    <div class="grid sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4 my-5">
      <ResidentCard :resident="residet1" />
      <ResidentCard :resident="residet1" />
      <ResidentCard :resident="residet1" />
      <ResidentCard :resident="residet1" />
      <ResidentCard :resident="residet1" />
      <ResidentCard :resident="residet1" />
    </div>
  </CardsContainer>
  <PaginationItem :totalPages=5 />
</template>
