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
import SimpleAlert from "@/components/SimpleAlert.vue";
import { AlertType } from "@/types/Alert";
import getUserRole from "@/utils/getUserRole";

enum MESSAGE {
  RESIDENCE_INFO = "Información de la Residencia",
  PEOPLE = "Habitantes",
  NO_PEOPLE_WARN = "Actualmente no existen habitantes en esta Residencia."
}

// Definición de props
const props = defineProps<{ residence: Residence }>();

// Variables reactivas y referencias
const modal = ref<typeof Modal>();
const residents = ref<Resident[]>([]);
const par = props.residence.uuid;

// Función para obtener los residentes
async function fetchResidence() {
  const { data } = await getResidentByResidence(par as string);
  residents.value = data.value?.data ?? [];
  console.log(data.value)
}

// Fetch de los residentes al montar el componente
onMounted(async () => {
  await fetchResidence();
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
        <div class="flex flex-col gap-2">
          <p class="text-sm font-medium">{{ MESSAGE.RESIDENCE_INFO }}</p>
          <div class="flex items-center gap-2 rounded-t-lg text-blue-400">
            <VueFeather type="home" class="size-6" stroke-width="1.5"></VueFeather>
            <div class="flex-1">
              <p class="text-base font-normal">
                {{ props.residence.habitants }} / {{ props.residence.maxHabitants }} {{ MESSAGE.PEOPLE }}
              </p>
            </div>
          </div>
          <p>{{ props.residence.description }}</p>
        </div>
        <!-- Lista de residentes -->
        <div class="flex flex-col gap-2 text-blue-400">
          <span class="text-sm font-semibold">Residentes</span>
          <ul class="grid gap-4 min-[480px]:grid-cols-2" v-if="residents.length > 0">
            <li class="flex gap-2" :key="index" v-for="(resident, index) in residents">
              <UserImage class="size-12" :image="resident.imageUrl" size="25" padding="0px" />
              <div class="flex-1">
                <p class="text-sm font-semibold">
                  {{ getUserRole(resident.role) }}
                </p>
                <p class="text-base font-normal">
                  {{ resident.firstName }} {{ resident.lastName }}
                </p>
              </div>
            </li>
          </ul>
          <SimpleAlert v-else  :alert="{message: MESSAGE.NO_PEOPLE_WARN, type: AlertType.INFO}" />
        </div>
      </BodyModal>
      <ControlsModal>
        <!-- Botón de Cancelar -->
        <button @click="modal?.close()" type="button"
          class="inline-flex items-center rounded-lg bg-red-100 p-2 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-200 active:scale-95">
          <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
          <span>Cancelar</span>
        </button>
      </ControlsModal>
    </form>
  </Modal>
</template>
