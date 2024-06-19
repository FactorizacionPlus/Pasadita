<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import EntryCard from "@/components/Cards/EntryCard.vue";
import type EntryCardT from "@/types/EntryCard";

const modal = ref<typeof Modal>();

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});

const entryList: EntryCardT[] = [
  {
    id: 0,
    user: {
      identifier: "0011904041016S",
      imageUrl: "https://www.github.com/davidquintr.png",
      firstName: "David",
      lastName: "Quintanilla",
      role: "Residente Encargado",
    },
    description: "Rumbo a la casas de los pueblos",
    entryDate: new Date("10-04-2005"),
  },
  {
    id: 1,
    user: {
      identifier: "0011904041016S",
      imageUrl: "https://www.github.com/poncka.png",
      firstName: "David",
      lastName: "Parrales Ponce",
      role: "Residente",
    },
    description: "Rumbo a la casas de los pueblos",
    entryDate: new Date("10-04-2015 12:00"),
  },
  {
    id: 2,
    user: {
      identifier: "0011904041016S",
      imageUrl: "https://www.github.com/JezerM.png",
      firstName: "Jezer",
      lastName: "Mejía Otero",
      role: "Residente",
    },
    description: "Rumbo a la casas de los pueblos",
    entryDate: new Date("10-04-2015 12:00"),
  },
  {
    id: 3,
    user: {
      identifier: "0011904041016S",
      imageUrl: "https://www.github.com/Mrsty21.png",
      firstName: "Marcelo",
      lastName: "Rivera Soto",
      role: "Residente",
    },
    description: "Rumbo a la casas de los pueblos",
    entryDate: new Date("10-04-2015 12:00"),
  },
  {
    id: 4,
    user: {
      identifier: "0011904041016S",
      imageUrl: "https://www.github.com/leocorea.png",
      firstName: "Leo",
      lastName: "Corea Navarrete",
      role: "Residente",
    },
    description: "Rumbo a la casas de los pueblos",
    entryDate: new Date("10-04-2015 12:00"),
  },
  {
    id: 4,
    user: {
      identifier: "0011904041016S",
      imageUrl:
        "https://phantom-marca.unidadeditorial.es/ed45b2f6e1292d0ab52646ae08d9e1a6/crop/0x0/814x458/resize/828/f/jpg/assets/multimedia/imagenes/2022/03/07/16466725254649.jpg",
      firstName: "Daniel José",
      lastName: "Santomé Lemus",
      role: "Residente",
    },
    description: "Rumbo a la casas de los pueblos",
    entryDate: new Date("10-04-2015 12:00"),
  },
];
</script>

<template>
  <Modal ref="modal">
    <form class="w-full max-w-[540px] overflow-hidden rounded-md bg-white">
      <HeaderModal title="Terminal" icon="tablet" action="view" />
      <ul class="flex max-h-[80vh] flex-col gap-4 overflow-auto p-4">
        <EntryCard :entry-card="item" :key="index" v-for="(item, index) in entryList" />
      </ul>
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
