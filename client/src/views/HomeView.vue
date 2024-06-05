<template>
  <div class="grid px-8 gap-y-4">
    <!--Add User-->
    <div class="flex flex-row justify-end">
      <button type="button"
        class="bg-pasadita-blue-4 font-normal rounded-lg text-sm p-2.5 text-center inline-flex items-center text-pasadita-blue-2 hover:bg-pasadita-blue-3 hover:text-white active:scale-95 hover:rounded-xl transition-all">
        <VueFeather type="plus" stroke-width="2.5" size="16" class="mr-2"></VueFeather>
        <span>Agregar Residencias</span>
      </button>
    </div>
    <!--Search Bar-->
    <div class="w-full">
      <form>
        <label for="simple-search" class="sr-only">Search</label>
        <div class="relative w-full">
          <div class="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">
            <VueFeather type="search" stroke="#d7d7d7" stroke-width="2.5" size="16"></VueFeather>
          </div>
          <input type="text" id="simple-search"
            class="bg-white border border-pasadita-shade-2 text-pasadita-shade-0 focus:ring-1 focus:outline-none focus:ring-pasadita-shade-2 text-sm rounded-lg block w-full ps-10 p-2.5"
            placeholder="Buscar por descripcion..." required />
        </div>
      </form>
    </div>
  </div>
  <!--Cards Grid-->
  <div class="grid grid-cols-5 gap-5 p-8">
    <div v-for="residence in residenceList" :key="residence.description">
      <ResidenceCard :residence="residence" />
    </div>
  </div>
</template>

<script setup lang="ts">
import ResidenceCard from "../components/ResidenceCard.vue"
import type Residence from "../types/Residence"
import VueFeather from 'vue-feather';

const residenceList : Residence[] = [
  {
  maxHabitants: 1,
  description: "Lorem Ipsum"
  },
  {
  maxHabitants: 2,
  description: "Lorem Ipsum"
  },
  {
  maxHabitants: 3,
  description: "Lorem Ipsum"
  },
  {
  maxHabitants: 4,
  description: "Lorem Ipsum"
  },
  {
  maxHabitants: 5,
  description: "Lorem Ipsum"
  },
  {
  maxHabitants: 6,
  description: "Lorem Ipsum"
  },
  
]

/*
const residenceList = ref<Residence[]>([])
const userToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDU0MjcyNEB1Y2EuZWR1LnN2IiwiaWF0IjoxNzE3MzE0NjQzLCJleHAiOjE3MTg2MTA2NDN9.5RUWkAv1p0D8D6zHNyPUw8k0BiyIeLbOv5kk2tQJt5fRPvaFGRQ0F1LKi73awXaqjyE5ZK1zVAO1x9ejnaQLig"

onMounted(async () => {
  const response = await fetch("http://localhost:8080/api/residence/", {
    headers: {
      Authorization: "Bearer " + userToken,
    }
  });

  if (response.ok) {
    const json = await response.json();
    residenceList.value = json.data
    console.log(json)
  }
})
*/
</script>