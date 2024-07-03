<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import UserImage from "@/components/UserImage.vue";
import type Residence from "@/types/Residence";
import type Resident from "@/types/Resident";

const modal = ref<typeof Modal>();

const residents: Resident[] = [
  {
    identifier: "0011904041016S",
    firstName: "Juan Daniel",
    lastName: "Treminio",
    imageUrl:
      "https://laverdadnoticias.com/img/2018/05/02/picozapato.jpg?__scale=c:transparent,w:480,h:680,t:3",
    role: "Administrador",
    identifierType: "PASSPORT",
    email: "",
    status: "ACTIVE",
    residence: {
      description: "",
      maxHabitants: 0,
      status: "ACTIVE",
    },
  },
  {
    identifier: "0011904041016S",
    firstName: "Nicolás",
    lastName: "Maduro Moros",
    imageUrl: "https://pasadita.vercel.app/madurito.png",
    role: "Dictador",
    identifierType: "PASSPORT",
    email: "",
    status: "ACTIVE",
    residence: {
      description: "",
      maxHabitants: 0,
      status: "ACTIVE",
    },
  },
  {
    identifier: "0011904041016S",
    firstName: "David Sebastián",
    lastName: "Parrales Ponce",
    imageUrl: "https://github.com/poncka.png",
    role: "Residente",
    identifierType: "PASSPORT",
    email: "",
    status: "ACTIVE",
    residence: {
      description: "",
      maxHabitants: 0,
      status: "ACTIVE",
    },
  },
];

const residence: Residence = {
  description: "Palacio de Miraflores, República Bolivariana de Venezuela",
  maxHabitants: 5,
  status: "ACTIVE",
};

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form class="overflow-hidden rounded-md bg-white">
      <HeaderModal title="Residente" icon="home" action="view" />
      <div class="flex max-h-[80vh] flex-col gap-4 p-4">
        <div class="flex items-center gap-2 rounded-t-lg text-pasadita-blue-1">
          <VueFeather type="home" size="40" stroke-width="1.5"></VueFeather>
          <div class="flex-1">
            <p class="text-base font-medium">Residencia</p>
            <p class="text-base font-normal">
              {{ residents.length }} / {{ residence.maxHabitants }} Habitantes
            </p>
          </div>
        </div>
        <p>{{ residence.description }}</p>
        <div>
          <span class="font-semibold text-pasadita-blue-1">Residentes</span>
          <ul class="grid gap-4 min-[480px]:grid-cols-2">
            <li class="flex gap-2" :key="index" v-for="(resident, index) in residents">
              <UserImage class="size-12" :image="resident.imageUrl" size="25" padding="0px" />
              <div class="flex-1 text-pasadita-blue-1">
                <p class="text-sm font-semibold">
                  {{ resident.role }}
                </p>
                <p class="text-base font-normal">
                  {{ resident.firstName }} {{ resident.lastName }}
                </p>
              </div>
            </li>
          </ul>
        </div>
      </div>

      <!-- Botones -->
      <div class="flex justify-end gap-2 border-t border-t-pasadita-shade-2 p-2">
        <button
          @click="modal?.close()"
          type="button"
          class="inline-flex items-center rounded-lg bg-pasadita-red-2 p-2 text-center text-sm font-normal text-pasadita-red-0 transition-all hover:rounded-xl hover:bg-pasadita-red-1 active:scale-95"
        >
          <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
          <span>Cancelar</span>
        </button>
      </div>
    </form>
  </Modal>
</template>
