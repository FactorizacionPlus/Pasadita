import type { Identifier } from "typescript";
import type { StatusType } from "./StatusType";

export default interface Resident {
  firstName: string;
  lastName: string;
  identifier: string;
  identifierType: Identifier;
  email: string;
  imageUrl: string;
  status: StatusType;
  role: string;
  residence_uuid: string;
}
