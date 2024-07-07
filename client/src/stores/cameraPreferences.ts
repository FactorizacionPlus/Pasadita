import { reactive, watch } from "vue";
import { defineStore } from "pinia";

export interface CameraPreference {
  cameraId: string | null;
  useTorch: boolean;
}

function createInitialData(): CameraPreference {
  return {
    cameraId: null,
    useTorch: false,
  };
}

export const useCameraPreferences = defineStore("preferredCamera", () => {
  const initialData = localStorage.getItem("preferredCamera");
  let data = createInitialData();
  if (initialData) {
    try {
      data = JSON.parse(initialData);
    } catch (e) {
      localStorage.removeItem("preferredCamera");
    }
  }

  const preferencesRef = reactive<CameraPreference>(data);

  watch(preferencesRef, (v) => {
    localStorage.setItem("preferredCamera", JSON.stringify(v));
  });

  return { preferences: preferencesRef };
});
