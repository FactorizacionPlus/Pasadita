<script setup lang="ts">
import VueFeather from "vue-feather";
import type AccessRequest from "../../types/AccessRequest.ts";
import UserImage from "@/components/UserImage.vue";
import getFormattedDateTime from "@/utils/getFormattedDateTime";

const props = defineProps<{ accessRequest: AccessRequest, controls?: boolean }>();
const isPending = props.accessRequest.status == "PENDING";
</script>

<template>
  <li class="flex flex-col bg-white">
    <!--Information-->
    <div class="flex flex-col gap-2 rounded-t-lg border border-pasadita-shade-2 bg-pasadita-blue-5 p-2">
      <p class="text-xl font-semibold text-pasadita-blue-1">Solicitud de acceso</p>
      <div class="flex items-center gap-2">
        <UserImage class="size-12" :image="accessRequest.image" size="25" padding="0px" />
        <div class="flex-1">
          <p class="text-base leading-none text-pasadita-blue-1/80">
            {{ props.accessRequest.residentId }}
          </p>
          <p class="text-lg font-medium text-pasadita-blue-1">
            {{ props.accessRequest.residentName }}
          </p>
        </div>
      </div>
    </div>
    <!--Description-->
    <div class="flex flex-row border-x border-pasadita-shade-2 p-4">
      <ul class="flex w-full flex-col justify-center gap-2 text-pasadita-blue-1">
        <li class="flex items-center gap-2">
          <VueFeather type="calendar" size="40" stroke="#01193F" stroke-width="1.5"></VueFeather>
          <div class="flex flex-col">
            <p class="font-semibold">Fecha de inicio</p>
            <p>{{ getFormattedDateTime(props.accessRequest.startDate) }}</p>
          </div>
        </li>
        <li class="flex items-center gap-2">
          <VueFeather type="calendar" size="40" stroke="#01193F" stroke-width="1.5"></VueFeather>
          <div class="flex flex-col">
            <p class="font-semibold">Fecha de fin</p>
            <p>{{ getFormattedDateTime(props.accessRequest.endDate) }}</p>
          </div>
        </li>
      </ul>
    </div>
    <!--CRUD-->
    <div class="flex flex-row gap-2 rounded-b-lg border border-pasadita-shade-2 p-2">
      <button type="button" :data-state="props.accessRequest.status" :disabled="!isPending" :hover="isPending"
        :active="isPending" for="text"
        class="inline-flex w-full items-center justify-center rounded-lg bg-pasadita-yellow-0 p-2.5 text-center text-sm font-normal text-pasadita-yellow-2 transition-all hover:rounded-xl hover:bg-pasadita-yellow-1 active:scale-95 disabled:scale-100 disabled:rounded-lg disabled:bg-pasadita-yellow-0/30 data-[state=ACCEPTED]:bg-pasadita-green-2 data-[state=REJECTED]:bg-pasadita-red-1 data-[state=ACCEPTED]:text-pasadita-green-1 data-[state=REJECTED]:text-pasadita-red-0 data-[state=ACCEPTED]:opacity-60 data-[state=REJECTED]:opacity-60">
        <p>
          Estado:
          <span class="capitalize">{{ props.accessRequest.status.toLocaleLowerCase() }}</span>
        </p>
      </button>
      <div v-if="props.controls" class="flex flex-row gap-2">
        <button v-if="isPending" type="button"
          class="inline-flex w-[2.625rem] items-center justify-center rounded-lg bg-pasadita-red-2 p-2.5 text-center text-sm font-normal text-pasadita-red-0 transition-all hover:rounded-xl hover:bg-pasadita-red-1 active:scale-95">
          <VueFeather type="trash-2" stroke-width="2.5" size="16"></VueFeather>
        </button>
        <button v-if="isPending" type="button"
          class="inline-flex w-[2.625rem] items-center justify-center rounded-lg bg-pasadita-blue-4 p-2.5 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-6 active:scale-95">
          <VueFeather type="edit-2" stroke-width="2.5" size="16"></VueFeather>
        </button>
      </div>
    </div>
  </li>
</template>
