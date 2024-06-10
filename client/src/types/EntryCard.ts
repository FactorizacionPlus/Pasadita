import type User from "./User";

export default interface EntryCard {
  id: number;
  entryDate: Date;
  description: string;
  user: User;
}
