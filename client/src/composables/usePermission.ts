import { ref } from "vue";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type { SavePermissionDto } from "@/types/Permission";

export function usePermission() {
  const error = ref<string | null>(null);
  const loading = ref(false);

  async function createPermission(dto: SavePermissionDto): Promise<void> {
    loading.value = true;
    error.value = null;

    try {
      const { data, error: fetchError } = await useAuthenticatedFetch("/api/permission", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(dto),
      }).json();
    } catch (err) {
      error.value = err instanceof Error ? err.message : "An unexpected error occurred";
    } finally {
      loading.value = false;
    }
  }

  return {
    createPermission,
    error,
    loading,
  };
}
