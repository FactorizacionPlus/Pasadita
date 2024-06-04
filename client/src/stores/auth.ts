import { defineStore } from "pinia";
import { computed, ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

export const useAuth = defineStore("auth", () => {
  const authToken = localStorage.getItem("authToken");
  const tokenRef = ref<string | null>(null);
  if (authToken) {
    tokenRef.value = authToken;
  }

  const token = computed(() => tokenRef.value);

  function setToken(tokenContent: string) {
    localStorage.setItem("authToken", tokenContent);
    tokenRef.value = tokenContent;
  }

  function logout() {
    localStorage.removeItem("authToken");
    tokenRef.value = null;

    router.push("/login");
  }

  return { token, setToken, logout };
});
