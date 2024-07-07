<script setup lang="ts">
import { defineProps, onMounted, ref } from "vue";
import VueFeather from "vue-feather";
import type Toast from "../types/Toast";
import { ToastType } from "../types/Toast";

const toastBackground: { [key in ToastType]: string } = {
  [ToastType.INFO]: "bg-white",
  [ToastType.LOADING]: "bg-white",
  [ToastType.ERROR]: "bg-red-100",
  [ToastType.SUCCESS]: "bg-green-100",
  [ToastType.WARNING]: "bg-yellow-100",
};

const toastBackgroundDark: { [key in ToastType]: string } = {
  [ToastType.INFO]: "group-hover:bg-blue-200",
  [ToastType.LOADING]: "group-hover:bg-blue-200",
  [ToastType.ERROR]: "group-hover:bg-red-200",
  [ToastType.SUCCESS]: "group-hover:bg-green-200",
  [ToastType.WARNING]: "group-hover:bg-yellow-200",
};

const toastBorder: { [key in ToastType]: string } = {
  [ToastType.INFO]: "border-blue-200",
  [ToastType.LOADING]: "border-blue-200",
  [ToastType.ERROR]: "border-red-200",
  [ToastType.SUCCESS]: "border-green-200",
  [ToastType.WARNING]: "border-yellow-200",
};

const toastIcons: { [key in ToastType]: string } = {
  [ToastType.INFO]: "info",
  [ToastType.LOADING]: "loader",
  [ToastType.ERROR]: "x-circle",
  [ToastType.SUCCESS]: "check",
  [ToastType.WARNING]: "alert-triangle",
};

const toastColor: { [key in ToastType]: string } = {
  [ToastType.INFO]: "text-blue-400",
  [ToastType.LOADING]: "text-blue-400",
  [ToastType.ERROR]: "text-red-400",
  [ToastType.SUCCESS]: "text-green-400",
  [ToastType.WARNING]: "text-yellow-400",
};

const props = defineProps<{ toast: Toast }>();

const li = ref<HTMLLIElement | undefined>();
onMounted(() => {
  setTimeout(() => {
    li.value?.remove();
  }, 10000);
});
</script>

<template>
  <li
    ref="li"
    class="my-2 flex animate-slide-left flex-row items-center justify-between gap-2 rounded-md border-2 border-b-4"
    :class="[toastBackground[props.toast.type], toastBorder[props.toast.type]]"
  >
    <div class="flex flex-row items-center gap-1 p-2" :class="toastColor[props.toast.type]">
      <VueFeather
        :type="toastIcons[props.toast.type]"
        class="aspect-square size-5 min-w-5"
        :class="props.toast.type == ToastType.LOADING && 'animate-spin'"
        stroke-width="1.5"
      >
      </VueFeather>
      <p>{{ props.toast.message }}</p>
    </div>

    <button
      @click="
        () => {
          li?.remove();
        }
      "
      class="group relative flex flex-row items-center justify-center border-l-2 px-3"
      :class="[toastColor[props.toast.type], toastBorder[props.toast.type]]"
    >
      <div
        class="absolute left-1/2 top-1/2 size-8 -translate-x-1/2 -translate-y-1/2 rounded-full transition-colors"
        :class="toastBackgroundDark[props.toast.type]"
      ></div>
      <VueFeather
        type="x"
        size="16"
        stroke-width="3.5"
        class="z-10 ml-[1.5px] transition-all group-active:scale-90"
      >
      </VueFeather>
    </button>
  </li>
</template>
