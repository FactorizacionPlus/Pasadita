import type { StatusType } from "../StatusType";
import type User from "./User";
import type { UserRole } from "./UserRole";

export default interface RegisteredUser extends User {
  email: string;
  imageUrl?: string;
  status: StatusType;
  role: UserRole;
}
