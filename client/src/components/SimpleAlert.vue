<script setup lang="ts">
import { defineProps } from "vue";
import VueFeather from "vue-feather";
import type Toast from "../types/Toast";
import { ToastType } from "../types/Toast";

const toastBackground: { [key in ToastType]: string } = {
    [ToastType.INFO]: "bg-blue-100",
    [ToastType.ERROR]: "bg-red-100",
    [ToastType.SUCCESS]: "bg-green-100",
    [ToastType.WARNING]: "bg-yellow-100",
};

const toastBorder: { [key in ToastType]: string } = {
    [ToastType.INFO]: "border-blue-300",
    [ToastType.ERROR]: "border-red-300",
    [ToastType.SUCCESS]: "border-green-300",
    [ToastType.WARNING]: "border-yellow-300",
};

const toastIcons: { [key in ToastType]: string } = {
    [ToastType.INFO]: "info",
    [ToastType.ERROR]: "x-circle",
    [ToastType.SUCCESS]: "check",
    [ToastType.WARNING]: "alert-triangle",
};

const toastColor: { [key in ToastType]: string } = {
    [ToastType.INFO]: "text-blue-400",
    [ToastType.ERROR]: "text-red-400",
    [ToastType.SUCCESS]: "text-green-400",
    [ToastType.WARNING]: "text-yellow-400",
};

const props = defineProps<{ toast: Toast }>();
</script>

<template>
    <div class="flex items-center gap-1 rounded-md border-2 p-2"
        :class="[toastBackground[props.toast.type], toastBorder[props.toast.type], toastColor[props.toast.type]]">
        <VueFeather :type="toastIcons[props.toast.type]" class="size-5" stroke-width="2"></VueFeather>
        <p>{{ props.toast.message }}</p>
    </div>
</template>
