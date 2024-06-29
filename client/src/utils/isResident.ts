import type Invited from "@/types/Invited";
import type Resident from "@/types/Resident";

export default function isResident(user: Resident | Invited): user is Resident {
  return (user as Resident).role !== undefined;
}
