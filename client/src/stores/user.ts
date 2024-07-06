import { defineStore } from "pinia";
import { computed, ref, watch } from "vue";
import { useAuth } from "./auth";
import type RegisteredUser from "@/types/User/RegisteredUser";

export const useUser = defineStore("user", () => {
  const auth = useAuth();

  const userData = localStorage.getItem("user");
  const userRef = ref<RegisteredUser | null>(null);
  if (userData) {
    try {
      userRef.value = JSON.parse(userData);
    } catch (e) {
      localStorage.removeItem("user");
    }
  }

  const user = computed(() => userRef.value);

  function setUser(user: RegisteredUser) {
    if (user == null || user == undefined) {
      return;
    }
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
