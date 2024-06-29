import type { Identifier } from "typescript";
import type { Status } from "./StatusType";

export default interface Resident {
  firstName: string;
  lastName: string;
  identifier: string;
  identifierType: Identifier;
  email: string;
  imageUrl: string;
  status: Status;
  role: string;
  residence_uuid: string;
}
