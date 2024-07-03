<script setup lang="ts">
import VueFeather from "vue-feather";
import UserImage from "@/components/UserImage.vue";
import getFormattedDateTime from "@/utils/getFormattedDateTime";
import type Permission from "@/types/Permission";

enum dic {
  TITLE = "Solicitud de Acceso",
  PERMISSION_APPROVED = "Aprobado",
  PERMISSION_REJECTED = "Rechazado",
  PERMISSION_PENDING = "Pendiente",
  START_DATE = "Fecha de Inicio",
  END_DATE = "Fecha de Fin",
  REQUESTED_BY = "A solicitud de",
  BUTTON_APPROVE = "Aprobar",
  BUTTON_REJECT = "Rechazar"
}

const props = defineProps<{ permission: Permission, showControls?: boolean, showRequestedBy?: boolean }>();

const currentState = props.permission.authorized != undefined ? props.permission.authorized ? dic.PERMISSION_APPROVED : dic.PERMISSION_REJECTED : dic.PERMISSION_PENDING;

</script>

<template>
  <li class="flex flex-col overflow-hidden rounded-lg border border-blue-200 bg-white">
    <!--Information-->
    <div class="flex flex-col gap-2 bg-shades-100 p-2">
      <h2 class="font-semibold text-blue-500">{{ dic.TITLE }}</h2>
      <div class="flex items-center gap-1">
        <UserImage class="size-8" />
        <div class="flex-1 text-blue-400">
          <p class="text-xs leading-none">
            {{ props.permission.invitedUser.identifier }}
          </p>
          <p>
            {{ props.permission.invitedUser.firstName }} {{ props.permission.invitedUser.lastName }}
          </p>
        </div>
      </div>
    </div>
    <!--Description-->
    <ul class="flex w-full flex-1 flex-col justify-center gap-2 p-2 text-blue-500">
      <li class="flex flex-col gap-1">
        <p class="text-xs font-medium">{{ dic.START_DATE }}</p>
        <div class="flex items-center gap-1 text-blue-400">
          <VueFeather type="calendar" class="size-6 min-w-6" stroke-width="1.5"></VueFeather>
          <p class="text-sm">{{ getFormattedDateTime(props.permission.startDate) }}</p>
        </div>
      </li>
      <li class="flex flex-col gap-1">
        <p class="text-xs font-medium">{{ dic.END_DATE }}</p>
        <div class="flex items-center gap-1 text-blue-400">
          <VueFeather type="calendar" class="size-6 min-w-6" stroke-width="1.5"></VueFeather>
          <p class="text-sm">{{ getFormattedDateTime(props.permission.endDate) }}</p>
        </div>
      </li>
    </ul>
    <div class="flex items-center gap-1 p-2" v-if="props.showRequestedBy && props.permission.authorized != undefined">
      <UserImage class="size-8" />
      <div class="flex-1 text-blue-400">
        <p class="text-xs font-medium leading-none text-blue-500">
          {{ dic.REQUESTED_BY }}
        </p>
        <p>
          {{ props.permission.resident.firstName }} {{ props.permission.resident.lastName }}
        </p>
      </div>
    </div>
    <!--CRUD-->
    <div :data-state="currentState"
      class="mt-auto inline-flex w-full items-center justify-center p-2.5 text-center text-sm font-medium transition-all data-[state=Aprobado]:bg-green-100 data-[state=Pendiente]:bg-blue-100 data-[state=Rechazado]:bg-red-100 data-[state=Aprobado]:text-green-400 data-[state=Pendiente]:text-blue-400 data-[state=Rechazado]:text-red-400">
      <span class="capitalize">{{ currentState.toLocaleLowerCase() }}</span>
    </div>
    <div class="flex flex-row justify-end gap-2 p-2" v-if="props.permission.authorized == undefined">
      <button type="button"
        v-if="props.showControls"
        class="inline-flex items-center gap-0.5 rounded-lg bg-red-100 p-2 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-200 active:scale-95">
        <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
        <span>{{ dic.BUTTON_REJECT }}</span>
      </button>
      <button type="button"
        v-if="props.showControls"
        class="inline-flex items-center gap-0.5 rounded-lg bg-green-200 p-2 text-center text-sm font-normal text-green-400 transition-all hover:rounded-xl hover:bg-green-300 active:scale-95">
        <VueFeather type="check" stroke-width="2.5" size="16"></VueFeather>
        <span>{{ dic.BUTTON_APPROVE }}</span>
      </button>
      <button type="button"
        v-if="!props.showControls"
        class="inline-flex items-center rounded-lg bg-red-100 p-2.5 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-200 active:scale-95">
        <VueFeather type="trash-2" stroke-width="2.5" size="16"></VueFeather>
      </button>
      <button type="button"
        v-if="!props.showControls"
        class="inline-flex items-center rounded-lg bg-blue-100 p-2.5 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-200 active:scale-95">
        <VueFeather type="edit-2" stroke-width="2.5" size="16"></VueFeather>
      </button>
    </div>
  </li>
</template>
