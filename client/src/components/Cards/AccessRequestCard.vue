<script setup lang="ts">
import VueFeather from "vue-feather";
import type AccessRequest from "../../types/AccessRequest.ts";

const props = defineProps<{ accessRequest: AccessRequest }>();

function getFormattedDateTime(date: Date): string {
  const formattedDate = date.toLocaleString("es-MX", {
    year: "numeric",
    month: "long",
    day: "numeric",
  });
  const formattedTime = date.toLocaleString("es-MX", {
    hour: "numeric",
    minute: "numeric",
    hour12: false,
  });

  const formattedDateTime = `${formattedDate}-${formattedTime}hrs`;

  return formattedDateTime;
}

function capitalizeFirstChar(word: string): string {
  const firstChar = word.charAt(0).toUpperCase();
  const restWord = word.slice(1);

  console.log(firstChar + restWord);
  return firstChar + restWord;
}

const isPending = props.accessRequest.status == "PENDING";
console.log(isPending);
</script>

<template>
  <li class="flex flex-col bg-white">
    <!--Information-->
    <div
      class="flex flex-col gap-1 rounded-t-lg border border-pasadita-shade-2 bg-pasadita-shade-3 p-2"
    >
      <p class="text-xl font-semibold text-pasadita-blue-1">Solicitud de acceso</p>
      <div class="flex items-center gap-2">
        <div
          class="flex size-10 shrink-0 items-center justify-center rounded-full border-2 border-pasadita-blue-2 bg-pasadita-blue-4"
        >
          <VueFeather type="user" size="25" stroke="#004f82" stroke-width="1.5"></VueFeather>
        </div>
        <div class="flex-1">
          <p class="text-base text-pasadita-blue-1/80">
            {{ props.accessRequest.residentId }}
          </p>
          <p class="text-lg font-medium text-pasadita-blue-1">
            {{ props.accessRequest.residentName }}
          </p>
        </div>
      </div>
    </div>
    <!--Description-->
    <div class="flex flex-row border-x border-pasadita-shade-2 p-2">
      <div class="flex w-full flex-col justify-center gap-2">
        <div class="row flex items-center gap-3 px-2">
          <div class="flex shrink-0 items-center justify-center">
            <VueFeather type="calendar" size="40" stroke="#01193F" stroke-width="1.5"></VueFeather>
          </div>
          <div class="flex flex-col text-pasadita-blue-1">
            <p class="font-semibold">Fecha inicio</p>
            <p>{{ getFormattedDateTime(props.accessRequest.startDate) }}</p>
          </div>
        </div>
        <div class="row flex items-center gap-3 px-2">
          <div class="flex shrink-0 items-center justify-center">
            <VueFeather type="calendar" size="40" stroke="#01193F" stroke-width="1.5"></VueFeather>
          </div>
          <div class="flex flex-col text-pasadita-blue-1">
            <p class="font-semibold">Fecha fin</p>
            <p>{{ getFormattedDateTime(props.accessRequest.endDate) }}</p>
          </div>
        </div>
      </div>
    </div>
    <!--CRUD-->
    <div class="flex flex-row gap-2 rounded-b-lg border border-pasadita-shade-2 p-2">
      <button
        type="button"
        :data-state="props.accessRequest.status"
        :disabled="!isPending"
        :hover="isPending"
        :active="isPending"
        for="text"
        class="inline-flex w-full items-center justify-center rounded-lg bg-pasadita-yellow-0 p-2.5 text-center text-sm font-normal text-pasadita-yellow-2 transition-all hover:rounded-xl hover:bg-pasadita-yellow-1 active:scale-95 disabled:scale-100 disabled:rounded-lg disabled:bg-pasadita-yellow-0/30 data-[state=ACCEPTED]:bg-pasadita-green-2 data-[state=REJECTED]:bg-pasadita-red-1 data-[state=ACCEPTED]:text-pasadita-green-1 data-[state=REJECTED]:text-pasadita-red-0 data-[state=ACCEPTED]:opacity-60 data-[state=REJECTED]:opacity-60"
      >
        <p>Estado: {{ capitalizeFirstChar(props.accessRequest.status.toLowerCase()) }}</p>
      </button>
      <button
        v-if="isPending"
        type="button"
        class="inline-flex w-[2.625rem] items-center rounded-lg bg-pasadita-red-2 p-2.5 text-center text-sm font-normal text-pasadita-red-0 transition-all hover:rounded-xl hover:bg-pasadita-red-1 active:scale-95"
      >
        <VueFeather type="trash-2" stroke-width="2.5" size="16"></VueFeather>
      </button>
      <button
        v-if="isPending"
        type="button"
        class="inline-flex w-[2.625rem] items-center rounded-lg bg-pasadita-blue-4 p-2.5 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-3 hover:text-white active:scale-95"
      >
        <VueFeather type="edit-2" stroke-width="2.5" size="16"></VueFeather>
      </button>
    </div>
  </li>
</template>
