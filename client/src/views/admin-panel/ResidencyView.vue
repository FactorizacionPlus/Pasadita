<script setup lang="ts">
import { ref, onMounted } from "vue";
import VueFeather from "vue-feather";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import Residences from "@/components/Cards/ResidenceCard.vue";
import type Residence from "@/types/Residence";
import SearchBar from "@/components/SearchBar.vue";
import ModalAdd from "@/components/Modal/Residence/ManageResidence.vue";
import PaginationItem from "@/components/PaginationItem.vue";
import { getResidence } from "@/composables/useResidence";
const residence = ref<Residence[]>([]);
const modalAdd = ref<typeof ModalAdd>();

enum Message {
  TITLE = "Residencias",
  CREATE_RESIDENCE = "Crear Residencia",
}

onMounted(async () => {
  await fetchResidence();
});

async function fetchResidence() {
  const { data } = await getResidence();
  const record = data.value;
  console.log(record)
  if (!record || !record.ok) return;
  residence.value = record.data ?? [];
}

console.log(residence.value);

/*const residenceList: Residence[] = [
  {
    maxHabitants: 2,
    description: "Palacio de Miraflores, República de Venezuela",
    status: "ACTIVE",
  },
  {
    maxHabitants: 2,
    description: "Casa Presidencial, Team Factorización, Mejicanos, San Salvador",
    status: "ACTIVE",
  },
  {
    maxHabitants: ,
    description: "Universidad Centroamericana, Managua, Nicaragua",
    status: "ACTIVE",
  },
];*/
</script>

<template>
  <CurrentPageInfo :title="Message.TITLE" icon="home">
    <button
      class="inline-flex items-center rounded-lg bg-white p-2 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-shades-100 active:scale-95"
      @click="modalAdd?.show()"
    >
      <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
      <span>{{ Message.CREATE_RESIDENCE }}</span>
    </button>
  </CurrentPageInfo>
  <article class="flex w-full flex-col gap-8 rounded-lg bg-white p-4">
    <SearchBar />
    <ul class="grid w-full gap-4 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 2xl:grid-cols-5">
      <Residences
        v-for="(residence, index) in residence"
        :key="index"
        :residence="residence"
      />
    </ul>
    <PaginationItem class="pt-4" :total-pages="6" />
    <ModalAdd ref="modalAdd" />
  </article>
</template>
