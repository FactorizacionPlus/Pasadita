import type Toast from "@/types/Toast";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useToast = defineStore("toast", () => {
  const toasts = ref<Toast[]>([]);

  const addToast = (toast: Toast) => {
    toast.id = Math.floor(Math.random() * 1000);
    toasts.value.push(toast);
  };

  const removeToast = (id: number) => {
    toasts.value = toasts.value.filter((toast) => toast.id !== id);
  };

  return {
    toasts,
    addToast,
    removeToast,
  };
});