import type { StatusType } from "./StatusType";

export default interface Residence {
  description: string;
  maxHabitants: number;
  status: StatusType
}
