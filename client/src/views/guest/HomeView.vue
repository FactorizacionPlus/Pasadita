<script setup lang="ts">
import { onMounted, ref } from "vue";
import ModalActive from "@/components/Modal/Guest/PermissionActive.vue";
import ModalNoActive from "@/components/Modal/Guest/NoActivePermission.vue";
import ModalAdd from "@/components/Modal/Guest/AddGuest.vue";
import SquareButton from "@/components/SquareButton.vue";
import Pasadita from '../../assets/PasaditaLogo.svg?url'
import type ActivePermission from "@/types/Guest/ActivePermission";
import { useUser } from "@/stores/user";
import { getOwnEntry } from "@/composables/usePermission";
import type Permission from "@/types/Permission";

const modalActive = ref<typeof ModalActive>();
const modalNoActive = ref<typeof ModalNoActive>();
const modalAdd = ref<typeof ModalAdd>();
const user = useUser();
const identifier = user.user?.identifier;
const showModal = ref(false); // Estado para alternar entre Menu1 y Menu2
const permission = ref<Permission>();

function toggleMenu() {
  showModal.value = !showModal.value;
}

onMounted(async () => {
  await fetchPermission();

  if (identifier == null) {
    modalAdd.value?.show()
    showModal.value = true;
  }
})

async function fetchPermission() {
  const { data } = await getOwnEntry();
  const record = data.value;
  //const record = { ok: false, data: null };
  if (!record || !record.ok) return;
  const endDate = new Date(record.data.endDate);
  const now = new Date();
  console.log(endDate)
  console.log(now)
  if (endDate > now) {
    permission.value = record.data;
  }
}

function handleSquareButtonClick() {
  if (permission.value) {
    modalActive.value?.show();
  } else {
    modalNoActive.value?.show();
  }
}
</script>

<template>
  <div class="grid h-screen place-items-center">
    <div v-if="showModal" class="flex max-w-[488px] gap-4">
      <img :src="Pasadita" alt="Pasadita">
      <div class="flex flex-col items-start justify-start gap-2">
        <p class="text-2xl font-medium text-blue-400">Debes completar el registro de usuario para continuar</p>
        <button
          class="inline-flex items-center gap-0.5 rounded-lg bg-white p-2 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-shades-100 active:scale-95"
          @click="modalAdd?.show()">
          A Finalizar registro
        </button>
      </div>
    </div>
    <SquareButton v-else type="button" icon="custom-days-clock" title="Ver Permiso Activo"
      @click="handleSquareButtonClick" />
  </div>
  <ModalActive v-if="permission" :activePermission="permission" ref="modalActive" />
  <!--<ModalActive :active-permission="activePermission" ref="modalActive" />-->
  <ModalNoActive ref="modalNoActive" />
  <ModalAdd ref="modalAdd" @toggle-menu="toggleMenu" />
</template>
