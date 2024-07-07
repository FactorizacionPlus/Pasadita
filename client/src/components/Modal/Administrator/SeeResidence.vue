<script setup lang="ts">
import { ref, onMounted } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import BodyModal from "@/components/Modal/BodyModal.vue";
import ControlsModal from "../ControlsModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import UserImage from "@/components/UserImage.vue";
import type Residence from "@/types/Residence";
import type Resident from "@/types/User/Resident";
import { getResidentByResidence } from "@/composables/useResidence";

// Definición de props
const props = defineProps<{ residence: Residence }>();

// Variables reactivas y referencias
const modal = ref<typeof Modal>();
const resident = ref<Resident[]>([]);
const par = props.residence.uuid;

// Función para obtener los residentes
async function fetchResidence() {
  const { data } = await getResidentByResidence(par as string);
  resident.value = data.value?.data ?? [];
}

// Fetch de los residentes al montar el componente
onMounted(async () => {
  await fetchResidence();
  console.log(par);
  console.log(resident.value);
});

// Exposición de métodos para el modal
defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form class="w-full max-w-xl overflow-hidden rounded-md bg-white">
      <HeaderModal title="Residente" icon="home" action="view" />
      <BodyModal>
        <!-- Información de la residencia -->
        <div class="flex items-center gap-2 rounded-t-lg text-pasadita-blue-1">
          <VueFeather type="home" size="40" stroke-width="1.5"></VueFeather>
          <div class="flex-1">
            <p class="text-base font-medium">Residencia</p>
            <p class="text-base font-normal">
              {{ props.residence.habitants }} / {{ props.residence.maxHabitants }} Habitantes
            </p>
          </div>
        </div>
        <p>{{ props.residence.description }}</p>
        <!-- Lista de residentes -->
        <div class="flex flex-col gap-2">
          <span class="font-semibold text-pasadita-blue-1">Residentes</span>
          <ul class="grid gap-4 min-[480px]:grid-cols-2">
            <li class="flex gap-2" :key="index" v-for="(resident, index) in resident">
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
      </BodyModal>
      <ControlsModal>
        <!-- Botón de Cancelar -->
        <button
          @click="modal?.close()"
          type="button"
          class="inline-flex items-center rounded-lg bg-red-100 p-2 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-200 active:scale-95"
        >
          <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
          <span>Cancelar</span>
        </button>
      </ControlsModal>
    </form>
  </Modal>
</template>
