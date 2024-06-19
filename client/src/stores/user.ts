import type User from "@/types/User";
import { defineStore } from "pinia";
import { computed, ref, watch } from "vue";
import { useAuth } from "./auth";

export const useUser = defineStore("user", () => {
  const auth = useAuth();

  const userData = localStorage.getItem("user");
  const userRef = ref<User | null>(null);
  if (userData) {
    userRef.value = JSON.parse(userData);
  }

  const user = computed(() => userRef.value);

  function setUser(user: User) {
    localStorage.setItem("user", JSON.stringify(user));
    userRef.value = user;
  }

  watch(
    () => auth.token,
    (token) => {
      if (token == null) {
        localStorage.removeItem("user");
        userRef.value = null;
      }
    }
  );

  return { user, setUser };
});
