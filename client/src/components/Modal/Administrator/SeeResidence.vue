<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import UserImage from "@/components/UserImage.vue";
import type User from "@/types/User";
import type Residence from "@/types/Residence";

const modal = ref<typeof Modal>();

const residents: User[] = [
  {
    id: "0011904041016S",
    name: "Juan Daniel",
    lastName: "Treminio",
    image: "https://laverdadnoticias.com/img/2018/05/02/picozapato.jpg?__scale=c:transparent,w:480,h:680,t:3",
    role: "Administrador"
  },
  {
    id: "0011904041016S",
    name: "Nicolás",
    lastName: "Maduro Moros",
    image: "https://pasadita.vercel.app/madurito.png",
    role: "Dictador"
  },
  {
    id: "0011904041016S",
    name: "David Sebastián",
    lastName: "Parrales Ponce",
    image: "https://github.com/poncka.png",
    role: "Residente"
  }
]

const residence: Residence = {
  currentResidents: residents.length,
  description: "Palacio de Miraflores, República Bolivariana de Venezuela",
  maxHabitants: 5,
}

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});

</script>

<template>
  <Modal ref="modal">
    <form class="overflow-hidden rounded-md bg-white">
      <HeaderModal title="Residente" icon="home" action="view" />
      <div class="flex max-h-[80vh] flex-col gap-4 p-6">
        <div class="flex items-center gap-2 rounded-t-lg text-pasadita-blue-1">
          <VueFeather type="home" size="40" stroke-width="1.5"></VueFeather>
          <div class="flex-1">
            <p class="text-base font-medium">Residencia</p>
            <p class="text-base font-normal">{{ residence.currentResidents }} / {{
              residence.maxHabitants }} Habitantes</p>
          </div>
        </div>
        <span class="font-semibold text-pasadita-blue-1">Residentes</span>
        <ul class="grid gap-4 min-[480px]:grid-cols-2">
          <li class="flex gap-2" :key="index" v-for="(resident, index) in residents">
            <UserImage class="size-12" :image="resident.image" size="25" padding="0px" />
            <div class="flex-1 text-pasadita-blue-1">
              <p class="text-sm font-semibold">
                {{ resident.role }}
              </p>
              <p class="text-base font-normal">
                {{ resident.name }} {{ resident.lastName }}
              </p>
            </div>
          </li>
        </ul>
      </div>

      <!-- Botones -->
      <div class="flex justify-end gap-2 border-t border-t-pasadita-shade-2 p-2">
        <button @click="modal?.close()" type="button"
          class="inline-flex items-center rounded-lg bg-pasadita-red-2 p-2 text-center text-sm font-normal text-pasadita-red-0 transition-all hover:rounded-xl hover:bg-pasadita-red-1 active:scale-95">
          <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
          <span>Cancelar</span>
        </button>
      </div>
    </form>
  </Modal>
</template>
