import type TerminalLogin from "@/types/TerminalLogin";
import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const useTerminal = defineStore("terminal", () => {
  const terminalData = localStorage.getItem("terminal-login");
  const terminalRef = ref<TerminalLogin | null>(null);
  if (terminalData) {
    terminalRef.value = JSON.parse(terminalData);
  }

  const terminalLogin = computed(() => terminalRef.value);

  function setTerminalLogin(terminal: TerminalLogin) {
    localStorage.setItem("terminal-login", JSON.stringify(terminal));
    terminalRef.value = terminal;
  }

  return { terminalLogin, setTerminalLogin };
});
