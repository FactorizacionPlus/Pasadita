import type TerminalLogin from "@/types/TerminalLogin";
import { defineStore } from "pinia";
import { computed, ref } from "vue";
import { useRouter } from "vue-router";

export const useTerminal = defineStore("terminal", () => {
  const router = useRouter();

  const terminalData = localStorage.getItem("terminalLogin");
  const terminalRef = ref<TerminalLogin | null>(null);
  if (terminalData) {
    terminalRef.value = JSON.parse(terminalData);
  }

  const terminalLogin = computed(() => terminalRef.value);

  function setTerminalLogin(terminal: TerminalLogin) {
    localStorage.setItem("terminalLogin", JSON.stringify(terminal));
    terminalRef.value = terminal;
  }

  function logout() {
    localStorage.removeItem("terminalLogin");
    terminalRef.value = null;

    if (router) {
      router.push("/auth/terminal");
    }
  }

  return { terminalLogin, setTerminalLogin, logout };
});
