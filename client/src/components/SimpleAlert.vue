<script setup lang="ts">
import { defineProps } from "vue";
import VueFeather from "vue-feather";
import type Alert from "@/types/Alert";
import { AlertType } from "@/types/Alert";

const toastBackground: { [key in AlertType]: string } = {
    [AlertType.INFO]: "bg-blue-100",
    [AlertType.ERROR]: "bg-red-100",
    [AlertType.SUCCESS]: "bg-green-100",
    [AlertType.WARNING]: "bg-yellow-100",
};

const toastBorder: { [key in AlertType]: string } = {
    [AlertType.INFO]: "border-blue-200",
    [AlertType.ERROR]: "border-red-200",
    [AlertType.SUCCESS]: "border-green-200",
    [AlertType.WARNING]: "border-yellow-200",
};

const toastIcons: { [key in AlertType]: string } = {
    [AlertType.INFO]: "info",
    [AlertType.ERROR]: "x-circle",
    [AlertType.SUCCESS]: "check",
    [AlertType.WARNING]: "alert-triangle",
};

const toastColor: { [key in AlertType]: string } = {
    [AlertType.INFO]: "text-blue-400",
    [AlertType.ERROR]: "text-red-400",
    [AlertType.SUCCESS]: "text-green-400",
    [AlertType.WARNING]: "text-yellow-400",
};

const props = defineProps<{ alert: Alert }>();
</script>

<template>
    <div class="flex items-center gap-1 rounded-md border p-1 text-sm"
        :class="[toastBackground[props.alert.type], toastBorder[props.alert.type], toastColor[props.alert.type]]">
        <VueFeather :type="toastIcons[props.alert.type]" class="size-4" stroke-width="2"></VueFeather>
        <p>{{ props.alert.message }}</p>
    </div>
</template>
