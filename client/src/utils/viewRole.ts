import { RoleRootPath, type ViewRole } from "@/router";
import { useTerminal } from "@/stores/terminal";
import { useUser } from "@/stores/user";

export function getViewRole(): ViewRole | null {
  const user = useUser();
  const terminal = useTerminal();

  let role: ViewRole | null = null;
  if (user.user) {
    role = user.user.role;
  } else if (terminal.terminalLogin) {
    if (terminal.terminalLogin.type == "MANUAL") {
      role = "TERMINAL_MANUAL";
    } else {
      role = "TERMINAL";
    }
  }
  return role;
}

export function getRootPath(): string | null {
  const viewRole = getViewRole();
  if (viewRole == null) {
    return null;
  }
  return RoleRootPath[viewRole];
}
