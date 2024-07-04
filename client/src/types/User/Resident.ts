import type Residence from "../Residence";
import type RegisteredUser from "./RegisteredUser";

export default interface Resident extends RegisteredUser {
  residence: Residence | null;
}
