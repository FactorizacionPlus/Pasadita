<script setup lang="ts">
import { defineProps } from "vue";
import VueFeather from "vue-feather";
import type Toast from "../types/Toast";
import { ToastType } from "../types/Toast";

const toastBackground: { [key in ToastType]: string } = {
  [ToastType.INFO]: "bg-pasadita-blue-4",
  [ToastType.ERROR]: "bg-pasadita-red-2",
  [ToastType.SUCCESS]: "bg-pasadita-green-3",
  [ToastType.WARNING]: "bg-pasadita-yellow-0",
};

const toastBackgroundDark: { [key in ToastType]: string } = {
  [ToastType.INFO]: "group-hover:bg-pasadita-blue-3",
  [ToastType.ERROR]: "group-hover:bg-pasadita-red-0",
  [ToastType.SUCCESS]: "group-hover:bg-pasadita-green-1",
  [ToastType.WARNING]: "group-hover:bg-pasadita-yellow-2",
};

const toastBorder: { [key in ToastType]: string } = {
  [ToastType.INFO]: "border-pasadita-blue-3",
  [ToastType.ERROR]: "border-pasadita-red-0",
  [ToastType.SUCCESS]: "border-pasadita-green-1",
  [ToastType.WARNING]: "border-pasadita-yellow-2",
};

const toastIcons: { [key in ToastType]: string } = {
  [ToastType.INFO]: "info",
  [ToastType.ERROR]: "x-circle",
  [ToastType.SUCCESS]: "check",
  [ToastType.WARNING]: "alert-triangle",
};

const toastColor: { [key in ToastType]: string } = {
  [ToastType.INFO]: "text-pasadita-blue-3",
  [ToastType.ERROR]: "text-pasadita-red-0",
  [ToastType.SUCCESS]: "text-pasadita-green-1",
  [ToastType.WARNING]: "text-pasadita-yellow-2",
};

const props = defineProps<{ toast: Toast }>();
</script>

<template>
  <div class="my-2 flex flex-row items-center justify-between gap-2 rounded-md border-2"
    :class="[toastBackground[props.toast.type], toastBorder[props.toast.type]]">
    <div class="flex flex-row items-center p-2" :class="toastColor[props.toast.type]">
      <div class="flex size-10 shrink-0 items-center justify-center">
        <VueFeather :type="toastIcons[props.toast.type]" size="25" stroke-width="1.5"></VueFeather>
      </div>

      <p class="font-semibold">{{ props.toast.message }}</p>
    </div>

    <button class="group relative flex flex-row items-center justify-center border-l-2 px-3"
      :class="[toastColor[props.toast.type], toastBorder[props.toast.type]]">
      <div class="absolute left-1/2 top-1/2 size-8 -translate-x-1/2 -translate-y-1/2 rounded-full transition-colors"
        :class="toastBackgroundDark[props.toast.type]"></div>
      <VueFeather type="x" size="16" stroke-width="3.5"
        class="z-10 ml-[1.5px] transition-colors group-hover:text-white"></VueFeather>
    </button>
  </div>
</template>
