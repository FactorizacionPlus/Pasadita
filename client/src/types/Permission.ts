import type Residence from "./Residence";
import type RegisteredUser from "./User/RegisteredUser";
import type Resident from "./User/Resident";

export default interface Permission {
  uuid: string;
  authorized: boolean | undefined;
  creationDate: string;
  endDate: string;
  startDate: string;
  invitedUser: RegisteredUser;
  residence: Residence;
  resident: Resident;
}
