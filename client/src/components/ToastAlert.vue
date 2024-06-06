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
  <div
    class="flex flex-row items-center justify-between gap-2 rounded-md border-2 my-2"
    :class="[toastBackground[toast.type], toastBorder[toast.type]]"
  >
    <div class="flex flex-row items-center p-2" :class="toastColor[toast.type]">
      <div class="flex justify-center items-center flex-shrink-0 w-10 h-10">
        <VueFeather :type="toastIcons[toast.type]" size="25" stroke-width="1.5"></VueFeather>
      </div>

      <p class="font-semibold">{{ toast.message }}</p>
    </div>

    <button
      class="flex flex-row items-center justify-center border-l-2 px-3 group relative"
      :class="[toastColor[toast.type], toastBorder[toast.type]]"
    >
      <div
        class="absolute rounded-full left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2 size-8 transition-colors"
        :class="toastBackgroundDark[toast.type]"
      ></div>
      <VueFeather
        type="x"
        size="16"
        stroke-width="3.5"
        class="group-hover:text-white z-10 ml-[1.5px] transition-colors"
      ></VueFeather>
    </button>
  </div>
</template>

