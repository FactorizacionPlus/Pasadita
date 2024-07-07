import type Preference from "@/types/Preferences";
import { defineStore } from "pinia";
import { computed, ref } from "vue";

function createInitialData(): Preference {
  const initialData: Preference = {
    highContrast: false,
    dyslexicFont: false,
    fontSize: "NORMAL",
  }
  return initialData;
}

export const usePreferences = defineStore("preferences", () => {
  const initialData = localStorage.getItem("preferences");
  const preferencesRef = ref<Preference>(createInitialData());
  if (initialData) {
    try {
      preferencesRef.value = JSON.parse(initialData);
    } catch (e) {
      localStorage.removeItem("preferences");
    }
  } else {
    resetData();
  }

  function resetData() {
    const initialData = createInitialData()
    setPreferences(initialData);
  }

  const preferences = computed(() => preferencesRef.value);

  function setPreferences(preferences: Preference) {
    preferencesRef.value = preferences;
    localStorage.setItem("preferences", JSON.stringify(preferences));
  };

  return { preferences, setPreferences, resetData };
});
