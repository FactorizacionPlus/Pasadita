import { defineStore } from "pinia";
import { ref } from "vue";

export const usePrefferedCamera = defineStore("prefferedCamera", () => {
  const initialData = localStorage.getItem("prefferedCamera");
  const prefferedCamera = ref<string>();
  if (initialData) {
    prefferedCamera.value = initialData;
  }

  const setPrefferedCamera = (id: string) => {
    prefferedCamera.value = id;
    localStorage.setItem("prefferedCamera", id);
  };

  return { id: prefferedCamera, setId: setPrefferedCamera };
});
