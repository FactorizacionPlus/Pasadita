import type { UserRole } from "@/types/User/UserRole";

const role: { [key in UserRole]: string } = {
  ROLE_INVITED: "Invitado",
  ROLE_RESIDENT: "Residente",
  ROLE_RESIDENT_SUDO: "Residente Encargado",
  ROLE_ADMIN: "Administrador",
};

export default function getUserRole(userRole: UserRole | undefined): string {
  if (userRole === undefined) {
    return "Anónimo";
  }
  return role[userRole];
}
