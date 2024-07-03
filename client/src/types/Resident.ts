import type { StatusType } from "./StatusType";
import type Residence from "./Residence";
import type { identifierType } from "./IdentifierType";

export default interface Resident {
  firstName: string;
  lastName: string;
  identifier: string;
  identifierType: identifierType;
  email: string;
  imageUrl: string;
  status: StatusType;
  role: string;
  residence: Residence;
}
