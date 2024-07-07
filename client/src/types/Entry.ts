import type Residence from "./Residence";
import type Terminal from "./Terminal";
import type User from "./User/User";

export default interface Entry {
  uuid: string;
  accessDate: string;
  description: string;
  terminal: Terminal;
  user: User;
  residence: Residence
}
