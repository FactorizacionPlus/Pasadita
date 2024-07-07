import type { StatusType } from "./StatusType";

export default interface Residence {
  uuid: string;
  description: string;
  maxHabitants: number;
  habitants: number;
  status: StatusType;
}
