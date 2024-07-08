<script setup lang="ts">
import { ref, onMounted } from "vue";
import VueFeather from "vue-feather";
import SearchBar from "@/components/SearchBar.vue";
import ModalAdd from "@/components/Modal/Resident/AddResidentModal.vue";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import AccessRequestCard from "@/components/Cards/AccessRequestCard.vue";
import { getOwnPermissions } from "@/composables/useListPermissions";
import type Permission from "@/types/Permission";
import { matchSearch } from "@/utils/matchSearch";

enum Message {
  TITLE = "Mis Solicitudes",
}

const modalAdd = ref<typeof ModalAdd>();
const searchText = ref("");
const hideNoResults = ref(false);
const fieldsToSearch = [
  "invitedUser.firstName",
  "invitedUser.lastName",
  "invitedUser.identifier",
  "residence.description",
];

const permissions = ref<Permission[]>([]);

onMounted(async () => {
  await loadPermissions();
});

async function loadPermissions() {
  const { data } = await getOwnPermissions();
  const permissionData = data.value;

  if (!permissionData || !permissionData.ok) return;
  permissions.value = permissionData.data ?? [];
}

const handlePermissionDeleted = (permissionId: string) => {
  permissions.value = permissions.value.filter((p) => p.uuid !== permissionId);
};

const handlePermissionAdded = () => {
  loadPermissions();
};
</script>

<template>
  <article class="flex flex-col gap-8 rounded-lg bg-white p-4">
    <CurrentPageInfo class="p-0" :title="Message.TITLE" icon="folder">
      <button
        class="inline-flex items-center rounded-lg bg-blue-100 p-2 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-200 active:scale-95"
        @click="modalAdd?.show()"
      >
        <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
        <span>Solicitar un permiso</span>
      </button>
    </CurrentPageInfo>

    <SearchBar @search="searchText = $event" @toggle-no-results="hideNoResults = $event" />

    <ul class="grid w-full gap-4 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
      <AccessRequestCard
        :class="{
          'animate-scale-up border-2 border-blue-400':
            matchSearch(item, searchText, fieldsToSearch) && searchText.length > 2,
          hidden:
            !matchSearch(item, searchText, fieldsToSearch) &&
            hideNoResults &&
            searchText.length > 2,
        }"
        :permission="item"
        :key="index"
        v-for="(item, index) in permissions"
        @delete-permission="handlePermissionDeleted"
      />
    </ul>
  </article>
  <ModalAdd ref="modalAdd" @permission-added="handlePermissionAdded">Hola</ModalAdd>
</template>
