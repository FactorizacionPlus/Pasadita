import type { identifierType } from "./IdentifierType";
import type { StatusType } from "./StatusType";

export default interface Invited {
  firstName: string;
  lastName: string;
  identifier: string;
  identifierType: identifierType;
  email: string;
  imageUrl: string;
  status: StatusType;
}
